package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ModItems;

/**
 * @author Everyone involved
 */

public class ItemCompoundGoggles extends ItemArmor {

	public ItemCompoundGoggles(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
		setCreativeTab(null);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.compoundGoggles.itemID)
			return "erebus:textures/armor/goggles_1.png";
		else
			return null;
	}
}