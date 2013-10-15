package erebus.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.client.model.armor.ModelArmorGlider;

public class ItemArmorGlider extends ItemArmor {

	public ItemArmorGlider(int id, int armorType) {
		super(id, ErebusMod.armorEXOSKELETON, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "erebus:textures/armor/ModelArmorGlider.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int slot) {
		ModelBiped model = new ModelArmorGlider();
		model.bipedHead.showModel = false;
		model.bipedHeadwear.showModel = false;
		model.bipedBody.showModel = false;
		model.bipedRightArm.showModel = false;
		model.bipedLeftArm.showModel = false;
		model.bipedRightLeg.showModel = false;
		model.bipedLeftLeg.showModel = false;
		return model;
	}
}