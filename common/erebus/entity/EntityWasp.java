package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;

public class EntityWasp extends EntityMob {
	private float heightOffset = 0.5F;
	public float wingFloat;
	AnimationMathHelper mathWings = new AnimationMathHelper();

	public EntityWasp(World par1World) {
		super(par1World);
		this.setSize(2.0F, 2.0F);
		// no AI needed - just seems to work o.k. being a 'monster'
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected String getLivingSound() {
		return "erebus:WaspSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:WaspHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4) {
		this.worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		
		this.entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, 10), 0.0F);
		switch (this.rand.nextInt(2)) {
		case 0:
			this.entityDropItem(new ItemStack(ModItems.erebusMaterials,
					this.rand.nextInt(3) + 1, 0), 0.0F);
		case 1:
			this.entityDropItem(
					new ItemStack(Item.dyePowder, this.rand.nextInt(2) + 1, 11),
					0.0F);
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(ModItems.waspSwordID, 1);
	}

	public boolean isFlying() {
		return !this.onGround;
	}

	@Override
	public boolean isOnLadder() {
		return this.isCollidedHorizontally;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(
				0.75D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(
				25.0D); // Max
						// Health
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(
				6.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(
				16.0D); // followRange

		if (!this.isFlying()) {
			this.wingFloat = 0.0F;
		}
		if (this.isFlying()) {
			this.wingFloat = mathWings.swing(4.0F, 0.1F);
		}
	}

	@Override
	public void onLivingUpdate() {
		if (!this.worldObj.isRemote) {
			this.heightOffset = (0.5F + (float) this.rand.nextGaussian() * 5.0F);
			if ((getEntityToAttack() != null)
					&& (getEntityToAttack().posY
							+ getEntityToAttack().getEyeHeight() > this.posY
							+ getEyeHeight() + this.heightOffset)) {
				this.motionY += (0.350000011920929D - this.motionY) * 0.350000011920929D;
			}
		}
		if ((!this.onGround) && (this.motionY < 0.0D)) {
			this.motionY *= 0.5D;
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if ((par1Entity instanceof EntityLivingBase)) {
				byte var2 = 0;

				if (this.worldObj.difficultySetting > 1) {
					if (this.worldObj.difficultySetting == 2) {
						var2 = 7;
					} else if (this.worldObj.difficultySetting == 3) {
						var2 = 15;
					}
				}

				if (var2 > 0) {
					((EntityLivingBase) par1Entity)
							.addPotionEffect(new PotionEffect(Potion.poison.id,
									var2 * 20, 0));
				}
			}

			return true;
		}

		return false;
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		if ((par2 < 2.0F)
				&& (par1Entity.boundingBox.maxY > this.boundingBox.minY)
				&& (par1Entity.boundingBox.minY < this.boundingBox.maxY)) {
			attackEntityAsMob(par1Entity);
		}
	}
}