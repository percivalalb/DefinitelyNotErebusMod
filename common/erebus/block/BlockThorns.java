package erebus.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockThorns extends Block implements IShearable {
	public BlockThorns(int par1) {
		super(par1, Material.vine);
		setTickRandomly(true);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return 20;
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
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float var7 = 1.0F;
		float var8 = 1.0F;
		float var9 = 1.0F;
		float var10 = 0.0F;
		float var11 = 0.0F;
		float var12 = 0.0F;
		boolean var13 = var6 > 0;

		if ((var6 & 2) != 0) {
			var10 = Math.max(var10, 0.0625F);
			var7 = 0.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
			var13 = true;
		}

		if ((var6 & 8) != 0) {
			var7 = Math.min(var7, 0.9375F);
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
			var13 = true;
		}

		if ((var6 & 4) != 0) {
			var12 = Math.max(var12, 0.0625F);
			var9 = 0.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var13 = true;
		}

		if ((var6 & 1) != 0) {
			var9 = Math.min(var9, 0.9375F);
			var12 = 1.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var13 = true;
		}

		if (!var13 && canBePlacedOn(par1IBlockAccess.getBlockId(par2, par3 + 1, par4))) {
			var8 = Math.min(var8, 0.9375F);
			var11 = 1.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
		}

		setBlockBounds(var7, var8, var9, var10, var11, var12);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5) {
		switch (par5) {
			case 1:
				return canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4));
			case 2:
				return canBePlacedOn(par1World.getBlockId(par2, par3, par4 + 1));
			case 3:
				return canBePlacedOn(par1World.getBlockId(par2, par3, par4 - 1));
			case 4:
				return canBePlacedOn(par1World.getBlockId(par2 + 1, par3, par4));
			case 5:
				return canBePlacedOn(par1World.getBlockId(par2 - 1, par3, par4));
			default:
				return false;
		}
	}

	private boolean canBePlacedOn(int par1) {
		if (par1 == 0)
			return false;
		else {
			Block var2 = Block.blocksList[par1];
			return var2.renderAsNormalBlock() && var2.blockMaterial.blocksMovement();
		}
	}

	private boolean canVineStay(World par1World, int par2, int par3, int par4) {
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		int var6 = var5;

		if (var5 > 0)
			for (int var7 = 0; var7 <= 3; ++var7) {
				int var8 = 1 << var7;

				if ((var5 & var8) != 0 && !canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[var7], par3, par4 + Direction.offsetZ[var7])) && (par1World.getBlockId(par2, par3 + 1, par4) != blockID || (par1World.getBlockMetadata(par2, par3 + 1, par4) & var8) == 0))
					var6 &= ~var8;
			}

		if (var6 == 0 && !canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4)))
			return false;
		else {
			if (var6 != var5)
				par1World.setBlock(par2, par3, par4, var6);

			return true;
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		if (!par1World.isRemote && !canVineStay(par1World, par2, par3, par4)) {
			dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote && par1World.rand.nextInt(4) == 0) {
			byte b0 = 4;
			int l = 5;
			boolean flag = false;
			int i1;
			int j1;
			int k1;
			label138:

				for (i1 = par2 - b0; i1 <= par2 + b0; ++i1)
					for (j1 = par4 - b0; j1 <= par4 + b0; ++j1)
						for (k1 = par3 - 1; k1 <= par3 + 1; ++k1)
							if (par1World.getBlockId(i1, k1, j1) == blockID) {
								--l;

								if (l <= 0) {
									flag = true;
									break label138;
								}
							}

			i1 = par1World.getBlockMetadata(par2, par3, par4);
			j1 = par1World.rand.nextInt(6);
			// TODO Direction fix
			k1 = /* Direction.rotateLeft[j1] */0;
			int l1;
			int i2;

			if (j1 == 1 && par3 < 255 && par1World.isAirBlock(par2, par3 + 1, par4)) {
				if (flag)
					return;

				l1 = par1World.rand.nextInt(16) & i1;

				if (l1 > 0) {
					for (i2 = 0; i2 <= 3; ++i2)
						if (!canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[i2], par3 + 1, par4 + Direction.offsetZ[i2])))
							l1 &= ~(1 << i2);

					if (l1 > 0)
						par1World.setBlock(par2, par3 + 1, par4, blockID, l1, 2);
				}
			} else {
				int j2;

				if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0) {
					if (flag)
						return;

					l1 = par1World.getBlockId(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1]);

					if (l1 != 0 && Block.blocksList[l1] != null) {
						if (Block.blocksList[l1].blockMaterial.isOpaque() && Block.blocksList[l1].renderAsNormalBlock())
							par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | 1 << k1, 2);
					} else {
						i2 = k1 + 1 & 3;
						j2 = k1 + 3 & 3;

						if ((i1 & 1 << i2) != 0 && canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2])))
							par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], blockID, 1 << i2, 2);
						else if ((i1 & 1 << j2) != 0 && canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2])))
							par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], blockID, 1 << j2, 2);
						else if ((i1 & 1 << i2) != 0 && par1World.isAirBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2]) &&
						canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[i2])))
							par1World.setBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[i2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[i2], blockID, 1 << (k1 + 2 & 3), 2);
						else if ((i1 & 1 << j2) != 0 && par1World.isAirBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2]) &&
						canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[j2])))
							par1World.setBlock(par2 + Direction.offsetX[k1] + Direction.offsetX[j2], par3, par4 + Direction.offsetZ[k1] + Direction.offsetZ[j2], blockID, 1 << (k1 + 2 & 3), 2);
						else if (canBePlacedOn(par1World.getBlockId(par2 + Direction.offsetX[k1], par3 + 1, par4 + Direction.offsetZ[k1])))
							par1World.setBlock(par2 + Direction.offsetX[k1], par3, par4 + Direction.offsetZ[k1], blockID, 0, 2);
					}
				} else if (par3 > 1) {
					l1 = par1World.getBlockId(par2, par3 - 1, par4);

					if (l1 == 0) {
						i2 = par1World.rand.nextInt(16) & i1;

						if (i2 > 0)
							par1World.setBlock(par2, par3 - 1, par4, blockID, i2, 2);
					} else if (l1 == blockID) {
						i2 = par1World.rand.nextInt(16) & i1;
						j2 = par1World.getBlockMetadata(par2, par3 - 1, par4);

						if (j2 != (j2 | i2))
							par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, j2 | i2, 2);
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
		byte var10 = 0;

		switch (par5) {
			case 2:
				var10 = 1;
				break;
			case 3:
				var10 = 4;
				break;
			case 4:
				var10 = 8;
				break;
			case 5:
				var10 = 2;
		}

		return var10 != 0 ? var10 : par9;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, 0));
		return ret;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		par5Entity.attackEntityFrom(DamageSource.cactus, 1);
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity) {
		return true;
	}
}
