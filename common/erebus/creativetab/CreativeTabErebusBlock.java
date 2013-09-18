package erebus.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

/**
 * @author ProPercivalalb
 */
public class CreativeTabErebusBlock extends CreativeTabs
{
    public CreativeTabErebusBlock(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        return new ItemStack(ModBlocks.umberstone, 1, 0);
    }
}
