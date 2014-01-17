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

	public void renderScorpion(EntityScorpion entityScorpion, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityScorpion, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderScorpion((EntityScorpion) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderScorpion((EntityScorpion) entity, x, y, z, rotationYaw, partialTickTime);
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
