package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPyramid extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par6, int par5)
    {
		for(int h = 10; h > 0; h--)
		{
			for(int w = -(14 - h); w < (14 - h); w++)
			{
				for(int w2 = -(14 - h); w2 < (14 - h); w2++)
	    		{
					if(w < 0 && w <= -(14 - h))
					{
						par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stairsSandStone.blockID, 0, 3);
					}
					if(w > 0 && w >= (14 - h - 1))
					{
						par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stairsSandStone.blockID, 1, 3);
					}
					if(w2 < 0 && w2 <= -(14 - h))
					{
						par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stairsSandStone.blockID, 2, 3);
					}
					if(w2 > 0 && w2 >= (14 - h - 1))
					{
						par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stairsSandStone.blockID, 3, 3);
					}
					if(w < 0 && w <= -(14 - h))
					{
						if(w2 < 0 && w2 <= -(14 - h))
						{
							par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stoneSingleSlab.blockID);
						}
					}
					if(w > 0 && w >= (14 - h - 1))
					{
						if(w2 > 0 && w2 >= (14 - h - 1))
	    				{
	    					par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stoneSingleSlab.blockID);
	    				}
					}
					if(w < 0 && w <= -(14 - h))
					{
						if(w2 > 0 && w2 >= (14 - h - 1))
	    				{
	    					par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stoneSingleSlab.blockID);
	    				}
					}
					if(w > 0 && w >= (14 - h - 1))
					{
						if(w2 < 0 && w2 <= -(14 - h))
	    				{
	    					par1World.setBlock(par3 + w, par6 + h + 1, par5 + w2, Block.stoneSingleSlab.blockID);
	    				}
					}
					
					par1World.setBlock(par3 + w, par6 + h, par5 + w2, Block.sandStone.blockID);
	    		}
			}
		}
		
		for(int w = -3; w < 3; w++)
		{
			for(int w2 = -3; w2 < 3; w2++)
			{
				par1World.setBlock(par3 + w, par6 + 11, par5 + w2, Block.sandStone.blockID);
			}
		}
		
		return true;
    }
}
