package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemJadeArmor extends ItemArmor {

	public ItemJadeArmor(int id, int armorType) {
		super(id, ErebusMod.armorJADE, 2, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.jadeHelmet.itemID || stack.itemID == ModItems.jadeBody.itemID || stack.itemID == ModItems.jadeBoots.itemID)
			return "erebus:textures/armor/jade_1.png";
		else
			return "erebus:textures/armor/jade_2.png";
	}
}