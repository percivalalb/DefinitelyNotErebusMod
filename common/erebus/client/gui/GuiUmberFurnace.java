package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityUmberFurnace;

@SideOnly(Side.CLIENT)
public class GuiUmberFurnace extends GuiContainer {

	private static final ResourceLocation GUI_UMBER_FURNACE = new ResourceLocation("erebus:textures/gui/umberfurnace.png");
	TileEntityUmberFurnace furnace;

	public GuiUmberFurnace(InventoryPlayer inventory, TileEntityUmberFurnace tile) {
		super(new ContainerUmberFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(I18n.getString(furnace.getInvName()), 8, 6, 4210752);
		fontRenderer.drawString(I18n.getString("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_UMBER_FURNACE);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		int i1 = furnace.getScaledFluidAmount(65);
		drawTexturedModalRect(k + 10, l + 75 - i1, 176, 96 - i1, 18, i1);
	}
}
