package erebus.core.handler;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import erebus.ErebusMod;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockUmberstone;
import erebus.utils.IdGenerator;

public class ConfigurationHandler {

	public static Configuration config;
	private static IdGenerator idGen = new IdGenerator(9706, 2500);

	private static int configBlock(String name) {
		return config.getBlock(name, idGen.getNextBlockID()).getInt(idGen.getLastBlockID());
	}

	private static int configItem(String name) {
		return config.getItem(name, idGen.getNextItemID()).getInt(idGen.getLastItemID());
	}

	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();

			/*
			 * Blocks
			 */
			ModBlocks.portalErebusID = configBlock("Block ID of Erebus Portal");

			ModBlocks.umberstoneID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Umberstone Block ID must be below 256").getInt(255);
			ModBlocks.umberOreBlockID = configBlock("Block ID of the Umberstone Ores");
			ModBlocks.oreFossilID = configBlock("Block ID of Fossil Ore");
			ModBlocks.redGemID = configBlock("Block ID of Red Gem");
			ModBlocks.blockAmberID = configBlock("Block ID of Amber");
			ModBlocks.quickSandID = configBlock("Block ID of Quick Sand");

			ModBlocks.logErebusGroup1ID = configBlock("Block ID of Log - group 1");
			ModBlocks.logErebusGroup2ID = configBlock("Block ID of Log - group 2");
			ModBlocks.planksErebusID = configBlock("Block ID of Planks");
			ModBlocks.leavesErebusID = configBlock("Block ID of Leaves");
			ModBlocks.erebusSaplingID = configBlock("Block ID of Erebus Saplings");
			ModBlocks.hollowLogAcaciaID = configBlock("Block ID of Hollow Log");

			ModBlocks.erebusGrassID = configBlock("Block ID of Erebus Grass");
			ModBlocks.thornsID = configBlock("Block ID of Thorns");
			ModBlocks.fernID = configBlock("Block ID of Ferns");
			ModBlocks.fiddleheadID = configBlock("Block ID of Fiddlehead");
			ModBlocks.blockTurnipID = configBlock("Block ID of Turnips");

			ModBlocks.blockSilkID = configBlock("Block ID of Silk");
			ModBlocks.mirBrickID = configBlock("Block ID of Mirbrick");
			ModBlocks.petrifiedWoodPlanksID = configBlock("Block ID of Petrified Wood Planks");
			ModBlocks.petrifiedCraftingTableID = configBlock("Block ID of Petrified Crafting Table");
			ModBlocks.bambooCrateID = configBlock("Block ID of Bamboo Crate");
			ModBlocks.umberFurnaceID = configBlock("Block ID of Umber Furnace ON");
			ModBlocks.umberFurnace_onID = configBlock("Block ID of Umebr Furnace OFF");
			ModBlocks.umberPaverID = configBlock("Block ID of Umebrpaver");

			ModBlocks.umbercobbleStairsID = new int[BlockUmberstone.iconPaths.length];
			for (int i = 0; i < ModBlocks.umbercobbleStairsID.length; i++)
				ModBlocks.umbercobbleStairsID[i] = configBlock("Block ID of Umbercobble Stairs " + i);
			ModBlocks.stairsAcaciaID = new int[BlockPlanksErebus.plankTypes.length];
			for (int i = 0; i < ModBlocks.stairsAcaciaID.length; i++)
				ModBlocks.stairsAcaciaID[i] = configBlock("Block ID of Plank Stairs " + i);
			ModBlocks.petrifiedWoodStairsID = configBlock("Block ID of Petrified Wood Stairs");

			ModBlocks.wallErebusID = configBlock("Block ID of Wall");

			ModBlocks.spiderSpawnerID = configBlock("Block ID of Spider Spawners");
			ModBlocks.caveSpiderSpawnerID = configBlock("Block ID of Cave Spider Spawners");

			ModBlocks.erebusOreExtraID = configBlock("Block ID of Extra Erebus Ores");
			/*
			 * Items
			 */
			ModItems.erebusFoodID = configItem("Item ID of Erebus Food");
			ModItems.erebusMaterialsID = configItem("Item ID of Erebus Materials");
			ModItems.bamBucketID = configItem("Item ID of Bambucket");
			ModItems.exoskeletonHelmetID = configItem("Item ID of Exoskeleton Helmet");
			ModItems.exoskeletonBodyID = configItem("Item ID of Exoskeleton Chestplate");
			ModItems.exoskeletonLegsID = configItem("Item ID of Exoskeleton Leggings");
			ModItems.exoskeletonBootsID = configItem("Item ID of Exoskeleton Boots");
			ModItems.portalActivatorID = configItem("Item ID of Portal Activator");
			ModItems.fossilClubID = configItem("Item ID of Caveman's Club");
			ModItems.maxSpeedBowID = configItem("Item ID of MaxSpeed Bow");
			ModItems.turnipID = configItem("Item ID of Turnips");
			ModItems.jadeHelmetID = configItem("Item ID of Jade Helmet");
			ModItems.jadeBodyID = configItem("Item ID of Jade Chestplate");
			ModItems.jadeLegsID = configItem("Item ID of Jade Leggings");
			ModItems.jadeBootsID = configItem("Item ID of Jade Boots");
			ModItems.jadeSwordID = configItem("Item ID of Jade Sword");
			ModItems.jadePickaxeID = configItem("Item ID of Jade Pickaxe");
			ModItems.jadeAxeID = configItem("Item ID of Jade Axe");
			ModItems.jadeShovelID = configItem("Item ID of Jade Shovel");
			ModItems.jadePaxelID = configItem("Item ID of Jade Paxel");
			ModItems.jadeHoeID = configItem("Item ID of Jade Hoe");
			ModItems.compoundGogglesID = configItem("Item ID of Compound Goggles");
			ModItems.waspSwordID = configItem("Item ID of Wasp Sword");
			ModItems.metalIngotID = configItem("Item ID of Metal Ingots");
			ModItems.jumpBootsID = configItem("Item ID of Jump Boots");
			ModItems.sprintLeggingsID = configItem("Item ID of Sprint Leggings");

			/*
			 * Biomes & misc
			 */
			ModBiomes.jungleID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt(151);
			ModBiomes.desertID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt(152);
			ModBiomes.savannahID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt(153);
			ModBiomes.cavernID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Cavern", 154, "Biome IDs must be below 256").getInt(154);

			ErebusMod.erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
			ErebusMod.activateExtraOres = config.get(Configuration.CATEGORY_GENERAL, "Should generate copper, lead, silver and tin?", false).getBoolean(false);
			ErebusMod.beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything").getInt(0);
			ErebusMod.grasshopperEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Grasshopper eating settings", 0, "dunno").getInt(0);
			ErebusMod.shouldDoVersionCheck = config.get(Configuration.CATEGORY_GENERAL, "Should do version check?", true).getBoolean(true);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Erebus has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			config.save();
		}
	}
}
