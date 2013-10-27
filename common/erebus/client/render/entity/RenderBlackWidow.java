package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBlackWidow;
import erebus.entity.EntityBlackWidow;

@SideOnly(Side.CLIENT)
public class RenderBlackWidow extends RenderLiving {

	protected ModelBlackWidow model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelBlackWidow.png");

	public RenderBlackWidow(ModelBlackWidow par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelBlackWidow) mainModel;

	}

	public void renderBlackWidow(EntityBlackWidow par1EntityBlackWidow, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityBlackWidow, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderBlackWidow((EntityBlackWidow) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderBlackWidow((EntityBlackWidow) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleBlackWidow((EntityBlackWidow) entityliving, f);
	}

	protected void scaleBlackWidow(EntityBlackWidow entityBlackWidow, float f) {
		EntityBlackWidow var8 = entityBlackWidow;
		float f1 = var8.WidowSize;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
