package erebus.Block;

import java.util.Random;

import erebus.core.proxy.CommonProxy;
import erebus.ErebusMod;
import erebus.api.Properties;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockUmberstone extends Block 
{
	
    public BlockUmberstone(int par1, Material par2Material) 
    {
        super(par1, par2Material);
        this.setCreativeTab(ErebusMod.tabErebus);
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    	if(this.blockID == ErebusMod.umberstone.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "Umberstone");	
    	}
    	if(this.blockID == ErebusMod.umbercobble.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "cobbleUmber");	
    	}
    	if(this.blockID == ErebusMod.umbercobbleMossy.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "cobbleUmberMossy");	
    	}
    }
}
