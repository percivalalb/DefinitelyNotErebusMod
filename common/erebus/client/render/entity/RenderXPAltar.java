package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelXPAltar;
import erebus.entity.EntityXPAltar;

@SideOnly(Side.CLIENT)
public class RenderXPAltar extends RenderLiving {

	public RenderXPAltar() {
		super(new ModelXPAltar(), 0.3F);
	}

	public void renderXPAltar(EntityXPAltar par1EntityXPAltar, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityXPAltar, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderXPAltar((EntityXPAltar) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderXPAltar((EntityXPAltar) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityXPAltar XPAltar = (EntityXPAltar) entity;
		if (XPAltar.animationTicks <= 5)
			return new ResourceLocation("erebus:textures/mob/XPAltar1.png");
		else if (XPAltar.animationTicks > 5 && XPAltar.animationTicks <= 10)
			return new ResourceLocation("erebus:textures/mob/XPAltar2.png");
		else if (XPAltar.animationTicks > 10 && XPAltar.animationTicks <= 15)
			return new ResourceLocation("erebus:textures/mob/XPAltar3.png");
		else if (XPAltar.animationTicks > 15 && XPAltar.animationTicks <= 20)
			return new ResourceLocation("erebus:textures/mob/XPAltar4.png");
		else if (XPAltar.animationTicks > 20 && XPAltar.animationTicks <= 25)
			return new ResourceLocation("erebus:textures/mob/XPAltar5.png");
		else
			return new ResourceLocation("erebus:textures/mob/XPAltar1.png");
	}
}
