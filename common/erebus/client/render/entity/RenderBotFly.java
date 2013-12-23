package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBotFly;
import erebus.entity.EntityBotFly;

@SideOnly(Side.CLIENT)
public class RenderBotFly extends RenderLiving {
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelBotFly.png");

	public RenderBotFly(ModelBotFly model, float par2) {
		super(model, par2);
	}

	public void renderBotFly(EntityBotFly entityBotFly, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityBotFly, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBotFly((EntityBotFly) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBotFly((EntityBotFly) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleBotFly((EntityBotFly) entityliving, f);
		EntityBotFly entityBotFly = (EntityBotFly) entityliving;
		if (entityBotFly.getIsFlyHanging())
			GL11.glRotatef(180.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleBotFly(EntityBotFly entityBotFly, float f) {
		float f1 = 1.0F;
		shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
