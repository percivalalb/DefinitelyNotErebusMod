package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityEngine;
import erebus.entity.EntityHealer;
import erebus.tileentity.TileEntityErebusAltar;

public class BlockErebusAltar extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;
	private int item;
	private int meta;

	public BlockErebusAltar(int id) {
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
		return new TileEntityErebusAltar();
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().itemID == ModItems.wandOfAnimation.itemID) {
				int type = world.getBlockMetadata(x, y, z);
				switch (type) {
					case 10:
						EntityEngine entityEngineEntity = new EntityEngine(world);
						entityEngineEntity.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
						if (!world.isRemote) {
							world.setBlock(x, y, z, 0);
							world.spawnEntityInWorld(entityEngineEntity);
							return true;
						}
					case 8:
						EntityHealer entityHealer = new EntityHealer(world);
						entityHealer.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
						if (!world.isRemote) {
							world.setBlock(x, y, z, 0);
							world.spawnEntityInWorld(entityHealer);
							return true;
						}
				}
			}
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 1 - f, k + 1 - f);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		double offsetY = 0.9D;
		if (par5Entity instanceof EntityItem)
			if (par5Entity.boundingBox.minY >= par3 + offsetY) {
				ItemStack itemstack = ((EntityItem) par5Entity).getEntityItem();
				int metadata = itemstack.getItemDamage();
				setItemOffering(itemstack.itemID, metadata);
				if (item == ModItems.erebusMaterials.itemID) {
					par1World.spawnParticle("flame", par5Entity.posX, par5Entity.posY + 0.3D, par5Entity.posZ, 0.0D, 0.0D, 0.0D);
					par1World.spawnParticle("smoke", par5Entity.posX, par5Entity.posY, par5Entity.posZ, 0.0D, 0.0D, 0.0D);
					par5Entity.setDead();
					System.out.println("Offering Accepted");
					par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 7);
				}
			}
	}

	private void setItemOffering(int itemID, int metadata) {
		item = itemID;
		meta = metadata;
	}

}