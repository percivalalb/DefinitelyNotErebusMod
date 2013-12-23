package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityBeetle;

@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving {

	private static ResourceLocation Texture;

	public RenderBeetle(ModelBase model, float par2) {
		super(model, par2);
	}

	public void renderBeetle(EntityBeetle entityBeetle, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityBeetle, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderBeetle((EntityBeetle) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderBeetle((EntityBeetle) entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBeetle beetle = (EntityBeetle) entity;
		if (beetle.skin > 0 && beetle.skin <= 10)
			Texture = new ResourceLocation("erebus:textures/mob/Beetleblue.png");
		if (beetle.skin > 10 && beetle.skin <= 20)
			Texture = new ResourceLocation("erebus:textures/mob/Beetlebrown.png");
		if (beetle.skin > 20 && beetle.skin <= 30)
			Texture = new ResourceLocation("erebus:textures/mob/Beetlegreen.png");
		if (beetle.skin > 30 && beetle.skin <= 40)
			Texture = new ResourceLocation("erebus:textures/mob/Beetlered.png");
		if (beetle.skin > 40 && beetle.skin <= 50)
			Texture = new ResourceLocation("erebus:textures/mob/Beetletan.png");
		if (beetle.skin == 0)
			Texture = new ResourceLocation("erebus:textures/mob/Beetlerarespawn.png");
		return Texture;
	}
}
