package erebus.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModBlocks;

@SideOnly(Side.CLIENT)
public class BlockBambooCropRender implements ISimpleBlockRenderingHandler {
	public BlockBambooCropRender() {
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.95D, 0.75D);
		renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.setRenderBounds(0.22D, 0.45D, 0.2D, 0.78D, 0.49D, 0.78D);
		renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.setRenderBounds(0.22D, 0.95D, 0.2D, 0.78D, 0.99D, 0.78D);
		renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.setRenderBounds(0.25D, 0.99D, 0.25D, 0.75D, 1D, 0.75D);
		renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.renderAllFaces = false;
	}

	private void renderSimpleBlock(Block block, int metadata, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.95D, 0.75D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.setRenderBounds(0.22D, 0.45D, 0.2D, 0.78D, 0.49D, 0.78D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.setRenderBounds(0.22D, 0.95D, 0.2D, 0.78D, 0.99D, 0.78D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.setRenderBounds(0.25D, 0.99D, 0.25D, 0.75D, 1D, 0.75D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.renderAllFaces = false;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ErebusMod.proxy.bambooCropRenderID;
	}
}