package erebus.Entity;

import java.util.Random;
import erebus.mod_Erebus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCentipede extends EntityMob
{

	public int skin = rand.nextInt(2);

	public EntityCentipede(World par1World)
	{
		super(par1World);		
		this.setSize(1.2F, 0.9F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	 protected void func_110147_ax()
	    {
	        super.func_110147_ax();
	        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
	        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
	        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.33000000417232513D);
	        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
	    }
	 
	 public boolean getCanSpawnHere()
	 {
	 	 float f1 = this.getBrightness(1.0F);

	      if (f1 > 0.5F)
	      {
	     return true;
	     }
	     return super.getCanSpawnHere();

	 }
	 
	 public int getAttackStrength(Entity par1Entity)
		{
			switch(worldObj.difficultySetting)
			{
			default:
				return 4;
			case 1:
				return 4;
			case 2:
				return 4;
			case 3: 
				return 5;
			}
		}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}


	@Override
	public int getTotalArmorValue()
	{
		return 8;
	}

	protected String getLivingSound()
	{
		return "Centipede.Sounds.CentipedeSound";
	}

	protected String getHurtSound()
	{
		return "Centipede.Sounds.CentipedeHurt";
	}

	protected String getDeathSound()
	{
		return "Centipede.Sounds.Squish";
	}

	protected String getTunnelingSound()
	{
		return "Centipede.Sounds.TunnelingSound";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4)
	{
		this.worldObj.playSoundAtEntity(this, "Centipede.Sounds.CentipedeWalk", 0.15F, 1.0F);
	}


	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()
	{
		return mod_Erebus.exoskeletonPlate.itemID;
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = 4 + this.rand.nextInt(3);
		int var4;
		for (var4 = 0; var4 < var3; ++var4)
		{
			this.dropItem(mod_Erebus.exoskeletonPlate.itemID, 1);
		}
	}
}
