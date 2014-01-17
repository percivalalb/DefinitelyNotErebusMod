package erebus.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityBambooBridge;

public class BlockBambooBridge extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon a, b;
	public BlockBambooBridge(int id) {
		super(id, Material.wood);
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
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBambooBridge();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? b : side == 1 ? a : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon("erebus:bambooBridge");
		a = reg.registerIcon("erebus:bambooBridge");
		b = reg.registerIcon("erebus:bambooBridge");
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l1 == 0)
			b0 = 2;
		if (l1 == 1)
			b0 = 5;
		if (l1 == 2)
			b0 = 3;
		if (l1 == 3)
			b0 = 4;
		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity) {
		float pixel = 0.0625F; // 1 pixel
		int meta = world.getBlockMetadata(x, y, z);
		// bottom
		if (meta == 2 || meta == 3) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, pixel * 2, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);

			// east
			setBlockBounds(1.0F - pixel * 2, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);

			// south
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F + pixel * 2, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
		}

		if (meta == 4 || meta == 5) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, pixel * 2, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);

			// east
			setBlockBounds(0.0F, 0.0F, 1.0F - pixel * 2, 1.0F, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);

			// south
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 0.0F + pixel * 2);
			super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
		}

		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);
	}
}