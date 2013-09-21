package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;

public class EntityAIEatCrops extends EntityAIBase 
{
	private int diffEaten = 1;//0-peaceful,1-easy,2-med,3-hard
	private int maxTicks = 240;//approx 30 tick/sec +- processing delays
	private int maxDistance = 16;//higher numbers increase load
	protected EntityLiving theEntity;
	protected double entityPosX;
	protected double entityPosY;
	protected double entityPosZ;
	public static int PlantX;
	public static int PlantY;
	public static int PlantZ;
	private double moveSpeed;
	private int ticksSpent=0;
	private int reproCap=0;
	
	
public EntityAIEatCrops(EntityLiving par1EntityLiving, double d)
{
	this.theEntity = par1EntityLiving;
	this.moveSpeed = d;
	this.setMutexBits(1);
}

/**
* Returns whether the EntityAIBase should begin execution.
*/
public boolean shouldExecute()
{
	if(this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
	{
		boolean hasFoundPlant = findClosestPlant(maxDistance);
		if(!hasFoundPlant)
		{
			((EntityGrasshopper) this.theEntity).setisEating(false);
			return false;
		}
		return true;
	}
	else
	{
		return false;
	}
}
/**
* Execute a one shot task or start executing a continuous task
*/
public void startExecuting()
{ 
	super.startExecuting();
}
/**
* Returns whether an in-progress EntityAIBase should continue executing
*/
public boolean continueExecuting()
{
	return(this.theEntity.worldObj.getBlockId(PlantX, PlantY, PlantZ) != Block.tallGrass.blockID) ? false : !this.theEntity.getNavigator().noPath()
	   || (this.theEntity.worldObj.getBlockId(PlantX, PlantY, PlantZ) != Block.crops.blockID)? false : !this.theEntity.getNavigator().noPath();
}
	        /**
	         * Updates the task
	         */
public void updateTask()
{
	AxisAlignedBB blockbounds = this.getBlockAABB(PlantX, PlantY, PlantZ);
	this.theEntity.getLookHelper().setLookPosition(this.PlantX, this.PlantY, this.PlantZ, 50.0F, 8.0F);//last two params are how quickly look can snap
	if(this.theEntity.getNavigator().noPath())
	{
		if(!((EntityGrasshopper) this.theEntity).isEating)
		{
	this.theEntity.getMoveHelper().setMoveTo(PlantX+0.5D, PlantY, PlantZ+0.5D, this.moveSpeed);
		}
	}
	this.ticksSpent++;
	if(this.theEntity.boundingBox.maxY >= blockbounds.minY && this.theEntity.boundingBox.minY <= blockbounds.maxY
			&& this.theEntity.boundingBox.maxX >= blockbounds.minX && this.theEntity.boundingBox.minX <= blockbounds.maxX
			&& this.theEntity.boundingBox.maxZ >= blockbounds.minZ && this.theEntity.boundingBox.minZ <= blockbounds.maxZ
			&& this.ticksSpent < maxTicks)
	{
		((EntityGrasshopper) this.theEntity).setCanJump(false);
		((EntityGrasshopper) this.theEntity).setMoveTasks(false);
		//System.out.println("Eating");
		((EntityGrasshopper) this.theEntity).setisEating(true);
		((EntityGrasshopper) this.theEntity).munchBlock();
		//((EntityGrasshopper) this.theEntity).setPositionAndUpdate(PlantX, PlantY, PlantZ);
	}
	else
	{
		((EntityGrasshopper) this.theEntity).setisEating(false);
	}
	if (this.ticksSpent >= maxTicks  && this.theEntity.worldObj.difficultySetting >= diffEaten && this.theEntity.boundingBox.maxY >= blockbounds.minY && this.theEntity.boundingBox.minY <= blockbounds.maxY)
	{
		this.theEntity.worldObj.destroyBlock(this.PlantX, this.PlantY, this.PlantZ, false);
		((EntityGrasshopper) this.theEntity).setMoveTasks(true);
		((EntityGrasshopper) this.theEntity).setCanJump(true);
		if(this.reproCap<2)
		{
			EntityGrasshopper entityGrasshopper = new EntityGrasshopper(theEntity.worldObj);
			entityGrasshopper.setPosition( PlantX, PlantY+1, PlantZ);
			this.theEntity.worldObj.spawnEntityInWorld(entityGrasshopper);
		}
		this.ticksSpent=0;
		if(this.reproCap<3)
		{
			reproCap++;
		}
		if(this.reproCap==3)
		{
			this.theEntity.setDead();
			EntityLocust entityLocust = new erebus.entity.EntityLocust(theEntity.worldObj);
			entityLocust.setPosition( PlantX, PlantY+1, PlantZ);
			this.theEntity.worldObj.spawnEntityInWorld(entityLocust);
			 this.theEntity.worldObj.playSoundAtEntity(entityLocust, "eni:locustspawn", 1.0F, 1.0F);
		}
	}
	super.updateTask();
}

private boolean findClosestPlant(int maxDistance)
	{//returns whether or not Plant was found
	for(int currentCheckDistance=1; currentCheckDistance<maxDistance; currentCheckDistance++)
	{
		for(int x = -currentCheckDistance; x<currentCheckDistance;x++)
		{
			for(int y = -currentCheckDistance; y<currentCheckDistance; y++)
			{
				for(int z = -currentCheckDistance; z<currentCheckDistance; z++)
				{
					if(this.theEntity.worldObj.getBlockId((int)this.theEntity.posX+x, (int)this.theEntity.posY+y, (int)this.theEntity.posZ+z) == Block.tallGrass.blockID
					|| this.theEntity.worldObj.getBlockId((int)this.theEntity.posX+x, (int)this.theEntity.posY+y, (int)this.theEntity.posZ+z) == Block.crops.blockID)
					{
						PlantX = (int)this.theEntity.posX+x;
						PlantY = (int)this.theEntity.posY+y;
						PlantZ = (int)this.theEntity.posZ+z;
						return true;
					}
				}
			}
		}
	}
	return false;
	}
protected AxisAlignedBB getBlockAABB(int par1, int par2, int par3) {
	return AxisAlignedBB.getAABBPool().getAABB((double)((float)PlantX), (double)((float)PlantY), (double)((float)PlantZ), (double)((float)PlantX + 1.0D), (double)((float)PlantY + 1.0D), (double)((float)PlantZ + 1.0D));
}
}

