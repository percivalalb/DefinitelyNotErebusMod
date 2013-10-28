package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.tileentity.TileEntityErebusAltarLightning;


public class BlockErebusAltarLightning extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;
	private int item;
	private int meta;

	public BlockErebusAltarLightning(int id) {
		super(id, Material.rock);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityErebusAltarLightning();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.doesBlockHaveSolidTopSurface(x, y - 1, z) || BlockFence.isIdAFence(world.getBlockId(x, y - 1, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? b : side == 1 ? a : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:blockErebusAltarBreak");
		a = reg.registerIcon("erebus:blockErebusAltarBreak");
		b = reg.registerIcon("erebus:blockErebusAltarBreak");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityErebusAltarLightning te = (TileEntityErebusAltarLightning) world.getBlockTileEntity(x, y, z);
		te.setActive(true);
		world.scheduleBlockUpdate(x, y, z, blockID, tickRate(world));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().itemID == ModItems.wandOfAnimation.itemID) {
				TileEntityErebusAltarLightning te = (TileEntityErebusAltarLightning) world.getBlockTileEntity(x, y, z);
				te.setActive(false);
				return true;
			}

		return false;
	}
	/*
	 * @Override public void updateTick(World world, int x, int y, int z, Random
	 * par5Random) { world.scheduleBlockUpdate(x, y, z, blockID,
	 * tickRate(world));
	 * 
	 * }
	 * 
	 * @Override public int tickRate(World par1World) { return 10; }
	 * 
	 * @Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World
	 * world, int i, int j, int k) { int f = 6; return
	 * AxisAlignedBB.getBoundingBox(i - f, j, k - f, i + 1 + f, j + 3, k + 1 +
	 * f); }
	 * 
	 * @Override public void onEntityCollidedWithBlock(World world, int x, int
	 * y, int z, Entity entity) { TileEntityErebusAltarLightning te =
	 * (TileEntityErebusAltarLightning) world.getBlockTileEntity(x, y, z); if
	 * (te.isActive()) if (entity instanceof EntityLivingBase) if
	 * (((EntityLivingBase)
	 * entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD)) {
	 * double a = entity.posX; double b = entity.boundingBox.minY; double c =
	 * entity.posZ; EntityLightningBolt entitybolt = new
	 * EntityLightningBolt(world, 0D, 0D, 0D);
	 * entitybolt.setLocationAndAngles(a, b, c, 0F, 0F);
	 * world.addWeatherEffect(entitybolt); } }
	 */
}
