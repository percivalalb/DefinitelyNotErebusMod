package erebus.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockLogErebus;
import erebus.block.BlockPlanksErebus;

public class RecipeHandler {

	public static void inti() {
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, 5), new Object[] {"GGG", "GEG", "GGG", 'G', new ItemStack(ModBlocks.blockAmber, 1, 1), 'E', new ItemStack(ModItems.erebusMaterials, 1, 4)}); 
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), new Object[] {"XXX", "OXO", "   ", 'O', new ItemStack(ModItems.erebusMaterials, 1, 5), 'X', new ItemStack(ModItems.erebusMaterials, 1, 0)});  
		
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), new Object[] {"sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), new Object[] {"s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), new Object[] {"sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), new Object[] {"   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 0), new Object[] {"S", "B", 'S', Item.silk, 'B', new ItemStack(ModItems.erebusMaterials, 1, 0)});  
		
		// Petrified Wood stuffs
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), new Object[] {"xx", "xx", 'x', ModItems.itemPetrifiedWood});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), new Object[] {"xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks});
		
		//Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), new Object[] {"X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), new Object[] {"X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1)});
		GameRegistry.addRecipe(new RecipePaxel());
		
		//Stone tools made from umberstone
		GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[] {"X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[] {"X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1)});

		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), new Object[] {"sss", "sss", "sss", 's', Item.silk});  
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), new Object[] {"ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMahogany), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataMahogany) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataEucalyptus), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataEucalyptus) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAcacia), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataAcacia) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataBaobab), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataBaobab) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMossbark), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataMossbark) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataPink), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataPink) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataScorched), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataScorched) });
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stairsMahogany, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.planksErebus, 1, BlockLogErebus.dataMahogany) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stairsEucalyptus, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.planksErebus, 1, BlockLogErebus.dataEucalyptus) });
		GameRegistry.addRecipe(new ItemStack(ModItems.portalActivator, 1), new Object[] {"  O", " / ", "/  ", 'O', new ItemStack(Item.spiderEye), '/', new ItemStack(Item.stick)});
		GameRegistry.addRecipe(new ItemStack(Item.silk, 9), new Object[] {"#", '#', new ItemStack(ModBlocks.blockSilk)});  
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 1, 15), new Object[] {"#", '#', new ItemStack(ModItems.erebusMaterials, 1, 2)});  
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 6, 15), new Object[] {"#", '#', new ItemStack(ModItems.fossilClub, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {"T", "S", "F", 'F', new ItemStack(Item.feather, 1, 0), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2)});  
		GameRegistry.addRecipe(new ItemStack(ModBlocks.screeBricks, 4), new Object[] {"ss", "ss", 's', ModBlocks.dryScree});  
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {"T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2)});  
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {"T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(Item.flint, 1, 0)});  

		FurnaceRecipes.smelting().addSmelting(ModBlocks.blockAmber.blockID, 0, new ItemStack(ModBlocks.blockAmber, 1, 1), 0.3F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 0, new ItemStack(ModItems.erebusFood, 1, 1), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 2, new ItemStack(ModItems.erebusFood, 1, 3), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 4, new ItemStack(ModItems.erebusFood, 1, 5), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberstone.blockID, 1, new ItemStack(ModBlocks.umberstone, 1), 0.2F);

		OreDictionary.registerOre("blockCobble", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup1, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup2, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planksErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(ModBlocks.erebusSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves",  new ItemStack(ModBlocks.leavesErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreGold_U", new ItemStack(ModBlocks.umberOreBlock, 1, 2));
		OreDictionary.registerOre("oreIron_U", new ItemStack(ModBlocks.umberOreBlock, 1, 1));
		OreDictionary.registerOre("oreLapis_U", new ItemStack(ModBlocks.umberOreBlock, 1, 3));
		OreDictionary.registerOre("oreDiamond_U", new ItemStack(ModBlocks.umberOreBlock, 1, 4));
		OreDictionary.registerOre("oreEmerald_U", new ItemStack(ModBlocks.umberOreBlock, 1, 5));
	}
}
