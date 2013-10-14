package erebus.client.render.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import cpw.mods.fml.client.FMLClientHandler;
import erebus.client.model.armor.ModelArmorGlider;

public class RenderArmorGlider extends ModelBiped implements IItemRenderer {
	private ModelArmorGlider ModelArmorGlider;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/armor/ModelArmorGlider.png");

	public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot) {
		return ModelArmorGlider;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
	}

}

