package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;

public class ItemWandOfAnimation extends Item {

	public ItemWandOfAnimation(int id) {
		super(id);
		setFull3D();
		setTextureName("paper");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!player.canPlayerEdit(x, y, z, side, stack))
			return false;
		else {
			int blockID = world.getBlockId(x, y, z);
			int blockMeta = world.getBlockMetadata(x, y, z);
			Block block = Block.blocksList[blockID];
			if (!world.isRemote && block != null && canAnimate(block)) {
				EntityAnimatedBlock entityAnimatedBlock;
				if (block.blockID == Block.chest.blockID)
					entityAnimatedBlock = new EntityAnimatedChest(world).setContents((TileEntityChest) world.getBlockTileEntity(x, y, z));
				else
					entityAnimatedBlock = new EntityAnimatedBlock(world);
				world.setBlockToAir(x, y, z);
				entityAnimatedBlock.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
				entityAnimatedBlock.setBlock(blockID, blockMeta);
				world.spawnEntityInWorld(entityAnimatedBlock);
				return true;
			}
		}
		return false;
	}

	private boolean canAnimate(Block block) {
		return block.blockID == Block.chest.blockID || !(block instanceof BlockContainer) && block.blockHardness >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F &&
		block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F;
	}
}