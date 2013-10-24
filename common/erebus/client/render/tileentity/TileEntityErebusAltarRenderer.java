package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelErebusAltar;
import erebus.tileentity.TileEntityErebusAltar;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarRenderer extends TileEntitySpecialRenderer {

	private final ModelErebusAltar ModelAltarBlock = new ModelErebusAltar();

	public void renderAModelAt(TileEntityErebusAltar tile, double d, double d1, double d2, float f) {
		bindTexture(new ResourceLocation("erebus:textures/blocks/blockErebusAltar.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
		GL11.glScalef(0.5F, -0.5F, -0.5F);
		GL11.glRotatef(0 * 90, 0.0F, 1.0F, 0.0F);
		ModelAltarBlock.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderAModelAt((TileEntityErebusAltar) par1TileEntity, par2, par4, par6, par8);
	}

}