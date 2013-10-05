package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBambooTorch extends Block {

	@SideOnly(Side.CLIENT)
	private Icon torchIconTop, torchIconBottom;

	public BlockBambooTorch(int id, Material material) {
		super(id, Material.wood);
		setTickRandomly(true);
		setLightValue(0.9F);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 2.0F, 0.625F);
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
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		dropTorchIfCantStay(par1World, par2, par3, par4);
	}

	protected boolean dropTorchIfCantStay(World par1World, int par2, int par3, int par4) {
		if (!canPlaceBlockAt(par1World, par2, par3, par4)) {
			if (par1World.getBlockId(par2, par3, par4) == blockID) {
				dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}
			return false;
		} else
			return true;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return blockID;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		double d0 = par2 + 0.4375F;
		double d1 = par3 + 2.0625F;
		double d2 = par4 + 0.4375F;
		double d3 = par2 + 0.5625F;
		double d4 = par4 + 0.5625F;
		double d5 = par2 + 0.5F;
		double d6 = par3 + 2.25F;
		double d7 = par4 + 0.5F;
		par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1, d4, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d4, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d3, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d3, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d3, d1, d4, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d3, d1, d4, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d5, d6, d7, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d5, d6, d7, 0.0D, 0.0D, 0.0D);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 0 ? torchIconBottom : par1 == 1 ? torchIconTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:blockBambooTorch");// Side
		torchIconTop = reg.registerIcon("erebus:blockBambooTorch_top");// Top
		torchIconBottom = reg.registerIcon("erebus:blockBambooTorch_bottom");
	}
}
