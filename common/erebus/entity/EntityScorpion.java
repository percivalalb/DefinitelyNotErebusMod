package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;

public class EntityScorpion extends EntityMob {
	protected EntityLiving theEntity;
	public boolean isCaptured;

	public EntityScorpion(World par1World) {

		super(par1World);
		this.stepHeight = 0.1F;
		this.isImmuneToFire = true;
		this.setSize(2.0F, 2.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D); // Max
																					// Health
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(6.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange

		if (!this.worldObj.isRemote && this.riddenByEntity == null) {
			this.setIsInJaws(false);
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:scorpionsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:scorpionhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4) {
		this.worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId() {

		return ModItems.erebusMaterialsID;

	}

	@Override
	public boolean isOnLadder() {
		return (this.isCollidedHorizontally);
	}

	@Override
	public boolean canRiderInteract() {
		return true;
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		super.onCollideWithPlayer(par1EntityPlayer);
		byte var2 = 0;
		if (!this.worldObj.isRemote && par1EntityPlayer.boundingBox.maxY >= this.boundingBox.minY && par1EntityPlayer.boundingBox.minY <= this.boundingBox.maxY && !this.isCaptured) {
			if (this.worldObj.difficultySetting > 1) {
				if (this.worldObj.difficultySetting == 2) {
					var2 = 7;
				} else if (this.worldObj.difficultySetting == 3) {
					var2 = 15;
				}
			}
			if (var2 > 0) {
				((EntityPlayer) par1EntityPlayer).addPotionEffect(new PotionEffect(Potion.weakness.id, var2 * 5, 0));
			}
			this.setIsInJaws(true);
			par1EntityPlayer.mountEntity(this);
			updateRiderPosition();
		}
	}

	@Override
	public void updateRiderPosition() {
		double a = Math.toRadians(this.rotationYaw);
		double offSetX = -Math.sin(a) * 0.75D;
		double offSetZ = Math.cos(a) * 0.75D;
		if (this.riddenByEntity != null) {
			this.riddenByEntity.setPosition(this.posX + offSetX, this.posY + 0.75D + this.riddenByEntity.getYOffset(), this.posZ + offSetZ);
		}
	}

	public void setIsInJaws(boolean par1) {
		this.isCaptured = par1;
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		super.attackEntity(par1Entity, par2);
		if (par2 < 1.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
			this.attackEntityAsMob(par1Entity);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if (par1Entity instanceof EntityLiving) {
				byte var2 = 0;
				if (this.worldObj.difficultySetting > 1) {
					if (this.worldObj.difficultySetting == 2) {
						var2 = 7;
					} else if (this.worldObj.difficultySetting == 3) {
						var2 = 15;
					}
				}
				if (var2 > 0) {
					((EntityLiving) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
