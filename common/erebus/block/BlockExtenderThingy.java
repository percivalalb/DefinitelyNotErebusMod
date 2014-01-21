package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityExtenderThingy;

public class BlockExtenderThingy extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private Icon iconTop, iconBottom;

	public BlockExtenderThingy(int id) {
		super(id, Material.wood);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		if (world.getBlockTileEntity(x, y, z) != null)
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_EXTENDER_THINGY, world, x, y, z);

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, BlockPistonBase.determineOrientation(world, x, y, z, player), 2);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		TileEntityExtenderThingy tile = (TileEntityExtenderThingy) world.getBlockTileEntity(x, y, z);
		tile.setExtending(world.isBlockIndirectlyGettingPowered(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtenderThingy();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? iconBottom : side == 1 ? iconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("erebus:extenderThingySide");// Side
		iconTop = iconRegister.registerIcon("erebus:extenderThingyTop");// Top
		iconBottom = iconRegister.registerIcon("erebus:extenderThingyBot");
	}
}