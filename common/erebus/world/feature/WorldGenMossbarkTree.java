package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMossbarkTree extends WorldGenerator
{


    public WorldGenMossbarkTree()
    {
 
    }

    @Override
	public boolean generate(World world, Random random, int i, int j, int k)
    {
    	int l = random.nextInt(3) + 8;
        boolean flag = true;

        if (j >= 1 && j + l + 1 <= 256)
        {
            int i1;
            byte b0;
            int j1;
            int k1;

            for (i1 = j; i1 <= j + 1 + l; ++i1)
            {
               
                    b0 = 4;
                

                for (int l1 = i - b0; l1 <= i + b0 && flag; ++l1)
                {
                    for (j1 = k - b0; j1 <= k + b0 && flag; ++j1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            k1 = world.getBlockId(l1, i1, j1);

                            boolean isAir = world.isAirBlock(l1, i1, j1);

                            if (!isAir &&
                                k1 != Block.grass.blockID &&
                                k1 != Block.dirt.blockID )
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
    	//-------------
    	if(world.getBlockId(i,j-1,k) != Block.dirt.blockID && world.getBlockId(i,j-1,k) != Block.grass.blockID)
    		return false;
    	
    	int stumpHeight = 4 + random.nextInt(2);
    	generateStump(world,random,i,j,k, stumpHeight);
    	
    	int middleBranchHeight = 1 + random.nextInt(2);
    	generateMiddleBranch(world,random,i+2,j+stumpHeight,k, middleBranchHeight);
    	
    	 middleBranchHeight = 1 + random.nextInt(2);
     	generateMiddleBranch(world,random,i-2,j+stumpHeight,k, middleBranchHeight);

     	 middleBranchHeight = 1 + random.nextInt(2);
     	generateMiddleBranch(world,random,i,j+stumpHeight,k+2, middleBranchHeight);
     	
     	 middleBranchHeight = 1 + random.nextInt(2);
     	generateMiddleBranch(world,random,i,j+stumpHeight,k-2, middleBranchHeight);
    	
    	int outsideBranchHeight = 2 + random.nextInt(2);
    	generateOutsideBranch(world,random,i,j+stumpHeight+ middleBranchHeight,k-3, outsideBranchHeight);
    	
    	outsideBranchHeight = 2 + random.nextInt(2);
    	generateOutsideBranch(world,random,i,j+stumpHeight+ middleBranchHeight,k+3, outsideBranchHeight);

    	outsideBranchHeight = 2 + random.nextInt(2);
    	generateOutsideBranch(world,random,i+3,j+stumpHeight+ middleBranchHeight,k, outsideBranchHeight);
    	
    	outsideBranchHeight = 2 + random.nextInt(2);
    	generateOutsideBranch(world,random,i-3,j+stumpHeight+ middleBranchHeight,k, outsideBranchHeight);
    	
    	return true;
        }
        return false;
    }
    
    public void generateStump(World world, Random random, int i, int j, int k, int height)
    {
    	for(int j1 = 0; j1 < height; ++j1){
    		world.setBlock(i,j+j1,k,Block.wood.blockID);
    		if(j1 == height-1){
    			world.setBlock(i,j+j1,k+1,Block.wood.blockID,8,3);
    			world.setBlock(i,j+j1,k-1,Block.wood.blockID,8,3);
    			world.setBlock(i+1,j+j1,k,Block.wood.blockID,4,3);
    			world.setBlock(i-1,j+j1,k,Block.wood.blockID,4,3);
    			break;
    		}
    	}
    }
    
    public void generateMiddleBranch(World world, Random random, int i, int j, int k, int height)
    {
    	for(int j1 = 0; j1 < height; ++j1){
    		world.setBlock(i,j+j1,k,Block.wood.blockID);
    		for(int k1 = -1; k1 <= 1; ++k1){
    			for(int i1 = -1; i1 <= 1; ++i1){
    				for(int j2 = -1; j2 <= 1; ++j2){
    					if(Math.abs(i1) + Math.abs(k1) + Math.abs(j2) < 2 && world.getBlockId(i+i1,j+j1+j2,k1+k) == 0){
    						world.setBlock(i+i1,j+j1+j2,k1+k,Block.leaves.blockID);
    						if(world.getBlockId(i+i1,j+j1+j2-1,k1+k) == 0 && random.nextInt(2) == 0)
    							world.setBlock(i+i1,j+j1+j2-1,k1+k,Block.leaves.blockID);
    					}
    				}
        		}
    		}
    		
    	}
    	
    }
    
    public void generateOutsideBranch(World world, Random random, int i, int j, int k, int height)
    {
    	for(int j1 = 0; j1 < height; ++j1){
    		world.setBlock(i,j+j1,k,Block.wood.blockID);
    		for(int k1 = -1; k1 <= 1; ++k1){
    			for(int i1 = -1; i1 <= 1; ++i1){
    				for(int j2 = -1; j2 <= 1; ++j2){
    					if(Math.abs(i1) + Math.abs(k1) + Math.abs(j2) < 2 && world.getBlockId(i+i1,j+j1+j2,k1+k) == 0){
    						world.setBlock(i+i1,j+j1+j2,k1+k,Block.leaves.blockID);
    						if(world.getBlockId(i+i1,j+j1+j2-1,k1+k) == 0 && random.nextInt(2) == 0)
    							world.setBlock(i+i1,j+j1+j2-1,k1+k,Block.leaves.blockID);
    						if(world.getBlockId(i+i1,j+j1+j2+1,k1+k) == 0 && random.nextInt(2) == 0)
    							world.setBlock(i+i1,j+j1+j2+1,k1+k,Block.leaves.blockID);
    					}
    				}
        		}
    		}
    		
    	}
    }
    
}
