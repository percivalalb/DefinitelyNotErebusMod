package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelFly;
import erebus.entity.EntityFly;

@SideOnly(Side.CLIENT)
public class RenderFly extends RenderLiving {

	private int renderedFlySize;

	public RenderFly() {
		super(new ModelFly(), 0.25F);
		renderedFlySize = ((ModelFly) mainModel).getFlySize();
	}

	public void func_82443_a(EntityFly entityFly, double par2, double par4, double par6, float par8, float par9) {
		int var10 = ((ModelFly) mainModel).getFlySize();

		if (var10 != renderedFlySize) {
			renderedFlySize = var10;
			mainModel = new ModelFly();
		}

		super.doRenderLiving(entityFly, par2, par4, par6, par8, par9);
	}

	protected void func_82442_a(EntityFly entityFly, float par2) {
		GL11.glScalef(0.35F, 0.35F, 0.35F);
	}

	protected void func_82445_a(EntityFly entityFly, double par2, double par4, double par6) {
		super.renderLivingAt(entityFly, par2, par4, par6);
	}

	protected void func_82444_a(EntityFly entityFly, float par2, float par3, float par4) {
		if (!entityFly.getIsFlyHanging())
			GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
		else
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);

		super.rotateCorpse(entityFly, par2, par3, par4);
	}

	protected void preRenderCallback(EntityLiving entityLiving, float par2) {
		func_82442_a((EntityFly) entityLiving, par2);
	}

	protected void rotateCorpse(EntityLiving entityLiving, float par2, float par3, float par4) {
		func_82444_a((EntityFly) entityLiving, par2, par3, par4);
	}

	protected void renderLivingAt(EntityLiving entityLiving, double par2, double par4, double par6) {
		func_82445_a((EntityFly) entityLiving, par2, par4, par6);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		func_82443_a((EntityFly) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		func_82443_a((EntityFly) entity, par2, par4, par6, par8, par9);
	}

	private final ResourceLocation resource = new ResourceLocation("erebus:textures/mob/Fly.png");

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return resource;
	}
}
