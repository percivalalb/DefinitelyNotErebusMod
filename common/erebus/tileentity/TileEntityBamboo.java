package erebus.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrate;

/**
 * @author ProPercivalalb
 */
public class TileEntityBamboo extends TileEntity implements IInventory {

	private ItemStack[] crateContents = new ItemStack[27];

	/** The custom GUI name of the crate **/
	private String customName;

	public TileEntityBamboo() {
	}

	@Override
	public int getSizeInventory() {
		return crateContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return crateContents[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (crateContents[par1] != null) {
			ItemStack itemstack;

			if (crateContents[par1].stackSize <= par2) {
				itemstack = crateContents[par1];
				crateContents[par1] = null;
				onInventoryChanged();
				return itemstack;
			} else {
				itemstack = crateContents[par1].splitStack(par2);

				if (crateContents[par1].stackSize == 0)
					crateContents[par1] = null;

				onInventoryChanged();
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (crateContents[par1] != null) {
			ItemStack itemstack = crateContents[par1];
			crateContents[par1] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		crateContents[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
			par2ItemStack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return isInvNameLocalized() ? customName : "container.bambooCrate";
	}

	@Override
	public boolean isInvNameLocalized() {
		return customName != null && customName.length() > 0;
	}

	public void setCrateGuiName(String par1Str) {
		customName = par1Str;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		if (true) {
			NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
			crateContents = new ItemStack[getSizeInventory()];

			if (par1NBTTagCompound.hasKey("CustomName"))
				customName = par1NBTTagCompound.getString("CustomName");

			for (int i = 0; i < nbttaglist.tagCount(); ++i) {
				NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j >= 0 && j < crateContents.length)
					crateContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		if (true) {
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < crateContents.length; ++i)
				if (crateContents[i] != null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte) i);
					crateContents[i].writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}

			par1NBTTagCompound.setTag("Items", nbttaglist);

			if (isInvNameLocalized())
				par1NBTTagCompound.setString("CustomName", customName);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return true;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer, boolean large) {
		BlockBambooCrate crate = (BlockBambooCrate) ModBlocks.bambooCrate;
		boolean close = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
		if (large)
			return crate.isValidCrate(worldObj, xCoord, yCoord, zCoord) && close;

		return close;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		int x = xCoord;
		int y = yCoord;
		int z = zCoord;
		if (getBlockMetadata() == 0) {
			getBlockType().setBlockBoundsBasedOnState(worldObj, x, y, z);
			return getBlockType().getCollisionBoundingBoxFromPool(worldObj, x, y, z);
		}
		return AxisAlignedBB.getAABBPool().getAABB(x - 1, y - 1, z - 1, x + 2, y + 2, z + 2);
	}
}
