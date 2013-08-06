package erebus.Block;

import java.util.Random;

import erebus.core.proxy.CommonProxy;
import erebus.mod_Erebus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOreFossil extends Block
{
    public BlockOreFossil(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	if (par2Random.nextInt(20) == 0)
    	{
    		return mod_Erebus.fossilClub.itemID;
    	}
    	else if (par2Random.nextInt(3) == 0)
    	{
    		return Item.bone.itemID;
    	}
    	else
    	{
    		return mod_Erebus.fossilShard.itemID;
    	}
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1 + par1Random.nextInt(3);
    }
}
