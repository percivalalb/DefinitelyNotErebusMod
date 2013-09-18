package erebus.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

/**
 * @author ProPercivalalb
 */
public class CreativeTabErebusGear extends CreativeTabs
{
    public CreativeTabErebusGear(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        return new ItemStack(ModItems.jadePaxel, 1, 0);
    }
}
