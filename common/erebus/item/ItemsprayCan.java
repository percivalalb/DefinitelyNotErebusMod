package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModBlocks;

public class ItemSprayCan extends  Item
{
	public ItemSprayCan(int id)
	{
		super(id);
		maxStackSize = 9;
		setCreativeTab(ErebusMod.tabErebusGear);
	}
	protected String getSprayCanSound()
	{
		return "erebus:SprayCanSound";
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 != 1)
			return false;
		else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
		{
			int i1 = par3World.getBlockId(par4, par5, par6);
			Block block = Block.blocksList[i1];
			int i2 = ModBlocks.insectRepellentID;
			if (block != null && par3World.doesBlockHaveSolidTopSurface(par4, par5, par6) && i1 != i2)
			{
				par3World.setBlock(par4, par5 + 1, par6, i2);
				--par1ItemStack.stackSize;
				par3World.playSoundEffect(par4, par5 + 1, par6, getSprayCanSound(), 1.0F, 1.0F);
				return true;
			} else
				return false;
		} else
			return false;
	}
}
