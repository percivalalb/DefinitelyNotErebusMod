package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenQuickSand extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int par3, int par4, int par5) {
		while (world.isAirBlock(par3, par4, par5) && par4 > 2)
			--par4;

		int var6 = world.getBlockId(par3, par4, par5);

		if (var6 != Block.grass.blockID)
			return false;
		else {
			world.setBlock(par3, par4, par5, ModBlocks.quickSand.blockID);
			world.setBlock(par3, par4 - 1, par5, ModBlocks.quickSand.blockID);
			world.setBlock(par3, par4 - 2, par5, ModBlocks.quickSand.blockID);

			world.setBlock(par3, par4, par5 - 1, ModBlocks.quickSand.blockID);
			world.setBlock(par3, par4 - 1, par5 - 1, ModBlocks.quickSand.blockID);

			world.setBlock(par3, par4, par5 + 1, ModBlocks.quickSand.blockID);
			world.setBlock(par3, par4 - 1, par5 + 1, ModBlocks.quickSand.blockID);

			world.setBlock(par3 - 1, par4, par5, ModBlocks.quickSand.blockID);
			world.setBlock(par3 - 1, par4 - 1, par5, ModBlocks.quickSand.blockID);

			world.setBlock(par3 + 1, par4, par5, ModBlocks.quickSand.blockID);
			world.setBlock(par3 + 1, par4 - 1, par5, ModBlocks.quickSand.blockID);

			world.setBlock(par3 - 2, par4, par5, ModBlocks.quickSand.blockID);
			world.setBlock(par3 - 1, par4, par5 - 1, ModBlocks.quickSand.blockID);

			world.setBlock(par3 + 2, par4, par5, ModBlocks.quickSand.blockID);
			world.setBlock(par3 + 1, par4, par5 + 1, ModBlocks.quickSand.blockID);

			world.setBlock(par3, par4, par5 - 2, ModBlocks.quickSand.blockID);
			world.setBlock(par3 - 1, par4, par5 + 1, ModBlocks.quickSand.blockID);

			world.setBlock(par3, par4, par5 + 2, ModBlocks.quickSand.blockID);
			world.setBlock(par3 + 1, par4, par5 - 1, ModBlocks.quickSand.blockID);

			// Top Layer
			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 1, par4 - 2, par5 + 0, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 0, par4 - 2, par5 + 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 0, par4 - 2, par5 - 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 1, par4 - 2, par5 + 0, ModBlocks.quickSand.blockID);

			// Middle Layer
			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 2, par4 - 1, par5 + 0, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 1, par4 - 1, par5 + 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 0, par4 - 1, par5 + 2, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 1, par4 - 1, par5 + 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 2, par4 - 1, par5 - 0, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 1, par4 - 1, par5 - 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 0, par4 - 1, par5 - 2, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 1, par4 - 1, par5 - 1, ModBlocks.quickSand.blockID);

			// Bottom Layer
			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 3, par4, par5 + 0, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 2, par4, par5 + 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 1, par4, par5 + 2, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 3, par4, par5 + 0, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 2, par4, par5 + 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 1, par4, par5 + 2, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 0, par4, par5 - 3, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 2, par4, par5 - 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 + 1, par4, par5 - 2, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 0, par4, par5 + 3, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 2, par4, par5 - 1, ModBlocks.quickSand.blockID);

			if (rand.nextInt(2) == 0)
				world.setBlock(par3 - 1, par4, par5 - 2, ModBlocks.quickSand.blockID);

			return true;
		}
	}
}
