package erebus.entity;

import java.util.Calendar;

import erebus.ErebusMod;
import erebus.client.render.entity.RenderInfo;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityVelvetWorm extends EntityMob implements IRangedAttackMob
{
	//Help for animation
    private RenderInfo renderdata = new RenderInfo();
    
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F);

	public EntityVelvetWorm(World par1World) 
	{
		super(par1World);
       // this.moveSpeed = 0.8F;
	    this.setSize(1.35F, 1.0F/*Never put lower than 1.0F else it would be nervous to hit*/);
	    this.getNavigator().setAvoidsWater(false);
	    this.experienceValue = 15;
        this.fireResistance = 10;
        this.isImmuneToFire = false;
        //this.health = 25;
        renderdata = new RenderInfo();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
       //TODO this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));


        if (par1World != null && !par1World.isRemote)
        {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
	}
	
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(13, new Byte((byte)0));
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
  	protected void applyEntityAttributes() {
  	    super.applyEntityAttributes();
  	    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25);
  	    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.8D);
  	}
	 /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    //Ten percent change to get the first else take the other
    public int skin = rand.nextInt(99);
	public String getTexture()
	{
		if(skin <= 10)
		{
			return "/Erebus/Textures/Mob/Velvet worm2.png";
		}
		else
		{
			return  "/Erebus/Textures/Mob/Velvet worm.png";	
		}
	}

	/**
	 * Returns the amount of damage a mob should deal.
	 */
	public int getAttackStrength(Entity par1Entity)
	{
		switch(worldObj.difficultySetting)
		{
		default:
			return 4;
		case 1:
			return 4;
		case 2:
			return 5;
		case 3: 
			return 6;
		}
	}
	
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        if (super.attackEntityAsMob(par1Entity))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

	protected void dropFewItems(boolean par1, int par2)
	{
		int chanceFiftyFifty = this.rand.nextInt(1) + 1;
		
		this.dropItem(Item.slimeBall.itemID, chanceFiftyFifty + par2);
	}
	
    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving, float par2)
    {
        EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLiving, 1.0F, (float)(14 - this.worldObj.difficultySetting * 4));
        entityarrow.setDamage((double)(par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting * 0.11F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
}
