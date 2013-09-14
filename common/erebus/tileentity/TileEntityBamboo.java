package erebus.tileentity;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrate;
import erebus.core.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author ProPercivalalb
 */
public class TileEntityBamboo extends TileEntity implements IInventory {

	private ItemStack[] crateContents = new ItemStack[27];

	/** The custom GUI name of the crate **/
	private String customName;

    public TileEntityBamboo() {}

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.crateContents[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.crateContents[par1] != null) {
            ItemStack itemstack;

            if (this.crateContents[par1].stackSize <= par2) {
                itemstack = this.crateContents[par1];
                this.crateContents[par1] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else {
                itemstack = this.crateContents[par1].splitStack(par2);

                if (this.crateContents[par1].stackSize == 0)
                {
                    this.crateContents[par1] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.crateContents[par1] != null) {
            ItemStack itemstack = this.crateContents[par1];
            this.crateContents[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.crateContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    @Override
    public String getInvName(){
        return this.isInvNameLocalized() ? this.customName : "container.bambooCrate";
    }

    @Override
    public boolean isInvNameLocalized() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCrateGuiName(String par1Str) {
        this.customName = par1Str;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readFromNBT(par1NBTTagCompound);
        if(this.getBlockMetadata() == 1) {
	        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	        this.crateContents = new ItemStack[this.getSizeInventory()];
	
	        if (par1NBTTagCompound.hasKey("CustomName"))
	        {
	            this.customName = par1NBTTagCompound.getString("CustomName");
	        }
	
	        for (int i = 0; i < nbttaglist.tagCount(); ++i)
	        {
	            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
	            int j = nbttagcompound1.getByte("Slot") & 255;
	
	            if (j >= 0 && j < this.crateContents.length)
	            {
	                this.crateContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	            }
	        }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        if(this.getBlockMetadata() == 1) {
	        NBTTagList nbttaglist = new NBTTagList();
	
	        for (int i = 0; i < this.crateContents.length; ++i)
	        {
	            if (this.crateContents[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.crateContents[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }
	
	        par1NBTTagCompound.setTag("Items", nbttaglist);
	
	        if (this.isInvNameLocalized())
	        {
	            par1NBTTagCompound.setString("CustomName", this.customName);
	        }
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
     	BlockBambooCrate crate = (BlockBambooCrate)ModBlocks.bambooCrate;
     	boolean close = this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
     	if(large) {
     		return crate.isValidCrate(worldObj, xCoord, yCoord, zCoord) && close;
     	}
     	
     	return close;
    }

    @Override
    public void openChest() {}
    @Override
    public void closeChest() {}

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
		if(this.getBlockMetadata() == 0) {
			this.getBlockType().setBlockBoundsBasedOnState(worldObj, x, y, z);
			return this.getBlockType().getCollisionBoundingBoxFromPool(worldObj, x, y, z);
		}
        return AxisAlignedBB.getAABBPool().getAABB(x - 1, y - 1, z - 1, x + 2, y + 2, z + 2);
    }
}
