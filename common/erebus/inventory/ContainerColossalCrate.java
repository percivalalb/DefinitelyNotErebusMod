package erebus.inventory;

import invtweaks.api.container.ChestContainer;

import java.util.List;

import erebus.tileentity.TileEntityBamboo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@ChestContainer(rowSize = 12, isLargeChest = false)
public class ContainerColossalCrate extends Container {
	private InventoryPlayer playerInventory;
	public TileEntityBamboo crate1;
	public TileEntityBamboo crate2;
	public TileEntityBamboo crate3;
	public TileEntityBamboo crate4;
	public TileEntityBamboo crate5;
	public TileEntityBamboo crate6;
	public TileEntityBamboo crate7;
	public TileEntityBamboo crate8;
	public List<TileEntityBamboo> crateList;

	public int page = 1;

	public ContainerColossalCrate(InventoryPlayer par1InventoryPlayer, List<TileEntityBamboo> list) {
		this.playerInventory = par1InventoryPlayer;
		this.crate1 = list.get(0);
		this.crate2 = list.get(1);
		this.crate3 = list.get(2);
		this.crate4 = list.get(3);
		this.crate5 = list.get(4);
		this.crate6 = list.get(5);
		this.crate7 = list.get(6);
		this.crate8 = list.get(7);
		this.crateList = list;
		crate1.openChest();
		crate2.openChest();
		crate3.openChest();
		crate4.openChest();
		crate5.openChest();
		crate6.openChest();
		crate7.openChest();
		crate8.openChest();
		this.addSlots();
	}

	public void addSlots() {
		int j;
		int k;
		int slotNo = page * 72 - 72;
		for (j = slotNo; j < slotNo + 72; ++j) {
			int crateNo = getCrateNumberFromSlotNo(j);
			this.addSlotToContainer(new Slot(crateList.get(crateNo), getSlotIDFromSlotNo(j), getSlotXFromSlotNo(j), getSlotYFromSlotNo(j)));
		}

		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 35 + k * 18, 138 + j * 18));
			}
		}

		for (j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(playerInventory, j, 35 + j * 18, 196));
		}
	}

	public void changePage(int i) {
		while (i < 1)
			i += 3;
		while (i > 3)
			i -= 3;
		this.page = i;
		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();
		this.addSlots();
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		if (par1EntityPlayer.worldObj.getWorldTime() % 5 == 0) {
			return this.crate1.isUseableByPlayer(par1EntityPlayer, true);
		}
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < 72) {
				if (!this.mergeItemStack(itemstack1, 72, this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 72, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		crate1.closeChest();
		crate2.closeChest();
		crate3.closeChest();
		crate4.closeChest();
		crate5.closeChest();
		crate6.closeChest();
		crate7.closeChest();
		crate8.closeChest();
	}

	public int getCrateNumberFromSlotNo(int slot) {
		int no = slot % 27;
		int roundedDown = slot - no;
		return roundedDown / 27;
	}

	public int getSlotIDFromSlotNo(int slot) {
		return slot % 27;
	}

	public int getSlotXFromSlotNo(int slot) {
		int coloumOn = slot;
		while (coloumOn >= 12)
			coloumOn -= 12;
		return 8 + coloumOn * 18;
	}

	public int getSlotYFromSlotNo(int slot) {
		while (slot >= 72)
			slot -= 72;
		int no = slot % 6;
		int roundedDown = slot - no;
		int rowOn = roundedDown / 12;
		return 18 + rowOn * 18;
	}
}
