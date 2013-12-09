package erebus.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.utils.Utils;

public class BlockPetrifiedChest extends BlockContainer {

	public BlockPetrifiedChest(int id) {
		super(id, Material.rock);
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
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
	public int getRenderType() {
		return 22;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		if (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == blockID)
			setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		else if (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == blockID)
			setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		else if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == blockID)
			setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		else if (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == blockID)
			setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		else
			setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		unifyAdjacentChests(par1World, par2, par3, par4);
		int l = par1World.getBlockId(par2, par3, par4 - 1);
		int i1 = par1World.getBlockId(par2, par3, par4 + 1);
		int j1 = par1World.getBlockId(par2 - 1, par3, par4);
		int k1 = par1World.getBlockId(par2 + 1, par3, par4);

		if (l == blockID)
			unifyAdjacentChests(par1World, par2, par3, par4 - 1);

		if (i1 == blockID)
			unifyAdjacentChests(par1World, par2, par3, par4 + 1);

		if (j1 == blockID)
			unifyAdjacentChests(par1World, par2 - 1, par3, par4);

		if (k1 == blockID)
			unifyAdjacentChests(par1World, par2 + 1, par3, par4);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int l = par1World.getBlockId(par2, par3, par4 - 1);
		int i1 = par1World.getBlockId(par2, par3, par4 + 1);
		int j1 = par1World.getBlockId(par2 - 1, par3, par4);
		int k1 = par1World.getBlockId(par2 + 1, par3, par4);
		byte b0 = 0;
		int l1 = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l1 == 0)
			b0 = 2;

		if (l1 == 1)
			b0 = 5;

		if (l1 == 2)
			b0 = 3;

		if (l1 == 3)
			b0 = 4;

		if (l != blockID && i1 != blockID && j1 != blockID && k1 != blockID)
			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
		else {
			if ((l == blockID || i1 == blockID) && (b0 == 4 || b0 == 5)) {
				if (l == blockID)
					par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
				else
					par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);

				par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
			}

			if ((j1 == blockID || k1 == blockID) && (b0 == 2 || b0 == 3)) {
				if (j1 == blockID)
					par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
				else
					par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);

