package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelHollowLog;
import erebus.tileentity.TileEntityHollowLog;

@SideOnly(Side.CLIENT)
public class TileEntityRenderHollowLog extends TileEntitySpecialRenderer {
	/** The hollow log chest model. */
	private final ModelHollowLog hollowLogModel = new ModelHollowLog();
	public static ResourceLocation hollowLogResource = new ResourceLocation("erebus:textures/item/hollowLog.png");

	public void renderTileEntityChestAt(TileEntityHollowLog hollowLog, double par2, double par4, double par6, float par8) {
		bindTexture(hollowLogResource);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 1.5F, (float) par6 + 0.5F);
		int direction = 0;
		if (hollowLog.worldObj != null) {
			int metadata = hollowLog.getBlockMetadata();
			if (metadata == 1)
				direction = 90;
		}

		GL11.glRotatef(direction, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		hollowLogModel.renderModel(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderTileEntityChestAt((TileEntityHollowLog) par1TileEntity, par2, par4, par6, par8);
	}
}
