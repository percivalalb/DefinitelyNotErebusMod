package erebus.World.Gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigLake extends WorldGenerator
{
    private int blockIndex;
    private int groundIndex;
    private int patchIndex;
    private double size;

    public WorldGenBigLake(int filler, int ground, int patchIndex, double size)
    {
        this.blockIndex = filler;
        this.groundIndex = ground;
        this.patchIndex = patchIndex;
        this.size = size;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	for(int x = 0; x < (int)size * 4; x+=4) {
    		for(int z = 0; z < (int)size * 4; z+=4) {
    			for(int y = 0; y < (int)size * 2; y+=4) {
    				int posX = par3 + x + par2Random.nextInt(4) - par2Random.nextInt(4);
    				int posY = par4 + y;
    				int posZ = par5 + z + par2Random.nextInt(4) - par2Random.nextInt(4);
    				(new WorldGenPonds(blockIndex, groundIndex, 1.5D)).generate(par1World, par1World.rand, posX, posY, posZ);
    				for(int x2 = -2; x2 < 3; x2++)
    		    		for(int z2 = -2; z2 < 3; z2++)
    		    			for(int y2 = -2; y2 < 3; y2++)
    				(new WorldGenErebusMinable(patchIndex, par2Random.nextInt(16) + 8, groundIndex)).generate(par1World, par1World.rand, posX + x2, posY + y2, posZ + z2);
    			}
        	}
    	}
    	
    	return true;
    }
    
    public double getDistance(double x, double y, double z, double x2, double y2, double z2) {
    	return Math.pow((x - x2), 2D) + Math.pow((y - y2), 2D) + Math.pow((z - z2), 2D);
    }
}
