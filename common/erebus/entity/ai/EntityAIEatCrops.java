package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import erebus.ErebusMod;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;

public class EntityAIEatCrops extends EntityAIBase {
	private final int diffEaten = 0;// 0-peaceful,1-easy,2-med,3-hard
	private final int maxTicks = 240;// approx 30 tick/sec +- processing delays
	private final int maxDToCrops = 8;// this variable has a childish name.
	// he-he.
	protected EntityLiving theEntity;
	protected double entityPosX;
	protected double entityPosY;
	protected double entityPosZ;
	public int PlantX, PlantY, PlantZ = -1;
	private final double moveSpeed;
	private int ticksSpent = 0;
	private int reproCap = 0;

	public EntityAIEatCrops(EntityLiving par1EntityLiving, double d) {
		theEntity = par1EntityLiving;
		moveSpeed = d;
		setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		if (theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
			boolean hasFoundCrops = findClosestCrops(maxDToCrops);
			if (!hasFoundCrops) {
				((EntityGrasshopper) theEntity).setIsEating(false);
				return false;
			}
			return true;
		} else
			return false;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		super.startExecuting();
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		return isBlockEdible(PlantX, PlantY, PlantZ) || !theEntity.getNavigator().noPath();
	}

	@Override
	public void updateTask() {
		AxisAlignedBB blockbounds = getBlockAABB(PlantX, PlantY, PlantZ);
		theEntity.getLookHelper().setLookPosition(PlantX + 0.5D, PlantY, PlantZ + 0.5D, 50.0F, 8.0F);
		theEntity.getNavigator().tryMoveToXYZ(PlantX + 0.5D, PlantY, PlantZ + 0.5D, moveSpeed);
		if (theEntity.getNavigator().noPath() && !theEntity.isCollidedHorizontally)
			theEntity.getMoveHelper().setMoveTo(PlantX + 0.5D, PlantY, PlantZ + 0.5D, moveSpeed);
		ticksSpent++;
		if (theEntity.boundingBox.maxY >= blockbounds.minY && theEntity.boundingBox.minY <= blockbounds.maxY && theEntity.boundingBox.maxX >= blockbounds.minX && theEntity.boundingBox.minX <= blockbounds.maxX && theEntity.boundingBox.maxZ >= blockbounds.minZ &&
		theEntity.boundingBox.minZ <= blockbounds.maxZ && ticksSpent < maxTicks) {
			((EntityGrasshopper) theEntity).setIsEating(true);
			((EntityGrasshopper) theEntity).munchBlock();
		} else
			((EntityGrasshopper) theEntity).setIsEating(false);
		if (ticksSpent >= maxTicks && theEntity.worldObj.difficultySetting >= diffEaten && theEntity.boundingBox.maxY >= blockbounds.minY && theEntity.boundingBox.minY <= blockbounds.maxY) {
			theEntity.worldObj.destroyBlock(PlantX, PlantY, PlantZ, false);
			((EntityGrasshopper) theEntity).setMoveTasks(true);
			ticksSpent = 0;
			if (reproCap < 3)
				reproCap++;
			if (reproCap == 3) {
				theEntity.setDead();
				EntityLocust entityLocust = new EntityLocust(theEntity.worldObj);
				entityLocust.setPosition(PlantX, PlantY + 1, PlantZ);
				theEntity.worldObj.spawnEntityInWorld(entityLocust);
			}
		}
		super.updateTask();
	}

	private boolean findClosestCrops(int maxDistance) {// returns whether or not
		// Wood was found
		// (he-he! he said wood)
		for (int currentCheckDistance = 1; currentCheckDistance < maxDistance; currentCheckDistance++)
			for (int x = -currentCheckDistance; x <= currentCheckDistance; x++)
				for (int y = -currentCheckDistance; y <= currentCheckDistance; y++)
					for (int z = -currentCheckDistance; z <= currentCheckDistance; z++)
						if (isBlockEdible((int) theEntity.posX + x, (int) theEntity.posY + y, (int) theEntity.posZ + z)) {
							PlantX = (int) theEntity.posX + x;
							PlantY = (int) theEntity.posY + y;
							PlantZ = (int) theEntity.posZ + z;
							return true;
						}
		return false;
	}

	private boolean isBlockEdible(int x, int y, int z) {
		int blockID = theEntity.worldObj.getBlockId(x, y, z);
		if (blockID == 0)
			return false;

		Block block = Block.blocksList[blockID];
		if (block.blockHardness == -1)
			return false;

		if (ErebusMod.grasshopperEating == 2)
			return true;
		else if (block instanceof BlockCrops)
			return false;
		else if (ErebusMod.grasshopperEating == 0 && block.hasTileEntity(theEntity.worldObj.getBlockMetadata(x, y, z)))
			return false;

		return true;
	}

	protected AxisAlignedBB getBlockAABB(int par1, int par2, int par3) {
		return AxisAlignedBB.getAABBPool().getAABB((PlantX), PlantY, (PlantZ), (float) (PlantX + 1.0D), PlantY + 1.0D, (float) (PlantZ + 1.0D));
	}
}
