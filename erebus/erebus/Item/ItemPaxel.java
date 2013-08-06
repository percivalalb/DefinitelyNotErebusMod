package erebus.Item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;

public class ItemPaxel extends ItemTool {

	public ItemPaxel(int par1, EnumToolMaterial par3EnumToolMaterial) {
		super(par1, 1.0F, par3EnumToolMaterial, new Block[0]);
	}
	
	@Override
	public boolean canHarvestBlock(Block par1Block) {
	    return Item.axeDiamond.canHarvestBlock(par1Block) || Item.pickaxeDiamond.canHarvestBlock(par1Block) || Item.shovelDiamond.canHarvestBlock(par1Block);
	}
	
	@Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		//Creates a backup of the diamond tool efficiencies
		float oldSpeedPickaxe = ((ItemTool)Item.pickaxeDiamond).efficiencyOnProperMaterial; 
		float oldSpeedAxe = ((ItemTool)Item.axeDiamond).efficiencyOnProperMaterial;
		float oldSpeedShovel = ((ItemTool)Item.shovelDiamond).efficiencyOnProperMaterial;
		//Sets the diamond tools efficiencies to this classes efficiencies
		((ItemTool)Item.pickaxeDiamond).efficiencyOnProperMaterial = this.efficiencyOnProperMaterial;
		((ItemTool)Item.axeDiamond).efficiencyOnProperMaterial = this.efficiencyOnProperMaterial;
		((ItemTool)Item.shovelDiamond).efficiencyOnProperMaterial = this.efficiencyOnProperMaterial;
		float pickaxeSpeed = Item.pickaxeDiamond.getStrVsBlock(par1ItemStack, par2Block);
		float axeSpeed = Item.axeDiamond.getStrVsBlock(par1ItemStack, par2Block);
		float shovelSpeed = Item.shovelDiamond.getStrVsBlock(par1ItemStack, par2Block);
		//Sets the diamond tools efficiencies back to what they were
		((ItemTool)Item.pickaxeDiamond).efficiencyOnProperMaterial = oldSpeedPickaxe;
		((ItemTool)Item.axeDiamond).efficiencyOnProperMaterial = oldSpeedAxe;
		((ItemTool)Item.shovelDiamond).efficiencyOnProperMaterial = oldSpeedShovel;
		if(pickaxeSpeed > 1.0F) return pickaxeSpeed;
		if(axeSpeed > 1.0F) return axeSpeed;
		if(shovelSpeed > 1.0F) return shovelSpeed;
		return 1.0F;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int meta)  {
	    if (ForgeHooks.isToolEffective(stack, block, meta)) {
	        return efficiencyOnProperMaterial;
	    }
	    return getStrVsBlock(stack, block);
	}
	
	//TODO
	//public static boolean isToolEffective(ItemStack stack, Block block, int metadata) {
	    //List toolClass = ForgeHooks.toolClasses.get(stack.getItem());
	    //return toolClass != null && toolEffectiveness.contains(Arrays.asList(block, metadata, toolClass.get(0)));
	//}
}
