package erebus.core.proxy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.block.BlockBambooCrate;
import erebus.client.gui.GuiBambooCrate;
import erebus.client.gui.GuiColossalCrate;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.client.gui.GuiUmberFurnace;
import erebus.inventory.ContainerBambooCrate;
import erebus.inventory.ContainerColossalCrate;
import erebus.inventory.ContainerPetrifiedCraftingTable;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntitySpawner;
import erebus.tileentity.TileEntityUmberFurnace;

public class CommonProxy implements IGuiHandler {

	public static final int GUI_ID_BAMBOO_CRATE = 1;
	public static final int GUI_ID_COLOSSAL_CRATE = 2;
	public static final int GUI_ID_PETRIFIED_CRAFT = 3;
	public static final int GUI_ID_UMBER_FURNACE = 4;
	public final int bambooCropRenderID = RenderingRegistry.getNextAvailableRenderId();
	public final int hollowLogRenderID = RenderingRegistry.getNextAvailableRenderId();

	public void registerRenderInformation() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySpawner.class, "Custom Spawner (Erebus)");
		GameRegistry.registerTileEntity(TileEntityBambooCrate.class, "Bamboo Crate (Erebus)");
		GameRegistry.registerTileEntity(TileEntityUmberFurnace.class, "Umber Furnace (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltar.class, "Tile Entity Bug Zapper (Erebus)");
	}

	public void handleParticlePacket(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		// Unused server side. -- see ClientProxy for implementation
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate)
				return new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate) tileentity);
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate) {
				if (world.getBlockId(x, y - 1, z) == ModBlocks.bambooCrate.blockID)
					y--;
				if (world.getBlockId(x - 1, y, z) == ModBlocks.bambooCrate.blockID)
					x--;
				if (world.getBlockId(x, y, z - 1) == ModBlocks.bambooCrate.blockID)
					z--;
				if (BlockBambooCrate.squareCrate(world, x, y, z)) {
					List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
					int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };
					for (int[] place : places) {
						TileEntity tile;
						tile = world.getBlockTileEntity(x + place[0], y + place[1], z + place[2]);
						if (tile != null && tile instanceof TileEntityBambooCrate) {
							TileEntityBambooCrate tilecrate = (TileEntityBambooCrate) tile;
							list.add(tilecrate);
						} else
							return null;
					}
					return new ContainerColossalCrate(player.inventory, list);
				}
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT)
			return new ContainerPetrifiedCraftingTable(player.inventory, world, x, y, z);

		if (ID == GUI_ID_UMBER_FURNACE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityUmberFurnace)
				return new ContainerUmberFurnace(player.inventory, (TileEntityUmberFurnace) tileentity);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate)
				return new GuiBambooCrate(player.inventory, (TileEntityBambooCrate) tileentity);
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate) {
				BlockBambooCrate crate = (BlockBambooCrate) ModBlocks.bambooCrate;
				if (world.getBlockId(x, y - 1, z) == crate.blockID)
					y--;
				if (world.getBlockId(x - 1, y, z) == crate.blockID)
					x--;
				if (world.getBlockId(x, y, z - 1) == crate.blockID)
					z--;
				if (BlockBambooCrate.squareCrate(world, x, y, z)) {
					List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
					int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };
					for (int[] place : places) {
						TileEntityBambooCrate tilecrate = (TileEntityBambooCrate) world.getBlockTileEntity(x + place[0], y + place[1], z + place[2]);
						list.add(tilecrate);
					}
					return new GuiColossalCrate(player.inventory, list);
				}
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT)
			return new GuiPetrifiedWorkbench(player.inventory, world, x, y, z);
		if (ID == GUI_ID_UMBER_FURNACE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityUmberFurnace)
				return new GuiUmberFurnace(player.inventory, (TileEntityUmberFurnace) tileentity);
		}

		return null;
	}

}