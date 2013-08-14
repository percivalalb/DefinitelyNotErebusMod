package erebus.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.ModItems;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author ProPercivalalb
 */
public class BlockErebusOre extends Block {
 
	/** The icon path, not including "erebus:" as it is joined when registering icon **/
	public static final String[] iconPaths = new String[] {"oreCoalU", "oreIronU", "oreGoldU", "oreLapisU", "oreDiamondU", "oreEmeraldU", "oreJadeU"};
	public static final Icon[] icons = new Icon[iconPaths.length];
	
    public BlockErebusOre(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(ErebusMod.tabErebus);
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
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
    }
    
    //Data about the item that drops when broken
    @Override
    public int damageDropped(int meta) {
    	switch(meta) {
    	case 0:
    	case 4:
    	case 5: return 0;
    	case 3: return 4;
    	}
    	return meta;
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
    	int _default = meta == 3 ? 4 + random.nextInt(5) : 1;
    	
    	if (meta > 0 && this.blockID != this.idDropped(meta, random, fortune)) {
    		int j = random.nextInt(fortune + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return _default * (j + 1);
        }
    	
        return _default;
    }
    
    @Override
    public int idDropped(int meta, Random random, int fortune) {
    	switch(meta) {
    	case 0: return Item.coal.itemID;
    	case 3: return Item.dyePowder.itemID;
    	case 4: return Item.diamond.itemID;
    	case 5: return Item.emerald.itemID;
    	//case 6: return ModItems.jade.itemID; 
    	}
    	return blockID;
    }
    
    @Override
    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(blockID, 1, meta);
    }
    
    /**
     * Used to decide what damage to pass to the stack when using the pick block button.
     */
    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
        int meta = world.getBlockMetadata(x, y, z);
        
        if (this.idDropped(par5, world.rand, par7) != this.blockID) {
            int j1 = 0;

            switch(meta) {
            case 0: j1 = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
            case 3: j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
            case 4:
            case 5:
            case 6: j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
            }

            this.dropXpOnBlockBreak(world, x, y, z, j1);
        }
    }
}
