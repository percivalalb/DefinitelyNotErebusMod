package erebus;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;


final class CreativeTabErebus extends CreativeTabs
{
    CreativeTabErebus(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return mod_Erebus.beetleLarvaRaw.itemID;
    }
}
