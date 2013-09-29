package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelVelvetWorm;
import erebus.entity.EntityVelvetWorm;

public class RenderVelvetWorm extends RenderLiving {

	protected ModelVelvetWorm model;
	private float scale = 1.0F;

	public RenderVelvetWorm(ModelVelvetWorm par1ModelBase, float par2, float par3) {
		super(par1ModelBase, par2 * par3);
		model = (ModelVelvetWorm) mainModel;
		scale = par3;
	}

	public void renderEntityVelvetWorm(EntityVelvetWorm par1EntityEntityVelvetWorm, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityEntityVelvetWorm, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderEntityVelvetWorm((EntityVelvetWorm) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderEntityVelvetWorm((EntityVelvetWorm) par1Entity, par2, par4, par6, par8, par9);
	}

	protected void preRenderScale(EntityVelvetWorm par1Entity, float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
		preRenderScale((EntityVelvetWorm) par1EntityLiving, par2);
	}

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Velvet worm.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/Velvet worm2.png");

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityVelvetWorm worm = (EntityVelvetWorm) entity;
		if (worm.skin <= 10)
			return resource2;
		else
			return resource1;
	}
}
