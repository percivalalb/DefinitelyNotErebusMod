package erebus.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockSlabPlanksErebus extends BlockHalfSlab{
	public static final String[] slabTypes = new String[] { "acacia", "eucalyptus", "mahogany", "baobab", "mossbark", "pink", "scorched", "asper" };

	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2, dataBaobab = 3, dataMossbark = 4, dataPink = 5, dataScorched = 6, dataAsper = 7;
	
	public BlockSlabPlanksErebus(int id, boolean isDouble){
		super(id,isDouble,Material.wood);
	}
	
	@Override
	public int idDropped(int meta, Random rand, int fortune){
		return ModBlocks.plankSlabs[0].blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int meta){
		return new ItemStack(ModBlocks.plankSlabs[0],isDoubleSlab?2:1,meta&7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z){
		return ModBlocks.plankSlabs[0].blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta){
		return ModBlocks.planksErebus.getIcon(side,meta&7);
	}

	@Override
	public String getFullSlabName(int meta){
		return super.getUnlocalizedName()+"."+slabTypes[meta&7];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list){
		if (isDoubleSlab) return;

		for(int a=0; a<slabTypes.length; a++) {
			list.add(new ItemStack(id,1,a));
		}
	}
}
