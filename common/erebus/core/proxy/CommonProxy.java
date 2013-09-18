package erebus.core.proxy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrate;
import erebus.client.gui.GuiBambooCrate;
import erebus.client.gui.GuiColossalCrate;
import erebus.inventory.ContainerBambooCrate;
import erebus.inventory.ContainerColossalCrate;
import erebus.inventory.ContainerPetrifiedCraftingTable;
import erebus.tileentity.TileEntityBamboo;

public class CommonProxy implements IGuiHandler {

	public static final int GUI_ID_BAMBOO_CRATE = 1;
	public static final int GUI_ID_COLOSSAL_CRATE = 2;
	public static final int GUI_ID_PETRIFIED_CRAFT = 3;

	/**
	 * Client side only register stuff...
	 */
	public void registerRenderInformation() {
		// Unused server side. -- see ClientProxy for implementation
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBamboo) {
				return new ContainerBambooCrate(player.inventory, (TileEntityBamboo) tileentity);
			}
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBamboo) {
				BlockBambooCrate crate = (BlockBambooCrate) ModBlocks.bambooCrate;
				if (world.getBlockId(x, y - 1, z) == crate.blockID)
					y--;
				if (world.getBlockId(x - 1, y, z) == crate.blockID)
					x--;
				if (world.getBlockId(x, y, z - 1) == crate.blockID)
					z--;
				if (crate.squareCrate(world, x, y, z)) {
					List<TileEntityBamboo> list = new ArrayList<TileEntityBamboo>();
					int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };
					for (int[] place : places) {
						TileEntityBamboo tilecrate = (TileEntityBamboo) world.getBlockTileEntity(x + place[0], y + place[1], z + place[2]);
						list.add(tilecrate);
					}
					return new ContainerColossalCrate(player.inventory, list);
				}
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT) {
			return new ContainerPetrifiedCraftingTable(player.inventory, world, x, y, z);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBamboo) {
				return new GuiBambooCrate(player.inventory, (TileEntityBamboo) tileentity);
			}
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBamboo) {
				BlockBambooCrate crate = (BlockBambooCrate) ModBlocks.bambooCrate;
				if (world.getBlockId(x, y - 1, z) == crate.blockID)
					y--;
				if (world.getBlockId(x - 1, y, z) == crate.blockID)
					x--;
				if (world.getBlockId(x, y, z - 1) == crate.blockID)
					z--;
				if (crate.squareCrate(world, x, y, z)) {
					List<TileEntityBamboo> list = new ArrayList<TileEntityBamboo>();
					int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };
					for (int[] place : places) {
						TileEntityBamboo tilecrate = (TileEntityBamboo) world.getBlockTileEntity(x + place[0], y + place[1], z + place[2]);
						list.add(tilecrate);
					}
					return new GuiColossalCrate(player.inventory, list);
				}
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT)
			return new GuiCrafting(player.inventory, world, x, y, z);

		return null;
	}

}