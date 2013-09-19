package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import erebus.ErebusMod;
import erebus.entity.EntityBeetleLarva;

public class EntityAIEatWoodenItem extends EntityAIBase {
	private int diffEaten = 0;// 0-peaceful,1-easy,2-med,3-hard
	private int maxTicks = 240;// approx 30 tick/sec +- processing delays
	private int maxDToWood = 8;// this variable has a childish name. he-he.
	protected EntityLiving theEntity;
	protected double entityPosX;
	protected double entityPosY;
	protected double entityPosZ;
	public int WoodX, WoodY, WoodZ = -1;
	private double moveSpeed;
	private int ticksSpent = 0;
	private int reproCap = 0;

	public EntityAIEatWoodenItem(EntityLiving par1EntityLiving, double d) {
		this.theEntity = par1EntityLiving;
		this.moveSpeed = d;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		if (this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
			boolean hasFoundWood = findClosestWood(maxDToWood);
			if (!hasFoundWood) {
				((EntityBeetleLarva) this.theEntity).setIsEating(false);
				return false;
			}
			return true;
		} else {
			return false;
		}
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
		/*int x = this.theEntity.worldObj.getBlockId(WoodX, WoodY, WoodZ);
		return (x != Block.fence.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.fenceGate.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.planks.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.doorWood.blockID) ? false
		: !this.theEntity.getNavigator().noPath() || (x != Block.workbench.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.trapdoor.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.stairsWoodOak.blockID) ? false : !this.theEntity.getNavigator()
		.noPath() || (x != Block.stairsWoodSpruce.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.stairsWoodBirch.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.stairsWoodJungle.blockID) ? false : !this.theEntity.getNavigator().noPath() ||
		(x != Block.woodDoubleSlab.blockID) ? false : !this.theEntity.getNavigator().noPath() || (x != Block.woodSingleSlab.blockID) ? false : !this.theEntity.getNavigator().noPath();*/
		return isBlockEdible(WoodX,WoodY,WoodZ)||!theEntity.getNavigator().noPath();
	}

	@Override
	public void updateTask() {
		AxisAlignedBB blockbounds = this.getBlockAABB(WoodX, WoodY, WoodZ);
		this.theEntity.getLookHelper().setLookPosition(this.WoodX + 0.5D, this.WoodY, this.WoodZ + 0.5D, 50.0F, 8.0F);
		this.theEntity.getNavigator().tryMoveToXYZ(WoodX + 0.5D, WoodY, WoodZ + 0.5D, this.moveSpeed);
		if (this.theEntity.getNavigator().noPath() && !this.theEntity.isCollidedHorizontally) {
			this.theEntity.getMoveHelper().setMoveTo(WoodX + 0.5D, WoodY, WoodZ + 0.5D, this.moveSpeed);
		}
		this.ticksSpent++;
		if (this.theEntity.boundingBox.maxY >= blockbounds.minY && this.theEntity.boundingBox.minY <= blockbounds.maxY && this.theEntity.boundingBox.maxX >= blockbounds.minX && this.theEntity.boundingBox.minX <= blockbounds.maxX && this.theEntity.boundingBox.maxZ >= blockbounds.minZ &&
		this.theEntity.boundingBox.minZ <= blockbounds.maxZ && this.ticksSpent < maxTicks) {
			System.out.println("Beetle Larva Eating");
			((EntityBeetleLarva) this.theEntity).setIsEating(true);
			((EntityBeetleLarva) this.theEntity).munchBlock();
		} else {
			((EntityBeetleLarva) this.theEntity).setIsEating(false);
		}
		if (this.ticksSpent >= maxTicks && this.theEntity.worldObj.difficultySetting >= diffEaten && this.theEntity.boundingBox.maxY >= blockbounds.minY && this.theEntity.boundingBox.minY <= blockbounds.maxY) {
			this.theEntity.worldObj.destroyBlock(this.WoodX, this.WoodY, this.WoodZ, false);
			((EntityBeetleLarva) this.theEntity).setMoveTasks(true);
			this.ticksSpent = 0;
			if (this.reproCap < 3) {
				reproCap++;
			}
			if (this.reproCap == 3) {
				// this bit of code is just here for future improvement
				// (spawning a full grown beetle)
				// this.theEntity.setDead();
				// EntityBeetleLarva entityBeetleLarva = new
				// EntityBeetleLarva(theEntity.worldObj);
				// entityBeetleLarva.setPosition(WoodX, WoodY+1, WoodZ);
				// this.theEntity.worldObj.spawnEntityInWorld(entityBeetleLarva);
			}
		}
		super.updateTask();
	}

	private boolean findClosestWood(int maxDistance) {// returns whether or not Wood was found (he-he! he said wood)
		for (int currentCheckDistance = 1; currentCheckDistance < maxDistance; currentCheckDistance++) {
			for (int x = -currentCheckDistance; x <= currentCheckDistance; x++) {
				for (int y = -currentCheckDistance; y <= currentCheckDistance; y++) {
					for (int z = -currentCheckDistance; z <= currentCheckDistance; z++) {
						if (isBlockEdible((int)theEntity.posX+x,(int)theEntity.posY+y,(int)theEntity.posZ+z)){
							WoodX = (int) this.theEntity.posX + x;
							WoodY = (int) this.theEntity.posY + y;
							WoodZ = (int) this.theEntity.posZ + z;
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean isBlockEdible(int x, int y, int z){
		int blockID=theEntity.worldObj.getBlockId(x,y,z);
		if (blockID==0)return false;
		
		Block block=Block.blocksList[blockID];
		if (block.blockHardness==-1)return false;
		
		if (ErebusMod.beetleLarvaEating==2)return true;
		else if (block.blockMaterial!=Material.wood||block instanceof BlockLog)return false;
		else if (ErebusMod.beetleLarvaEating==0&&block.hasTileEntity(theEntity.worldObj.getBlockMetadata(x,y,z)))return false;
		
		return true;
	}

	protected AxisAlignedBB getBlockAABB(int par1, int par2, int par3) {
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) WoodX), (double) WoodY, (double) ((float) WoodZ), (double) (float) (WoodX + 1.0D), (double) WoodY + 1.0D, (double) (float) (WoodZ + 1.0D));
	}
}
