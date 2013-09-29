package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBotFly;
import erebus.entity.EntityBotFly;

@SideOnly(Side.CLIENT)
public class RenderBotFly extends RenderLiving {

	protected ModelBotFly model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelBotFly.png");

	public RenderBotFly(ModelBotFly par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelBotFly) mainModel;
	}

	public void renderBotFly(EntityBotFly par1EntityBotFly, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityBotFly, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderBotFly((EntityBotFly) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderBotFly((EntityBotFly) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleBotFly((EntityBotFly) entityliving, f);
		EntityBotFly entityBotFly = (EntityBotFly) entityliving;
		if (entityBotFly.getIsFlyHanging())
			GL11.glRotatef(180.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleBotFly(EntityBotFly entityBotFly, float f) {
		float f1 = 1.0F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
