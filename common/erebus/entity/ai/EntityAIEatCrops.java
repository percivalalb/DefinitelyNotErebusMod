package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import erebus.ModBlocks;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;

public class EntityAIEatCrops extends EntityAIBase {
	private final int diffEaten = 0;// 0-peaceful,1-easy,2-med,3-hard
	private final int maxTicks = 240;// approx 30 tick/sec +- processing delays
	private final int maxDistance = 8;// higher numbers increase load
	protected EntityLiving theEntity;
	protected double entityPosX;
	protected double entityPosY;
	protected double entityPosZ;
	public static int PlantX;
	public static int PlantY;
	public static int PlantZ;
	private final double moveSpeed;
	private int ticksSpent = 0;
	private int reproCap = 0;

	public EntityAIEatCrops(EntityLiving par1EntityLiving, double d) {
		theEntity = par1EntityLiving;
		moveSpeed = d;
		setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		if (theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
			boolean hasFoundPlant = findClosestPlant(maxDistance);
			if (!hasFoundPlant) {
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
		return (theEntity.worldObj.getBlockId(PlantX, PlantY, PlantZ) != Block.tallGrass.blockID) ? false : !theEntity.getNavigator().noPath() || (theEntity.worldObj.getBlockId(PlantX, PlantY, PlantZ) != Block.crops.blockID) ? false : !theEntity.getNavigator().noPath();
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {
		AxisAlignedBB blockbounds = getBlockAABB(PlantX, PlantY, PlantZ);
		theEntity.getLookHelper().setLookPosition(PlantX, PlantY, PlantZ, 50.0F, 8.0F);// last
		// two
		// params
		// are
		// how
		// quickly
		// look
		// can
		// snap
		if (theEntity.getNavigator().noPath())
			if (!((EntityGrasshopper) theEntity).isEating)
				theEntity.getMoveHelper().setMoveTo(PlantX + 0.5D, PlantY, PlantZ + 0.5D, moveSpeed);
		ticksSpent++;
		if (theEntity.boundingBox.maxY >= blockbounds.minY && theEntity.boundingBox.minY <= blockbounds.maxY && theEntity.boundingBox.maxX >= blockbounds.minX && theEntity.boundingBox.minX <= blockbounds.maxX && theEntity.boundingBox.maxZ >= blockbounds.minZ &&
		theEntity.boundingBox.minZ <= blockbounds.maxZ && ticksSpent < maxTicks) {
			((EntityGrasshopper) theEntity).setCanJump(false);
			((EntityGrasshopper) theEntity).setMoveTasks(false);
			// System.out.println("Eating");
			((EntityGrasshopper) theEntity).setIsEating(true);
			((EntityGrasshopper) theEntity).munchBlock();
			// ((EntityGrasshopper) this.theEntity).setPositionAndUpdate(PlantX,
			// PlantY, PlantZ);
		} else
			((EntityGrasshopper) theEntity).setIsEating(false);
		if (ticksSpent >= maxTicks && theEntity.worldObj.difficultySetting >= diffEaten && theEntity.boundingBox.maxY >= blockbounds.minY && theEntity.boundingBox.minY <= blockbounds.maxY) {
			theEntity.worldObj.destroyBlock(PlantX, PlantY, PlantZ, false);
			((EntityGrasshopper) theEntity).setMoveTasks(true);
			((EntityGrasshopper) theEntity).setCanJump(true);
			if (reproCap == 4 || reproCap == 8) {
				EntityGrasshopper entityGrasshopper = new EntityGrasshopper(theEntity.worldObj);
				entityGrasshopper.setPosition(PlantX, PlantY + 1, PlantZ);
				theEntity.worldObj.spawnEntityInWorld(entityGrasshopper);
			}
			ticksSpent = 0;
			if (reproCap < 12)
				reproCap++;
			if (reproCap == 12) {
				theEntity.setDead();
				EntityLocust entityLocust = new EntityLocust(theEntity.worldObj);
				entityLocust.setPosition(PlantX, PlantY + 1, PlantZ);
				theEntity.worldObj.spawnEntityInWorld(entityLocust);
				theEntity.worldObj.playSoundAtEntity(entityLocust, "erebus:locustspawn", 1.0F, 1.0F);
			}
		}
		super.updateTask();
	}

	private boolean findClosestPlant(int maxDistance) {// returns whether or not
		// Plant was found
		for (int currentCheckDistance = 1; currentCheckDistance < maxDistance; currentCheckDistance++)
			for (int x = -currentCheckDistance; x < currentCheckDistance; x++)
				for (int y = -currentCheckDistance; y < currentCheckDistance; y++)
					for (int z = -currentCheckDistance; z < currentCheckDistance; z++)
						if (isPlant(theEntity.worldObj.getBlockId((int) theEntity.posX + x, (int) theEntity.posY + y, (int) theEntity.posZ + z))) {
							PlantX = (int) theEntity.posX + x;
							PlantY = (int) theEntity.posY + y;
							PlantZ = (int) theEntity.posZ + z;
							return true;
						}
		return false;
	}

	private boolean isPlant(int blockID) {
		return blockID == Block.tallGrass.blockID || blockID == ModBlocks.erebusGrass.blockID || blockID == Block.crops.blockID;
	}

	protected AxisAlignedBB getBlockAABB(int par1, int par2, int par3) {
		return AxisAlignedBB.getAABBPool().getAABB((PlantX), (PlantY), (PlantZ), PlantX + 1.0D, PlantY + 1.0D, PlantZ + 1.0D);
	}
}
