package erebus.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

/**
 * @author ProPercivalalb
 */
public class BlockErebusOre extends Block {

	/**
	 * The icon path, not including "erebus:" as it is joined when registering
	 * icon
	 **/
	protected String[] iconPaths = new String[] { "oreCoalU", "oreIronU", "oreGoldU", "oreLapisU", "oreDiamondU", "oreEmeraldU", "oreJadeU", "petrifiedWoodOre" };
	@SideOnly(Side.CLIENT)
	protected Icon[] icons;

	public BlockErebusOre(int id) {
		super(id, Material.rock);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		
		for(int a=0; a<iconPaths.length; a++){
			icons[a] = iconRegister.registerIcon("erebus:" + iconPaths[a]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int a = 0; a < iconPaths.length; a++) {
			list.add(new ItemStack(id, 1, a));
		}
	}

	// Data about the item that drops when broken
	@Override
	public int damageDropped(int meta) {
		switch (meta) {
			case 0:
			case 4:
			case 5:
				return 0;
			case 3:
				return 4;
			case 6:
				return 1;
			case 7:
				return 7;
		}
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		int _default = meta == 3 ? 4 + random.nextInt(5) : 1;

		if (this.blockID != this.idDropped(meta, random, fortune)) {
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
		switch (meta) {
			case 0:
				return Item.coal.itemID;
			case 3:
				return Item.dyePowder.itemID;
			case 4:
				return Item.diamond.itemID;
			case 5:
				return Item.emerald.itemID;
			case 6:
				return ModItems.erebusMaterials.itemID;
			case 7:
				return ModItems.erebusMaterials.itemID;
		}
		return blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(blockID, 1, meta);
	}

	/**
	 * Used to decide what damage to pass to the stack when using the pick block
	 * button.
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

			switch (meta) {
				case 0:
					j1 = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
				case 3:
					j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
				case 4:
				case 5:
				case 6:
					j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			}

			this.dropXpOnBlockBreak(world, x, y, z, j1);
		}
	}
}
