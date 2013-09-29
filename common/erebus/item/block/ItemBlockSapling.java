package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockSapling extends ItemBlockGeneric {

	private final Block targetBlock;

	public ItemBlockSapling(int par1) {
		super(par1, "erebusSapling");
		targetBlock = Block.blocksList[par1 + 256];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int par1) {
		return targetBlock.getIcon(2, par1);
	}
}
