package erebus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMetalIngots extends Item {

	@SideOnly(Side.CLIENT)
	private Icon[] icons = new Icon[5];
	
	public ItemMetalIngots(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.metalIngot" + stack.getItemDamage();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		return icons[i];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for(int i = 0; i < icons.length; i++)
			list.add(new ItemStack(itemID, 1, i));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:metalIngot" + i);
	}
}
