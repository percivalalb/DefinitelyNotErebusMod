package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockLadder;

public class BlockBambooLadder extends BlockLadder {

	public BlockBambooLadder(int id) {
		super(id);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return blockID;
	}
}
