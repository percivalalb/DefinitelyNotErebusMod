package erebus.Block;

import erebus.CommonErebusProxy;
import erebus.mod_Erebus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockPlanksErebus extends Block
{
    public BlockPlanksErebus(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(mod_Erebus.tabErebus);
    }
}
