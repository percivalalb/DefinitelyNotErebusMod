package erebus.World.Gen;

import java.util.Random;

import erebus.ErebusMod;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenQuickSand extends WorldGenerator
{
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        while (par1World.isAirBlock(par3, par4, par5) && par4 > 2)
        {
            --par4;
        }

        int var6 = par1World.getBlockId(par3, par4, par5);

        if (var6 != Block.grass.blockID)
        {
            return false;
        }
        else
        {
        	par1World.setBlock(par3, par4, par5, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3, par4 - 1, par5, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3, par4 - 2, par5, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3, par4, par5 - 1, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3, par4 - 1, par5 - 1, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3, par4, par5 + 1, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3, par4 - 1, par5 + 1, ErebusMod.quickSand.blockID);

        	par1World.setBlock(par3 - 1, par4, par5, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3 - 1, par4 - 1, par5, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3 + 1, par4, par5, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3 + 1, par4 - 1, par5, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3 - 2, par4, par5, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3 - 1, par4, par5 - 1, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3 + 2, par4, par5, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3 + 1, par4, par5 + 1, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3, par4, par5 - 2, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3 - 1, par4, par5 + 1, ErebusMod.quickSand.blockID);
        	
        	par1World.setBlock(par3, par4, par5 + 2, ErebusMod.quickSand.blockID);
        	par1World.setBlock(par3 + 1, par4, par5 - 1, ErebusMod.quickSand.blockID);
        	
        	//Top Layer
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 1, par4 - 2, par5 + 0, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 0, par4 - 2, par5 + 1, ErebusMod.quickSand.blockID);
        	}        	
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 0, par4 - 2, par5 - 1, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 1, par4 - 2, par5 + 0, ErebusMod.quickSand.blockID);
        	}
        	  	
        	
        	//Middle Layer
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 2, par4 - 1, par5 + 0, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 1, par4 - 1, par5 + 1, ErebusMod.quickSand.blockID);
        	}
        	
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 0, par4 - 1, par5 + 2, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 1, par4 - 1, par5 + 1, ErebusMod.quickSand.blockID);
        	}
        	
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 2, par4 - 1, par5 - 0, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 1, par4 - 1, par5 - 1, ErebusMod.quickSand.blockID);
        	}
        	
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 0, par4 - 1, par5 - 2, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 1, par4 - 1, par5 - 1, ErebusMod.quickSand.blockID);
        	}
        	
        	
        	//Bottom Layer
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 3, par4, par5 + 0, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 2, par4, par5 + 1, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 1, par4, par5 + 2, ErebusMod.quickSand.blockID);
        	}
        	

        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 3, par4, par5 + 0, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 2, par4, par5 + 1, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 1, par4, par5 + 2, ErebusMod.quickSand.blockID);
        	}
        	

        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 0, par4, par5 - 3, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 2, par4, par5 - 1, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 + 1, par4, par5 - 2, ErebusMod.quickSand.blockID);
        	}
        	

        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 0, par4, par5 + 3, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 2, par4, par5 - 1, ErebusMod.quickSand.blockID);
        	}
        	
        	if (par2Random.nextInt(2) == 0)
        	{
        		par1World.setBlock(par3 - 1, par4, par5 - 2, ErebusMod.quickSand.blockID);
        	}
        	
            return true;
        }
    }
}
