package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import erebus.ErebusMod;

public class BlockStairsErebus extends BlockStairs {

	public BlockStairsErebus(int id, Block block, int meta) {
		super(id, block, meta);
		setHardness(2.0F);
		setCreativeTab(ErebusMod.tabErebusBlock);
	}

	public BlockStairsErebus(int id, Block block) {
		super(id, block, 0);
		setHardness(2.0F);
		setCreativeTab(ErebusMod.tabErebusBlock);
	}
}