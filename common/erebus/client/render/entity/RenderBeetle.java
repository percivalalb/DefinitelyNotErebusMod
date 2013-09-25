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

	public RenderBeetle(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	public void renderBeetle(EntityBeetle par1EntityBeetle, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityBeetle, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderBeetle((EntityBeetle) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderBeetle((EntityBeetle) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBeetle beetle = (EntityBeetle) entity;
		if (beetle.skin > 0 && beetle.skin <= 10) {
			Texture = new ResourceLocation("erebus:textures/mob/Beetleblue.png");
		}
		if (beetle.skin > 10 && beetle.skin <= 20) {
			Texture = new ResourceLocation("erebus:textures/mob/Beetlebrown.png");
		}
		if (beetle.skin > 20 && beetle.skin <= 30) {
			Texture = new ResourceLocation("erebus:textures/mob/Beetlegreen.png");
		}
		if (beetle.skin > 30 && beetle.skin <= 40) {
			Texture = new ResourceLocation("erebus:textures/mob/Beetlered.png");
		}
		if (beetle.skin > 40 && beetle.skin <= 50) {
			Texture = new ResourceLocation("erebus:textures/mob/Beetletan.png");
		}
		if (beetle.skin == 0) {
			Texture = new ResourceLocation("erebus:textures/mob/Beetlerarespawn.png");
		}
		return Texture;
	}
}
