package erebus.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockLogErebus;
import erebus.block.BlockPlanksErebus;
import erebus.item.ItemErebusFood;
import erebus.item.ItemErebusMaterial;

public class RecipeHandler {

	public static void init() {
		// Wood
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAcacia), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataAcacia) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataEucalyptus), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataEucalyptus) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMahogany), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataMahogany) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataBaobab), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataBaobab) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMossbark), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataMossbark) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataPink), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataPink) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataScorched), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataScorched) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAsper), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataAsper) });
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataWhite), "plankWood", "dyeWhite"));

		// Umber stuff
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 0), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 1), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 2), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberFurnace, 1), new Object[] { "###", "# #", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 4), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });

		// Stone tools made from umberstone
		GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[] { "XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[] { "X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[] { "XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[] { "XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[] { "X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });

		// Petrified Wood stuffs
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), new Object[] { "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, 7) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), new Object[] { "xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks });

		// Stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.umbercobbleStairs[i], 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.umberstone, 1, i) });
		for (int i = 0; i < ModBlocks.plankStairs.length; i++)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.plankStairs[i], 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.planksErebus, 1, i) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.amberBrickStairs, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodStairs, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodSlab[0]), new Object[] { "xxx", 'x', ModBlocks.petrifiedWoodPlanks });

		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 0), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 1), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 2), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 3), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 4), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 5), new Object[] { "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 6), new Object[] { "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 7), new Object[] { "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2) });

		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 0), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 1), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 2), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 3), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 4), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 5), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 6), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 7), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 7) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[2], 6, 0), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 8) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[2], 6, 1), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 9) });

		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 0), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 1), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 2), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 3), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 4), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 5), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 6), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 7), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 8), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });

		// Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), new Object[] { "XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), new Object[] { "X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), new Object[] { "XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), new Object[] { "XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), new Object[] { "X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), new Object[] { "XXX", "XSX", "XSX", 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade), 'S', Item.stick });
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHelmet, 1), new Object[] { "###", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBody, 1), new Object[] { "# #", "###", "###", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeLegs, 1), new Object[] { "###", "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBoots, 1), new Object[] { "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });

		// Exoskeleton armor
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), new Object[] { "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), new Object[] { "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), new Object[] { "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), new Object[] { "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, 0) });

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 2, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataRedGem) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataRedGem) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), new Object[] { " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Item.stick, 1, 0) });

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 0), new Object[] { "S", "B", 'S', Item.silk, 'B', new ItemStack(ModItems.erebusMaterials, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate, 1, 1), new Object[] { "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo), 'b', new ItemStack(ModItems.erebusMaterials, 1, 3) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataBambooSoup), new Object[] { new ItemStack(Item.bowlEmpty), new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo),
			new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBambooShoot) });

		// Compound goggles
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, 5), new Object[] { "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.blockAmber, 1, 1), 'G', new ItemStack(ModItems.erebusMaterials, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), new Object[] { "XXX", "OXO", "   ", 'O', new ItemStack(ModItems.erebusMaterials, 1, 5), 'X', new ItemStack(ModItems.erebusMaterials, 1, 0) });

		// Jump Boots
		GameRegistry.addRecipe(new ItemStack(ModItems.jumpBoots), new Object[] { "F F", "BXB", "B B", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'B', new ItemStack(ModItems.erebusMaterials, 1, 9), 'X', new ItemStack(ModItems.exoskeletonBoots, 1) });

		// Sprint Leggings
		GameRegistry.addRecipe(new ItemStack(ModItems.sprintLeggings), new Object[] { "BBB", "BXB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, 8), 'X', new ItemStack(ModItems.exoskeletonLegs, 1) });

		// Wasp Dagger
		GameRegistry.addRecipe(new ItemStack(ModItems.waspDagger), new Object[] { "   ", " W ", " S ", 'W', new ItemStack(ModItems.erebusMaterials, 1, 10), 'S', new ItemStack(Item.stick) });

		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), new Object[] { "sss", "sss", "sss", 's', Item.silk });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), new Object[] { "ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.portalActivator, 1), new Object[] { "  O", " / ", "/  ", 'O', new ItemStack(Item.spiderEye), '/', new ItemStack(Item.stick) });
		GameRegistry.addRecipe(new ItemStack(Item.silk, 9), new Object[] { "#", '#', new ItemStack(ModBlocks.blockSilk) });
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 1, 15), new Object[] { "#", '#', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 6, 15), new Object[] { "#", '#', new ItemStack(ModItems.fossilClub, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(Item.feather, 1, 0), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(Item.flint, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.sprayCan, 9), new Object[] { " B ", "XRX", "XXX", 'X', Item.ingotIron, 'B', Block.woodenButton, 'R', Item.redstone });

		// Furnace smelting
		FurnaceRecipes.smelting().addSmelting(ModBlocks.blockAmber.blockID, 0, new ItemStack(ModBlocks.blockAmber, 1, 1), 0.3F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 0, new ItemStack(ModItems.erebusFood, 1, 1), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 2, new ItemStack(ModItems.erebusFood, 1, 3), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 4, new ItemStack(ModItems.erebusFood, 1, 5), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberstone.blockID, 1, new ItemStack(ModBlocks.umberstone, 1), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 0, new ItemStack(Item.coal, 1), 0.1F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 1, new ItemStack(Item.ingotIron, 1), 0.7F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 2, new ItemStack(Item.ingotGold, 1), 1.0F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 3, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 4, new ItemStack(Item.diamond, 1), 1.0F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 5, new ItemStack(Item.emerald, 1), 1.0F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 6, new ItemStack(ModItems.erebusMaterials, 1, 1), 1.0F);

		// Ore dictionary registrations
		OreDictionary.registerOre("blockCobble", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup1, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup2, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planksErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(ModBlocks.erebusSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ModBlocks.leavesErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreCoal", new ItemStack(ModBlocks.umberOreBlock, 1, 0));
		OreDictionary.registerOre("oreIron", new ItemStack(ModBlocks.umberOreBlock, 1, 1));
		OreDictionary.registerOre("oreGold", new ItemStack(ModBlocks.umberOreBlock, 1, 2));
		OreDictionary.registerOre("oreLapis", new ItemStack(ModBlocks.umberOreBlock, 1, 3));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.umberOreBlock, 1, 4));
		OreDictionary.registerOre("oreEmerald", new ItemStack(ModBlocks.umberOreBlock, 1, 5));
		OreDictionary.registerOre("craftingtable", new ItemStack(ModBlocks.petrifiedCraftingTable));

		if (ErebusMod.activateExtraOres) {
			OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.metalIngot, 1, 0));
			OreDictionary.registerOre("ingotLead", new ItemStack(ModItems.metalIngot, 1, 1));
			OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.metalIngot, 1, 2));
			OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.metalIngot, 1, 3));

			OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.erebusOreExtra, 1, 0));
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.erebusOreExtra, 1, 1));
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.erebusOreExtra, 1, 2));
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.erebusOreExtra, 1, 3));
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.erebusOreExtra, 1, 4));
		}
	}
}
