package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelSolifuge;
import erebus.entity.EntitySolifuge;

@SideOnly(Side.CLIENT)
public class RenderSolifuge extends RenderLiving {

	protected ModelSolifuge model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelSolifuge.png");

	public RenderSolifuge(ModelSolifuge par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelSolifuge) mainModel;

	}

	public void renderSolifuge(EntitySolifuge par1EntitySolifuge, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntitySolifuge, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderSolifuge((EntitySolifuge) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderSolifuge((EntitySolifuge) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleSolifuge((EntitySolifuge) entityliving, f);
	}

	protected void scaleSolifuge(EntitySolifuge entitySolifuge, float f) {
		float f1 = 1.0F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
