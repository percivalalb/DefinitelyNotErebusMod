package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityEngine;
import erebus.tileentity.TileEntityLightningAltar;

public class BlockLightningAltar extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;

	public BlockLightningAltar(int id) {
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
		return new TileEntityLightningAltar();
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
		blockIcon = reg.registerIcon("erebus:BlockEngineOfIllapa");
		a = reg.registerIcon("erebus:BlockEngineOfIllapa");
		b = reg.registerIcon("erebus:BlockEngineOfIllapa");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().itemID == ModItems.wandOfAnimation.itemID) {
				EntityEngine entityEngineEntity = new EntityEngine(world);
				entityEngineEntity.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
				if (!world.isRemote) {
					world.setBlock(x, y, z, 0);
					world.spawnEntityInWorld(entityEngineEntity);
				}
				return true;
			}
		return false;
	}

	// Why doesn't this method below work?
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if (par5Entity instanceof EntityItem)
			System.out.println("Entity Landed On Block");
	}

}