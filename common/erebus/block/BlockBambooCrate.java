package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;

/**
 * @author ProPercivalalb
 */
public class BlockBambooCrate extends BlockContainer {

	private final Random crateRand = new Random();

	public BlockBambooCrate(int id) {
		super(id, Material.wood);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBambooCrate();
	}

	@Override
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:bambooCrate");
	}

	public enum Row {
		BOTTOM, TOP, UNKNOWN;
	}

	public boolean squareCrate(World world, int x, int y, int z) {
		for (int xCount = x - 1; xCount <= x + 2; xCount++)
			for (int yCount = y - 1; yCount <= y + 2; yCount++)
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
						if (blockId == blockID && blockMeta == 1)
							return false;
					} else if (blockId != blockID && blockMeta == 0)
						return false;
				}
		return true;
	}

	public boolean squareCrateExcluede(World world, int x, int y, int z) {
		for (int xCount = x - 1; xCount <= x + 2; xCount++)
			for (int yCount = y - 1; yCount <= y + 2; yCount++)
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

					if (flag1 && !flag5 || flag2 || flag3 || flag4 || flag5 || flag6) {
						if (blockId == blockID)
							return false;
					} else if (blockId != blockID)
						return false;
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
		return squareCrate(world, x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		else {
			TileEntityBambooCrate tileentitybamboocrate = (TileEntityBambooCrate) world.getBlockTileEntity(x, y, z);
			if (tileentitybamboocrate != null)
				if (isValidCrate(world, x, y, z))
					player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_COLOSSAL_CRATE, world, x, y, z);
				else
					player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_BAMBOO_CRATE, world, x, y, z);
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
		TileEntityBambooCrate tileentitycrate = (TileEntityBambooCrate) par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentitycrate != null) {
			for (int j1 = 0; j1 < tileentitycrate.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentitycrate.getStackInSlot(j1);

				if (itemstack != null) {
					float f = crateRand.nextFloat() * 0.8F + 0.1F;
					float f1 = crateRand.nextFloat() * 0.8F + 0.1F;
					float f2 = crateRand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int k1 = crateRand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize)
							k1 = itemstack.stackSize;

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound())
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());

						float f3 = 0.05F;
						entityitem.motionX = (float) crateRand.nextGaussian() * f3;
						entityitem.motionY = (float) crateRand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) crateRand.nextGaussian() * f3;
						par1World.spawnEntityInWorld(entityitem);
					}
				}
			}

			par1World.func_96440_m(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
}