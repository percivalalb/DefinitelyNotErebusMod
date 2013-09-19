package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import erebus.tileentity.TileEntityUmberFurnace;

public class ContainerUmberFurnace extends Container {

	public ContainerUmberFurnace(InventoryPlayer inventory, TileEntityUmberFurnace tile) {
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
