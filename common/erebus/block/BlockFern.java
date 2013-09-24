package erebus.block;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockFern extends BlockUndergroundFlower implements IShearable {

	public BlockFern(int par1) {
		super(par1, Material.vine);
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		if (rand.nextInt(16) == 0) {
			return Item.seeds.itemID;
		} else if (rand.nextInt(15) == 0) {
			return Item.melonSeeds.itemID;
		} else
			return 0;
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("erebus:erebusfern");
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return true;
	}
}
