package erebus.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import erebus.core.helper.LogHelper;
import erebus.core.proxy.CommonProxy;
import erebus.ErebusMod;
import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author ProPercivalalb
 */
public class BlockRedLamp extends Block {
	
	/** The icon path, not including "erebus:" as it is joined when registering icon **/
	public static final String[] iconPaths = new String[] {"redlamp_off", "redlamp_on"};
	public static final Icon[] icons = new Icon[iconPaths.length];
	
    public BlockRedLamp(int par1) {
        super(par1, Material.glass);
        this.setCreativeTab(ErebusMod.tabErebusBlock);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
    	int i = 0;
    	for(String path : iconPaths) {
    		icons[i++] = iconRegister.registerIcon("erebus:" + path);
    	}
    }
    
    @Override
    public Icon getIcon(int side, int meta) {
		if(meta < 0 || meta >= icons.length) return null;
		return icons[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }
    
    //Data about the item that drops when broken
    @Override
    public int damageDropped(int meta) {
    	switch(meta) {
    	case 1: return 0;
    	}
    	return meta;
    }
    
    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
    	return true;
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
    	return 1;
    }
    
    @Override
    public int idDropped(int meta, Random random, int fortune) {
    	return blockID;
    }
    
    /**
     * Used to decide what damage to pass to the stack when using the pick block button.
     */
    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        int realMeta = world.getBlockMetadata(x, y, z);
    	return realMeta == 1 ? 0 : realMeta;
    }
    
    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    	int meta = world.getBlockMetadata(x, y, z);
    	if(meta == 1) return false;
    	return true;
    }
    
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
    	int meta = world.getBlockMetadata(x, y, z);
    	if(meta == 1) {
    		return 15;
    	}
    	return 0;
    }
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
        if (!par1World.isRemote && (meta == 0 || meta == 1)) {
            if (meta == 1 && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (meta != 1 && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, this.blockID, 1, 2);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
        if (!par1World.isRemote && (meta == 1 || meta == 2)) {
            if (meta == 1 && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
            }
            else if (meta != 1 && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1World.setBlock(par2, par3, par4, this.blockID, meta + 1, 2);
            }
        }
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
        if (!par1World.isRemote && (meta == 1 || meta == 2) && meta == 2 && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
            par1World.setBlock(par2, par3, par4, this.blockID, 1, 2);
        }
    }
}