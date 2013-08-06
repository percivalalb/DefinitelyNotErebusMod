package erebus.core.helper;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ErebusWorldHelper
{
  public static void setBlockIfEmpty(int par1, int par2, int par3, int par4, int par5, int par6, World world)
	{
		if(world.isAirBlock(par1, par2, par3))
		{
			world.setBlock(par1, par2, par3, par4, par5, par6);
		}
	}
	
	public static void setBlockUnderSky(int par1, int par2, int par3, int par4, int par5, int par6, World world)
	{
		if(world.canBlockSeeTheSky(par1, par2, par3))
		{
			world.setBlock(par1, par2, par3, par4, par5, par6);
		}
	}
}
