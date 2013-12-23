package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityFirebrat;

@SideOnly(Side.CLIENT)
public class RenderFirebrat extends RenderLiving {

	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelFirebrat.png");

	public RenderFirebrat(ModelBase model, float par2) {
		super(model, par2);
	}

	public void renderFirebrat(EntityFirebrat entityFirebrat, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityFirebrat, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderFirebrat((EntityFirebrat) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase) {
		return 180F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int par2, float par3) {
		return -1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderFirebrat((EntityFirebrat) entity, par2, par4, par6, par8, par9);
	}
}
