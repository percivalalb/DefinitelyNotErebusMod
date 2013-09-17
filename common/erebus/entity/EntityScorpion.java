package erebus.entity;

import erebus.ErebusMod;
import erebus.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityScorpion extends EntityMob{
	protected EntityLiving theEntity;
	    
	public EntityScorpion(World par1World)
	{
		
		super(par1World);
		this.stepHeight = 0.1F;
		this.isImmuneToFire = true;
		this.setSize(2.0F, 2.0F);
	}
	
	@Override
	 protected void entityInit()
	    {
	        super.entityInit();
	    }
	 
	 @Override
	 public void onUpdate()
	 {
	     super.onUpdate();
	      getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); //Movespeed
	      getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D); //Max Health
	      getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(6.0D); //atkDmg
	      getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); //followRange
	    }

@Override
public EnumCreatureAttribute getCreatureAttribute()
{
	return EnumCreatureAttribute.ARTHROPOD;
}

@Override
protected String getLivingSound()
{
	return "erebus:scorpionsound";
}

@Override
protected String getHurtSound()
{
	return "erebus:scorpionhurt";
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
protected int getDropItemId()
{	

		return ModItems.erebusMaterialsID;

}

@Override
public boolean isOnLadder()
{
  return (this.isCollidedHorizontally);
}

@Override
protected void attackEntity(Entity par1Entity, float par2)
{
	super.attackEntity(par1Entity, par2);
        if (par2 < 1.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
        	this.attackEntityAsMob(par1Entity);
        }
    }

@Override
public boolean attackEntityAsMob(Entity par1Entity)
{	
	if (super.attackEntityAsMob(par1Entity))	    		
    {
        if (par1Entity instanceof EntityLiving)
        {
            byte var2 = 0;
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
                ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
                }
        }
        return true; 
    }
    else
    {
        return false;
    }
}
}

