package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityScytodes;

public class RenderScytodes extends RenderLiving
{
	protected ModelScytodes model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelScytodes.png");


	public RenderScytodes(ModelScytodes model, float shadowSize) {
		super(model, shadowSize);
		model =(ModelScytodes)mainModel;
	}

	public void renderScytodes(EntityScytodes entityScytodes, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityScytodes, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderScytodes((EntityScytodes) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderScytodes((EntityScytodes) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleScytodes((EntityScytodes) entityliving, f);
	}

	protected void scaleScytodes(EntityScytodes entityScytodes, float f) {
		float f1 = 1.0F;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}

