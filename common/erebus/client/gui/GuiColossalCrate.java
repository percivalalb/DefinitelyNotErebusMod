package erebus.client.gui;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerColossalCrate;
import erebus.network.PacketHandler;
import erebus.tileentity.TileEntityBamboo;

@SideOnly(Side.CLIENT)
public class GuiColossalCrate extends GuiContainer {

	public static final boolean hasInventoryTweaks = Loader.isModLoaded("inventorytweaks");
	private static final ResourceLocation GUI_BAMBOO_CRATE = new ResourceLocation("erebus:textures/gui/collosalcrate.png");
	public TileEntityBamboo crate1;
	public TileEntityBamboo crate2;
	public TileEntityBamboo crate3;
	public TileEntityBamboo crate4;
	public TileEntityBamboo crate5;
	public TileEntityBamboo crate6;
	public TileEntityBamboo crate7;
	public TileEntityBamboo crate8;

	public GuiColossalCrate(InventoryPlayer par1InventoryPlayer, List<TileEntityBamboo> list) {
		super(new ContainerColossalCrate(par1InventoryPlayer, list));
		this.crate1 = list.get(0);
		this.crate2 = list.get(1);
		this.crate3 = list.get(2);
		this.crate4 = list.get(3);
		this.crate5 = list.get(4);
		this.crate6 = list.get(5);
		this.crate7 = list.get(6);
		this.crate8 = list.get(7);
		this.allowUserInput = false;
		this.ySize = 220;
		this.xSize = 230;
	}

	@Override
	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiInvisibleButton(0, guiLeft + 7, guiTop + 4, 17, 11));
		this.buttonList.add(new GuiInvisibleButton(1, guiLeft + 205 - (hasInventoryTweaks ? 50 : 0), guiTop + 4, 17, 11));
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	public int getPageNumber() {
		return ((ContainerColossalCrate) inventorySlots).page;
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			int newPage = 1;
			switch (button.id) {
				case 0:
					newPage = getPageNumber() - 1;
					this.mc.getNetHandler().addToSendQueue(PacketHandler.buildPacket(1, newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
				case 1:
					newPage = getPageNumber() + 1;
					this.mc.getNetHandler().addToSendQueue(PacketHandler.buildPacket(1, newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(I18n.getString("container.colossalCrate"), 28, 6, 4210752);
		String str = getPageNumber() + "/3";
		this.fontRenderer.drawString(str, this.xSize / 2 - this.fontRenderer.getStringWidth(str) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 32, this.ySize - 96 + 3, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_BAMBOO_CRATE);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
