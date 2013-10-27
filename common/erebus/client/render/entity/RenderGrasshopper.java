package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGrasshopper;
import erebus.entity.EntityGrasshopper;

@SideOnly(Side.CLIENT)
public class RenderGrasshopper extends RenderLiving {

	protected ModelGrasshopper model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelGrasshopper.png");

	public RenderGrasshopper(ModelGrasshopper par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelGrasshopper) mainModel;

	}

	public void renderGrasshopper(EntityGrasshopper par1EntityGrasshopper, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityGrasshopper, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderGrasshopper((EntityGrasshopper) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderGrasshopper((EntityGrasshopper) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleGrasshopper((EntityGrasshopper) entityliving, f);
	}

	protected void scaleGrasshopper(EntityGrasshopper entityGrasshopper, float f) {
		float f1 = 1.0F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
