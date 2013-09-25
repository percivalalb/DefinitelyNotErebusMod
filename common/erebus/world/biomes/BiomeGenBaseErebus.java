package erebus.world.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.world.feature.WorldGenErebusMinable;

public abstract class BiomeGenBaseErebus extends BiomeGenBase {
	public BiomeGenBaseErebus(int par1) {
		super(par1);
	}

	/**
	 * Generates biome specific world generation Put here all the biome specific
	 * world generation like lakes, trees, grass, etc.
	 **/
	public abstract void generateTerrain(World worldObj, Random rand, IChunkProvider par1IChunkProvider, int x, int z);

	/**
	 * Moved all ores to a default generator since they generate in every biome.
	 * Can be overridden by the biome generator. Put here all the default world
	 * generation like ores
	 **/
	public void generateDefault(World worldObj, Random rand, IChunkProvider par1iChunkProvider, int x, int z) {
		boolean extraOres = ErebusMod.activateExtraOres;

		for (int a = 0; a < (extraOres ? 14 : 16); ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataCoal, 14, worldObj, rand, x, z, 16, 112, 3);
		}

		for (int a = 0; a < (extraOres ? 15 : 17); ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataIron, 10, worldObj, rand, x, z, 16, 112, 3);
		}

		for (int a = 0; a < (extraOres ? 5 : 6); ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataGold, 7, worldObj, rand, x, z, 16, 112, 3);
		}

		for (int a = 0; a < 3; ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataLapis, 6, worldObj, rand, x, z, 16, 112, 2);
		}

		for (int a = 0; a < 4; ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataDiamond, 1, worldObj, rand, x, z, 16, 112, 2);
		}

		for (int a = 0; a < 3; ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataJade, 4, worldObj, rand, x, z, 16, 112, 2);
		}

		for (int a = 0; a < (extraOres ? 6 : 7); ++a) {
			generateOreCluster(ModBlocks.umberOreBlock, BlockErebusOre.dataPetrifiedWood, 9, worldObj, rand, x, z, 16, 112, 2);
		}

		if (rand.nextInt(3) == 0) {
			for (int a = 0; a < 8; ++a) {
				generateOreCluster(ModBlocks.oreFossil, 3, 14, worldObj, rand, x, z, 16, 112, 3);
			}
		}

		if (extraOres) {
			for (int a = 0; a < 5; ++a) {
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataAluminium, 4, worldObj, rand, x, z, 16, 112, 2);
			}

			for (int a = 0; a < 13; ++a) {
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataCopper, 7, worldObj, rand, x, z, 16, 112, 3);
			}

			for (int a = 0; a < 7; ++a) {
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataLead, 5, worldObj, rand, x, z, 16, 112, 2);
			}

			for (int a = 0; a < 8; ++a) {
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataSilver, 9, worldObj, rand, x, z, 16, 112, 3);
			}

			for (int a = 0; a < 8; ++a) {
				generateOreCluster(ModBlocks.erebusOreExtra, BlockErebusOreExtras.dataTin, 6, worldObj, rand, x, z, 16, 112, 2);
			}
		}
	}

	private static final byte[] checkX = new byte[] { -1, -1, 1, 1, 0, 0 }, checkY = new byte[] { 0, 0, 0, 0, -1, 1 }, checkZ = new byte[] { -1, 1, -1, 1, 0, 0 };

	private void generateOreCluster(Block oreBlock, int oreMeta, int oreNumber, World world, Random rand, int x, int z, int minY, int maxY, int checkArea) {
		WorldGenErebusMinable gen = new WorldGenErebusMinable(oreBlock.blockID, oreMeta, oreNumber);

		for (int xx, yy, zz, attempt = 0; attempt < 36; attempt++) {
			xx = x + 2 + rand.nextInt(12);
			zz = z + 2 + rand.nextInt(12);
			yy = minY + rand.nextInt(Math.max(1, 1 + maxY - minY));

			for (int a = 0; a < 6; a++) {
				int testX = xx + checkX[a] * checkArea, testY = yy + checkY[a] * checkArea, testZ = zz + checkZ[a] * checkArea;
				if ((testX >> 4) != (x >> 4))
					testX = x;
				if ((testZ >> 4) != (z >> 4))
					testZ = z;

				if (world.isAirBlock(testX, testY, testZ)) {
					gen.generate(world, rand, xx, yy, zz);
					return;
				}
			}
		}
	}
}
