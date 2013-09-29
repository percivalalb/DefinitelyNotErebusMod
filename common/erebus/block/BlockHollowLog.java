package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;

public class BlockHollowLog extends Block {

	@SideOnly(Side.CLIENT)
	private Icon tree_top, tree_side;

	public BlockHollowLog(int id) {
		super(id, Material.wood);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		int k = meta & 12;
		return k == 4 && (side == 1 || side == 0) ? tree_top : k == 0 && (side == 2 || side == 3) ? tree_top : k == 4 && (side == 2 || side == 3) ? tree_top : tree_side;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		tree_top = reg.registerIcon("erebus:log_acacia_top");
		tree_side = reg.registerIcon("erebus:log_acacia");
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return ErebusMod.proxy.hollowLogRenderID;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int s, AxisAlignedBB box, List list, Entity entity) {
		float pixel = 0.0625F; // 1 pixel
		// bottom
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, pixel, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		// top
		setBlockBounds(0.0F, 1.0F - pixel, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		// east
		setBlockBounds(1.0F - pixel, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		// south
		setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F + pixel, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int l = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0)
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		if (l == 1)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		if (l == 2)
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		if (l == 3)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
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
	public int idDropped(int par1, Random par2Random, int par3) {
		return Item.stick.itemID;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1 + par1Random.nextInt(4);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return true;
	}
}