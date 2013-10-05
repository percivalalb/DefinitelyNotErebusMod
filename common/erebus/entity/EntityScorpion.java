package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;

public class EntityScorpion extends EntityMob {
	private boolean sting;
	private boolean poisoned;
	public static float stingticks;

	public EntityScorpion(World par1World) {
		super(par1World);
		stepHeight = 0.1F;
		isImmuneToFire = true;
		setSize(2.0F, 2.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && !captured())
			setisStinging(false);
		if (!worldObj.isRemote && captured())
			updateRiderPosition();
		if (sting && stingticks < 0.64F) {
			stingticks = stingticks + 0.16F;
			if (stingticks >= 0.64F) {
				setHasBeenStung(true);
				setisStinging(false);
			}
		}
		if (!sting && stingticks > 0F && poisoned) {
			stingticks = stingticks - 0.16F;
			if (stingticks <= 0.0F)
				setHasBeenStung(false);
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/*
	 * just to avoid crashes
	 * 
	 * @Override protected String getLivingSound() { return
	 * "erebus:scorpionsound"; }
	 * 
	 * @Override protected String getHurtSound() { return "erebus:scorpionhurt";
	 * }
	 */

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = rand.nextInt(4) + rand.nextInt(1 + par2);
		int var4;
		for (var4 = 0; var4 < var3; ++var4)
			entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, 0), 0.0F);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean canRiderInteract() {
		return true;
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	public boolean captured() {
		return riddenByEntity != null;
	}

	private void setisStinging(boolean par1) {
		sting = par1;
	}

	private void setHasBeenStung(boolean par1) {
		poisoned = par1;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		super.onCollideWithPlayer(par1EntityPlayer);
		byte var2 = 0;
		if (!worldObj.isRemote && par1EntityPlayer.boundingBox.maxY >= boundingBox.minY && par1EntityPlayer.boundingBox.minY <= boundingBox.maxY && captured())
			if (worldObj.difficultySetting > 1)
				if (worldObj.difficultySetting == 2)
					var2 = 7;
				else if (worldObj.difficultySetting == 3)
					var2 = 15;
		if (var2 > 0 && rand.nextInt(200) == 0) {
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 10, 0));
			setisStinging(true);
		}
		if (!worldObj.isRemote && !captured())
			par1EntityPlayer.mountEntity(this);
	}

	@Override
	public void updateRiderPosition() {
		double a = Math.toRadians(rotationYaw);
		double offSetX = -Math.sin(a) * 0.75D;
		double offSetZ = Math.cos(a) * 0.75D;
		if (captured())
			riddenByEntity.setPosition(posX + offSetX, posY + 0.75D + riddenByEntity.getYOffset(), posZ + offSetZ);
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		super.attackEntity(par1Entity, par2);
		if (par2 < 1.0F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(par1Entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		return super.attackEntityAsMob(par1Entity);
	}
}