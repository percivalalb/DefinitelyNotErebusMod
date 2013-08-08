package erebus.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRock extends WorldGenerator
{
    private int blockIndex;
    private double size;

    public WorldGenRock(int par1, double size)
    {
        this.blockIndex = par1;
        this.size = size;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	for(int y = 0; y < (int)size + 1; y++)
    	{
    		if(par1World.getBlockId(par3, par4 - y, par5) == 0)
    		{
    			return false;
    		}
    	}
    	for(int x = -8; x < 8; x++)
    	{
    		for(int y = -8; y < 8; y++)
        	{
    			for(int z = -8; z < 8; z++)
    	    	{
    	    		if(getDistance(par3, par3 + x, par4, par4 + y, par5, par5 + z) < this.size)
    	    		{
    	    			par1World.setBlock(par3 + x, par4 + y, par5 + z, this.blockIndex);
    	    		}
    	    	}
        	}
    	}
    	return true;
    }
    
    public double getDistance(int x1, int x2, int y1, int y2, int z1, int z2)
    {
    	return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }
}
