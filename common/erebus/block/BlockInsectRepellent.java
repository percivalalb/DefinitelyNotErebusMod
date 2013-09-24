package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import erebus.ModBlocks;
import erebus.client.fx.EntityRepellent;

public class BlockInsectRepellent extends Block {


	public BlockInsectRepellent(int id, Material material) {
		super(id, Material.air);
		setTickRandomly(true);
		setTextureName("erebus:blockInsectRepellent");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
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
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		sparkle(par1World, par2, par3, par4);
	}

	private void sparkle(World par1World, int par2, int par3, int par4) {
		Random random = par1World.rand;
		double d0 = 0.0625D;

		for (int l = 0; l < 6; ++l) {
			double d1 = par2 + random.nextFloat();
			double d2 = par3 + random.nextFloat();
			double d3 = par4 + random.nextFloat();

			if (l == 0 && !par1World.isBlockOpaqueCube(par2, par3 + 1, par4))
				d2 = par3 + 1 + d0;

			if (l == 1 && !par1World.isBlockOpaqueCube(par2, par3 - 1, par4))
				d2 = par3 + 0 - d0;

			if (l == 2 && !par1World.isBlockOpaqueCube(par2, par3, par4 + 1))
				d3 = par4 + 1 + d0;

			if (l == 3 && !par1World.isBlockOpaqueCube(par2, par3, par4 - 1))
				d3 = par4 + 0 - d0;

			if (l == 4 && !par1World.isBlockOpaqueCube(par2 + 1, par3, par4))
				d1 = par2 + 1 + d0;

			if (l == 5 && !par1World.isBlockOpaqueCube(par2 - 1, par3, par4))
				d1 = par2 + 0 - d0;

			if (d1 < par2 || d1 > par2 + 1 || d2 < 0.0D || d2 > par3 + 1 || d3 < par4 || d3 > par4 + 1) {
				EntityFX repellent = new EntityRepellent(par1World, d1, d2, d3, 0.0F, 0.0F, 0.0F);
				Minecraft.getMinecraft().effectRenderer.addEffect(repellent);
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