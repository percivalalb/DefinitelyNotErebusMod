package erebus.Block;

import java.util.Random;

import erebus.core.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockMossyBrick extends Block
{
    public BlockMossyBrick(int par1, int par2)
    {
        super(par1, Material.clay);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public Icon getBlockIconFromSide(int i)
    {
	     return this.blockIcon;
    }
}
