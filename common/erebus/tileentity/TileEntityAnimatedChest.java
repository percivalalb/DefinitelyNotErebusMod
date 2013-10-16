package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import erebus.entity.EntityAnimatedChest;

public class TileEntityAnimatedChest extends TileEntityChest {

	private final ItemStack[] chestContents;

	public TileEntityAnimatedChest(EntityAnimatedChest chest) {
		chestContents = chest.inventory;
	}

	@Override
	public Block getBlockType() {
		return Block.chest;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return chestContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (chestContents[slot] != null) {
			ItemStack itemstack;
			if (chestContents[slot].stackSize <= size) {
				itemstack = chestContents[slot];
				chestContents[slot] = null;
				return itemstack;
			} else {
				itemstack = chestContents[slot].splitStack(size);
				if (chestContents[slot].stackSize == 0)
					chestContents[slot] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (chestContents[slot] != null) {
			ItemStack itemstack = chestContents[slot];
			chestContents[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		chestContents[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}
}