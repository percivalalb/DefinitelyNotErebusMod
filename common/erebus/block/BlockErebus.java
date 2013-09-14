package erebus.block;

import java.util.Random;

import erebus.ErebusMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockErebus extends Block {
	
    public BlockErebus(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setCreativeTab(ErebusMod.tabErebusBlock);
    }
}
