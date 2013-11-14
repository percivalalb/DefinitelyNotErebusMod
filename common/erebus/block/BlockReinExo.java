package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReinExo extends Block {

	@SideOnly(Side.CLIENT)
	private Icon Top, Bottom;

	public BlockReinExo(int id) {
		super(id, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
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
	public Icon getIcon(int par1, int par2) {
		return par1 == 0 ? Bottom : par1 == 1 ? Top : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:blockReinExo");// Side
		Top = reg.registerIcon("erebus:blockReinExo");// Top
		Bottom = reg.registerIcon("erebus:blockReinExo");
	}
}
