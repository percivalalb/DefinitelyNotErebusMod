package erebus.Block;

import java.util.Random;

import erebus.core.proxy.CommonProxy;
import erebus.mod_Erebus;
import erebus.World.Gen.WorldGenMossbarkTree;
import erebus.api.Properties;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAmber extends Block
{
    public BlockAmber(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setLightOpacity(3);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    	if(this.blockID == mod_Erebus.blockAmber.blockID) {
            this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "blockAmber");	
    	}
    	else if(this.blockID == mod_Erebus.glassAmber.blockID) {
    	    this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "glassAmber");	
    	}
    	else if(this.blockID == mod_Erebus.brickAmber.blockID) {
    	    this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "brickAmber");	
    	}
    }
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
	 	WorldGenMossbarkTree gen = new WorldGenMossbarkTree();
	 	par1World.setBlock(par2,par3,par4,0);
	 	gen.generate(par1World, par1World.rand ,par2, par3, par4);
        return true;
    }
}
