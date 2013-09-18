package erebus.core.handler;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import erebus.ErebusMod;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;

public class ConfigurationHandler {

	public static Configuration configurationFile;
	
	public static void loadConfig(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		configurationFile = config;
		config.load();
		
		/*
		 * Blocks
		 */
		ModBlocks.portalErebusID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Portal", 2500).getInt();
		
		ModBlocks.umberstoneID = config.get(config.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Umberstone Block ID must be below 256").getInt();
		ModBlocks.umbercobbleStairsID = config.get(config.CATEGORY_BLOCK, "Block ID of Umbercobble Stairs", 2501).getInt();
		ModBlocks.umberOreBlockID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Ores", 2502).getInt();	  
		ModBlocks.oreFossilID = config.get(config.CATEGORY_BLOCK, "Block ID of Fossil Ore", 2503).getInt();
		ModBlocks.redGemID = config.get(config.CATEGORY_BLOCK, "Block ID of Red Gem", 2504).getInt();
		ModBlocks.blockAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber", 2505).getInt();
		ModBlocks.quickSandID = config.get(config.CATEGORY_BLOCK, "Block ID of Quick Sand", 2506).getInt();
		
		ModBlocks.logErebusGroup1ID = config.get(config.CATEGORY_BLOCK, "Block ID of Log - group 1", 2507).getInt();
		ModBlocks.logErebusGroup2ID = config.get(config.CATEGORY_BLOCK, "Block ID of Log - group 2", 2508).getInt();
		ModBlocks.planksErebusID = config.get(config.CATEGORY_BLOCK, "Block ID of Planks", 2509).getInt();
		ModBlocks.leavesErebusID = config.get(config.CATEGORY_BLOCK, "Block ID of Leaves", 2510).getInt();
		ModBlocks.erebusSaplingID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Saplings", 2511).getInt();
		ModBlocks.stairsAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Stairs", 2512).getInt();
		ModBlocks.stairsEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Stairs", 2513).getInt();
		ModBlocks.stairsMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Stairs", 2514).getInt();
		ModBlocks.stairsBaobabID = config.get(config.CATEGORY_BLOCK, "Block ID of Baobab Stairs", 2515).getInt();
		ModBlocks.stairsMossbarkID = config.get(config.CATEGORY_BLOCK, "Block ID of Mossbark Stairs", 2516).getInt();
		ModBlocks.stairsPinkID = config.get(config.CATEGORY_BLOCK, "Block ID of Pink Stairs", 2517).getInt();
		ModBlocks.stairsScorchedID = config.get(config.CATEGORY_BLOCK, "Block ID of Scorched Stairs", 2518).getInt();
		ModBlocks.hollowLogAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Hollow Log", 2519).getInt();
		
		ModBlocks.erebusGrassID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Grass", 2520).getInt();
		ModBlocks.thornsID = config.get(config.CATEGORY_BLOCK, "Block ID of Thorns", 2521).getInt();
		ModBlocks.fernID = config.get(config.CATEGORY_BLOCK, "Block ID of Ferns", 2522).getInt();
		ModBlocks.blockTurnipID = config.get(config.CATEGORY_BLOCK, "Block ID of Turnips", 2523).getInt();
		
		ModBlocks.blockSilkID = config.get(config.CATEGORY_BLOCK, "Block ID of Silk", 2524).getInt();
		ModBlocks.mirBrickID = config.get(config.CATEGORY_BLOCK, "Block ID of Mirbrick", 2525).getInt();
		ModBlocks.petrifiedWoodPlanksID = config.get(config.CATEGORY_BLOCK, "Block ID of Petrified Wood Planks", 2526).getInt();
		ModBlocks.petrifiedWoodStairsID = config.get(config.CATEGORY_BLOCK, "Block ID of Petrified Wood Stairs", 2527).getInt();
		ModBlocks.petrifiedCraftingTableID = config.get(config.CATEGORY_BLOCK, "Block ID of Petrified Crafting Table", 2528).getInt();
		ModBlocks.bambooCrateID = config.get(config.CATEGORY_BLOCK, "Block ID of Bamboo Crate", 2529).getInt();
		
		ModBlocks.spiderSpawnerID = config.get(config.CATEGORY_BLOCK, "Block ID of Spider Spawners", 2530).getInt();
		ModBlocks.caveSpiderSpawnerID = config.get(config.CATEGORY_BLOCK, "Block ID of Cave Spider Spawners", 2531).getInt();
		
		/*
		 * Items
		 */
		ModItems.exoskeletonHelmetID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Helmet", 9706).getInt();
		ModItems.exoskeletonBodyID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Chestplate", 9707).getInt();
		ModItems.exoskeletonLegsID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Leggings", 9708).getInt();
		ModItems.exoskeletonBootsID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Boots", 9709).getInt();
		ModItems.portalActivatorID = config.get(config.CATEGORY_ITEM, "Item ID of Portal Activator", 9710).getInt();
		ModItems.fossilClubID = config.get(config.CATEGORY_ITEM, "Item ID of Caveman's Club", 9712).getInt();
		ModItems.maxSpeedBowID = config.get(config.CATEGORY_ITEM, "Item ID of MaxSpeed Bow", 9713).getInt();
		ModItems.turnipID = config.get(config.CATEGORY_ITEM, "Item ID of Turnips", 9716).getInt();
		ModItems.jadeHelmetID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Helmet", 9718).getInt();
		ModItems.jadeBodyID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Chestplate", 9719).getInt();
		ModItems.jadeLegsID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Leggings", 9720).getInt();
		ModItems.jadeBootsID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Boots", 9721).getInt();
		ModItems.jadeSwordID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Sword", 9722).getInt();
		ModItems.jadePickaxeID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Pickaxe", 9723).getInt();
		ModItems.jadeAxeID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Axe", 9724).getInt();
		ModItems.jadeShovelID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Shovel", 9725).getInt();
		ModItems.jadePaxelID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Paxel", 9726).getInt();
		ModItems.jadeHoeID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Hoe", 9727).getInt();
		ModItems.compoundGogglesID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Goggles", 9730).getInt(); 
		ModItems.waspSwordID = config.get(config.CATEGORY_ITEM, "Item ID of Wasp Sword", 9731).getInt();
		ModItems.metalIngotID = config.get(config.CATEGORY_ITEM, "Item ID of Metal Ingots", 9732).getInt();
		
		/*
		 * Biomes & misc
		 */
		ModBiomes.jungleID = config.get(config.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt();
		ModBiomes.desertID = config.get(config.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt();
		ModBiomes.savannahID = config.get(config.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt();
		ModBiomes.cavernID = config.get(config.CATEGORY_GENERAL, "Biome ID of Cavern", 154, "Biome IDs must be below 256").getInt();

		ErebusMod.erebusDimensionID = config.get(config.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt();
		
		config.save();
	}

}
