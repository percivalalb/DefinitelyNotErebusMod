package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelHealer;
import erebus.entity.EntityHealer;

@SideOnly(Side.CLIENT)
public class RenderHealer extends RenderLiving {

	public RenderHealer() {
		super(new ModelHealer(), 0.3F);
	}

	public void renderHealer(EntityHealer par1EntityHealer, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityHealer, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderHealer((EntityHealer) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderHealer((EntityHealer) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityHealer healer = (EntityHealer) entity;
		if (healer.animationTicks <= 5)
			return new ResourceLocation("erebus:textures/mob/HealingAltar1.png");
		else if (healer.animationTicks > 5 && healer.animationTicks <= 10)
			return new ResourceLocation("erebus:textures/mob/HealingAltar2.png");
		else if (healer.animationTicks > 10 && healer.animationTicks <= 15)
			return new ResourceLocation("erebus:textures/mob/HealingAltar3.png");
		else if (healer.animationTicks > 15 && healer.animationTicks <= 20)
			return new ResourceLocation("erebus:textures/mob/HealingAltar4.png");
		else if (healer.animationTicks > 20 && healer.animationTicks <= 25)
			return new ResourceLocation("erebus:textures/mob/HealingAltar5.png");
		else
			return new ResourceLocation("erebus:textures/mob/HealingAltar1.png");
	}
}
