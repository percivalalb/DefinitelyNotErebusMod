package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;

public class EntityLocust extends EntityMob
{
	private float heightOffset = 0.5F;
	protected EntityLiving theEntity;
	public boolean canJump=true;
	public EntityLocust(World par1World)
	{
		super(par1World);
		this.stepHeight = 1.0F;
		this.jumpMovementFactor=0.05F;
		this.setSize(2F, 1F);
		this.getNavigator().setAvoidsWater(true);
	
	}
	
	@Override
protected void entityInit()
{
	super.entityInit();
}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
	    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); //Movespeed
	    getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(50.0D); //Max Health
	    getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D); //atkDmg
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
	return "erebus:locustsound";
}

@Override
protected String getHurtSound()
{
	return "erebus:locusthurt";
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

    	this.entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, 9), 0.0F);
    }

public boolean randJump()
{
	return (this.rand.nextInt(50)==0);
}

@Override
protected void jump()
{
    this.motionY = 0.61999998688697815D;
    this.setCanJump(false);
}

public void setCanJump(boolean par1)
{
	this.canJump = par1; 
}

@Override
protected void fall(float par1)
{
}

@Override
public void onUpdate()
{
    super.onUpdate();
}

@Override
public void onLivingUpdate()
{
	if(!this.worldObj.isRemote && this.onGround && this.randJump() && this.canJump)
 	{ 
	jump();
 	}
    if (!this.worldObj.isRemote && !this.canJump)
    { 	
         this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
       if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset)
        {
    	   this.motionY += (Math.signum(this.getEntityToAttack().posY) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
        }
    }
    
    if (!this.canJump && !this.onGround && this.motionY < 0.0D)
    {
    	
        this.motionY *= 0.5D;
    }
    
    if (this.onGround)
    {
    	this.setCanJump(true);
    	this.heightOffset = 0F;
    }
    super.onLivingUpdate();
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
                ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.hunger.id, var2 * 20, 0));
                }
        }

        return true; 
    }
    else
    {
        return false;
    }
	
}

@Override
protected void attackEntity(Entity par1Entity, float par2)
{
    if (par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
    {
        this.attackEntityAsMob(par1Entity);
    }
}
}
