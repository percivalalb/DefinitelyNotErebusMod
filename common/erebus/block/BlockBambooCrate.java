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

	public static boolean isFullCrate(World world, int x, int y, int z) {
		TileEntityBambooCrate master = getMasterTileEntity(world, x, y, z);
		if (master != null) {
			x = master.xCoord;
			y = master.yCoord;
			z = master.zCoord;

			return checkPlain(world, x, y, z) && checkPlain(world, x, y + 1, z);
		}
		return false;
	}

	private static boolean checkPlain(World world, int x, int y, int z) {
		if (world.getBlockId(x, y, z) == ModBlocks.bambooCrate.blockID && !((TileEntityBambooCrate) world.getBlockTileEntity(x, y, z)).isTagged())
			if (world.getBlockId(x, y, z + 1) == ModBlocks.bambooCrate.blockID && !((TileEntityBambooCrate) world.getBlockTileEntity(x, y, z + 1)).isTagged())
				if (world.getBlockId(x + 1, y, z + 1) == ModBlocks.bambooCrate.blockID && !((TileEntityBambooCrate) world.getBlockTileEntity(x + 1, y, z + 1)).isTagged())
					if (world.getBlockId(x + 1, y, z) == ModBlocks.bambooCrate.blockID && !((TileEntityBambooCrate) world.getBlockTileEntity(x + 1, y, z)).isTagged())
						return true;

		return false;
	}

	private void untagSingleCrate(TileEntity crate) {
		if (crate != null && crate instanceof TileEntityBambooCrate)
			((TileEntityBambooCrate) crate).untag();
	}

	public static TileEntityBambooCrate getMasterTileEntity(World world, int x, int y, int z) {
		int newX = x, newY = y, newZ = z;
		if (world.getBlockId(x, y - 1, z) == ModBlocks.bambooCrate.blockID)
			newY--;
		if (world.getBlockId(x - 1, y, z) == ModBlocks.bambooCrate.blockID)
			newX--;
		if (world.getBlockId(x, y, z - 1) == ModBlocks.bambooCrate.blockID)
			newZ--;

		if (world.getBlockId(newX, newY, newZ) == ModBlocks.bambooCrate.blockID)
			return (TileEntityBambooCrate) world.getBlockTileEntity(newX, newY, newZ);
		return null;
	}

	private void tagFullCrateComponents(World world, int x, int y, int z) {
		TileEntityBambooCrate master = getMasterTileEntity(world, x, y, z);
		if (master != null) {
			x = master.xCoord;
			y = master.yCoord;
			z = master.zCoord;

			tagSingleCrate(world.getBlockTileEntity(x, y, z));
			tagSingleCrate(world.getBlockTileEntity(x, y, z + 1));
			tagSingleCrate(world.getBlockTileEntity(x + 1, y, z + 1));
			tagSingleCrate(world.getBlockTileEntity(x + 1, y, z));

			tagSingleCrate(world.getBlockTileEntity(x, y + 1, z));
			tagSingleCrate(world.getBlockTileEntity(x, y + 1, z + 1));
			tagSingleCrate(world.getBlockTileEntity(x + 1, y + 1, z + 1));
			tagSingleCrate(world.getBlockTileEntity(x + 1, y + 1, z));
		}
	}

	private void tagSingleCrate(TileEntity crate) {
		if (crate != null && crate instanceof TileEntityBambooCrate)
			((TileEntityBambooCrate) crate).tag();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (isFullCrate(world, x, y, z))
			tagFullCrateComponents(world, x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			TileEntityBambooCrate tileCrate = (TileEntityBambooCrate) world.getBlockTileEntity(x, y, z);

			if (tileCrate.isTagged()) {
				tileCrate = getMasterTileEntity(world, x, y, z);
				player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_COLOSSAL_CRATE, world, tileCrate.xCoord, tileCrate.yCoord, tileCrate.zCoord);
			} else if (tileCrate != null)
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
			if (tile.isTagged()) {
				TileEntityBambooCrate master = getMasterTileEntity(world, x, y, z);
				if (master != null) {
					x = master.xCoord;
					y = master.yCoord;
					z = master.zCoord;

					untagSingleCrate(world.getBlockTileEntity(x, y, z));
					untagSingleCrate(world.getBlockTileEntity(x, y, z + 1));
					untagSingleCrate(world.getBlockTileEntity(x + 1, y, z + 1));
					untagSingleCrate(world.getBlockTileEntity(x + 1, y, z));

					untagSingleCrate(world.getBlockTileEntity(x, y + 1, z));
					untagSingleCrate(world.getBlockTileEntity(x, y + 1, z + 1));
					untagSingleCrate(world.getBlockTileEntity(x + 1, y + 1, z + 1));
					untagSingleCrate(world.getBlockTileEntity(x + 1, y + 1, z));
				}
			}

			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
}