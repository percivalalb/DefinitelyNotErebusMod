package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import erebus.client.model.entity.ModelUmberGolem;
import erebus.entity.EntityUmberGolem;


public class RenderUmberGolem extends RenderLiving
{
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/UmberGolem.png");

	public RenderUmberGolem(ModelUmberGolem model, float shadowSize)
	{
		super(model, shadowSize);

	}
	public void renderUmberGolem(EntityUmberGolem entityUmberGolem, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(entityUmberGolem, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderUmberGolem((EntityUmberGolem)entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderUmberGolem((EntityUmberGolem)entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleGolem((EntityUmberGolem) entityliving, f);
	}

	protected void scaleGolem(EntityUmberGolem entityUmberGolem, float f) {
		float f1 = 0.75F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}

}
