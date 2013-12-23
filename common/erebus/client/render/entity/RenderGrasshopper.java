package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGrasshopper;
import erebus.entity.EntityGrasshopper;

@SideOnly(Side.CLIENT)
public class RenderGrasshopper extends RenderLiving {
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelGrasshopper.png");

	public RenderGrasshopper(ModelGrasshopper model, float shadowSize) {
		super(model, shadowSize);

	}

	public void renderGrasshopper(EntityGrasshopper entityGrasshopper, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityGrasshopper, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderGrasshopper((EntityGrasshopper) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderGrasshopper((EntityGrasshopper) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		shadowSize = 0.5F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
