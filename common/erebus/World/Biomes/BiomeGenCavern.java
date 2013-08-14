package erebus.world.biomes;

import java.util.List;
import java.util.Random;

import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.entity.*;
import erebus.world.*;
import erebus.world.feature.WorldGenAmber;
import erebus.world.feature.WorldGenBigMushroomErebus;
import erebus.world.feature.WorldGenErebusHugeTree;
import erebus.world.feature.WorldGenErebusMinable;
import erebus.world.feature.WorldGenErebusTrees;
import erebus.world.feature.WorldGenGlowStoneErebus1;
import erebus.world.feature.WorldGenGlowStoneErebus2;
import erebus.world.feature.WorldGenMelonErebus;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenCavern extends BiomeGenBaseErebus
{
    public BiomeGenCavern(int par1)
    {
        super(par1);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntityBeetleLarva.class, 150, 1, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityBeetle.class, 30, 1, 3));
        spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 35, 1, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 10, 1, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityTarantula.class, 5, 1, 4));
        spawnableCaveCreatureList.add(new SpawnListEntry(EntityFly.class, 10, 8, 8));
        this.topBlock = (byte)ModBlocks.umberstone.blockID;
        this.fillerBlock = (byte)ModBlocks.umberstone.blockID;
    }
    
    public void decorate(World worldObj, Random rand, int x, int z)
    {
    	{
    		/**Generating jungle vines**/
            for(int i1 = 0; i1 < 250; i1++)
            {
                int posX3 = x + rand.nextInt(16);
                int posY3 = 6 + rand.nextInt(120);
                int posZ3 = z + rand.nextInt(16);
    	    	if(worldObj.getBlockId(posX3, posY3, posZ3) == 0)
    	    	{
            		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 + 1)))
            		{ 
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 1, 3);
            		    	}
                		}
            		}
            		if(Block.isNormalCube(worldObj.getBlockId(posX3 - 1, posY3, posZ3)))
            		{
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 2, 3);
            		    	}
                		}
            		}
            		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 - 1)))
            		{ 
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 4, 3);
            		    	}
                		}
            		}
            		if(Block.isNormalCube(worldObj.getBlockId(posX3 + 1, posY3, posZ3)))
            		{ 
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 0, 3);
            		    	}
                		}
            		}
            	}
            }
            
            /**Generating thorns**/
            for(int i1 = 0; i1 < 250; i1++)
            {
                int posX3 = x + rand.nextInt(16);
                int posY3 = 6 + rand.nextInt(120);
                int posZ3 = z + rand.nextInt(16);
    	    	if(worldObj.getBlockId(posX3, posY3, posZ3) == 0)
    	    	{
            		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 + 1)))
            		{ 
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 1, 3);
            		    	}
                		}
            		}
            		if(Block.isNormalCube(worldObj.getBlockId(posX3 - 1, posY3, posZ3)))
            		{
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 2, 3);
            		    	}
                		}
            		}
            		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 - 1)))
            		{ 
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 4, 3);
            		    	}
                		}
            		}
            		if(Block.isNormalCube(worldObj.getBlockId(posX3 + 1, posY3, posZ3)))
            		{ 
            			for(int c = rand.nextInt(30); c > 0; c--)
                		{
            				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
            		    	{
            					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 0, 3);
            		    	}
                		}
            		}
            	}
            }
            if (rand.nextInt(1) == 0)
            {
                int i2 = x + rand.nextInt(16) + 8;
                int k3 = rand.nextInt(128);
                int i5 = z + rand.nextInt(16) + 8;
                (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, i2, k3, i5);
            }

            if (rand.nextInt(1) == 0)
            {
                int j2 = x + rand.nextInt(16) + 8;
                int l3 = rand.nextInt(128);
                int j5 = z + rand.nextInt(16) + 8;
                (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, j2, l3, j5);
            }
            
            for(int c = 10; c > 0; c--)
           	{
        		int j2 = x + rand.nextInt(16) + 8;
        		int l3 = rand.nextInt(120);
            	int j5 = z + rand.nextInt(16) + 8;
            	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == ModBlocks.umberstone.blockID)
            	{
            		(new WorldGenAmber()).generate(worldObj, rand, j2, l3, j5);
            	}
        	}
            
            //Glowstone
            for(int c = 10; c > 0; c--)
            {
                int j2 = x + rand.nextInt(16) + 8;
                int l3 = rand.nextInt(128);
                int j5 = z + rand.nextInt(16) + 8;
                (new WorldGenGlowStoneErebus1()).generate(worldObj, rand, j2, l3, j5);
            }
            
            for(int c = 10; c > 0; c--)
            {
                int j2 = x + rand.nextInt(16) + 8;
                int l3 = rand.nextInt(128);
                int j5 = z + rand.nextInt(16) + 8;
                (new WorldGenGlowStoneErebus2()).generate(worldObj, rand, j2, l3, j5);
            }
            
            //Giant Mushrooms
            for(int c = 15; c > 0; c--)
        	{
        		int j2 = x + rand.nextInt(16) + 8;
        		int l3 = rand.nextInt(120);
            	int j5 = z + rand.nextInt(16) + 8;
            	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == ModBlocks.umberstone.blockID)
            	{
            		(new WorldGenBigMushroomErebus(0)).generate(worldObj, rand, j2, l3, j5);
            	}
        	}  
            
            for(int c = 25; c > 0; c--)
        	{
        		int j2 = x + rand.nextInt(16) + 8;
        		int l3 = rand.nextInt(120);
            	int j5 = z + rand.nextInt(16) + 8;
            	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == ModBlocks.umberstone.blockID)
            	{
            		(new WorldGenBigMushroomErebus(1)).generate(worldObj, rand, j2, l3, j5);
            	}
        	}  
            
            for (int var5 = 0; var5 < 20; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.oreCoal.blockID, 10)).generate(worldObj, rand, var6, var7, var8);
            }
            
            for (int var5 = 0; var5 < 20; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.oreIron.blockID, 8)).generate(worldObj, rand, var6, var7, var8);
            }
            
            for (int var5 = 0; var5 < 4; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.oreGold.blockID, 8)).generate(worldObj, rand, var6, var7, var8);
            }
            
            for (int var5 = 0; var5 < 1; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.oreLapis.blockID, 6)).generate(worldObj, rand, var6, var7, var8);
            }    
            
            for (int var5 = 0; var5 < 8; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.oreRedstone.blockID, 7)).generate(worldObj, rand, var6, var7, var8);
            }
            
            for (int var5 = 0; var5 < 1; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(worldObj, rand, var6, var7, var8);
            }        
             	    		
            for (int var5 = 0; var5 < 6; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(Block.silverfish.blockID, 8)).generate(worldObj, rand, var6, var7, var8);
            }
            
            for (int var5 = 0; var5 < 20; ++var5)
            {
                int var6 = x + rand.nextInt(16);
                int var7 = rand.nextInt(128);
                int var8 = z + rand.nextInt(16);
                (new WorldGenMinable(ModBlocks.oreFossil.blockID, 3)).generate(worldObj, rand, var6, var7, var8);
            }
    	}
    }
	
    @Override
	public void generateTerrain(World worldObj, Random rand, IChunkProvider par1iChunkProvider, int x, int z)
	{
    	/**Generating jungle vines**/
        for(int i1 = 0; i1 < 250; i1++)
        {
            int posX3 = x + rand.nextInt(16);
            int posY3 = 6 + rand.nextInt(120);
            int posZ3 = z + rand.nextInt(16);
	    	if(worldObj.getBlockId(posX3, posY3, posZ3) == 0)
	    	{
        		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 + 1)))
        		{ 
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 1, 3);
        		    	}
            		}
        		}
        		if(Block.isNormalCube(worldObj.getBlockId(posX3 - 1, posY3, posZ3)))
        		{
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 2, 3);
        		    	}
            		}
        		}
        		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 - 1)))
        		{ 
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 4, 3);
        		    	}
            		}
        		}
        		if(Block.isNormalCube(worldObj.getBlockId(posX3 + 1, posY3, posZ3)))
        		{ 
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, Block.vine.blockID, 0, 3);
        		    	}
            		}
        		}
        	}
        }
        
        /**Generating thorns**/
        for(int i1 = 0; i1 < 250; i1++)
        {
            int posX3 = x + rand.nextInt(16);
            int posY3 = 6 + rand.nextInt(120);
            int posZ3 = z + rand.nextInt(16);
	    	if(worldObj.getBlockId(posX3, posY3, posZ3) == 0)
	    	{
        		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 + 1)))
        		{ 
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 1, 3);
        		    	}
            		}
        		}
        		if(Block.isNormalCube(worldObj.getBlockId(posX3 - 1, posY3, posZ3)))
        		{
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 2, 3);
        		    	}
            		}
        		}
        		if(Block.isNormalCube(worldObj.getBlockId(posX3, posY3, posZ3 - 1)))
        		{ 
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 4, 3);
        		    	}
            		}
        		}
        		if(Block.isNormalCube(worldObj.getBlockId(posX3 + 1, posY3, posZ3)))
        		{ 
        			for(int c = rand.nextInt(30); c > 0; c--)
            		{
        				if(worldObj.getBlockId(posX3, posY3 - c, posZ3) == 0)
        		    	{
        					worldObj.setBlock(posX3, posY3 - c, posZ3, ModBlocks.thorns.blockID, 0, 3);
        		    	}
            		}
        		}
        	}
        }
        
        if (rand.nextInt(1) == 0)
        {
            int i2 = x + rand.nextInt(16) + 8;
            int k3 = rand.nextInt(128);
            int i5 = z + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, i2, k3, i5);
        }

        if (rand.nextInt(1) == 0)
        {
            int j2 = x + rand.nextInt(16) + 8;
            int l3 = rand.nextInt(128);
            int j5 = z + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, j2, l3, j5);
        }
        
        for(int c = 10; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == ModBlocks.umberstone.blockID)
        	{
        		(new WorldGenAmber()).generate(worldObj, rand, j2, l3, j5);
        	}
    	}
        
        //Glowstone
        for(int c = 10; c > 0; c--)
        {
            int j2 = x + rand.nextInt(16) + 8;
            int l3 = rand.nextInt(128);
            int j5 = z + rand.nextInt(16) + 8;
            (new WorldGenGlowStoneErebus1()).generate(worldObj, rand, j2, l3, j5);
        }
        
        for(int c = 10; c > 0; c--)
        {
            int j2 = x + rand.nextInt(16) + 8;
            int l3 = rand.nextInt(128);
            int j5 = z + rand.nextInt(16) + 8;
            (new WorldGenGlowStoneErebus2()).generate(worldObj, rand, j2, l3, j5);
        }
        
        //Giant Mushrooms
        for(int c = 15; c > 0; c--)
    	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == ModBlocks.umberstone.blockID)
        	{
        		(new WorldGenBigMushroomErebus(0)).generate(worldObj, rand, j2, l3, j5);
        	}
    	}  
        
        for(int c = 25; c > 0; c--)
    	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlockId(j2, l3, j5) == 0 && worldObj.getBlockId(j2, l3 - 1, j5) == ModBlocks.umberstone.blockID)
        	{
        		(new WorldGenBigMushroomErebus(1)).generate(worldObj, rand, j2, l3, j5);
        	}
    	}
        
        for (int var5 = 0; var5 < 20; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.umberOreBlock.blockID, 0, 10)).generate(worldObj, rand, var6, var7, var8);
        }
        
        for (int var5 = 0; var5 < 20; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.umberOreBlock.blockID, 1, 8)).generate(worldObj, rand, var6, var7, var8);
        }
        
        for (int var5 = 0; var5 < 4; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.umberOreBlock.blockID, 2, 8)).generate(worldObj, rand, var6, var7, var8);
        }
        
        for (int var5 = 0; var5 < 1; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.umberOreBlock.blockID, 3, 6)).generate(worldObj, rand, var6, var7, var8);
        }    
        
        for (int var5 = 0; var5 < 1; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.umberOreBlock.blockID, 4, 1)).generate(worldObj, rand, var6, var7, var8);
        }  
        
        for (int var5 = 0; var5 < 1; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.umberOreBlock.blockID, 6, 4)).generate(worldObj, rand, var6, var7, var8);
        }  
        
        for (int var5 = 0; var5 < 20; ++var5)
        {
            int var6 = x + rand.nextInt(16);
            int var7 = rand.nextInt(128);
            int var8 = z + rand.nextInt(16);
            (new WorldGenErebusMinable(ModBlocks.oreFossil.blockID, 3)).generate(worldObj, rand, var6, var7, var8);
        }
	}
}