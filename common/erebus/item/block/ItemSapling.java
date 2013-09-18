package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author ProPercivalalb
 **/
public class ItemSapling extends ItemBlockGeneric {
    
	private Block targetBlock;

    public ItemSapling(int par1) {
        super(par1, "erebusSapling");
        this.targetBlock = Block.blocksList[par1 + 256];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int par1) {
        return this.targetBlock.getIcon(2, par1);
    }
}
