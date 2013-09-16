package erebus.block;

import java.util.Random;

import erebus.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPetrifiedWoodOre extends Block {

	public BlockPetrifiedWoodOre(int id) {
		super(id, Material.rock);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return ModItems.itemPetrifiedWood.itemID;
	}
	
	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}
}
