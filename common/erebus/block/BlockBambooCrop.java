package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import erebus.ErebusMod;
import erebus.ModItems;

public class BlockBambooCrop extends Block {

	public BlockBambooCrop(int id) {
		super(id, Material.wood);
		setHardness(1.0F);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return ModItems.erebusMaterials.itemID;
	}

	@Override
	public int damageDropped(int meta) {
		return 3;
	}

	@Override
	public int getRenderType() {
		return ErebusMod.proxy.bambooCropRenderID;
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
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		return world.getBlockId(x, y - 1, z) == blockID || block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}
}