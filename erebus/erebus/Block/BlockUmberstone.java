package erebus.Block;

import java.util.Random;

import erebus.CommonErebusProxy;
import erebus.mod_Erebus;
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
        this.setCreativeTab(mod_Erebus.tabErebus);
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    	if(this.blockID == mod_Erebus.umberstone.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "Umberstone");	
    	}
    	if(this.blockID == mod_Erebus.umbercobble.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "cobbleUmber");	
    	}
    	if(this.blockID == mod_Erebus.umbercobbleMossy.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "cobbleUmberMossy");	
    	}
    }
}
