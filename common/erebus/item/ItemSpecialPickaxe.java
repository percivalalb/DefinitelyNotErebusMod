package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemSpecialPickaxe extends ItemPickaxe {

	public ItemSpecialPickaxe(int id) {
		super(id, EnumToolMaterial.EMERALD);
		setCreativeTab(null);
	}

	@Override
	public boolean canHarvestBlock(Block block) {
		return block.blockHardness > 0;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block) {
		return 50F;
	}
}