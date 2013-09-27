package erebus.block;

import java.util.Random;

import net.minecraft.item.Item;

public class BlockSpiderSpawner extends BlockSpawner {

	public BlockSpiderSpawner(int id, String mobName) {
		super(id, mobName);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Item.silk.itemID;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return par1Random.nextInt(3);
	}
}
