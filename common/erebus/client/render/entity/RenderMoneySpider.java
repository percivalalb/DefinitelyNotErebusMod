package erebus.client.render.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityErebusSpiderMoney;

@SideOnly(Side.CLIENT)
public class RenderMoneySpider extends RenderErebusSpider
{
	private static final ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");

	public RenderMoneySpider() {
		shadowSize *= 0.5F;
	}

	protected void scaleSpider(EntityErebusSpiderMoney par1EntityErebusSpiderMoney, float par2) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
	}

	protected ResourceLocation getCaveSpiderTextures(EntityErebusSpiderMoney par1EntityErebusSpiderMoney) {
		return caveSpiderTextures;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
		scaleSpider((EntityErebusSpiderMoney) par1EntityLivingBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return getCaveSpiderTextures((EntityErebusSpiderMoney) par1Entity);
	}
}
