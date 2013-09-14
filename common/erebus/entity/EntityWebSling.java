package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWebSling extends EntitySnowball
{

  public EntityWebSling(World par1World)
	    {
	        super(par1World);
	    }

	    public EntityWebSling(World par1World, EntityLiving par2EntityLiving)
	    {
	        super(par1World, par2EntityLiving);
	    }

	    public EntityWebSling(World par1World, double par2, double par4, double par6)
	    {
	        super(par1World, par2, par4, par6);
	    }
	    
	    protected String getWebSlingSplatSound()
	    {
	    	return "erebus:webslingsplat";
	    }

    /**
     * Called when this Entity hits a block or entity.
     */
@Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
        	int var1 = MathHelper.floor_double(this.posX);
        	int var2 = MathHelper.floor_double(this.posY);
        	int var3 = MathHelper.floor_double(this.posZ);
        	
            if (par1MovingObjectPosition.entityHit != null)
            {
            	this.worldObj.setBlock(var1, var2, var3, Block.web.blockID);	
            }
            else if (par1MovingObjectPosition.entityHit == null && Block.web.canPlaceBlockAt(this.worldObj, var1, var2, var3))
            {
	         this.worldObj.setBlock(var1, var2, var3, Block.web.blockID);
	        }
	        if (!this.worldObj.isRemote)
	        {
	            this.setDead();
	        }
	    }
        this.worldObj.playSoundAtEntity(this, getWebSlingSplatSound(), 1.0F, 1.0F);
	}
    
    


    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        return false;
    }
    
    
}