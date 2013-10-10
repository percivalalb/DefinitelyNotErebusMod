package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityEngine;
import erebus.tileentity.TileEntityLightningAltar;

public class BlockLightningAltar extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;

	public BlockLightningAltar(int id) {
		super(id, Material.rock);
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
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityLightningAltar();
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || BlockFence.isIdAFence(par1World.getBlockId(par2, par3 - 1, par4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 0 ? b : par1 == 1 ? a : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("engineofillapa:BlockEngineOfIllapa");
		a = par1IconRegister.registerIcon("engineofillapa:BlockEngineOfIllapa");
		b = par1IconRegister.registerIcon("engineofillapa:BlockEngineOfIllapa");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.getCurrentEquippedItem() != null)
			if (par5EntityPlayer.getCurrentEquippedItem().itemID == ModItems.WandOfAnimation.itemID) {
				EntityEngine entityEngineEntity = new EntityEngine(par1World);
				entityEngineEntity.setLocationAndAngles((double) par2 + 0.5F, par3, (double) par4 + 0.5F, 0.0F, 0.0F);
				if (!par1World.isRemote) {
					par1World.setBlock(par2, par3, par4, 0);
					par1World.spawnEntityInWorld(entityEngineEntity);
				}
				return true;
			}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
}