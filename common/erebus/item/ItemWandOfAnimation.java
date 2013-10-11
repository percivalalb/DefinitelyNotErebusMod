package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.entity.EntityAnimatedBlock;

public class ItemWandOfAnimation extends Item {

	public static int blockID;
	public static int blockMeta;
	public ItemWandOfAnimation(int id) {
		super(id);
		setFull3D();
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!player.canPlayerEdit(x, y, z, side, stack))
			return false;
		else {
			blockID = world.getBlockId(x, y, z);
			blockMeta = world.getBlockMetadata(x, y, z);
			Block block = Block.blocksList[blockID];
			if (!world.isRemote && block != null && !(block instanceof BlockContainer)) {
				world.setBlockToAir(x, y, z);
				EntityAnimatedBlock entityAnimatedBlock = new EntityAnimatedBlock(world);
				entityAnimatedBlock.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
				entityAnimatedBlock.setBlock(blockID, blockMeta);
				world.spawnEntityInWorld(entityAnimatedBlock);
				return true;
			}
		}
		return false;
	}
}