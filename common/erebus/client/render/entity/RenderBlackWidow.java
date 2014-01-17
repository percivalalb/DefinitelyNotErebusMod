package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBlackWidow;
import erebus.entity.EntityBlackWidow;

@SideOnly(Side.CLIENT)
public class RenderBlackWidow extends RenderLiving {
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelBlackWidow.png");

	public RenderBlackWidow(ModelBlackWidow model, float shadowSize) {
		super(model, shadowSize);

	}

	public void renderBlackWidow(EntityBlackWidow entityBlackWidow, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityBlackWidow, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBlackWidow((EntityBlackWidow) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBlackWidow((EntityBlackWidow) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleBlackWidow((EntityBlackWidow) entityliving, f);
	}

	protected void scaleBlackWidow(EntityBlackWidow entityBlackWidow, float f) {
		EntityBlackWidow var8 = entityBlackWidow;
		float f1 = var8.getWidowSize() * 0.3F;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
