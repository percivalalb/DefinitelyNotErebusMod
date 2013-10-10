package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelEngineEntity;
import erebus.entity.EntityEngine;

@SideOnly(Side.CLIENT)
public class RenderEngineOfIllapa extends RenderLiving {

	public RenderEngineOfIllapa() {
		super(new ModelEngineEntity(), 0.3F);
	}

	public void renderEngineEntity(EntityEngine par1EntityEngineEntity, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityEngineEntity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderEngineEntity((EntityEngine) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderEngineEntity((EntityEngine) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityEngine engine = (EntityEngine) entity;
		if (engine.animationTicks <= 5)
			return new ResourceLocation("engineofillapa:textures/entities/EngineOfIllapa1.png");
		else if (engine.animationTicks > 5 && engine.animationTicks <= 10)
			return new ResourceLocation("engineofillapa:textures/entities/EngineOfIllapa2.png");
		else if (engine.animationTicks > 10 && engine.animationTicks <= 15)
			return new ResourceLocation("engineofillapa:textures/entities/EngineOfIllapa3.png");
		else if (engine.animationTicks > 15 && engine.animationTicks <= 20)
			return new ResourceLocation("engineofillapa:textures/entities/EngineOfIllapa4.png");
		else if (engine.animationTicks > 20 && engine.animationTicks <= 25)
			return new ResourceLocation("engineofillapa:textures/entities/EngineOfIllapa5.png");
		else
			return new ResourceLocation("engineofillapa:textures/entities/EngineOfIllapa1.png");
	}
}
