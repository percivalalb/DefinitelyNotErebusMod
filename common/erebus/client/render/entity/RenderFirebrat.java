package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityFirebrat;

@SideOnly(Side.CLIENT)
public class RenderFirebrat extends RenderLiving
{
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelFirebrat.png");

	public RenderFirebrat(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	protected float getFirebratDeathRotation(EntityFirebrat par1EntityFirebrat)
	{
		return 180.0F;
	}

	public void renderFirebrat(EntityFirebrat par1EntityFirebrat, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(par1EntityFirebrat, par2, par4, par6, par8, par9);
	}

	protected int shouldFirebratRenderPass(EntityFirebrat par1EntityFirebrat, int par2, float par3)
	{
		return -1;
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderFirebrat((EntityFirebrat) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase)
	{
		return getFirebratDeathRotation((EntityFirebrat) par1EntityLivingBase);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return shouldFirebratRenderPass((EntityFirebrat) par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return Texture;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderFirebrat((EntityFirebrat) par1Entity, par2, par4, par6, par8, par9);
	}
}
