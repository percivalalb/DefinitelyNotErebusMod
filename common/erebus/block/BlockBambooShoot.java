package erebus.block;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockBambooShoot extends BlockFlower implements IPlantable {
	public static byte calculateBambooHappiness(World world, int x, int y, int z, int blockID){
		byte happiness=0;
		
		// WATER
		
		
		
		// CLIMATE
		
		BiomeGenBase biome=world.getBiomeGenForCoords(x,z);
		boolean canSeeSun=world.canBlockSeeTheSky(x,y,z);
		
		if (biome==BiomeGenBase.hell)happiness-=8;
		else if (biome.temperature>=2F)happiness-=6;
		else if (biome.temperature>=1.4F && biome.temperature<2F)happiness+=canSeeSun?-2:5;
		else if (biome.temperature>=0.9F && biome.temperature<1.4F)happiness+=canSeeSun?2:6;
		else if (biome.temperature>=0.7F && biome.temperature<0.9F)happiness+=canSeeSun?4:1;
		else if (biome.temperature>=0.3F && biome.temperature<0.7F)happiness+=canSeeSun?4:-2;
		else if (biome.temperature>=0.1F && biome.temperature<0.3F)happiness-=canSeeSun?8:4;
		else if (biome.temperature<0.1F)happiness-=canSeeSun?16:10;
		
		happiness+=biome.canSpawnLightningBolt()?4:-2;
		
		// SOIL
		
		for(int yy=y,id; yy>0; yy--){
			if ((id=world.getBlockId(x,--yy,z))!=blockID){
				Float soilValue=soilValues.get(id);
				if (soilValue!=null)happiness=(byte)Math.floor((double)happiness*soilValue);
				break;
			}
		}
		
		return happiness;
	}
	
	private static Map<Integer,Float> soilValues = new HashMap<Integer,Float>();
	static{
		soilValues.put(Block.dirt.blockID, 1F);
		soilValues.put(Block.grass.blockID, 1F);
		soilValues.put(Block.blockClay.blockID, 0.75F);
		soilValues.put(Block.sand.blockID, 0.4F);
		// add mulch 1.5 and claysoil 1.25
	}
	
	public BlockBambooShoot(int id){
		super(id,Material.wood);
		setTickRandomly(true);
        float f = 0.2F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		if (!world.isRemote){
			super.updateTick(world,x,y,z,rand);
			// TODO do magic
			/*
			 * if (world.getBlockLightValue(x,y+1,z)>=9&&rand.nextInt(7)==0){
			 * this.markOrGrowMarked(world,x,y,z,rand); }
			 */
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z){
		Block soil=blocksList[world.getBlockId(x,y-1,z)];
		return (soil!=null&&soilValues.containsKey(soil.blockID));
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z){
		return EnumPlantType.Plains;
	}

	
}
