package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelCentipede;
import erebus.entity.EntityCentipede;

public class RenderCentipede extends RenderLiving {

	protected ModelCentipede model;
	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Centipede.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/ModelCentipede.png");
	private final ResourceLocation resource3 = new ResourceLocation("erebus:textures/mob/CentipedeBlack.png");

	public RenderCentipede(ModelCentipede par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelCentipede) mainModel;

	}

	public void renderCentipede(EntityCentipede par1EntityCentipede, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityCentipede, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderCentipede((EntityCentipede) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderCentipede((EntityCentipede) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleCentipede((EntityCentipede) entityliving, f);
	}

	protected void rotateCentipede(EntityLivingBase entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleCentipede(EntityCentipede entityCentipede, float f) {
		float f1 = 1.0F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityCentipede entityCentipede = (EntityCentipede) entity;
		switch (entityCentipede.skin) {
			case 0:
				return resource1;
			case 1:
				return resource2;
			case 2:
				return resource3;
		}
		return resource1;
	}
}
