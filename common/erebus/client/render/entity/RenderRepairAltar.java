package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelRepairAltar;
import erebus.entity.EntityRepairAltar;

@SideOnly(Side.CLIENT)
public class RenderRepairAltar extends RenderLiving {

	public RenderRepairAltar() {
		super(new ModelRepairAltar(), 0.3F);
	}

	public void renderRepairAltar(EntityRepairAltar par1EntityRepairAltar, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityRepairAltar, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderRepairAltar((EntityRepairAltar) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderRepairAltar((EntityRepairAltar) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityRepairAltar repair = (EntityRepairAltar) entity;
		if (repair.animationTicks <= 5)
			return new ResourceLocation("erebus:textures/mob/RepairAltar1.png");
		else if (repair.animationTicks > 5 && repair.animationTicks <= 10)
			return new ResourceLocation("erebus:textures/mob/RepairAltar2.png");
		else if (repair.animationTicks > 10 && repair.animationTicks <= 15)
			return new ResourceLocation("erebus:textures/mob/RepairAltar3.png");
		else if (repair.animationTicks > 15 && repair.animationTicks <= 20)
			return new ResourceLocation("erebus:textures/mob/RepairAltar4.png");
		else if (repair.animationTicks > 20 && repair.animationTicks <= 25)
			return new ResourceLocation("erebus:textures/mob/RepairAltar5.png");
		else
			return new ResourceLocation("erebus:textures/mob/RepairAltar1.png");
	}
}
