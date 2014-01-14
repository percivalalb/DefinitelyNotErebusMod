package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelJumpingSpider;
import erebus.entity.EntityJumpingSpider;

public class RenderJumpingSpider extends RenderLiving
{
	protected ModelJumpingSpider model;
	private static ResourceLocation Texture;

	public RenderJumpingSpider(ModelJumpingSpider model, float shadowSize) {
		super(model, shadowSize);
		model = (ModelJumpingSpider) mainModel;
	}

	public void renderJumpingSpider(EntityJumpingSpider entityJumpingSpider, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityJumpingSpider, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderJumpingSpider((EntityJumpingSpider) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderJumpingSpider((EntityJumpingSpider) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleJumpingSpider((EntityJumpingSpider) entityliving, f);
	}

	protected void scaleJumpingSpider(EntityJumpingSpider entityJumpingSpider, float f) {
		float f1 = 0.7F;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityJumpingSpider spider = (EntityJumpingSpider) entity;
		if (spider.skin == 0)
			Texture = new ResourceLocation("erebus:textures/mob/ModelJumpingSpider.png");
		if (spider.skin == 1)
			Texture = new ResourceLocation("erebus:textures/mob/ModelJumpingSpider_green.png");
		if (spider.skin == 2)
			Texture = new ResourceLocation("erebus:textures/mob/ModelJumpingSpider_red.png");
		return Texture;
	}
}

