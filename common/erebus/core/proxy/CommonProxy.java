package erebus.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import erebus.client.gui.GuiBambooCrate;
import erebus.inventory.ContainerBambooCrate;
import erebus.tileentity.TileEntityBambooCrate;

public class CommonProxy implements IGuiHandler {

	public static final int GUI_ID_BAMBOO_CRATE = 1;
	
	/**
	 * Client side only register stuff...
	 */
	public void registerRenderInformation()  {
		//Unused server side. -- see ClientProxy for implementation
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if(tileentity instanceof TileEntityBambooCrate) {
				return new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate)tileentity);
			}
		}
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if(tileentity instanceof TileEntityBambooCrate) {
				return new GuiBambooCrate(player.inventory, (TileEntityBambooCrate)tileentity);
			}
		}
		
		return null;
	}

}