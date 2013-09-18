package erebus.item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import erebus.core.helper.ReflectionHelper;

public class ItemPaxel extends ItemTool {

	public ItemPaxel(int par1, EnumToolMaterial par3EnumToolMaterial) {
		super(par1, 1.0F, par3EnumToolMaterial, new Block[0]);
	}

	@Override
	public boolean canHarvestBlock(Block par1Block) {
		return Item.axeIron.canHarvestBlock(par1Block) || Item.pickaxeIron.canHarvestBlock(par1Block) || Item.shovelIron.canHarvestBlock(par1Block);
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		// Creates a backup of the Iron tool efficiencies
		float oldSpeedPickaxe = ((ItemTool) Item.pickaxeIron).efficiencyOnProperMaterial;
		float oldSpeedAxe = ((ItemTool) Item.axeIron).efficiencyOnProperMaterial;
		float oldSpeedShovel = ((ItemTool) Item.shovelIron).efficiencyOnProperMaterial;
		// Sets the Iron tools efficiencies to this classes efficiencies
		((ItemTool) Item.pickaxeIron).efficiencyOnProperMaterial = this.efficiencyOnProperMaterial;
		((ItemTool) Item.axeIron).efficiencyOnProperMaterial = this.efficiencyOnProperMaterial;
		((ItemTool) Item.shovelIron).efficiencyOnProperMaterial = this.efficiencyOnProperMaterial;
		float pickaxeSpeed = Item.pickaxeIron.getStrVsBlock(par1ItemStack, par2Block);
		float axeSpeed = Item.axeIron.getStrVsBlock(par1ItemStack, par2Block);
		float shovelSpeed = Item.shovelIron.getStrVsBlock(par1ItemStack, par2Block);
		// Sets the Iron tools efficiencies back to what they were
		((ItemTool) Item.pickaxeIron).efficiencyOnProperMaterial = oldSpeedPickaxe;
		((ItemTool) Item.axeIron).efficiencyOnProperMaterial = oldSpeedAxe;
		((ItemTool) Item.shovelIron).efficiencyOnProperMaterial = oldSpeedShovel;
		if (pickaxeSpeed > 1.0F)
			return pickaxeSpeed;
		if (axeSpeed > 1.0F)
			return axeSpeed;
		if (shovelSpeed > 1.0F)
			return shovelSpeed;
		return 1.0F;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int meta) {
		if (isToolEffective(block, meta)) {
			return efficiencyOnProperMaterial;
		}
		return getStrVsBlock(stack, block);
	}

	public static boolean isToolEffective(Block block, int metadata) {
		List toolClass = (List) ReflectionHelper.getField(ForgeHooks.class, HashMap.class, null, "toolClasses").get(Item.pickaxeIron);
		if (toolClass != null) {
			return ReflectionHelper.getField(ForgeHooks.class, HashSet.class, null, "toolEffectiveness").contains(Arrays.asList(block, metadata, toolClass.get(0)));
		}

		toolClass = (List) ReflectionHelper.getField(ForgeHooks.class, HashMap.class, null, "toolClasses").get(Item.axeIron);
		if (toolClass != null) {
			return ReflectionHelper.getField(ForgeHooks.class, HashSet.class, null, "toolEffectiveness").contains(Arrays.asList(block, metadata, toolClass.get(0)));
		}

		toolClass = (List) ReflectionHelper.getField(ForgeHooks.class, HashMap.class, null, "toolClasses").get(Item.shovelIron);
		if (toolClass != null) {
			return ReflectionHelper.getField(ForgeHooks.class, HashSet.class, null, "toolEffectiveness").contains(Arrays.asList(block, metadata, toolClass.get(0)));
		}

		return false;
	}
}
