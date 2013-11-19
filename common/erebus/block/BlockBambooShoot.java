package erebus.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockBambooShoot extends BlockFlower implements IPlantable {
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
			/*if (world.getBlockLightValue(x,y+1,z)>=9&&rand.nextInt(7)==0){
				this.markOrGrowMarked(world,x,y,z,rand);
			}*/
		}
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){
		Block soil=blocksList[world.getBlockId(x,y-1,z)]; // TODO complete this
		return (world.getFullBlockLightValue(x,y,z)>=8||world.canBlockSeeTheSky(x,y,z))&&(soil!=null&&soil.canSustainPlant(world,x,y-1,z,ForgeDirection.UP,this));
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z){
		return EnumPlantType.Plains;
	}
	
	public static byte calculateBambooHappiness(World world, int x, int y, int z) {
		return 0;
	}
}
