package erebus.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityRendererErebus extends EntityRenderer {

	public boolean hasNightVisionEffect = false;

	public EntityRendererErebus() {
		super(Minecraft.getMinecraft());
	}

	@Override
	protected float getNightVisionBrightness(EntityPlayer par1EntityPlayer, float par2) {
		if (hasNightVisionEffect)
			return 1F;
		else {
			int i = par1EntityPlayer.getActivePotionEffect(Potion.nightVision).getDuration();
			return i > 200 ? 1.0F : 0.7F + MathHelper.sin((i - par2) * (float) Math.PI * 0.2F) * 0.3F;
		}
	}

	@Override
	protected void updateLightmap(float par1) {
		WorldClient worldclient = mc.theWorld;

		if (worldclient != null) {
			for (int i = 0; i < 256; ++i) {
				float f1 = worldclient.getSunBrightness(1.0F) * 0.95F + 0.05F;
				float f2 = worldclient.provider.lightBrightnessTable[i / 16] * f1;
				float f3 = worldclient.provider.lightBrightnessTable[i % 16] * (torchFlickerX * 0.1F + 1.5F);

				if (worldclient.lastLightningBolt > 0)
					f2 = worldclient.provider.lightBrightnessTable[i / 16];

				float f4 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
				float f5 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
				float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
				float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
				float f8 = f4 + f3;
				float f9 = f5 + f6;
				float f10 = f2 + f7;
				f8 = f8 * 0.96F + 0.03F;
				f9 = f9 * 0.96F + 0.03F;
				f10 = f10 * 0.96F + 0.03F;
				float f11;

				if (field_82831_U > 0.0F) {
					f11 = field_82832_V + (field_82831_U - field_82832_V) * par1;
					f8 = f8 * (1.0F - f11) + f8 * 0.7F * f11;
					f9 = f9 * (1.0F - f11) + f9 * 0.6F * f11;
					f10 = f10 * (1.0F - f11) + f10 * 0.6F * f11;
				}

				if (worldclient.provider.dimensionId == 1) {
					f8 = 0.22F + f3 * 0.75F;
					f9 = 0.28F + f6 * 0.75F;
					f10 = 0.25F + f7 * 0.75F;
				}

				float f12;

				if (mc.thePlayer.isPotionActive(Potion.nightVision) || hasNightVisionEffect) {
					f11 = getNightVisionBrightness(mc.thePlayer, par1);
					f12 = 1.0F / f8;

					if (f12 > 1.0F / f9)
						f12 = 1.0F / f9;

					if (f12 > 1.0F / f10)
						f12 = 1.0F / f10;

					f8 = f8 * (1.0F - f11) + f8 * f12 * f11;
					f9 = f9 * (1.0F - f11) + f9 * f12 * f11;
					f10 = f10 * (1.0F - f11) + f10 * f12 * f11;
				}

				if (f8 > 1.0F)
					f8 = 1.0F;

				if (f9 > 1.0F)
					f9 = 1.0F;

				if (f10 > 1.0F)
					f10 = 1.0F;

				f11 = mc.gameSettings.gammaSetting;
				f12 = 1.0F - f8;
				float f13 = 1.0F - f9;
				float f14 = 1.0F - f10;
				f12 = 1.0F - f12 * f12 * f12 * f12;
				f13 = 1.0F - f13 * f13 * f13 * f13;
				f14 = 1.0F - f14 * f14 * f14 * f14;
				f8 = f8 * (1.0F - f11) + f12 * f11;
				f9 = f9 * (1.0F - f11) + f13 * f11;
				f10 = f10 * (1.0F - f11) + f14 * f11;
				f8 = f8 * 0.96F + 0.03F;
				f9 = f9 * 0.96F + 0.03F;
				f10 = f10 * 0.96F + 0.03F;

				if (f8 > 1.0F)
					f8 = 1.0F;

				if (f9 > 1.0F)
					f9 = 1.0F;

				if (f10 > 1.0F)
					f10 = 1.0F;

				if (f8 < 0.0F)
					f8 = 0.0F;

				if (f9 < 0.0F)
					f9 = 0.0F;

				if (f10 < 0.0F)
					f10 = 0.0F;

				short short1 = 255;
				int j = (int) (f8 * 255.0F);
				int k = (int) (f9 * 255.0F);
				int l = (int) (f10 * 255.0F);
				lightmapColors[i] = short1 << 24 | j << 16 | k << 8 | l;
			}

			lightmapTexture.updateDynamicTexture();
			lightmapUpdateNeeded = false;
		}
	}

	@Override
	protected void updateFogColor(float par1) {
		WorldClient worldclient = mc.theWorld;
		EntityLivingBase entitylivingbase = mc.renderViewEntity;
		float f1 = 1.0F / (4 - mc.gameSettings.renderDistance);
		f1 = 1.0F - (float) Math.pow(f1, 0.25D);
		Vec3 vec3 = worldclient.getSkyColor(mc.renderViewEntity, par1);
		float f2 = (float) vec3.xCoord;
		float f3 = (float) vec3.yCoord;
		float f4 = (float) vec3.zCoord;
		Vec3 vec31 = worldclient.getFogColor(par1);
		fogColorRed = (float) vec31.xCoord;
		fogColorGreen = (float) vec31.yCoord;
		fogColorBlue = (float) vec31.zCoord;
		float f5;

		if (mc.gameSettings.renderDistance < 2) {
			Vec3 vec32 = MathHelper.sin(worldclient.getCelestialAngleRadians(par1)) > 0.0F ? worldclient.getWorldVec3Pool().getVecFromPool(-1.0D, 0.0D, 0.0D) : worldclient.getWorldVec3Pool().getVecFromPool(1.0D, 0.0D, 0.0D);
			f5 = (float) entitylivingbase.getLook(par1).dotProduct(vec32);

			if (f5 < 0.0F)
				f5 = 0.0F;

			if (f5 > 0.0F) {
				float[] afloat = worldclient.provider.calcSunriseSunsetColors(worldclient.getCelestialAngle(par1), par1);

				if (afloat != null) {
					f5 *= afloat[3];
					fogColorRed = fogColorRed * (1.0F - f5) + afloat[0] * f5;
					fogColorGreen = fogColorGreen * (1.0F - f5) + afloat[1] * f5;
					fogColorBlue = fogColorBlue * (1.0F - f5) + afloat[2] * f5;
				}
			}
		}

		fogColorRed += (f2 - fogColorRed) * f1;
		fogColorGreen += (f3 - fogColorGreen) * f1;
		fogColorBlue += (f4 - fogColorBlue) * f1;
		float f6 = worldclient.getRainStrength(par1);
		float f7;

		if (f6 > 0.0F) {
			f5 = 1.0F - f6 * 0.5F;
			f7 = 1.0F - f6 * 0.4F;
			fogColorRed *= f5;
			fogColorGreen *= f5;
			fogColorBlue *= f7;
		}

		f5 = worldclient.getWeightedThunderStrength(par1);

		if (f5 > 0.0F) {
			f7 = 1.0F - f5 * 0.5F;
			fogColorRed *= f7;
			fogColorGreen *= f7;
			fogColorBlue *= f7;
		}

		int i = ActiveRenderInfo.getBlockIdAtEntityViewpoint(mc.theWorld, entitylivingbase, par1);
		float f8;

		if (cloudFog) {
			Vec3 vec33 = worldclient.getCloudColour(par1);
			fogColorRed = (float) vec33.xCoord;
			fogColorGreen = (float) vec33.yCoord;
			fogColorBlue = (float) vec33.zCoord;
		} else if (i != 0 && Block.blocksList[i].blockMaterial == Material.water) {
			f8 = EnchantmentHelper.getRespiration(entitylivingbase) * 0.2F;
			fogColorRed = 0.02F + f8;
			fogColorGreen = 0.02F + f8;
			fogColorBlue = 0.2F + f8;
		} else if (i != 0 && Block.blocksList[i].blockMaterial == Material.lava) {
			fogColorRed = 0.6F;
			fogColorGreen = 0.1F;
			fogColorBlue = 0.0F;
		}

		f8 = fogColor2 + (fogColor1 - fogColor2) * par1;
		fogColorRed *= f8;
		fogColorGreen *= f8;
		fogColorBlue *= f8;
		double d0 = (entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * par1) * worldclient.provider.getVoidFogYFactor();

		if (entitylivingbase.isPotionActive(Potion.blindness)) {
			int j = entitylivingbase.getActivePotionEffect(Potion.blindness).getDuration();

			if (j < 20)
				d0 *= 1.0F - j / 20.0F;
			else
				d0 = 0.0D;
		}

		if (d0 < 1.0D) {
			if (d0 < 0.0D)
				d0 = 0.0D;

			d0 *= d0;
			fogColorRed = (float) (fogColorRed * d0);
			fogColorGreen = (float) (fogColorGreen * d0);
			fogColorBlue = (float) (fogColorBlue * d0);
		}

		float f9;

		if (field_82831_U > 0.0F) {
			f9 = field_82832_V + (field_82831_U - field_82832_V) * par1;
			fogColorRed = fogColorRed * (1.0F - f9) + fogColorRed * 0.7F * f9;
			fogColorGreen = fogColorGreen * (1.0F - f9) + fogColorGreen * 0.6F * f9;
			fogColorBlue = fogColorBlue * (1.0F - f9) + fogColorBlue * 0.6F * f9;
		}

		float f10;

		if (entitylivingbase.isPotionActive(Potion.nightVision) || hasNightVisionEffect) {
			f9 = getNightVisionBrightness(mc.thePlayer, par1);
			f10 = 1.0F / fogColorRed;

			if (f10 > 1.0F / fogColorGreen)
				f10 = 1.0F / fogColorGreen;

			if (f10 > 1.0F / fogColorBlue)
				f10 = 1.0F / fogColorBlue;

			fogColorRed = fogColorRed * (1.0F - f9) + fogColorRed * f10 * f9;
			fogColorGreen = fogColorGreen * (1.0F - f9) + fogColorGreen * f10 * f9;
			fogColorBlue = fogColorBlue * (1.0F - f9) + fogColorBlue * f10 * f9;
		}

		if (mc.gameSettings.anaglyph) {
			f9 = (fogColorRed * 30.0F + fogColorGreen * 59.0F + fogColorBlue * 11.0F) / 100.0F;
			f10 = (fogColorRed * 30.0F + fogColorGreen * 70.0F) / 100.0F;
			float f11 = (fogColorRed * 30.0F + fogColorBlue * 70.0F) / 100.0F;
			fogColorRed = f9;
			fogColorGreen = f10;
			fogColorBlue = f11;
		}

		GL11.glClearColor(fogColorRed, fogColorGreen, fogColorBlue, 0.0F);
	}
}