package erebus.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import erebus.ErebusMod;
import erebus.ModBlocks;

public class BlockInsectRepellent extends Block {

	public BlockInsectRepellent(int id, Material material) {
		super(id, Material.air);
		setTickRandomly(true);
		setTextureName("erebus:blockInsectRepellent");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
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
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		int l = par1World.getBlockId(par2, par3 - 1, par4);
		Block block = Block.blocksList[l];
		if (block == null)
			return false;
		if (block == this && (par1World.getBlockMetadata(par2, par3 - 1, par4) & 7) == 7)
			return true;
		if (!block.isLeaves(par1World, par2, par3 - 1, par4) && !Block.blocksList[l].isOpaqueCube())
			return false;
		return par1World.getBlockMaterial(par2, par3 - 1, par4).blocksMovement();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;

	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
		Random random = world.rand;
		double d0 = 0.0625D;

		for (int l = 0; l < 6; ++l) {
			double particleX = x + random.nextFloat();
			double particleY = y + random.nextFloat();
			double particleZ = z + random.nextFloat();

			if (l == 0 && !world.isBlockOpaqueCube(x, y + 1, z))
				particleY = y + 1 + d0;

			if (l == 1 && !world.isBlockOpaqueCube(x, y - 1, z))
				particleY = y + 0 - d0;

			if (l == 2 && !world.isBlockOpaqueCube(x, y, z + 1))
				particleZ = z + 1 + d0;

			if (l == 3 && !world.isBlockOpaqueCube(x, y, z - 1))
				particleZ = z + 0 - d0;

			if (l == 4 && !world.isBlockOpaqueCube(x + 1, y, z))
				particleX = x + 1 + d0;

			if (l == 5 && !world.isBlockOpaqueCube(x - 1, y, z))
				particleX = x + 0 - d0;

			if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1) {
				ErebusMod.proxy.spawnCustomParticle("repellent",world,particleX,particleY,particleZ,0D,0D,0D);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if (!par1World.isRemote && par5Entity instanceof EntityLiving)
			if (par5Entity.worldObj.getBlockId(par2, par3, par4) == ModBlocks.insectRepellentID && ((EntityLiving) par5Entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD)) {
				int Knockback = 1;
				par5Entity.addVelocity(MathHelper.sin(par5Entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.1D, MathHelper.cos(par5Entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
				par5Entity.worldObj.playSoundAtEntity(par5Entity, "damage.fallbig", 1.0F, 1.0F);
			}
	}

	@ForgeSubscribe
	public void onArthropodSpawn(LivingSpawnEvent.CheckSpawn par1) {
		if (par1.entity instanceof EntityLivingBase)
			if (par1.entity.worldObj.getBlockId((int) par1.x, (int) par1.y, (int) par1.z) == ModBlocks.insectRepellentID)
				if (((EntityLivingBase) par1.entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD))
					par1.setResult(Result.DENY);
	}
}