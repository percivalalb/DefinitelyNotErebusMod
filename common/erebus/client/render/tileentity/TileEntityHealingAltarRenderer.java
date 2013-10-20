package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelHealingAltar;
import erebus.tileentity.TileEntityHealingAltar;

@SideOnly(Side.CLIENT)
public class TileEntityHealingAltarRenderer extends TileEntitySpecialRenderer {

	private final ModelHealingAltar modelAltarBlock = new ModelHealingAltar();

	public void renderAModelAt(TileEntityHealingAltar tile, double d, double d1, double d2, float f) {
		bindTexture(new ResourceLocation("erebus:textures/mob/HealingAltar1.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
		GL11.glScalef(0.5F, -0.5F, -0.5F);
		GL11.glRotatef(0 * 90, 0.0F, 1.0F, 0.0F);
		modelAltarBlock.render();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderAModelAt((TileEntityHealingAltar) par1TileEntity, par2, par4, par6, par8);
	}

}