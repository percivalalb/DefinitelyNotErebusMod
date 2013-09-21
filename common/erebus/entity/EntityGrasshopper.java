package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import erebus.ModItems;
import erebus.entity.ai.EntityAIEatCrops;

public class EntityGrasshopper extends EntityMob
{
	protected EntityLiving theEntity;
	private ChunkCoordinates currentJumpTarget;
	private EntityAIWander aiWander = new EntityAIWander(this, 0.6F);
	public boolean isEating;
	public boolean canJump=true;
	
	public EntityGrasshopper(World par1World)
	{
		super(par1World);
		this.stepHeight = 1.0F;
		this.jumpMovementFactor=0.1F;
		this.setSize(1.3F, 0.5F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, aiWander);
		this.tasks.addTask(3, new EntityAIEatCrops(this, 0.6D));
		this.tasks.addTask(4, new EntityAIPanic(this, 0.8D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		
	}
	
@Override
protected void entityInit()
{
	super.entityInit();
}

@Override
protected void applyEntityAttributes() {
	super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6D); //Movespeed
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); //Max Health
}

@Override	
protected boolean isAIEnabled()
{
	return true;
}

@Override		
public EnumCreatureAttribute getCreatureAttribute()
{
	return EnumCreatureAttribute.ARTHROPOD;
}

@Override
protected String getLivingSound()
{
	return "erebus:grasshoppersound";
}

@Override
protected String getHurtSound()
{
	return "erebus:grasshopperhurt";
}

@Override
protected String getDeathSound()
{
	return "erebus:squish";
}

protected void getStepSound(int par1, int par2, int par3, int par4)
{
	this.worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
}

@Override
protected void dropFewItems(boolean par1, int par2) {
    if (this.isBurning()) {
    	this.entityDropItem(new ItemStack(ModItems.erebusFood, 1, 3), 0.0F);
    }
    else {
    	this.entityDropItem(new ItemStack(ModItems.erebusFood, 1, 2), 0.0F);
    }
}

@Override
public boolean getCanSpawnHere()
{
	 float f1 = this.getBrightness(1.0F);

     if (f1 > 0.5F)
     {
    return true;
    }
    return super.getCanSpawnHere();
}

public boolean randJump()
{
	return (this.rand.nextInt(50)==0);
}

@Override
protected void jump()
{
    this.motionY = 0.61999998688697815D;
}

public void setisEating(boolean par1)
{
	this.isEating = par1;
}

public void setMoveTasks(boolean par1)
{
    if(par1==false)	
    {
    this.tasks.removeTask(this.aiWander);
    }
    
    if(par1==true)
    {
     this.tasks.addTask(2, this.aiWander);	   
    }
}

public void setCanJump(boolean par1)
{
	this.canJump = par1; 
}

@Override
protected void fall(float par1)
{
}

protected void jumpMovevement()
{ 
    if (this.currentJumpTarget != null && (!this.worldObj.isAirBlock(this.currentJumpTarget.posX, this.currentJumpTarget.posY, this.currentJumpTarget.posZ) || this.currentJumpTarget.posY < 1))
    {
    this.currentJumpTarget = null;
    }
    if (this.currentJumpTarget == null || this.rand.nextInt(30) == 0 || this.currentJumpTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
    {
    this.currentJumpTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
    }
    double d0 = (double)this.currentJumpTarget.posX + 0.5D - this.posX;
    double d2 = (double)this.currentJumpTarget.posZ + 0.5D - this.posZ;
    this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
    this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
    float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
    float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
    this.moveForward = 0.2F;
    this.rotationYaw += f1;
    if (this.rand.nextInt(100) == 0 && this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ)))
    {
   	 this.motionY = 0;
   	 setPositionAndUpdate(this.posX, this.posY, this.posZ);
    }
}

@Override
public void onLivingUpdate()
{
	if(!this.worldObj.isRemote && this.onGround && this.randJump() && !this.isEating && this.canJump)
 	{ 
	 jump();
 	}
	if (!this.worldObj.isRemote && this.motionY<0 && !this.onGround && !this.isEating)
 	{
	 jumpMovevement();
 	}
	super.onLivingUpdate();
}

public void munchBlock() { 
	if (this.isEating && FMLCommonHandler.instance().getSide().isClient() && this.worldObj.getWorldTime() % 5 == 0)
	{
    	double woodX = EntityAIEatCrops.PlantX;
    	double woodY = EntityAIEatCrops.PlantY;
    	double woodZ = EntityAIEatCrops.PlantZ;;
        for(int countparticles = 0; countparticles <= 50; ++countparticles)
        {
    		double d0 = this.rand.nextGaussian() * 0.5D;
        	double d1 = this.rand.nextGaussian() * 0.01D;
        	double d2 = this.rand.nextGaussian() * 0.5D;
        	Minecraft.getMinecraft().effectRenderer.addEffect (new EntityDiggingFX(Minecraft.getMinecraft().theWorld, woodX + 0.5D + (rand.nextDouble() - 0.5D) * (double)this.width, woodY + 0.2D + rand.nextDouble() * (double)this.height - (double)this.yOffset, woodZ + 0.5D + (rand.nextDouble() - 0.5D) * (double)this.width, d0 ,d1, d2, Block.blocksList[31], 0));
        }
	}
}

public void onUpdate()
{
    super.onUpdate();
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6D); //Movespeed
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(15.0D); //Max Health
}
}
