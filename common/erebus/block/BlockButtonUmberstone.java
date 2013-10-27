package erebus.block;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.util.Icon;

public class BlockButtonUmberstone extends BlockButtonStone{
	public BlockButtonUmberstone(int id){
		super(id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2){
		return ModBlocks.umberstone.getBlockTextureFromSide(1);
	}
}
