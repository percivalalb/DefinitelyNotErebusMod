package erebus.client.render.item;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import erebus.client.model.block.ModelHollowLog;

/**
 * @author ProPercivalalb
 */
public class HollowLogItemRenderer implements IItemRenderer {

	private ModelHollowLog modelHollowLog;
	private ResourceLocation resource;

	public HollowLogItemRenderer(ResourceLocation resourceLocation) {
		this.modelHollowLog = new ModelHollowLog();
		this.resource = resourceLocation;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch (type) {
			case ENTITY:
				renderHollowLog(0.0F, 1.0F, 0.0F, 1.0D);
				break;
			case EQUIPPED:
				renderHollowLog(0.5F, 1.5F, 0.5F, 1.0D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderHollowLog(0.5F, 1.5F, 0.5F, 1.0D);
				break;
			case INVENTORY:
				renderHollowLog(0.0F, 1.0F, 0.0F, 1.0D);
				break;
			default:
				break;
		}
	}

	private void renderHollowLog(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(resource);
		GL11.glPushMatrix(); // Start Rendering
		GL11.glTranslatef(x, y, z); // Position
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-90F, 0, 1F, 0);
		GL11.glScaled(size, size, size); // Changes the size (Only really used
											// when reading in the inventory)
		modelHollowLog.renderModel(0.0625F); // Render the Hollow log
		GL11.glPopMatrix(); // End Rendering
	}
}
