package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import erebus.ErebusMod;
import erebus.entity.ai.EntityAIEatWoodenItem;
import erebus.core.helper.ClientHelper;

/**
 * @author ProPercivalalb
 */
public class EntityBeetleLarva extends EntityUndergroundAnimal {
	
	private EntityAIWander aiWander = new EntityAIWander(this, 0.48D);
	private EntityAIWatchClosest aiWatchClosest = new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F);
	public EntityAIEatWoodenItem aiEatWoodItem = new EntityAIEatWoodenItem(this, 0.48D);
	
	public boolean isEating;
	private double moveSpeed;
	public boolean isSquashed;
	
	public EntityBeetleLarva(World par1World)
    {
        super(par1World);
        this.setSize(0.9F, 0.5F);
        this.moveSpeed = 0.35D;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, aiEatWoodItem);
        this.tasks.addTask(2, new EntityAITempt(this, 0.48D, Item.stick.itemID, false));
        this.tasks.addTask(3, aiWander);
        this.tasks.addTask(4, aiWatchClosest);
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIPanic(this, 0.48D));
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
	protected void func_110147_ax() {
	    super.func_110147_ax();
	    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0F); //Max Health
	    this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.moveSpeed); //Movespeed
	}
	
    @Override
	public boolean canDespawn() {
    	return false;
	}
    
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
    	return EnumCreatureAttribute.ARTHROPOD;
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
    
    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    { 
    	super.onCollideWithPlayer(par1EntityPlayer);
             byte var2 = 0;
             if(!this.worldObj.isRemote && par1EntityPlayer.boundingBox.maxY >= this.boundingBox.minY && par1EntityPlayer.boundingBox.minY <= this.boundingBox.maxY
          		&& par1EntityPlayer.boundingBox.maxX >= this.boundingBox.minX && par1EntityPlayer.boundingBox.minX <= this.boundingBox.maxX
            	&& par1EntityPlayer.boundingBox.maxZ >= this.boundingBox.minZ && par1EntityPlayer.boundingBox.minZ <= this.boundingBox.maxZ
            	&& !par1EntityPlayer.onGround
            	)
             {
            	 if (this.worldObj.difficultySetting > 1)    
             {
                 if (this.worldObj.difficultySetting == 2)
                 {
                     var2 = 7;
                 }
                 else if (this.worldObj.difficultySetting == 3)
                 {
                     var2 = 15;
                 }
             }

             if (var2 > 0)
             {
            	 ((EntityPlayer)par1EntityPlayer).addPotionEffect(new PotionEffect(Potion.confusion.id, var2 * 20, 0));
              }
             this.setisSquashed(true);
        	 this.setDead();
        	 this.onDeathUpdate();
             }
    }
 
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
    	String actionSound = "erebus:beetlelarvasound";
    	if(this.isEating)
    	{
    		actionSound =  "erebus:beetlelarvamunch";
    }
    	return actionSound;
    }
    
    @Override
    protected String getHurtSound() {
    	return "erebus:beetlelarvahurt";
    }

    @Override
    protected String getDeathSound() {
    	return "erebus:squish";
    }
    
    protected String getJumpedOnSound() {
    	return "erebus:beetlelarvasplat";
    }
    
    protected static String getHasMunched() {
    	return "erebus:beetlelarvamunch";
    }

    @Override
    protected float getSoundVolume() {
        return 0.5F;
    }

   @Override
    public void onUpdate() {
        super.onUpdate();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.moveSpeed); //Movespeed
    }
    
    @Override
    public void onDeathUpdate() {
        
    	super.onDeathUpdate();
        if (!this.worldObj.isRemote && this.isDead && !this.isSquashed) {
        	dropFewItems(false, 0);
        }
        if (this.isSquashed && FMLCommonHandler.instance().getSide().isClient()) {
        	double d0 = this.rand.nextGaussian() * 0.02D;
        	double d1 = this.rand.nextGaussian() * 0.02D;
        	double d2 = this.rand.nextGaussian() * 0.02D;
        	for(int countparticles = 0; countparticles <= 200; ++countparticles)
            {
        		//ClientHelper.getMinecraft().effectRenderer.addEffect(new net.minecraft.client.particle.EntityBreakingFX(this.worldObj, this.posX + (rand.nextDouble() - 0.5D) * (double)this.width, this.posY + rand.nextDouble() * (double)this.height - (double)this.yOffset, this.posZ + (rand.nextDouble() - 0.5D) * (double)this.width, Item.slimeBall));
            }
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, getJumpedOnSound(), 1.0F, 0.5F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, getDeathSound(), 1.0F, 0.7F);	
        }
    }
    
    @Override
	protected void dropFewItems(boolean par1, int par2) {
        if (this.isBurning()) {
            this.dropItem(ErebusMod.beetleLarvaCooked.itemID, 1);
        }
        else {
            this.dropItem(ErebusMod.beetleLarvaRaw.itemID, 1);
        }
    }
    
	@Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        if (!this.worldObj.isRemote && itemstack != null && itemstack.itemID == Item.wheat.itemID) {
        	System.out.println("Should do something here");
            return true;
        }
        
        return super.interact(par1EntityPlayer);
    }
    
    public void setMoveTasks(boolean par1) {
        if(par1==false)	 {
        	this.tasks.removeTask(this.aiWander);
        	this.tasks.removeTask(this.aiWatchClosest);
        }
        
        if(par1==true) {
        	this.tasks.addTask(2, this.aiWander);
        	this.tasks.addTask(4, aiWatchClosest);
        }
    }
    
    public void setIsEating(boolean par1){
    	this.isEating = par1;
    }
    
    public void munchBlock() { 
    	if (this.isEating && FMLCommonHandler.instance().getSide().isClient() && this.worldObj.getWorldTime() % 20 == 0) {
        	double woodX = this.aiEatWoodItem.WoodX;
        	double woodY = this.aiEatWoodItem.WoodY;
        	double woodZ = this.aiEatWoodItem.WoodZ;
            for(int countparticles = 0; countparticles <= 50; ++countparticles)
            {
        		double d0 = this.rand.nextGaussian() * 0.5D;
            	double d1 = this.rand.nextGaussian() * 0.01D;
            	double d2 = this.rand.nextGaussian() * 0.5D;
        		//ClientHelper.getMinecraft().effectRenderer.addEffect(new net.minecraft.client.particle.EntityDiggingFX(this.worldObj, woodX + 0.5D + (rand.nextDouble() - 0.5D) * (double)this.width, woodY + 0.2D + rand.nextDouble() * (double)this.height - (double)this.yOffset, woodZ + 0.5D + (rand.nextDouble() - 0.5D) * (double)this.width, d0 ,d1, d2, Block.blocksList[5], 0));
            }
    	}
    }
    
    public void setisSquashed(boolean par1) {
    	this.isSquashed = par1;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox() {
    	return this.boundingBox;
    }
}

