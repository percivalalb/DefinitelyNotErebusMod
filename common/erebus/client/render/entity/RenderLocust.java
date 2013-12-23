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

	public void renderLocust(EntityLocust entityLocust, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityLocust, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderLocust((EntityLocust) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderLocust((EntityLocust) entity, par2, par4, par6, par8, par9);
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
