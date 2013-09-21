package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelLocust;
import erebus.entity.EntityLocust;

public class RenderLocust extends RenderLiving {

	protected ModelLocust model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelLocust.png");

	public RenderLocust(ModelLocust par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = ((ModelLocust) mainModel);
	}

	public void renderLocust(EntityLocust par1EntityLocust, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityLocust, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderLocust((EntityLocust) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderLocust((EntityLocust) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleLocust((EntityLocust) entityliving, f);
	}

	protected void scaleLocust(EntityLocust entityLocust, float f) {
		float f1 = 1.5F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
