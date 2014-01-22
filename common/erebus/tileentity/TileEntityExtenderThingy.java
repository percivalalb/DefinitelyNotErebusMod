package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import erebus.ModBlocks;

public class TileEntityExtenderThingy extends TileEntity implements IInventory {

	private boolean extending;
	private int index = 0;
	private ForgeDirection dir = null;
	private final ItemStack[] inventory = new ItemStack[6];

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (dir == null)
			dir = getDirectionFromMetadata(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));

		boolean stop;
		int increment;
		int blockID;
		Block extension = getExtension(dir);

		if (extending) {
			stop = index > 16;
			increment = 1;
			blockID = extension.blockID;
		} else {
			stop = index <= 0;
			increment = -1;
			blockID = 0;
		}
		System.out.println(index);
		if (!stop) {
			int x = xCoord + index * dir.offsetX;
			int y = yCoord + index * dir.offsetY;
			int z = zCoord + index * dir.offsetZ;
			if (x == xCoord && y == yCoord && z == zCoord) {
				index += increment;
				return;
			}

			if (blockID == 0 ? worldObj.getBlockId(x, y, z) == extension.blockID : worldObj.isAirBlock(x, y, z)) {
				if(decreaseInventory(blockID))
					if (addToInventory(x, y, z)) {
						worldObj.setBlock(x, y, z, blockID, getMetaFromDirection(dir), 3);
						if (extending)
							worldObj.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, extension.stepSound.getPlaceSound(), (extension.stepSound.getVolume() + 1.0F) / 2.0F, extension.stepSound.getPitch() * 0.8F);
						else
							worldObj.playAuxSFXAtEntity(null, 2001, x, y, z, extension.blockID + (worldObj.getBlockMetadata(x, y, z) << 12));
					}
				index += increment;
			}
		}
	}

	private boolean addToInventory(int x, int y, int z) {
		int blockID = worldObj.getBlockId(x, y, z);
		int meta = worldObj.getBlockMetadata(x, y, z);

		if (worldObj.isAirBlock(x, y, z))
			return true;
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] == null) {
				inventory[i] = new ItemStack(blockID, 1, meta);
				return true;
			} else if (inventory[i].itemID == blockID && inventory[i].getItemDamage() == meta && inventory[i].stackSize < inventory[i].getMaxStackSize() && inventory[i].stackSize < getInventoryStackLimit()) {
				inventory[i].stackSize++;
				return true;
			}
		return false;
	}

	private boolean decreaseInventory(int blockID) {
		if (blockID == 0)
			return true;
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null && inventory[i].itemID == blockID) {
				inventory[i].stackSize--;
				if (inventory[i].stackSize <= 0)
					inventory[i] = null;
				return true;
			}
		return false;
	}

	private Block getExtension(ForgeDirection dir) {
		return dir == ForgeDirection.UP || dir == ForgeDirection.DOWN ? ModBlocks.bambooPole : ModBlocks.bambooBridge;
	}

	private int getMetaFromDirection(ForgeDirection dir) {
		switch(dir) {
			case UP:
				return 1;
			case DOWN:
				return 1;
			case EAST:
			case WEST:
				return 4;
			case NORTH:
			case SOUTH:
				return 3;
			default:
				return 0;
		}
	}

	private ForgeDirection getDirectionFromMetadata(int meta) {
		switch (meta) {
			case 0:
				return ForgeDirection.DOWN;
			case 1:
				return ForgeDirection.UP;
			case 2:
				return ForgeDirection.NORTH;
			case 3:
				return ForgeDirection.SOUTH;
			case 4:
				return ForgeDirection.WEST;
			case 5:
				return ForgeDirection.EAST;
		}
		return null;
	}

	public void setExtending(boolean extending) {
		this.extending = extending;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (inventory[slot] != null) {
			ItemStack is;

			if (inventory[slot].stackSize <= amount) {
				is = inventory[slot];
				inventory[slot] = null;
				onInventoryChanged();
				return is;
			} else {
				is = inventory[slot].splitStack(amount);

				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;

				onInventoryChanged();
				return is;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack is = inventory[slot];
			inventory[slot] = null;
			return is;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		inventory[slot] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "container.extenderThingy";
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
	public boolean isUseableByPlayer(EntityPlayer player) {
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
		return stack != null && (stack.itemID == ModBlocks.bambooPole.blockID || stack.itemID == ModBlocks.bambooBridge.blockID);
	}
}