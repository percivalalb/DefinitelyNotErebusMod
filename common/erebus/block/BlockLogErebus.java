package erebus.block;
import java.util.List;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;

public class BlockLogErebus extends BlockLog{
	public static final String[] logTypes = new String[]{
		"acacia", "eucalyptus", "mahogany"
	};
	
	public static final byte dataAcacia = 0, dataEucalyptus = 1, dataMahogany = 2;
	
	@SideOnly(Side.CLIENT)
	private Icon[] iconSide,iconTop;
	
	public BlockLogErebus(int id){
		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int meta){
		return iconSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int meta){
		return iconTop[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list){
		list.add(new ItemStack(id,1,0));
		list.add(new ItemStack(id,1,1));
		list.add(new ItemStack(id,1,2));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		iconSide=new Icon[logTypes.length];
		iconTop=new Icon[logTypes.length];

		for(int a=0; a<logTypes.length; a++){
			iconSide[a]=iconRegister.registerIcon("erebus:tree_"+logTypes[a]);
			iconTop[a]=iconRegister.registerIcon("erebus:tree_"+logTypes[a]+"_top");
		}
	}
}
