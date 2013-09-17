package erebus.world.feature;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSavannaRock extends WorldGenerator{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		for(int xx=x-3; xx<=x+3; xx++){
			for(int zz=z-3; zz<=z+3; zz++){
				if (world.getBlockId(xx,y-1,zz)!=Block.grass.blockID)return false;
			}
		}
		
		float radX,radZ;
		
		if (rand.nextInt(2)==0){
			radX=rand.nextFloat()*0.8F+1.6F;
			radZ=rand.nextFloat()*0.2F+1.1F;
		}
		else{
			radX=rand.nextFloat()*0.2F+1.1F;
			radZ=rand.nextFloat()*0.8F+1.6F;
		}
		
		return true;
	}
}
