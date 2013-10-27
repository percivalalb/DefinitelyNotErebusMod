package erebus.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class IEntityArrowAttack extends EntityAIBase {
	/** The entity the AI instance has been applied to */
	private final EntityLiving entityHost;

	/**
	 * The entity (as a RangedAttackMob) the AI instance has been applied to.
	 */
	private final IRangedAttackMob rangedAttackEntityHost;
	private EntityLivingBase attackTarget;

	/**
	 * A decrementing tick that spawns a ranged attack once this value reaches
	 * 0. It is then set back to the maxRangedAttackTime.
	 */
	private int rangedAttackTime;
	private int field_96561_g;

	/**
	 * The maximum time the AI has to wait before peforming another ranged
	 * attack.
	 */
	private int maxRangedAttackTime;
	private float field_96562_i;
	private float field_82642_h;

	public IEntityArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, float par5) {
		this(par1IRangedAttackMob, par2, par4, par4, par5);
	}

	public IEntityArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, int par5, float par6) {
		rangedAttackTime = -1;

		if (!(par1IRangedAttackMob instanceof EntityLivingBase))
			throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
		else {
			rangedAttackEntityHost = par1IRangedAttackMob;
			entityHost = (EntityLiving) par1IRangedAttackMob;
			field_96561_g = par4;
			maxRangedAttackTime = par5;
			field_96562_i = par6;
			field_82642_h = par6 * par6;
			setMutexBits(3);
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = entityHost.getAttackTarget();

		if (entitylivingbase == null)
			return false;
		else {
			attackTarget = entitylivingbase;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		return shouldExecute() || !entityHost.getNavigator().noPath();
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		attackTarget = null;
		rangedAttackTime = -1;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {
		double d0 = entityHost.getDistanceSq(attackTarget.posX, attackTarget.boundingBox.minY, attackTarget.posZ);

		float f;

		if (--rangedAttackTime == 0) {
			if (d0 > field_82642_h)
				return;

			f = MathHelper.sqrt_double(d0) / field_96562_i;
			float f1 = f;

			if (f < 0.1F)
				f1 = 0.1F;

			if (f1 > 1.0F)
				f1 = 1.0F;

			rangedAttackEntityHost.attackEntityWithRangedAttack(attackTarget, f1);
			rangedAttackTime = MathHelper.floor_float(f * (maxRangedAttackTime - field_96561_g) + field_96561_g);
		} else if (rangedAttackTime < 0) {
			f = MathHelper.sqrt_double(d0) / field_96562_i;
			rangedAttackTime = MathHelper.floor_float(f * (maxRangedAttackTime - field_96561_g) + field_96561_g);
		}
	}
}
