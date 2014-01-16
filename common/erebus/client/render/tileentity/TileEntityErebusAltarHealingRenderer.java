package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltarHealing;
import erebus.tileentity.TileEntityErebusAltarHealing;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarHealingRenderer extends TileEntitySpecialRenderer {

	private final ModelAltarHealing ModelAltarBlock = new ModelAltarHealing();

	public void renderAModelAt(TileEntityErebusAltarHealing tile, double d, double d1, double d2, float f) {
		TileEntityErebusAltarHealing te = tile;
		if (te.animationTicks <= 5)
			bindTexture(new ResourceLocation("erebus:textures/blocks/HealingAltar1.png"));
		else if (te.animationTicks > 5 && te.animationTicks <= 10)
			bindTexture(new ResourceLocation("erebus:textures/blocks/HealingAltar2.png"));
		else if (te.animationTicks > 10 && te.animationTicks <= 15)
			bindTexture(new ResourceLocation("erebus:textures/blocks/HealingAltar3.png"));
		else if (te.animationTicks > 15 && te.animationTicks <= 20)
			bindTexture(new ResourceLocation("erebus:textures/blocks/HealingAltar4.png"));
		else if (te.animationTicks > 20 && te.animationTicks <= 25)
			bindTexture(new ResourceLocation("erebus:textures/blocks/HealingAltar5.png"));
		int meta = tile.getBlockMetadata();
		switch (meta) {
			case 2:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
				GL11.glScalef(0.5F, -0.5F, -0.5F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelAltarBlock.render(tile);
				GL11.glPopMatrix();
				break;
			case 3:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
				GL11.glScalef(0.5F, -0.5F, -0.5F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				ModelAltarBlock.render(tile);
				GL11.glPopMatrix();
				break;
			case 4:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
				GL11.glScalef(0.5F, -0.5F, -0.5F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				ModelAltarBlock.render(tile);
				GL11.glPopMatrix();
				break;
			case 5:
				GL11.glPushMatrix();
				GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.75F, (float) d2 + 0.5F);
				GL11.glScalef(0.5F, -0.5F, -0.5F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				ModelAltarBlock.render(tile);
				GL11.glPopMatrix();
				break;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderAModelAt((TileEntityErebusAltarHealing) par1TileEntity, par2, par4, par6, par8);
	}

}