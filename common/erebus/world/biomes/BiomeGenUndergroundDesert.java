package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import erebus.ModBlocks;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityScorpion;
import erebus.world.feature.trees.WorldGenScorchedTree;

public class BiomeGenUndergroundDesert extends BiomeGenBaseErebus {
	public BiomeGenUndergroundDesert(int par1) {
		super(par1);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityScorpion.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityCentipede.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityBlackWidow.class, 5, 1, 1));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBotFly.class, 10, 4, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 35, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 10, 1, 4));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class, 10, 8, 8));
		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sandStone.blockID;
	}

	@Override
	public void generateTerrain(World worldObj, Random rand, IChunkProvider par1iChunkProvider, int x, int z) {
		for (int c = 40; c > 0; c--) {
			int posX = x + rand.nextInt(16);
			int posY = rand.nextInt(120);
			int posZ = z + rand.nextInt(16);
			if (worldObj.getBlockId(posX, posY, posZ) == 0 && worldObj.getBlockId(posX, posY - 1, posZ) == Block.sand.blockID)
				(new WorldGenLakes(Block.lavaMoving.blockID)).generate(worldObj, worldObj.rand, posX, posY, posZ);
		}

		for (int c = 10; c > 0; c--) {
			int posX = x + rand.nextInt(16);
			int posY = rand.nextInt(120);
			int posZ = z + rand.nextInt(16);
			if (worldObj.getBlockId(posX, posY, posZ) == ModBlocks.umberstone.blockID && worldObj.getBlockId(posX, posY - 1, posZ) == 0) {
				worldObj.setBlock(posX, posY, posZ, Block.lavaMoving.blockID);
				/**
				 * Makes lava fall instantly Avoids a lot of block update lag
				 **/
				worldObj.scheduledUpdatesAreImmediate = true;
				Block.blocksList[Block.lavaMoving.blockID].updateTick(worldObj, posX, posY, posZ, rand);
				worldObj.scheduledUpdatesAreImmediate = false;
			}
		}

		for (int c = 22; c > 0; c--) {
			int j2 = x + rand.nextInt(16);
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16);
			if (worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.sand.blockID) {
				(new WorldGenScorchedTree()).generate(worldObj, rand, j2, l3, j5);
				if (rand.nextInt(4) != 0)
					break;
			}
		}
	}
}