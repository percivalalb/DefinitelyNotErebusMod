package erebus.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.handler.ConfigurationHandler;
import erebus.core.teleport.TeleportClient;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;

public class BlockPortalErebus extends BlockBreakable {

	public BlockPortalErebus(int id) {
		super(id, "erebus:portalErebus", Material.portal, false);
		setTickRandomly(true);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		if (ConfigurationHandler.spawnPortalMobs&&world.difficultySetting>0&&world.provider.isSurfaceWorld()&&rand.nextInt(100)<3D+world.difficultySetting*0.5D){
			int yy;
			for(yy=y; !world.doesBlockHaveSolidTopSurface(x,yy,z)&&yy>0; --yy);

			if (yy>0&&!world.isBlockNormalCube(x,yy+1,z)){
				EntityLiving entity=null;
				
				if (rand.nextInt(2)==0)entity=new EntityBeetle(world);
				else entity=new EntityBeetleLarva(world);
				
				if (entity!=null){
					entity.setLocationAndAngles(x+0.5D, yy+1.1D, z+0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
					entity.rotationYawHead=entity.renderYawOffset = entity.rotationYaw;
					entity.onSpawnWithEgg((EntityLivingData)null);
					world.spawnEntityInWorld(entity);
                    entity.playLivingSound();
					entity.timeUntilPortal=entity.getPortalCooldown();
				}
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
			if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				ErebusMod.teleportHandler.getPlayer(player.username).setInPortal();
			}
		} else if (side == Side.CLIENT && entity instanceof EntityPlayer) {
			TeleportClient.setInPortal();
			((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 80, 69));
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
		if (iblockaccess.getBlockId(i - 1, j, k) != blockID && iblockaccess.getBlockId(i + 1, j, k) != blockID) {
			float f = 0.125F;
			float f2 = 0.5F;
			setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
		} else {
			float f1 = 0.5F;
			float f3 = 0.125F;
			setBlockBounds(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
		}
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
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		byte b0 = 0;
		byte b1 = 1;

		if (par1World.getBlockId(par2 - 1, par3, par4) == blockID || par1World.getBlockId(par2 + 1, par3, par4) == blockID) {
			b0 = 1;
			b1 = 0;
		}

		int i1;

		for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == blockID; --i1) {
		}

		if (par1World.getBlockId(par2, i1 - 1, par4) != Block.stoneBrick.blockID)
			par1World.setBlockToAir(par2, par3, par4);
		else {
			int j1;

			for (j1 = 1; j1 < 4 && par1World.getBlockId(par2, i1 + j1, par4) == blockID; ++j1) {
			}

			if (j1 == 3 && par1World.getBlockId(par2, i1 + j1, par4) == Block.stoneBrick.blockID) {
				boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == blockID || par1World.getBlockId(par2 + 1, par3, par4) == blockID;
				boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == blockID || par1World.getBlockId(par2, par3, par4 + 1) == blockID;

				if (flag && flag1)
					par1World.setBlockToAir(par2, par3, par4);
				else if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != Block.stoneBrick.blockID || par1World.getBlockId(par2 - b0, par3, par4 - b1) != blockID) &&
				(par1World.getBlockId(par2 - b0, par3, par4 - b1) != Block.stoneBrick.blockID || par1World.getBlockId(par2 + b0, par3, par4 + b1) != blockID))
					par1World.setBlockToAir(par2, par3, par4);
			} else
				par1World.setBlockToAir(par2, par3, par4);
		}
	}

	public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4) {
		byte b0 = 0;
		byte b1 = 0;

		if (par1World.getBlockId(par2 - 1, par3, par4) == Block.stoneBrick.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.stoneBrick.blockID)
			b0 = 1;

		if (par1World.getBlockId(par2, par3, par4 - 1) == Block.stoneBrick.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.stoneBrick.blockID)
			b1 = 1;

		if (b0 == b1)
			return false;
		else {
			if (par1World.getBlockId(par2 - b0, par3, par4 - b1) == 0) {
				par2 -= b0;
				par4 -= b1;
			}

			int l;
			int i1;

			for (l = -1; l <= 2; ++l)
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						int j1 = par1World.getBlockId(par2 + b0 * l, par3 + i1, par4 + b1 * l);

						if (flag) {
							if (j1 != Block.stoneBrick.blockID)
								return false;
						} else if (j1 != 0 && j1 != Block.fire.blockID)
							return false;
					}
				}

			for (l = 0; l < 2; ++l)
				for (i1 = 0; i1 < 3; ++i1)
					par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, ModBlocks.portalErebus.blockID, 0, 2);

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		if (iblockaccess.getBlockId(i, j, k) == blockID)
			return false;
		else {
			boolean flag = iblockaccess.getBlockId(i - 1, j, k) == blockID && iblockaccess.getBlockId(i - 2, j, k) != blockID;
			boolean flag1 = iblockaccess.getBlockId(i + 1, j, k) == blockID && iblockaccess.getBlockId(i + 2, j, k) != blockID;
			boolean flag2 = iblockaccess.getBlockId(i, j, k - 1) == blockID && iblockaccess.getBlockId(i, j, k - 2) != blockID;
			boolean flag3 = iblockaccess.getBlockId(i, j, k + 1) == blockID && iblockaccess.getBlockId(i, j, k + 2) != blockID;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return !flag4 || l != 4 ? !flag4 || l != 5 ? !flag5 || l != 2 ? flag5 && l == 3 : true : true : true;
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (random.nextInt(100) == 0) {
			// TODO world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D,
			// (double)k + 0.5D, "portalambiance", 0.5F, random.nextFloat() *
			// 0.4F + 0.8F);
		}
		for (int l = 0; l < 4; l++) {
			double d = i + random.nextFloat();
			double d1 = j + random.nextFloat();
			double d2 = k + random.nextFloat();
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = random.nextInt(2) * 2 - 1;
			d3 = (random.nextFloat() - 0.5D) * 0.5D;
			d4 = (random.nextFloat() - 0.5D) * 0.5D;
			d5 = (random.nextFloat() - 0.5D) * 0.5D;
			if (world.getBlockId(i - 1, j, k) != blockID && world.getBlockId(i + 1, j, k) != blockID) {
				d = i + 0.5D + 0.25D * i1;
				d3 = random.nextFloat() * 2.0F * i1;
			} else {
				d2 = k + 0.5D + 0.25D * i1;
				d5 = random.nextFloat() * 2.0F * i1;
			}
			world.spawnParticle("smoke", d, d1, d2, d3 / 4D, d4 / 4D, d5 / 4D);
		}
	}

}
