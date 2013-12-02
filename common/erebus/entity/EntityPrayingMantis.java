package erebus.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import erebus.entity.ai.IEntityAIAttackOnCollide;

public class EntityPrayingMantis extends EntityMob {

	static float transparency;

	public EntityPrayingMantis(World par1World) {
		super(par1World);
		isImmuneToFire = true;
		setSize(2.0F, 2.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new IEntityAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean getCanSpawnHere() {
		float f1 = getBrightness(1.0F);
		if (f1 > 0.5F)
			return true;
		return super.getCanSpawnHere();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && getAttackTarget() != null) {
			double d1 = getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			if (d1 >= 4.0D)
				setTransparency((16.0F - (float) d1) * 0.03125F);
			if (d1 < 4.0D)
				setTransparency((16.0F - (float) d1) * 0.0625F);
		}
		if (!worldObj.isRemote && getAttackTarget() == null)
			setTransparency(1.0F);
	}

	public void setTransparency(float d) {
		transparency = d;
		if (transparency > 0.8F)
			transparency = 1.0F;
	}

	public float getTransparency() {
		return transparency;
	}

}