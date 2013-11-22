package erebus.client.render.item;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelScorpionPincer;

@SideOnly(Side.CLIENT)
public class ScorpionPincerItemRenderer implements IItemRenderer {
	private final ModelScorpionPincer model;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/mob/ModelScorpion.png");

	public ScorpionPincerItemRenderer() {
		model = new ModelScorpionPincer();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return helper != ItemRendererHelper.BLOCK_3D;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
			case ENTITY:
				renderPincer(0.0F, 1.0F, 0.0F, 2.5D);
				break;
			case EQUIPPED:
				renderEquipped(0.35F, -2.0F, 0.8F, 2.5D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderPincerFirstPerson(0.0F, -2.0F, 0.0F, 2.5D);
				break;
			case INVENTORY:
				renderPincerInventory(0F, -2F, 0F, 2.0D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderPincer(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x - 0.2F, y - 1.0F, z);
			GL11.glRotatef(0F, 1F, 0, 0);
			GL11.glRotatef(0F, 0, 1F, 0);
			GL11.glRotatef(0F, 0, 0, 1F);
			GL11.glScaled(0.3F, 0.3F, 0.3F);
			model.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(0F, 1F, 0, 0);
			GL11.glRotatef(0F, 0, 1F, 0);
			GL11.glRotatef(0F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			model.render();
			GL11.glPopMatrix();
		}
	}

	private void renderPincerFirstPerson(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0F, 1F, 0, 0);
		GL11.glRotatef(0F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderPincerInventory(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0F, 1F, 0, 0);
		GL11.glRotatef(0F, 0, 1F, 0);
		GL11.glRotatef(0F, 0, 0, 1F);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}
}
