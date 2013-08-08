package erebus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

/**
 * @author ProPercivalalb
 */
public class CreativeTabErebus extends CreativeTabs
{
    public CreativeTabErebus(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {
        return ErebusMod.beetleLarvaRaw.itemID;
    }
}
