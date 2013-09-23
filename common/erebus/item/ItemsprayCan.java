package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemsprayCan extends  Item
{
	public ItemsprayCan(int id) 
	{
        super(id);
        this.maxStackSize = 9;
        this.setCreativeTab(CreativeTabs.tabTools);
	}
	protected String getSprayCanSound()
	{
		return "erebus:SprayCanSound";
	}

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
        {
            int i1 = par3World.getBlockId(par4, par5, par6);
            Block block = Block.blocksList[i1];
            int i2 = InsectRepellent.InsectRepellent.blockID;
            if (block != null && par3World.doesBlockHaveSolidTopSurface(par4, par5, par6) && i1 != i2)
            {
                par3World.setBlock(par4, par5 + 1, par6, i2);
                --par1ItemStack.stackSize;
             par3World.playSoundEffect(par4, par5 + 1, par6, getSprayCanSound(), 1.0F, 1.0F);
                return true;
            }
            else
            {
                return false;
            }
        }
      else
      {
        return false;
     }
    }
	
    @SideOnly(Side.CLIENT)
    
	 public Icon getIcon()
	 {
	 return this.itemIcon;
	 }
    public void updateIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon("erebus:sprayCan");
    }
    public boolean isFull3D()
    {
        return true;
    }
}
