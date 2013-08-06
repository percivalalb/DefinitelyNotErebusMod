package erebus.Block;

import java.util.Random;

import erebus.mod_Erebus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockErebus extends Block {
	
    public BlockErebus(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setCreativeTab(mod_Erebus.tabErebus);
    }
}
