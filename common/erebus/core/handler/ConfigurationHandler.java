package erebus.core.handler;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import erebus.ErebusMod;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.utils.IdGenerator;

public class ConfigurationHandler {

	public static Configuration configurationFile;
	private static IdGenerator idGen = new IdGenerator(9706, 2500);

	public static void loadConfig(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		configurationFile = config;
		config.load();

		/*
		 * Blocks
		 */
		ModBlocks.portalErebusID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Erebus Portal", idGen.getNextBlockID()).getInt();

		ModBlocks.umberstoneID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Umberstone Block ID must be below 256").getInt();
		ModBlocks.umbercobbleStairsID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umbercobble Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.umberOreBlockID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of the Umberstone Ores", idGen.getNextBlockID()).getInt();
		ModBlocks.oreFossilID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Fossil Ore", idGen.getNextBlockID()).getInt();
		ModBlocks.redGemID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Red Gem", idGen.getNextBlockID()).getInt();
		ModBlocks.blockAmberID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Amber", idGen.getNextBlockID()).getInt();
		ModBlocks.quickSandID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Quick Sand", idGen.getNextBlockID()).getInt();

		ModBlocks.logErebusGroup1ID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Log - group 1", idGen.getNextBlockID()).getInt();
		ModBlocks.logErebusGroup2ID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Log - group 2", idGen.getNextBlockID()).getInt();
		ModBlocks.planksErebusID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Planks", idGen.getNextBlockID()).getInt();
		ModBlocks.leavesErebusID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Leaves", idGen.getNextBlockID()).getInt();
		ModBlocks.erebusSaplingID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Erebus Saplings", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsAcaciaID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Acacia Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsEucalyptusID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Eucalyptus Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsMahoganyID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Mahogany Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsBaobabID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Baobab Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsMossbarkID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Mossbark Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsPinkID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Pink Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsScorchedID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Scorched Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.stairsAsperID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Asper Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.hollowLogAcaciaID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Hollow Log", idGen.getNextBlockID()).getInt();

		ModBlocks.erebusGrassID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Erebus Grass", idGen.getNextBlockID()).getInt();
		ModBlocks.thornsID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Thorns", idGen.getNextBlockID()).getInt();
		ModBlocks.fernID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Ferns", idGen.getNextBlockID()).getInt();
		ModBlocks.fiddleheadID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Fiddlehead", idGen.getNextBlockID()).getInt();
		ModBlocks.blockTurnipID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Turnips", idGen.getNextBlockID()).getInt();

		ModBlocks.blockSilkID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Silk", idGen.getNextBlockID()).getInt();
		ModBlocks.mirBrickID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Mirbrick", idGen.getNextBlockID()).getInt();
		ModBlocks.petrifiedWoodPlanksID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Petrified Wood Planks", idGen.getNextBlockID()).getInt();
		ModBlocks.petrifiedWoodStairsID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Petrified Wood Stairs", idGen.getNextBlockID()).getInt();
		ModBlocks.petrifiedCraftingTableID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Petrified Crafting Table", idGen.getNextBlockID()).getInt();
		ModBlocks.bambooCrateID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Bamboo Crate", idGen.getNextBlockID()).getInt();
		ModBlocks.umberFurnaceID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umber Furnace ON", idGen.getNextBlockID()).getInt();
		ModBlocks.umberFurnace_onID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umebr Furnace OFF", idGen.getNextBlockID()).getInt();
		ModBlocks.umberPaverID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umebrpaver", idGen.getNextBlockID()).getInt();

		ModBlocks.spiderSpawnerID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Spider Spawners", idGen.getNextBlockID()).getInt();
		ModBlocks.caveSpiderSpawnerID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Cave Spider Spawners", idGen.getNextBlockID()).getInt();

		ModBlocks.erebusOreExtraID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Extra Erebus Ores", idGen.getNextBlockID()).getInt();
		/*
		 * Items
		 */
		ModItems.exoskeletonHelmetID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Exoskeleton Helmet", idGen.getNextItemID()).getInt();
		ModItems.exoskeletonBodyID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Exoskeleton Chestplate", idGen.getNextItemID()).getInt();
		ModItems.exoskeletonLegsID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Exoskeleton Leggings", idGen.getNextItemID()).getInt();
		ModItems.exoskeletonBootsID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Exoskeleton Boots", idGen.getNextItemID()).getInt();
		ModItems.portalActivatorID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Portal Activator", idGen.getNextItemID()).getInt();
		ModItems.fossilClubID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Caveman's Club", idGen.getNextItemID()).getInt();
		ModItems.maxSpeedBowID = config.get(Configuration.CATEGORY_ITEM, "Item ID of MaxSpeed Bow", idGen.getNextItemID()).getInt();
		ModItems.turnipID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Turnips", idGen.getNextItemID()).getInt();
		ModItems.jadeHelmetID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Helmet", idGen.getNextItemID()).getInt();
		ModItems.jadeBodyID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Chestplate", idGen.getNextItemID()).getInt();
		ModItems.jadeLegsID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Leggings", idGen.getNextItemID()).getInt();
		ModItems.jadeBootsID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Boots", idGen.getNextItemID()).getInt();
		ModItems.jadeSwordID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Sword", idGen.getNextItemID()).getInt();
		ModItems.jadePickaxeID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Pickaxe", idGen.getNextItemID()).getInt();
		ModItems.jadeAxeID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Axe", idGen.getNextItemID()).getInt();
		ModItems.jadeShovelID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Shovel", idGen.getNextItemID()).getInt();
		ModItems.jadePaxelID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Paxel", idGen.getNextItemID()).getInt();
		ModItems.jadeHoeID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Jade Hoe", idGen.getNextItemID()).getInt();
		ModItems.compoundGogglesID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Compound Goggles", idGen.getNextItemID()).getInt();
		ModItems.waspSwordID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Wasp Sword", idGen.getNextItemID()).getInt();
		ModItems.metalIngotID = config.get(Configuration.CATEGORY_ITEM, "Item ID of Metal Ingots", idGen.getNextItemID()).getInt();

		/*
		 * Biomes & misc
		 */
		ModBiomes.jungleID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt();
		ModBiomes.desertID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt();
		ModBiomes.savannahID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt();
		ModBiomes.cavernID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Cavern", 154, "Biome IDs must be below 256").getInt();

		ErebusMod.erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt();
		ErebusMod.activateExtraOres = config.get(Configuration.CATEGORY_GENERAL, "Should generate copper, lead, silver and tin?", false).getBoolean(false);
		ErebusMod.beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything").getInt();
		ErebusMod.shouldDoVersionCheck = config.get(Configuration.CATEGORY_GENERAL, "Should do version check?", true).getBoolean(true);

		config.save();
	}

}
