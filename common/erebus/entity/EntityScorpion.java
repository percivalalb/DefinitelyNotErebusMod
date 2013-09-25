package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;

public class EntityScorpion extends EntityMob {
	protected EntityLiving theEntity;
	public boolean isCaptured;
	public float wingFloat;
	AnimationMathHelper mathWings = new AnimationMathHelper();

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
		if (!worldObj.isRemote && riddenByEntity == null)
			setIsInJaws(false);
		if (!worldObj.isRemote && riddenByEntity != null)
			updateRiderPosition();
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
	 * 
	 * @Override protected String getDeathSound() { return "erebus:squish"; }
	 */
	protected void getStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
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
		return (isCollidedHorizontally);
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
		byte var2 = 1;
		if (!worldObj.isRemote && par1EntityPlayer.boundingBox.maxY >= boundingBox.minY && par1EntityPlayer.boundingBox.minY <= boundingBox.maxY && !isCaptured)
			if (worldObj.difficultySetting > 1)
				if (worldObj.difficultySetting == 2)
					var2 = 7;
				else if (worldObj.difficultySetting == 3)
					var2 = 15;
		if (var2 > 0 && rand.nextInt(200) == 0)
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 5, 0));
		wingFloat = mathWings.swing(1.0F, 0.2F);
		setIsInJaws(true);
		if (!worldObj.isRemote && riddenByEntity == null)
			par1EntityPlayer.mountEntity(this);
	}

	@Override
	public void updateRiderPosition() {
		double a = Math.toRadians(rotationYaw);
		double offSetX = -Math.sin(a) * 0.75D;
		double offSetZ = Math.cos(a) * 0.75D;
		if (riddenByEntity != null)
			riddenByEntity.setPosition(posX + offSetX, posY + 0.75D + riddenByEntity.getYOffset(), posZ + offSetZ);
	}

	public void setIsInJaws(boolean par1) {
		isCaptured = par1;
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