				par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
			}
		}

		if (par6ItemStack.hasDisplayName())
			((TileEntityPetrifiedWoodChest) par1World.getBlockTileEntity(par2, par3, par4)).setChestGuiName(par6ItemStack.getDisplayName());
	}

	public void unifyAdjacentChests(World par1World, int par2, int par3, int par4) {
		if (!par1World.isRemote) {
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			boolean flag = true;
			int l1;
			int i2;
			boolean flag1;
			byte b0;
			int j2;

			if (l != blockID && i1 != blockID) {
				if (j1 != blockID && k1 != blockID) {
					b0 = 3;

					if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
						b0 = 3;

					if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
						b0 = 2;

					if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
						b0 = 5;

					if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
						b0 = 4;
				} else {
					l1 = par1World.getBlockId(j1 == blockID ? par2 - 1 : par2 + 1, par3, par4 - 1);
					i2 = par1World.getBlockId(j1 == blockID ? par2 - 1 : par2 + 1, par3, par4 + 1);
					b0 = 3;
					flag1 = true;

					if (j1 == blockID)
						j2 = par1World.getBlockMetadata(par2 - 1, par3, par4);
					else
						j2 = par1World.getBlockMetadata(par2 + 1, par3, par4);

					if (j2 == 2)
						b0 = 2;

					if ((Block.opaqueCubeLookup[l] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[i2])
						b0 = 3;

					if ((Block.opaqueCubeLookup[i1] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[l1])
						b0 = 2;
				}
			} else {
				l1 = par1World.getBlockId(par2 - 1, par3, l == blockID ? par4 - 1 : par4 + 1);
				i2 = par1World.getBlockId(par2 + 1, par3, l == blockID ? par4 - 1 : par4 + 1);
				b0 = 5;
				flag1 = true;

				if (l == blockID)
					j2 = par1World.getBlockMetadata(par2, par3, par4 - 1);
				else
					j2 = par1World.getBlockMetadata(par2, par3, par4 + 1);

				if (j2 == 4)
					b0 = 4;

				if ((Block.opaqueCubeLookup[j1] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[i2])
					b0 = 5;

				if ((Block.opaqueCubeLookup[k1] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[l1])
					b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		int l = 0;

		if (par1World.getBlockId(par2 - 1, par3, par4) == blockID)
			++l;

		if (par1World.getBlockId(par2 + 1, par3, par4) == blockID)
			++l;

		if (par1World.getBlockId(par2, par3, par4 - 1) == blockID)
			++l;

		if (par1World.getBlockId(par2, par3, par4 + 1) == blockID)
			++l;

		return l > 1 ? false : isThereANeighborChest(par1World, par2 - 1, par3, par4) ? false : isThereANeighborChest(par1World, par2 + 1, par3, par4) ? false : isThereANeighborChest(par1World, par2, par3, par4 - 1) ? false : !isThereANeighborChest(par1World, par2, par3, par4 + 1);
	}

	private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockId(par2, par3, par4) != blockID ? false : par1World.getBlockId(par2 - 1, par3, par4) == blockID ? true : par1World.getBlockId(par2 + 1, par3, par4) == blockID ? true : par1World.getBlockId(par2, par3, par4 - 1) == blockID ? true : par1World.getBlockId(par2, par3,
		par4 + 1) == blockID;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		TileEntityPetrifiedWoodChest tileentitychest = (TileEntityPetrifiedWoodChest) par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentitychest != null)
			tileentitychest.updateContainingBlockInfo();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntityPetrifiedWoodChest tile = (TileEntityPetrifiedWoodChest) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote)
			return true;
		else {
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_PETRIFIED_CHEST, world, x, y, z);
			return true;
		}
	}

	public static IInventory getInventory(World par1World, int par2, int par3, int par4) {
		Object object = par1World.getBlockTileEntity(par2, par3, par4);
		int blockID = ModBlocks.petrifiedWoodChest.blockID;

		if (object == null)
			return null;
		else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
			return null;
		else if (isOcelotBlockingChest(par1World, par2, par3, par4))
			return null;
		else if (par1World.getBlockId(par2 - 1, par3, par4) == blockID && (par1World.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 - 1, par3, par4)))
			return null;
		else if (par1World.getBlockId(par2 + 1, par3, par4) == blockID && (par1World.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 + 1, par3, par4)))
			return null;
		else if (par1World.getBlockId(par2, par3, par4 - 1) == blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 - 1)))
			return null;
		else if (par1World.getBlockId(par2, par3, par4 + 1) == blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 + 1)))
			return null;
		else {
			if (par1World.getBlockId(par2 - 1, par3, par4) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (TileEntityPetrifiedWoodChest) par1World.getBlockTileEntity(par2 - 1, par3, par4), (IInventory) object);

			if (par1World.getBlockId(par2 + 1, par3, par4) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (IInventory) object, (TileEntityPetrifiedWoodChest) par1World.getBlockTileEntity(par2 + 1, par3, par4));

			if (par1World.getBlockId(par2, par3, par4 - 1) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (TileEntityPetrifiedWoodChest) par1World.getBlockTileEntity(par2, par3, par4 - 1), (IInventory) object);

			if (par1World.getBlockId(par2, par3, par4 + 1) == blockID)
				object = new InventoryLargeChest("container.petrifiedChestDouble", (IInventory) object, (TileEntityPetrifiedWoodChest) par1World.getBlockTileEntity(par2, par3, par4 + 1));

			return (IInventory) object;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityPetrifiedWoodChest();
	}

	public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {
		Iterator iterator = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(par1, par2 + 1, par3, par1 + 1, par2 + 2, par3 + 1)).iterator();
		EntityOcelot entityocelot;

		do {
			if (!iterator.hasNext())
				return false;

			EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();
			entityocelot = entityocelot1;
		} while (!entityocelot.isSitting());

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory(getInventory(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("planks_oak");
	}
}