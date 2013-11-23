package erebus.client.render.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityErebusSpider;

@SideOnly(Side.CLIENT)
public class RenderErebusSpider extends RenderLiving
{
	private static final ResourceLocation spiderEyesTextures = new ResourceLocation("textures/entity/spider_eyes.png");
	private static final ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");

	public RenderErebusSpider() {
		super(new ModelSpider(), 1.0F);
		setRenderPassModel(new ModelSpider());
	}

	protected float setSpiderDeathMaxRotation(EntityErebusSpider par1EntityErebusSpider) {
		return 180.0F;
	}

	protected void scaleSpider(EntityErebusSpider par1EntityErebusSpider, float par2) {
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	protected int setSpiderEyeBrightness(EntityErebusSpider par1EntityErebusSpider, int par2, float par3) {
		if (par2 != 0)
			return -1;
		else {
			bindTexture(spiderEyesTextures);
			float f1 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (par1EntityErebusSpider.isInvisible())
				GL11.glDepthMask(false);
			else
				GL11.glDepthMask(true);

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return 1;
		}
	}

	protected ResourceLocation getSpiderTextures(EntityErebusSpider par1EntityErebusSpider) {
		return spiderTextures;
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase) {
		return setSpiderDeathMaxRotation((EntityErebusSpider) par1EntityLivingBase);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
		return setSpiderEyeBrightness((EntityErebusSpider) par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return getSpiderTextures((EntityErebusSpider) par1Entity);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
		scaleSpider((EntityErebusSpider) par1EntityLivingBase, par2);
	}
}
