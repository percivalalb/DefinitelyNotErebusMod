package erebus.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBambooCrate extends TileEntity implements IInventory {

	private ItemStack[] crateContents = new ItemStack[27];

	@Override
	public int getSizeInventory() {
		return crateContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return crateContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (crateContents[slot] != null) {
			ItemStack itemstack;
			if (crateContents[slot].stackSize <= size) {
				itemstack = crateContents[slot];
				crateContents[slot] = null;
				return itemstack;
			} else {
				itemstack = crateContents[slot].splitStack(size);
				if (crateContents[slot].stackSize == 0)
					crateContents[slot] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (crateContents[slot] != null) {
			ItemStack itemstack = crateContents[slot];
			crateContents[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		crateContents[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName() {
		return "container.bambooCrate";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		NBTTagList nbttaglist = data.getTagList("Items");
		crateContents = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < crateContents.length)
				crateContents[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < crateContents.length; ++i)
			if (crateContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				crateContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		data.setTag("Items", nbttaglist);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
}
