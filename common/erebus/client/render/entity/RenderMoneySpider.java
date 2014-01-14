package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityMoneySpider;

@SideOnly(Side.CLIENT)
public class RenderMoneySpider extends RenderLiving {
	protected ModelScytodes model;
	private static ResourceLocation Texture;

	public RenderMoneySpider(ModelScytodes model, float shadowSize) {
		super(model, shadowSize);
		model = (ModelScytodes) mainModel;
		shadowSize *= 0.3F;
	}

	public void renderMoneySpider(EntityMoneySpider entityMoneySpider, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityMoneySpider, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMoneySpider((EntityMoneySpider) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMoneySpider((EntityMoneySpider) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float par2) {
		GL11.glScalef(0.3F, 0.3F, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityMoneySpider spider = (EntityMoneySpider) entity;
		if (spider.skin == 0)
			Texture = new ResourceLocation("erebus:textures/mob/ModelMoneySpider.png");
		if (spider.skin == 1)
			Texture = new ResourceLocation("erebus:textures/mob/ModelMoneySpider_euro.png");
		if (spider.skin == 2)
			Texture = new ResourceLocation("erebus:textures/mob/ModelMoneySpider_pound.png");
		return Texture;
	}
}
