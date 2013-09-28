package erebus.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.client.model.block.ModelBamboo;

@SideOnly(Side.CLIENT)
public class BlockBambooCropRender implements ISimpleBlockRenderingHandler {

	private final ModelBamboo bambooModel = new ModelBamboo();
	private final ResourceLocation bambooResource = new ResourceLocation("erebus:textures/item/bamboo.png");

	public BlockBambooCropRender() {
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// TODO make this stop crashing
		GL11.glPushMatrix();
		GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		// bambooModel.renderModel();
		GL11.glPopMatrix();
		return false;
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