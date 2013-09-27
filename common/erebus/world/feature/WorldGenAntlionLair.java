package erebus.world.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenAntlionLair extends WorldGenerator{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z){
		for(int a=0; a<15; a++){
			if (world.isAirBlock(x,y,z)&&world.getBlockId(x,y-1,z)==Block.sand.blockID){
				for(int xx=x-3; xx<=x+3; xx++){
					for(int zz=z-3; zz<=z+3; zz++){
						if (world.getBlockId(xx,y-1,zz)!=Block.sand.blockID){
							return false;
						}
					}
				}
				break;
			}

			if (--y<=6) return false;
		}
		
		for(int xx=x-3; xx<=x+3; xx++){
			for(int zz=z-3; zz<=z+3; zz++){
				for(int yy=y-1; yy>=y-6; yy--){
					world.setBlock(xx,yy,zz,yy==y?ModBlocks.ghostSand.blockID:0);
				}
			}
		}
		
		// TODO chests, the antlion

		return true;
	}
}
