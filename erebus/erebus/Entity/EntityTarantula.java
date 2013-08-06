package erebus.Entity;

import erebus.mod_Erebus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTarantula extends EntitySpider
{

	public EntityTarantula(World par1World) 
	{
		super(par1World);
		setSize(1.0F, 0.6F);
	}

	@Override
	protected void func_110147_ax() {
	    super.func_110147_ax();
	    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
	    this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 4;
	}

	 /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
     * false.
     */
    public void setBesideClimbableBlock(boolean par1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            var2 = (byte)(var2 | 1);
        }
        else
        {
            var2 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(var2));
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    public int skin = rand.nextInt(99);

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
	
	@Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        if (super.attackEntityAsMob(par1Entity))
        {
            if (par1Entity instanceof EntityLiving)
            {
                byte var2 = 0;

                if (this.worldObj.difficultySetting > 1 && rand.nextInt(19) == 0)
                {
                    if (this.worldObj.difficultySetting == 2)
                    {
                        var2 = 5;
                    }
                    else if (this.worldObj.difficultySetting == 3)
                    {
                        var2 = 10;
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

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int chanceFiftyFifty = this.rand.nextInt(1) + 1;

		int chance40x40x20 = this.rand.nextInt(4);
		int stringDrop = 0;

		switch(chance40x40x20)
		{
		case 0: stringDrop = 1;
		break;
		case 1: stringDrop = 1;
		break;
		case 2: stringDrop = 2;
		break;
		case 3: stringDrop = 2;
		break;
		case 4: stringDrop = 3;
		break;
		}

		int chance20x60x20 = this.rand.nextInt(4);
		int legDrop = 0;

		switch(chance20x60x20)
		{
		case 0: legDrop = 1;
		break;
		case 1: legDrop = 2;
		break;
		case 2: legDrop = 2;
		break;
		case 3: legDrop = 2;
		break;
		case 4: legDrop = 3;
		break;
		}

		this.dropItem(Item.silk.itemID, stringDrop + par2);

		if (this.isBurning())
        {
			this.dropItem(mod_Erebus.legTarantulaCooked.itemID, legDrop + par2);
        }
		else
		{
			this.dropItem(mod_Erebus.legTarantula.itemID, legDrop + par2);
		}
        
		this.dropItem(Item.spiderEye.itemID, chanceFiftyFifty + par2);

		/* 50% chance for 1 Spider eye, 50% for two
		 * 40% chance for 1 String, 40% for two and 20% for three
		 * 20% for zero tarantula legs, 60% for 1 and 20% for two*/
	}
}
