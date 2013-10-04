package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.utils.Utils;

public class BlockBambooCrate extends BlockContainer {

	private final Random crateRand = new Random();

	public BlockBambooCrate(int id) {
		super(id, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBambooCrate();
	}

	@Override
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:bambooCrate");
	}

	public static boolean squareCrate(World world, int x, int y, int z) {
		for (int xCount = x - 1; xCount <= x + 2; xCount++)
			for (int yCount = y - 1; yCount <= y + 2; yCount++)
				for (int zCount = z - 1; zCount <= z + 2; zCount++) {
					int blockId = world.getBlockId(xCount, yCount, zCount);
					int blockMeta = world.getBlockMetadata(xCount, yCount, zCount);

					boolean flag1 = xCount == x - 1;
					boolean flag2 = xCount == x + 2;
					boolean flag3 = yCount == y - 1;
					boolean flag4 = yCount == y + 2;
					boolean flag5 = zCount == z - 1;
					boolean flag6 = zCount == z + 2;

					if (flag1 && (flag5 || flag6))
						continue;
					if (flag2 && (flag5 || flag6))
						continue;
					if (flag3 && (flag1 || flag2 || flag5 || flag6))
						continue;
					if (flag4 && (flag1 || flag2 || flag5 || flag6))
						continue;

					if (flag1 || flag2 || flag3 || flag4 || flag5 || flag6) {
						if (blockId == ModBlocks.bambooCrate.blockID && blockMeta == 1)
							return false;
					} else if (blockId != ModBlocks.bambooCrate.blockID && blockMeta == 0)
						return false;
				}
		return true;
	}

	public static boolean isValidCrate(World world, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) == ModBlocks.bambooCrate.blockID)
			y--;
		if (world.getBlockId(x - 1, y, z) == ModBlocks.bambooCrate.blockID)
			x--;
		if (world.getBlockId(x, y, z - 1) == ModBlocks.bambooCrate.blockID)
			z--;
		return squareCrate(world, x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			TileEntityBambooCrate tileentitybamboocrate = (TileEntityBambooCrate) world.getBlockTileEntity(x, y, z);
			if (tileentitybamboocrate != null)
				if (isValidCrate(world, x, y, z))
					player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_COLOSSAL_CRATE, world, x, y, z);
				else
					player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_BAMBOO_CRATE, world, x, y, z);
			return true;
		}
	}

	@Override
	public int getRenderType() {
		return -1;
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
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntityBambooCrate tile = (TileEntityBambooCrate) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); ++i) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
}