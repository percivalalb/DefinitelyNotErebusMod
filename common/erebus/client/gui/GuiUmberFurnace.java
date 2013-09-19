package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityUmberFurnace;

@SideOnly(Side.CLIENT)
public class GuiUmberFurnace extends GuiContainer {

	public GuiUmberFurnace(InventoryPlayer inventory, TileEntityUmberFurnace tile) {
		super(new ContainerUmberFurnace(inventory, tile));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

	}
}
