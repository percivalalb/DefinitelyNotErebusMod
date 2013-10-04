package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityMosquito;

@SideOnly(Side.CLIENT)
public class RenderMosquito extends RenderLiving {

	public RenderMosquito(ModelBase par1ModelBase, float par3) {
		super(par1ModelBase, par3);
		setRenderPassModel(par1ModelBase);
	}

	public void renderLivingMosquito(EntityMosquito par1EntityMosquito, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityMosquito, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderLivingMosquito((EntityMosquito) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderLivingMosquito((EntityMosquito) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		EntityMosquito mosquito = (EntityMosquito) entityliving;
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(0.0F, -1.4F, -0.5F);
		if (mosquito.ridingEntity != null)
			GL11.glTranslatef(0.0F, 0.0F, 0.5F);
	}

	private final ResourceLocation resource = new ResourceLocation("erebus:textures/mob/Mosquito.png");

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return resource;
	}
}
