package erebus.world.gen;

import java.util.Random;

import erebus.ErebusMod;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSavannaLeaves extends WorldGenerator
{
	public int width;
	public int height;
	public WorldGenSavannaLeaves(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
    public boolean generate(World par1World, Random par2Random, int par3, int par6, int par5)
    {
		for(int h = this.height; h > 0; h--)
		{
			for(int w = -(this.width - h); w < (this.width - h) + 1; w++)
			{
				for(int w2 = -(this.width - h); w2 < (this.width - h) + 1; w2++)
	    		{
					if(par1World.getBlockId(par3 + w, par6 + h, par5 + w2) == 0)par1World.setBlock(par3 + w, par6 + h, par5 + w2, ErebusMod.leavesAcacia.blockID);
	    		}
			}
		}
		
		return true;
    }
}
