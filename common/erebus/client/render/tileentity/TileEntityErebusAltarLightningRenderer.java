package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelEngineEntity;
import erebus.tileentity.TileEntityErebusAltarLightning;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarLightningRenderer extends TileEntitySpecialRenderer {

	private final ModelEngineEntity ModelAltarBlock = new ModelEngineEntity();

	public void renderAModelAt(TileEntityErebusAltarLightning tile, double d, double d1, double d2, float f) {
		TileEntityErebusAltarLightning te = tile;
		if (te.animationTicks <= 5)
			bindTexture(new ResourceLocation("erebus:textures/mob/EngineOfIllapa1.png"));
		else if (te.animationTicks > 5 && te.animationTicks <= 10)
			bindTexture(new ResourceLocation("erebus:textures/mob/EngineOfIllapa2.png"));
		else if (te.animationTicks > 10 && te.animationTicks <= 15)
			bindTexture(new ResourceLocation("erebus:textures/mob/EngineOfIllapa3.png"));
		else if (te.animationTicks > 15 && te.animationTicks <= 20)
			bindTexture(new ResourceLocation("erebus:textures/mob/EngineOfIllapa4.png"));
		else if (te.animationTicks > 20 && te.animationTicks <= 25)
			bindTexture(new ResourceLocation("erebus:textures/mob/EngineOfIllapa5.png"));
		else
			bindTexture(new ResourceLocation("erebus:textures/mob/EngineOfIllapa1.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
		GL11.glScalef(0.5F, -0.5F, -0.5F);
		GL11.glRotatef(0 * 90, 0.0F, 1.0F, 0.0F);
		ModelAltarBlock.render(tile);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderAModelAt((TileEntityErebusAltarLightning) par1TileEntity, par2, par4, par6, par8);
	}

}