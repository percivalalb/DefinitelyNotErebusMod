package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelScorpion;
import erebus.entity.EntityScorpion;

@SideOnly(Side.CLIENT)
public class RenderScorpion extends RenderLiving {
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelScorpion.png");

	public RenderScorpion(ModelScorpion model, float shadowSize) {
		super(model, shadowSize);

	}

	public void renderScorpion(EntityScorpion entityScorpion, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityScorpion, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderScorpion((EntityScorpion) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderScorpion((EntityScorpion) entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleScorpion((EntityScorpion) entityliving, f);
	}

	protected void scaleScorpion(EntityScorpion entityScorpion, float f) {
		float f1 = 1.0F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
