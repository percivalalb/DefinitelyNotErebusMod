package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelLocust;
import erebus.entity.EntityLocust;

@SideOnly(Side.CLIENT)
public class RenderLocust extends RenderLiving {
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelLocust.png");

	public RenderLocust(ModelLocust model, float shadowSize) {
		super(model, shadowSize);
	}

	public void renderLocust(EntityLocust entityLocust, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityLocust, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderLocust((EntityLocust) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderLocust((EntityLocust) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleLocust((EntityLocust) entityliving, f);
	}

	protected void scaleLocust(EntityLocust entityLocust, float f) {
		float f1 = 1.5F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
