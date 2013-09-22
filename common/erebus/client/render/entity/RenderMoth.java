package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMoth;
import erebus.entity.EntityMoth;

@SideOnly(Side.CLIENT)
public class RenderMoth extends RenderLiving {
	protected ModelMoth model;
	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Moth.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/Moth2.png");
	private final ResourceLocation resource3 = new ResourceLocation("erebus:textures/mob/Moth3.png");

	public RenderMoth(ModelMoth par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = ((ModelMoth) mainModel);
	}

	public void renderMoth(EntityMoth par1EntityMoth, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityMoth, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderMoth((EntityMoth) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderMoth((EntityMoth) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleMoth((EntityMoth) entityliving, f);
		EntityMoth entityMoth = (EntityMoth) entityliving;
		if (entityMoth.getIsMothHanging())
			GL11.glRotatef(180.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleMoth(EntityMoth entityMoth, float f) {
		float f1 = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityMoth entityMoth = (EntityMoth) entity;
		switch (entityMoth.skin) {
			case 0:
				return resource1;
			case 1:
				return resource2;
			case 2:
				return resource3;
		}
		return resource1;
	}
}
