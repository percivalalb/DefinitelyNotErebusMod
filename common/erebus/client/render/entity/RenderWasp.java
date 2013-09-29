package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelWasp;
import erebus.entity.EntityWasp;

public class RenderWasp extends RenderLiving {

	protected ModelWasp model;

	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelWasp.png");

	public RenderWasp(ModelWasp par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelWasp) mainModel;

	}

	public void renderWasp(EntityWasp par1EntityWasp, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityWasp, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderWasp((EntityWasp) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderWasp((EntityWasp) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleWasp((EntityWasp) entityliving, f);
	}

	protected void scaleWasp(EntityWasp entityWasp, float f) {
		float f1 = 1.5F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
