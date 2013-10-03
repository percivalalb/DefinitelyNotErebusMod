package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrate;
import erebus.client.model.block.ModelBambooCrate;
import erebus.client.model.block.ModelColossalCrate;
import erebus.tileentity.TileEntityBambooCrate;

@SideOnly(Side.CLIENT)
public class TileEntityRenderBambooCrate extends TileEntitySpecialRenderer {

	private final ModelBambooCrate bambooCrateModel = new ModelBambooCrate();
	public static ResourceLocation bambooCrateResource = new ResourceLocation("erebus:textures/item/bambooCrate.png");

	private final ModelColossalCrate colossalCrateModel = new ModelColossalCrate();
	public static ResourceLocation colossalCrateResource = new ResourceLocation("erebus:textures/item/colossalcrate.png");

	public void renderTileEntityChestAt(TileEntityBambooCrate bambooCrate, double par2, double par4, double par6, float par8) {
		int x = bambooCrate.xCoord;
		int y = bambooCrate.yCoord;
		int z = bambooCrate.zCoord;

		if (bambooCrate.worldObj.getBlockId(x, y - 1, z) == ModBlocks.bambooCrate.blockID)
			y--;
		if (bambooCrate.worldObj.getBlockId(x - 1, y, z) == ModBlocks.bambooCrate.blockID)
			x--;
		if (bambooCrate.worldObj.getBlockId(x, y, z - 1) == ModBlocks.bambooCrate.blockID)
			z--;
		if (BlockBambooCrate.squareCrate(bambooCrate.worldObj, x, y, z)) {
			if (bambooCrate.xCoord != x || bambooCrate.yCoord != y || bambooCrate.zCoord != z)
				return;

			bindTexture(colossalCrateResource);

			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2 + 1.5F, (float) par4 + 1.5F, (float) par6 + 1.5F);
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, -1F, -1F);
			colossalCrateModel.renderModel(0.0625F);
			GL11.glPopMatrix();
			return;
		} else {
			bindTexture(bambooCrateResource);

			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 1.5F, (float) par6 + 0.5F);
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, -1F, -1F);
			bambooCrateModel.renderModel();
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderTileEntityChestAt((TileEntityBambooCrate) par1TileEntity, par2, par4, par6, par8);
	}
}
