package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltarXP;
import erebus.tileentity.TileEntityErebusAltarXP;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarXPRenderer extends TileEntitySpecialRenderer {

	private final ModelAltarXP ModelAltarBlock = new ModelAltarXP();

	public void renderAModelAt(TileEntityErebusAltarXP tile, double d, double d1, double d2, float f) {
		TileEntityErebusAltarXP te = tile;
		if (te.animationTicks <= 5)
			bindTexture(new ResourceLocation("erebus:textures/blocks/XPAltar1.png"));
		else if (te.animationTicks > 5 && te.animationTicks <= 10)
			bindTexture(new ResourceLocation("erebus:textures/blocks/XPAltar2.png"));
		else if (te.animationTicks > 10 && te.animationTicks <= 15)
			bindTexture(new ResourceLocation("erebus:textures/blocks/XPAltar3.png"));
		else if (te.animationTicks > 15 && te.animationTicks <= 20)
			bindTexture(new ResourceLocation("erebus:textures/blocks/XPAltar4.png"));
		else if (te.animationTicks > 20 && te.animationTicks <= 25)
			bindTexture(new ResourceLocation("erebus:textures/blocks/XPAltar5.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
		GL11.glScalef(0.5F, -0.5F, -0.5F);
		GL11.glRotatef(0 * 90, 0.0F, 1.0F, 0.0F);
		ModelAltarBlock.render(tile);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderAModelAt((TileEntityErebusAltarXP) par1TileEntity, par2, par4, par6, par8);
	}

}