package erebus.World.Biomes;

import java.util.Random;

import erebus.ErebusMod;
import erebus.World.Gen.WorldGenErebusMinable;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public abstract class BiomeGenBaseErebus extends BiomeGenBase
{
	public BiomeGenBaseErebus(int par1)
	{
		super(par1);
	}

	/**Generates biome specific world generation
	 * Put here all the biome specific world generation like lakes, trees, grass, etc.
	**/
	public abstract void generateTerrain(World worldObj, Random rand, IChunkProvider par1IChunkProvider, int x, int z);
	
	/**Moved all ores to a default generator since they generate in every biome. Can be overridden by the biome generator.
	 * Put here all the default world generation like ores
	**/
	public void generateDefault(World worldObj, Random rand, IChunkProvider par1iChunkProvider, int x, int z) {
		for (int var5 = 0; var5 < 20; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreCoal_U.blockID, 10)).generate(worldObj, rand, var6, var7, var8);
		}

		for (int var5 = 0; var5 < 20; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreIron_U.blockID, 8)).generate(worldObj, rand, var6, var7, var8);
		}

		for (int var5 = 0; var5 < 4; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreGold_U.blockID, 8)).generate(worldObj, rand, var6, var7, var8);
		}

		for (int var5 = 0; var5 < 1; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreLapis_U.blockID, 6)).generate(worldObj, rand, var6, var7, var8);
		}    

		for (int var5 = 0; var5 < 8; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreRedstone_U.blockID, 7)).generate(worldObj, rand, var6, var7, var8);
		}

		for (int var5 = 0; var5 < 1; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreDiamond_U.blockID, 1)).generate(worldObj, rand, var6, var7, var8);
		}  

		for (int var5 = 0; var5 < 1; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreJade_U.blockID, 4)).generate(worldObj, rand, var6, var7, var8);
		}  

		for (int var5 = 0; var5 < 20; ++var5)
		{
			int var6 = x + rand.nextInt(16);
			int var7 = rand.nextInt(128);
			int var8 = z + rand.nextInt(16);
			(new WorldGenErebusMinable(ErebusMod.oreFossil.blockID, 3)).generate(worldObj, rand, var6, var7, var8);
		}
	}
}
