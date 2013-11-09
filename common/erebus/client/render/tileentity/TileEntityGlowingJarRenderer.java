package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelGlowingJar;
import erebus.tileentity.TileEntityGlowingJar;

@SideOnly(Side.CLIENT)
public class TileEntityGlowingJarRenderer extends TileEntitySpecialRenderer {

	private final ModelGlowingJar ModelGlowingJar = new ModelGlowingJar();

	public void renderAModelAt(TileEntityGlowingJar tile, double d, double d1, double d2, float f) {
		bindTexture(new ResourceLocation("erebus:textures/blocks/ModelGlowingJar.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
		GL11.glScalef(0.7F, -1F, -0.7F);
		ModelGlowingJar.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderAModelAt((TileEntityGlowingJar) par1TileEntity, par2, par4, par6, par8);
	}

}


