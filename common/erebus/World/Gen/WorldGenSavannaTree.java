package erebus.World.Gen;

import java.util.Random;

import erebus.ErebusMod;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSavannaTree extends WorldGenerator
{
	int height = 5;
	public WorldGenSavannaTree(int height)
	{
		this.height = height;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		for(int i = 0; i < this.height + 6; i++)
		{
			if(var1.getBlockId(var3, var4 + i, var5) != 0)
			{
				return false;
			}
		}
		for(int i = 0; i < this.height + 6; i++)
		{
			var1.setBlock(var3, var4 + i, var5, ErebusMod.woodAcacia.blockID);
		}
		int size = var1.rand.nextInt(this.height + 1);
		WorldGenSavannaLeaves worldGen = new WorldGenSavannaLeaves(4+size, 3+size);
		worldGen.generate(var1, var1.rand, var3, var4 + this.height + 3, var5);
		return true;
	}
}
