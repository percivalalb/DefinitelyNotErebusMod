package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.tileentity.TileEntityExtenderThingy;

public class BlockExtenderThingy extends BlockContainer {

	public BlockExtenderThingy(int id) {
		super(id, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtenderThingy();
	}
}