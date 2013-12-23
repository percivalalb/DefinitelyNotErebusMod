package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGlowWorm;
import erebus.entity.EntityGlowWorm;

@SideOnly(Side.CLIENT)
public class RenderGlowWorm extends RenderLiving
{
	private static final ResourceLocation texGlowing = new ResourceLocation("erebus:textures/mob/ModelGlowWormGlow.png"),
										  texNormal = new ResourceLocation("erebus:textures/mob/ModelGlowWorm.png");
	
	public RenderGlowWorm(ModelGlowWorm model, float shadowSize)
	{
		super(model, shadowSize);

		setRenderPassModel(new ModelGlowWorm());
	}

	public void renderGlowWorm(EntityGlowWorm entityGlowWorm, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(entityGlowWorm, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderGlowWorm((EntityGlowWorm)entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderGlowWorm((EntityGlowWorm)entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleGlowWorm((EntityGlowWorm) entityliving, f);
	}

	protected void rotateGlowWorm(EntityLivingBase entityliving)
	{
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleGlowWorm(EntityGlowWorm entityGlowWorm, float f) {
		float f1 = 0.75F;
		shadowSize = 0.0F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityGlowWorm)entity).isGlowing()?texGlowing:texNormal;
	}
}
