package erebus.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import erebus.block.BlockPetrifiedChest;
import erebus.inventory.ContainerPetrifiedWoodChest;

public class TileEntityPetrifiedWoodChest extends TileEntity implements IInventory {
	private ItemStack[] chestContents = new ItemStack[36];

	public boolean adjacentChestChecked;
	public TileEntityPetrifiedWoodChest adjacentChestZNeg, adjacentChestXPos, adjacentChestXNeg, adjacentChestZPosition;

	public float lidAngle;
	public float prevLidAngle;

	public int numUsingPlayers;

	private int ticksSinceSync;
	private String customName;

	@Override
	public int getSizeInventory() {
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return chestContents[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (chestContents[par1] != null) {
			ItemStack itemstack;

			if (chestContents[par1].stackSize <= par2) {
				itemstack = chestContents[par1];
				chestContents[par1] = null;
				onInventoryChanged();
				return itemstack;
			} else {
				itemstack = chestContents[par1].splitStack(par2);

				if (chestContents[par1].stackSize == 0)
					chestContents[par1] = null;

				onInventoryChanged();
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (chestContents[par1] != null) {
			ItemStack itemstack = chestContents[par1];
			chestContents[par1] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		chestContents[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
			par2ItemStack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return isInvNameLocalized() ? customName : "container.petrifiedWoodChest";
	}

	@Override
	public boolean isInvNameLocalized() {
		return customName != null && customName.length() > 0;
	}

	public void setChestGuiName(String par1Str) {
		customName = par1Str;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		chestContents = new ItemStack[getSizeInventory()];

		if (par1NBTTagCompound.hasKey("CustomName"))
			customName = par1NBTTagCompound.getString("CustomName");

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < chestContents.length)
				chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < chestContents.length; ++i)
			if (chestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (isInvNameLocalized())
			par1NBTTagCompound.setString("CustomName", customName);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		adjacentChestChecked = false;
	}

	private void func_90009_a(TileEntityPetrifiedWoodChest par1TileEntityPetrifiedWoodChest, int par2) {
		if (par1TileEntityPetrifiedWoodChest.isInvalid())
			adjacentChestChecked = false;
		else if (adjacentChestChecked)
			switch (par2) {
				case 0:
					if (adjacentChestZPosition != par1TileEntityPetrifiedWoodChest)
						adjacentChestChecked = false;

					break;
				case 1:
					if (adjacentChestXNeg != par1TileEntityPetrifiedWoodChest)
						adjacentChestChecked = false;

					break;
				case 2:
					if (adjacentChestZNeg != par1TileEntityPetrifiedWoodChest)
						adjacentChestChecked = false;

					break;
				case 3:
					if (adjacentChestXPos != par1TileEntityPetrifiedWoodChest)
						adjacentChestChecked = false;
			}
	}

	public void checkForAdjacentChests() {
		if (!adjacentChestChecked) {
			adjacentChestChecked = true;
			adjacentChestZNeg = null;
			adjacentChestXPos = null;
			adjacentChestXNeg = null;
			adjacentChestZPosition = null;

			if (adjacentChestZNeg != null)
				adjacentChestZNeg.func_90009_a(this, 0);

			if (adjacentChestZPosition != null)
				adjacentChestZPosition.func_90009_a(this, 2);

			if (adjacentChestXPos != null)
				adjacentChestXPos.func_90009_a(this, 1);

			if (adjacentChestXNeg != null)
				adjacentChestXNeg.func_90009_a(this, 3);
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		checkForAdjacentChests();
		++ticksSinceSync;
		float f;

		if (!worldObj.isRemote && numUsingPlayers != 0 && (ticksSinceSync + xCoord + yCoord + zCoord) % 200 == 0) {
			numUsingPlayers = 0;
			f = 5.0F;
			List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(xCoord - f, yCoord - f, zCoord - f, xCoord + 1 + f, yCoord + 1 + f, zCoord + 1 + f));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();

				if (entityplayer.openContainer instanceof ContainerPetrifiedWoodChest) {
					IInventory iinventory = ((ContainerPetrifiedWoodChest) entityplayer.openContainer).getLowerChestInventory();

					if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest) iinventory).isPartOfLargeChest(this))
						++numUsingPlayers;
				}
			}
		}

		prevLidAngle = lidAngle;
		f = 0.1F;
		double d0;

		if (numUsingPlayers > 0 && lidAngle == 0.0F && adjacentChestZNeg == null && adjacentChestXNeg == null) {
			double d1 = xCoord + 0.5D;
			d0 = zCoord + 0.5D;

			if (adjacentChestZPosition != null)
				d0 += 0.5D;

			if (adjacentChestXPos != null)
				d1 += 0.5D;

			worldObj.playSoundEffect(d1, yCoord + 0.5D, d0, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
			float f1 = lidAngle;

			if (numUsingPlayers > 0)
				lidAngle += f;
			else
				lidAngle -= f;

			if (lidAngle > 1.0F)
				lidAngle = 1.0F;

			float f2 = 0.5F;

			if (lidAngle < f2 && f1 >= f2 && adjacentChestZNeg == null && adjacentChestXNeg == null) {
				d0 = xCoord + 0.5D;
				double d2 = zCoord + 0.5D;

				if (adjacentChestZPosition != null)
					d2 += 0.5D;

				if (adjacentChestXPos != null)
					d0 += 0.5D;

				worldObj.playSoundEffect(d0, yCoord + 0.5D, d2, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0.0F)
				lidAngle = 0.0F;
		}
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1) {
			numUsingPlayers = par2;
			return true;
		} else
			return super.receiveClientEvent(par1, par2);
	}

	@Override
	public void openChest() {
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		++numUsingPlayers;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
	}

	@Override
	public void closeChest() {
		if (getBlockType() != null && getBlockType() instanceof BlockPetrifiedChest) {
			--numUsingPlayers;
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
		}
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return true;
	}

	@Override
	public void invalidate() {
		super.invalidate();
		updateContainingBlockInfo();
		checkForAdjacentChests();
	}
}