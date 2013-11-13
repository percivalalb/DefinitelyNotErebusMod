package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBombardierBeetle;
import erebus.entity.EntityBombardierBeetle;

@SideOnly(Side.CLIENT)
public class RenderBombardierBeetle extends RenderLiving
{
	protected ModelBombardierBeetle model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelBombardierBeetle.png");


	public RenderBombardierBeetle(ModelBombardierBeetle par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
		model =(ModelBombardierBeetle)mainModel;

	}
	public void renderBombardierBeetle(EntityBombardierBeetle par1EntityBombardierBeetle, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityBombardierBeetle, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderBombardierBeetle((EntityBombardierBeetle)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderBombardierBeetle((EntityBombardierBeetle)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleBombardierBeetle((EntityBombardierBeetle) entityliving, f);
		EntityBombardierBeetle entityBombardierBeetle = (EntityBombardierBeetle) entityliving;
		if (entityBombardierBeetle.isClimbing())
			rotateBeetle(entityliving);
	}

	protected void rotateBeetle(EntityLivingBase entityliving)
	{
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleBombardierBeetle(EntityBombardierBeetle entityBombardierBeetle, float f) {
		float f1 = 0.75F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
