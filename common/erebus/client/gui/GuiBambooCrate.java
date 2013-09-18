package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerBambooCrate;
import erebus.tileentity.TileEntityBamboo;

@SideOnly(Side.CLIENT)
public class GuiBambooCrate extends GuiContainer {
   
	private static final ResourceLocation GUI_BAMBOO_CRATE = new ResourceLocation("erebus:textures/gui/bambooCrate.png");
	private TileEntityBamboo bambooCrateInventory;

    public GuiBambooCrate(InventoryPlayer par1InventoryPlayer, TileEntityBamboo par2TileEntityBambooCrate) {
        super(new ContainerBambooCrate(par1InventoryPlayer, par2TileEntityBambooCrate));
        this.bambooCrateInventory = par2TileEntityBambooCrate;
        this.allowUserInput = false;
        this.ySize = 168;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(this.bambooCrateInventory.isInvNameLocalized() ? this.bambooCrateInventory.getInvName() : I18n.getString(this.bambooCrateInventory.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
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
