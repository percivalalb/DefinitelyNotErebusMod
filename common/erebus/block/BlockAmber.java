package erebus.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAmber extends Block {
	public Icon blockAmber;
	public Icon glassAmber;
	public Icon brickAmber;

	public BlockAmber(int par1) {
		super(par1, Material.rock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
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
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		switch (meta) {
			case 0:
				return this.blockAmber;
			case 1:
				return this.glassAmber;
			case 2:
				return this.brickAmber;
		}
		return null;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockAmber = par1IconRegister.registerIcon("erebus:blockAmber");
		this.glassAmber = par1IconRegister.registerIcon("erebus:glassAmber");
		this.brickAmber = par1IconRegister.registerIcon("erebus:brickAmber");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}

	/**
	 * public boolean onBlockActivated(World par1World, int par2, int par3, int
	 * par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8,
	 * float par9) { WorldGenMossbarkTree gen = new WorldGenMossbarkTree();
	 * par1World.setBlock(par2,par3,par4,0); gen.generate(par1World,
	 * par1World.rand ,par2, par3, par4); return true; }
	 **/
}
