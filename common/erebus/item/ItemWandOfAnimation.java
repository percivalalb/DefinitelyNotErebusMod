package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.entity.EntityAnimatedBlock;

public class ItemWandOfAnimation extends Item {

	public ItemWandOfAnimation(int id) {
		super(id);
		setFull3D();
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (stack.stackSize == 0)
			return false;
		else if (!player.canPlayerEdit(x, y, z, side, stack))
			return false;
		else {
			int blockID = world.getBlockId(x, y, z);
			int blockMeta = world.getBlockMetadata(x, y, z);
			Block block = Block.blocksList[blockID];
			if (!world.isRemote && block != null && !(block instanceof BlockContainer)) {
				world.setBlockToAir(x, y, z);
				EntityAnimatedBlock entityAnimatedBlock = new EntityAnimatedBlock(world);
				entityAnimatedBlock.setBlock(blockID, blockMeta);
				entityAnimatedBlock.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
				world.spawnEntityInWorld(entityAnimatedBlock);
				return true;
			}
		}
		return stack != null;
	}
}