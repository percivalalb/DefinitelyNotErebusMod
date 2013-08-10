package erebus.inventory;

import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBambooCrate extends Container
{
	private InventoryPlayer playerInventory; 
	private TileEntityBambooCrate bambooCrateInventory;
	public int numRows = 3;
	
    public ContainerBambooCrate(InventoryPlayer par1InventoryPlayer, TileEntityBambooCrate par2TileEntityBambooCrate)
    {
        this.playerInventory = par1InventoryPlayer;
        this.bambooCrateInventory = par2TileEntityBambooCrate;
        par2TileEntityBambooCrate.openChest();
        int i = (this.numRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < this.numRows; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(par2TileEntityBambooCrate, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, k + j * 9 + 9, 8 + k * 18, 104 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, j, 8 + j * 18, 162 + i));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.bambooCrateInventory.isUseableByPlayer(par1EntityPlayer, false);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        this.bambooCrateInventory.closeChest();
    }
}
