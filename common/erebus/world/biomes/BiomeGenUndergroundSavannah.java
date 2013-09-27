package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import erebus.ModBlocks;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityMoth;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.feature.WorldGenAmberGround;
import erebus.world.feature.WorldGenAmberUmberstone;
import erebus.world.feature.WorldGenBamboo;
import erebus.world.feature.WorldGenRottenAcacia;
import erebus.world.feature.WorldGenSavannaRock;
import erebus.world.feature.trees.WorldGenAsperTree;
import erebus.world.feature.trees.WorldGenSavannaTree;

public class BiomeGenUndergroundSavannah extends BiomeGenBaseErebus {
	public BiomeGenUndergroundSavannah(int par1) {
		super(par1);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();

		spawnableMonsterList.add(new SpawnListEntry(EntityCentipede.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityScorpion.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityWasp.class, 30, 4, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBotFly.class, 10, 4, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class, 10, 8, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 35, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 10, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityTarantula.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityBeetleLarva.class, 150, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityGrasshopper.class, 10, 4, 8));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityMoth.class, 5, 4, 4));
		topBlock = (byte) Block.grass.blockID;
		fillerBlock = (byte) Block.dirt.blockID;
	}

	@Override
	public void generateTerrain(World worldObj, Random rand, IChunkProvider par1iChunkProvider, int x, int z) {
		for (int c = 75; c > 0; c--) {
			int j2 = x + rand.nextInt(16);
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID) {
				int size = worldObj.rand.nextInt(3);
				(new WorldGenSavannaTree(size)).generate(worldObj, rand, j2, l3, j5);
			}
		}

		if (rand.nextInt(3) == 0)
			for (int yy = 100; yy > 20; yy--) {
				int xx = x + 4 + rand.nextInt(8), zz = z + 4 + rand.nextInt(8);
				if (worldObj.getBlockId(xx, yy, zz) == 0 && worldObj.getBlockId(xx, yy - 1, zz) == Block.grass.blockID)
					new WorldGenSavannaRock().generate(worldObj, rand, xx, yy, zz);
			}

		if (rand.nextInt(26) == 0)
			for (int yy = 100; yy > 20; yy--) {
				int xx = x + 4 + rand.nextInt(8), zz = z + 4 + rand.nextInt(8);
				if (worldObj.getBlockId(xx, yy, zz) == 0 && worldObj.getBlockId(xx, yy - 1, zz) == Block.grass.blockID)
					new WorldGenBamboo(7).generate(worldObj, rand, xx, yy, zz);
			}
		else
			for (int c = 10; c > 0; c--) {
				int j2 = x + rand.nextInt(16);
				int l3 = rand.nextInt(120);
				int j5 = z + rand.nextInt(16);
				if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
					(new WorldGenAsperTree()).generate(worldObj, rand, j2, l3, j5);
			}

		for (int c = 0; c < 200; c++) {
			int j2 = x + rand.nextInt(16) + 8;
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16) + 8;
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				(new WorldGenTallGrass(ModBlocks.erebusGrass.blockID, 1)).generate(worldObj, rand, j2, l3, j5);
		}

		for (int c = 0; c < 50; c++) {
			int j2 = x + rand.nextInt(16) + 8;
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16) + 8;
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
				(new WorldGenRottenAcacia()).generate(worldObj, rand, j2, l3, j5);
		}

		if (rand.nextInt(6) == 0)
			for (int attempt = 0; attempt < 6; attempt++)
				if (new WorldGenAmberGround().generate(worldObj, rand, x + rand.nextInt(16), rand.nextInt(120), z + rand.nextInt(16)))
					break;

		if (rand.nextInt(3) == 0)
			for (int attempt = 0; attempt < 5; attempt++)
				if (new WorldGenAmberUmberstone().generate(worldObj, rand, x + rand.nextInt(16), rand.nextInt(120), z + rand.nextInt(16)))
					break;
	}
}
