package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelVelvetWorm;
import erebus.entity.EntityVelvetWorm;

@SideOnly(Side.CLIENT)
public class RenderVelvetWorm extends RenderLiving {
	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Velvet worm.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/Velvet worm2.png");

	private float scale = 1.0F;

	public RenderVelvetWorm(ModelVelvetWorm model, float shadowSize, float scale) {
		super(model, shadowSize * scale);
		this.scale = scale;
	}

	public void renderEntityVelvetWorm(EntityVelvetWorm entityEntityVelvetWorm, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityEntityVelvetWorm, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderEntityVelvetWorm((EntityVelvetWorm) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderEntityVelvetWorm((EntityVelvetWorm) entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (((EntityVelvetWorm)entity).skin <= 10)
			return resource2;
		else
			return resource1;
	}
}
