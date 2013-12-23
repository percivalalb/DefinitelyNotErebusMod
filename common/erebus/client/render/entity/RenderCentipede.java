package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelCentipede;
import erebus.entity.EntityCentipede;

@SideOnly(Side.CLIENT)
public class RenderCentipede extends RenderLiving {
	private static final ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Centipede.png");
	private static final ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/ModelCentipede.png");
	private static final ResourceLocation resource3 = new ResourceLocation("erebus:textures/mob/CentipedeBlack.png");

	public RenderCentipede(ModelCentipede model, float shadowSize) {
		super(model, shadowSize);

	}

	public void renderCentipede(EntityCentipede entityCentipede, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityCentipede, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderCentipede((EntityCentipede) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderCentipede((EntityCentipede) entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		shadowSize = 0.5F;
	}

	protected void rotateCentipede() { // TODO unused
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityCentipede entityCentipede = (EntityCentipede) entity;
		switch (entityCentipede.skin) {
			case 0:
				return resource1;
			case 1:
				return resource2;
			case 2:
				return resource3;
		}
		return resource1;
	}
}
