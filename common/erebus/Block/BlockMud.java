package erebus.block;

import java.util.Random;

import erebus.core.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockMud extends Block
{
    public BlockMud(int par1, int par2)
    {
        super(par1, Material.clay);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    /*public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_Erebus.mud.blockID;
    }*/

    public Icon getBlockIconFromSide(int i)
    {
	     return this.blockIcon;
    }
}
