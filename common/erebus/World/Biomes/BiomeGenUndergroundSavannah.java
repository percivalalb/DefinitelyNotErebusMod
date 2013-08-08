package erebus.world.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import erebus.ErebusMod;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityFly;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.world.gen.WorldGenRock;
import erebus.world.gen.WorldGenRottenAcacia;
import erebus.world.gen.WorldGenSavannaTree;

public class BiomeGenUndergroundSavannah extends BiomeGenBaseErebus
{
	public BiomeGenUndergroundSavannah(int par1)
	{
		super(par1);
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class, 10, 8, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityBeetleLarva.class, 150, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityBeetle.class, 30, 1, 3));
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 35, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 10, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityTarantula.class, 5, 1, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityWasp.class, 30, 1, 8));
		this.topBlock = (byte)Block.grass.blockID;
		this.fillerBlock = (byte)Block.dirt.blockID;
	}

	@Override
	public void generateTerrain(World worldObj, Random rand, IChunkProvider par1iChunkProvider, int x, int z) 
	{
		/*for(int c = 20; c > 0; c--)
		{
			int posX = x + rand.nextInt(16);
			int posY = rand.nextInt(120);
			int posZ = z + rand.nextInt(16);
			if(worldObj.getBlockId(posX, posY, posZ) == 0 && worldObj.getBlockId(posX, posY - 1, posZ) == Block.grass.blockID)
			{
				(new WorldGenRock(Block.cobblestone.blockID, worldObj.rand.nextDouble() * 3 + 4)).generate(worldObj, worldObj.rand, posX, posY - 4, posZ);
			}
		}*/

		for(int c = 200; c > 0; c--)
		{
			int j2 = x + rand.nextInt(16);
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16);
			if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
			{
				int size = worldObj.rand.nextInt(3);
				(new WorldGenSavannaTree(size)).generate(worldObj, rand, j2, l3, j5);
			}
		}

		/*for(int c = 0; c < 50; c++)
        {
        	int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
        	{
        		(new WorldGenRottenAcacia()).generate(worldObj, rand, j2, l3, j5);
        	}
        }*/

		/*for(int c = 0; c < 30; c++)
        {
        	int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
        	{
        		(new WorldGenScree()).generate(worldObj, rand, j2, l3, j5);
        	}
        }*/

		for(int c = 0; c < 600; c++)
		{
			int j2 = x + rand.nextInt(16) + 8;
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16) + 8;
			if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
			{
				(new WorldGenTallGrass(ErebusMod.erebusGrass.blockID, 1)).generate(worldObj, rand, j2, l3, j5);
			}
		}

		for(int c = 0; c < 50; c++)
		{
			int j2 = x + rand.nextInt(16) + 8;
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16) + 8;
			if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == Block.grass.blockID)
			{
				(new WorldGenRottenAcacia()).generate(worldObj, rand, j2, l3, j5);
			}
		}
	}
}
