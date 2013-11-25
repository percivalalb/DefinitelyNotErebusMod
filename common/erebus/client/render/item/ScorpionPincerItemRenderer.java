package erebus.client.render.item;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelScorpionPincer;
import erebus.entity.EntityScorpionPincer;

@SideOnly(Side.CLIENT)
public class ScorpionPincerItemRenderer extends Render implements IItemRenderer {
	private final ModelScorpionPincer model;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/item/ModelScorpionPincer.png");

	public ScorpionPincerItemRenderer(ModelScorpionPincer par1ModelBase, float par2) {
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
				renderEquipped(0.6F, -0.5F, 0.5F, 2.5D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderPincerFirstPerson(0.0F, 0.5F, 0.5F, 2.5D);
				break;
			case INVENTORY:
				renderPincerInventory(0F, -0.5F, 0F, 2.0D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();

		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderPincer(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x - 0.4F, y - 1.0F, z + 0.1F);
			GL11.glRotatef(0F, 1F, 0, 0);
			GL11.glRotatef(0F, 0, 1F, 0);
			GL11.glRotatef(-60F, 0, 0, 1F);
			GL11.glScaled(1.8F, 1.8F, 1.8F);
			model.render(0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(0F, 1F, 0, 0);
			GL11.glRotatef(0F, 0, 1F, 0);
			GL11.glRotatef(0F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			model.render(0.0625F);
			GL11.glPopMatrix();
		}
	}

	private void renderPincerFirstPerson(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(135.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(225.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
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
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderScorpionPincer((EntityScorpionPincer) par1Entity, par2, par4, par6, par8, par9);
	}

	public void renderScorpionPincer(EntityScorpionPincer par1EntityScorpionPincer, double par2, double par4, double par6, float par8, float par9) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glRotatef(par1EntityScorpionPincer.prevRotationYaw + (par1EntityScorpionPincer.rotationYaw - par1EntityScorpionPincer.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(par1EntityScorpionPincer.prevRotationPitch + (par1EntityScorpionPincer.rotationPitch - par1EntityScorpionPincer.prevRotationPitch) * par9 - 90.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(1.5F, 1.5F, 1.5F);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
