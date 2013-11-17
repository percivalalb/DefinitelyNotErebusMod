package erebus.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class IEntityAIAttackOnCollide extends EntityAIBase
{
	World worldObj;
	EntityCreature attacker;

	/**
	 * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
	 */
	int attackTick;

	/** The speed with which the mob will approach the target */
	double speedTowardsTarget;

	/**
	 * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
	 */
	boolean longMemory;

	/** The PathEntity of our entity. */
	PathEntity entityPathEntity;
	Class classTarget;
	private int field_75445_i;

	private int failedPathFindingPenalty;

	public IEntityAIAttackOnCollide(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par5)
	{
		this(par1EntityCreature, par3, par5);
		classTarget = par2Class;
	}

	public IEntityAIAttackOnCollide(EntityCreature par1EntityCreature, double par2, boolean par4)
	{
		attacker = par1EntityCreature;
		worldObj = par1EntityCreature.worldObj;
		speedTowardsTarget = par2;
		longMemory = par4;
		setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();

		if (entitylivingbase == null)
			return false;
		else if (!entitylivingbase.isEntityAlive())
			return false;
		else if (classTarget != null && !classTarget.isAssignableFrom(entitylivingbase.getClass()))
			return false;
		else if (-- field_75445_i <= 0)
		{
			entityPathEntity = attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
			field_75445_i = 4 + attacker.getRNG().nextInt(7);
			return entityPathEntity != null;
		} else
			return true;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		return entitylivingbase == null ? false : !entitylivingbase.isEntityAlive() ? false : !longMemory ? !attacker.getNavigator().noPath() : attacker.func_110176_b(MathHelper.floor_double(entitylivingbase.posX), MathHelper.floor_double(entitylivingbase.posY), MathHelper.floor_double(entitylivingbase.posZ));
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
		field_75445_i = 0;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		attacker.getNavigator().clearPathEntity();
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		EntityLivingBase entitylivingbase = attacker.getAttackTarget();
		attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);

		if ((longMemory || attacker.getEntitySenses().canSee(entitylivingbase)) && --field_75445_i <= 0)
		{
			field_75445_i = failedPathFindingPenalty + 4 + attacker.getRNG().nextInt(7);
			attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget);
			if (attacker.getNavigator().getPath() != null)
			{
				PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
				if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1)
					failedPathFindingPenalty = 0;
				else
					failedPathFindingPenalty += 10;
			} else
				failedPathFindingPenalty += 10;
		}

		attackTick = Math.max(attackTick - 1, 0);
		double d0 = attacker.width * attacker.width + entitylivingbase.width;

		if (attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ) <= d0)
			if (attackTick <= 0)
			{
				attackTick = 20;

				if (attacker.getHeldItem() != null)
					attacker.swingItem();

				attacker.attackEntityAsMob(entitylivingbase);
			}
	}
}
