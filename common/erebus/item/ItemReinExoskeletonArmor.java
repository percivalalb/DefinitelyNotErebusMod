package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemReinExoskeletonArmor extends ItemArmor {

	public ItemReinExoskeletonArmor(int id, int armorType) {
		super(id, ErebusMod.armorREINEXOSKELETON, 2, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.reinExoskeletonHelmet.itemID || stack.itemID == ModItems.reinExoskeletonBody.itemID || stack.itemID == ModItems.reinExoskeletonBoots.itemID)
			return "erebus:textures/armor/reinforcedExoskeleton_1.png";
		else
			return "erebus:textures/armor/reinforcedExoskeleton_2.png";
	}
}