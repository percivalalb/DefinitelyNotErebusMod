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
	protected ModelGlowWorm model;
	private static ResourceLocation Texture;
	public RenderGlowWorm(ModelGlowWorm par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
		model =(ModelGlowWorm)mainModel;

		setRenderPassModel(new ModelGlowWorm());
	}

	public void renderGlowWorm(EntityGlowWorm par1EntityGlowWorm, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityGlowWorm, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderGlowWorm((EntityGlowWorm)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderGlowWorm((EntityGlowWorm)par1Entity, par2, par4, par6, par8, par9);
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
		EntityGlowWorm glowworm = (EntityGlowWorm) entity;
		if (glowworm.isGlowing())
			Texture = new ResourceLocation("erebus:textures/mob/ModelGlowWormGlow.png");
		if (!glowworm.isGlowing())
			Texture = new ResourceLocation("erebus:textures/mob/ModelGlowWorm.png");
		return Texture;
	}
}
