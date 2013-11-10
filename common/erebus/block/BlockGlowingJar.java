package erebus.block;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.tileentity.TileEntityGlowingJar;

public class BlockGlowingJar extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private Icon a, b;
	public BlockGlowingJar(int id) {
		super(id, Material.glass);
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);;
		setLightValue(1.0F);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ModBlocks.glowingJarID;
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
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityGlowingJar();
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || BlockFence.isIdAFence(par1World.getBlockId(par2, par3 - 1, par4)) || isIdAJar(par1World.getBlockId(par2, par3 - 1, par4));
	}

	public static boolean isIdAJar(int id)
	{
		return id == ModBlocks.glowingJarID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:blockBioJarBreak");
		a = reg.registerIcon("erebus:blockBioJarBreak");
		b = reg.registerIcon("erebus:blockBioJarBreak");
	}
}


