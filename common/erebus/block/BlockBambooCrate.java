package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModItems;
import erebus.core.proxy.CommonProxy;
import erebus.item.ItemErebusMaterial;
import erebus.tileentity.TileEntityBamboo;

/**
 * @author ProPercivalalb
 */
public class BlockBambooCrate extends BlockContainer {

	private final Random crateRand = new Random();

	public BlockBambooCrate(int par1) {
		super(par1, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBamboo();
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return meta == 0 ? ModItems.erebusMaterials.itemID : blockID;
	}

	@Override
	public int damageDropped(int meta) {
		return meta == 0 ? 3 : meta;
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune){
		if (metadata == 0 && world.rand.nextInt(20) == 0){
			ArrayList<ItemStack> ret=new ArrayList<ItemStack>();
			ret.add(new ItemStack(ModItems.erebusMaterials.itemID,1,ItemErebusMaterial.dataBambooShoot));
			return ret;
		}
		else return super.getBlockDropped(world,x,y,z,metadata,fortune);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
			case 0:
				return 1.0F;
			case 1:
				return this.blockHardness;
			default:
				return super.getBlockHardness(world, x, y, z);
		}

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("erebus:bambooCrate");
	}

	public enum Row {
		BOTTOM, TOP, UNKNOWN;
	}

	public boolean squareCrate(World world, int x, int y, int z) {
		for (int xCount = x - 1; xCount <= x + 2; xCount++) {
			for (int yCount = y - 1; yCount <= y + 2; yCount++) {
				for (int zCount = z - 1; zCount <= z + 2; zCount++) {
					int blockId = world.getBlockId(xCount, yCount, zCount);
					int blockMeta = world.getBlockMetadata(xCount, yCount, zCount);

					boolean flag1 = xCount == x - 1;
					boolean flag2 = xCount == x + 2;
					boolean flag3 = yCount == y - 1;
					boolean flag4 = yCount == y + 2;
					boolean flag5 = zCount == z - 1;
					boolean flag6 = zCount == z + 2;

					if (flag1 && (flag5 || flag6))
						continue;
					if (flag2 && (flag5 || flag6))
						continue;
					if (flag3 && (flag1 || flag2 || flag5 || flag6))
						continue;
					if (flag4 && (flag1 || flag2 || flag5 || flag6))
						continue;

					if (flag1 || flag2 || flag3 || flag4 || flag5 || flag6) {
						if (blockId == blockID && blockMeta == 1) {
							return false;
						}
					} else {
						if (blockId != blockID && blockMeta == 0) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean squareCrateExcluede(World world, int x, int y, int z) {
		for (int xCount = x - 1; xCount <= x + 2; xCount++) {
			for (int yCount = y - 1; yCount <= y + 2; yCount++) {
				for (int zCount = z - 1; zCount <= z + 2; zCount++) {
					if (xCount == x && yCount == y && zCount == z)
						continue;
					int blockId = world.getBlockId(xCount, yCount, zCount);

					boolean flag1 = xCount == x - 1;
					boolean flag2 = xCount == x + 2;
					boolean flag3 = yCount == y - 1;
					boolean flag4 = yCount == y + 2;
					boolean flag5 = zCount == z - 1;
					boolean flag6 = zCount == z + 2;

					if (flag1 && (flag5 || flag6))
						continue;
					if (flag2 && (flag5 || flag6))
						continue;
					if (flag3 && (flag1 || flag2 || flag5 || flag6))
						continue;
					if (flag4 && (flag1 || flag2 || flag5 || flag6))
						continue;

					if ((flag1 && !flag5) || flag2 || flag3 || flag4 || flag5 || flag6) {
						if (blockId == blockID) {
							return false;
						}
					} else {
						if (blockId != blockID) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isValidCrate(World world, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) == 1)
			y--;
		if (world.getBlockId(x - 1, y, z) == blockID && world.getBlockMetadata(x - 1, y, z) == 1)
			x--;
		if (world.getBlockId(x, y, z - 1) == blockID && world.getBlockMetadata(x, y, z - 1) == 1)
			z--;
		return this.squareCrate(world, x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.getBlockMetadata(x, y, z) == 0)
			return false;

		if (world.isRemote) {
			return true;
		} else {
			if (isValidCrate(world, x, y, z)) {
				TileEntityBamboo tileentitybamboocrate = (TileEntityBamboo) world.getBlockTileEntity(x, y, z);

				if (tileentitybamboocrate != null) {
					player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_COLOSSAL_CRATE, world, x, y, z);
				}
				return true;
			}

			TileEntityBamboo tileentitybamboocrate = (TileEntityBamboo) world.getBlockTileEntity(x, y, z);

			if (tileentitybamboocrate != null) {
				player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_BAMBOO_CRATE, world, x, y, z);
			}

			return true;
		}
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
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
			return;
		TileEntityBamboo tileentitycrate = (TileEntityBamboo) par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentitycrate != null) {
			for (int j1 = 0; j1 < tileentitycrate.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentitycrate.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.crateRand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.crateRand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.crateRand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int k1 = this.crateRand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(par1World, (double) ((float) par2 + f), (double) ((float) par3 + f1), (double) ((float) par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.crateRand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.crateRand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.crateRand.nextGaussian() * f3);
						par1World.spawnEntityInWorld(entityitem);
					}
				}
			}

			par1World.func_96440_m(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
		int meta = par1IBlockAccess.getBlockMetadata(x, y, z);
		if (meta == 0) {
			this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
		} else if (meta == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId) {
		if (world.getBlockMetadata(x, y, z) == 0) {
			this.checkBlockCoordValid(world, x, y, z);
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack stack) {
		if (stack.getItemDamage() == 0) {
			Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
			if (!((world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) == 0) || (block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP)))) {
				return false;
			}

		}
		return true;
	}

	protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4) {
		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			return super.canBlockStay(world, x, y, z);
		}
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		return (world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) == 0) || (block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP));
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		if (world.getBlockMetadata(x, y, z) == 0) {
			Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
			if (!((world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) == 0) || (block != null && block.isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP)))) {
				world.setBlockToAir(x, y, z);
			}

		}
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
}
