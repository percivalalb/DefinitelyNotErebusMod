package erebus.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import erebus.inventory.ContainerBambooCrate;
import erebus.tileentity.TileEntityBambooCrate;

@SideOnly(Side.CLIENT)
public class GuiBambooCrate extends GuiContainer {
   
	private static final ResourceLocation GUI_BAMBOO_CRATE = new ResourceLocation("erebus:textures/gui/bambooCrate.png");
	private InventoryPlayer playerInventory; 
	private TileEntityBambooCrate bambooCrateInventory;

    public GuiBambooCrate(InventoryPlayer par1InventoryPlayer, TileEntityBambooCrate par2TileEntityBambooCrate) {
        super(new ContainerBambooCrate(par1InventoryPlayer, par2TileEntityBambooCrate));
        this.playerInventory = par1InventoryPlayer;
        this.bambooCrateInventory = par2TileEntityBambooCrate;
        this.allowUserInput = false;
        this.ySize = 168;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(this.bambooCrateInventory.isInvNameLocalized() ? this.bambooCrateInventory.getInvName() : I18n.func_135053_a(this.bambooCrateInventory.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(I18n.func_135053_a("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.func_110434_K().func_110577_a(GUI_BAMBOO_CRATE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
