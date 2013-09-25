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

public class ConfigurationHandler {

	public static Configuration config;

	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();

			/*
			 * Blocks
			 */
			ModBlocks.portalErebusID = config.getBlock("Block ID of Erebus Portal", 2500).getInt();

			ModBlocks.umberstoneID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Umberstone Block ID must be below 256").getInt(255);
			ModBlocks.umberOreBlockID = config.getBlock("Block ID of the Umberstone Ores", 2501).getInt(2501);
			ModBlocks.oreFossilID = config.getBlock("Block ID of Fossil Ore", 2502).getInt(2502);
			ModBlocks.redGemID = config.getBlock("Block ID of Red Gem", 2503).getInt(2503);
			ModBlocks.blockAmberID = config.getBlock("Block ID of Amber", 2504).getInt(2504);
			ModBlocks.quickSandID = config.getBlock("Block ID of Quick Sand", 2505).getInt(2505);
			ModBlocks.erebusOreExtraID = config.getBlock("Block ID of Extra Erebus Ores", 2506).getInt(2506);
			// available: 2507-2512
			
			ModBlocks.logErebusGroup1ID = config.getBlock("Block ID of Log - group 1", 2513).getInt(2513);
			ModBlocks.logErebusGroup2ID = config.getBlock("Block ID of Log - group 2", 2514).getInt(2514);
			ModBlocks.planksErebusID = config.getBlock("Block ID of Planks", 2515).getInt(2515);
			ModBlocks.leavesErebusID = config.getBlock("Block ID of Leaves", 2516).getInt(2516);
			ModBlocks.erebusSaplingID = config.getBlock("Block ID of Erebus Saplings", 2517).getInt(2517);
			ModBlocks.hollowLogAcaciaID = config.getBlock("Block ID of Hollow Log", 2518).getInt(2518);
			// available: 2519-2525
			
			ModBlocks.erebusGrassID = config.getBlock("Block ID of Erebus Grass", 2525).getInt(2525);
			ModBlocks.thornsID = config.getBlock("Block ID of Thorns", 2526).getInt(2526);
			ModBlocks.fernID = config.getBlock("Block ID of Ferns", 2527).getInt(2527);
			ModBlocks.fiddleheadID = config.getBlock("Block ID of Fiddlehead", 2528).getInt(2528);
			ModBlocks.blockTurnipID = config.getBlock("Block ID of Turnips", 2529).getInt(2529);
			// available: 2530-2540
			
			ModBlocks.blockSilkID = config.getBlock("Block ID of Silk", 2541).getInt(2541);
			ModBlocks.mirBrickID = config.getBlock("Block ID of Mirbrick", 2542).getInt(2542);
			ModBlocks.petrifiedWoodPlanksID = config.getBlock("Block ID of Petrified Wood Planks", 2543).getInt(2543);
			ModBlocks.petrifiedCraftingTableID = config.getBlock("Block ID of Petrified Crafting Table", 2544).getInt(2544);
			ModBlocks.bambooCrateID = config.getBlock("Block ID of Bamboo Crate", 2545).getInt(2545);
			ModBlocks.umberFurnaceID = config.getBlock("Block ID of Umber Furnace ON", 2546).getInt(2546);
			ModBlocks.umberFurnace_onID = config.getBlock("Block ID of Umebr Furnace OFF", 2547).getInt(2547);
			ModBlocks.umberPaverID = config.getBlock("Block ID of Umebrpaver", 2548).getInt(2548);
			ModBlocks.insectRepellentID = config.getBlock("Block ID of Insect Repellent Block", 2549).getInt(2549);
			// available: 2550-2564
			
			int id=2565;
			ModBlocks.umbercobbleStairsID = new int[BlockUmberstone.iconPaths.length];
			for (int i = 0; i < ModBlocks.umbercobbleStairsID.length; i++) {
				ModBlocks.umbercobbleStairsID[i] = config.getBlock("Block ID of Umbercobble Stairs " + i, id).getInt(id++);
			}
			ModBlocks.plankStairsID = new int[BlockPlanksErebus.plankTypes.length];
			for (int i = 0; i < ModBlocks.plankStairsID.length; i++) {
				ModBlocks.plankStairsID[i] = config.getBlock("Block ID of Plank Stairs " + i, id).getInt(id++);
			}
			// 25 blocks for stairs
			ModBlocks.petrifiedWoodStairsID = config.getBlock("Block ID of Petrified Wood Stairs", 2590).getInt(2590);
			ModBlocks.stoneSlabsID = new int[2];
			for (int i = 0; i < ModBlocks.stoneSlabsID.length; i++) {
				ModBlocks.stoneSlabsID[i] = config.getBlock("Block ID of Stone Slabs " + i, 2591 + i).getInt(2591 + i);
			}
			ModBlocks.plankSlabsID = new int[2];
			for (int i = 0; i < ModBlocks.plankSlabsID.length; i++) {
				ModBlocks.plankSlabsID[i] = config.getBlock("Block ID of Plank Slabs " + i, 2593 + i).getInt(2593 + i);
			}
			ModBlocks.wallErebusID = config.getBlock("Block ID of Wall", 2595).getInt(2595);
			// available: 2596-2605
			
			ModBlocks.spiderSpawnerID = config.getBlock("Block ID of Spider Spawners", 2606).getInt(2606);
			ModBlocks.caveSpiderSpawnerID = config.getBlock("Block ID of Cave Spider Spawners", 2607).getInt(2607);

			/*
			 * Items
			 */
			ModItems.portalActivatorID = config.getItem("Item ID of Portal Activator", 9706).getInt(9706);
			ModItems.erebusMaterialsID = config.getItem("Item ID of Erebus Materials", 9707).getInt(9707);
			ModItems.erebusFoodID = config.getItem("Item ID of Erebus Food", 9708).getInt(9708);
			ModItems.metalIngotID = config.getItem("Item ID of Metal Ingots", 9709).getInt(9709);
			ModItems.bamBucketID = config.getItem("Item ID of Bambucket", 9710).getInt(9710);
			ModItems.turnipID = config.getItem("Item ID of Turnips", 9711).getInt(9711);
			ModItems.sprayCanID = config.getItem("Item ID of Insect Repellent", 9712).getInt(9712);
			// available: 9713-9725
			
			ModItems.jadeHelmetID = config.getItem("Item ID of Jade Helmet", 9726).getInt(9726);
			ModItems.jadeBodyID = config.getItem("Item ID of Jade Chestplate", 9727).getInt(9727);
			ModItems.jadeLegsID = config.getItem("Item ID of Jade Leggings", 9728).getInt(9728);
			ModItems.jadeBootsID = config.getItem("Item ID of Jade Boots", 9729).getInt(9729);
			ModItems.jadeSwordID = config.getItem("Item ID of Jade Sword", 9730).getInt(9730);
			ModItems.jadePickaxeID = config.getItem("Item ID of Jade Pickaxe", 9731).getInt(9731);
			ModItems.jadeAxeID = config.getItem("Item ID of Jade Axe", 9732).getInt(9732);
			ModItems.jadeShovelID = config.getItem("Item ID of Jade Shovel", 9733).getInt(9733);
			ModItems.jadePaxelID = config.getItem("Item ID of Jade Paxel", 9734).getInt(9734);
			ModItems.jadeHoeID = config.getItem("Item ID of Jade Hoe", 9735).getInt(9735);
			// available: 9736-9740
			
			ModItems.exoskeletonHelmetID = config.getItem("Item ID of Exoskeleton Helmet", 9741).getInt(9741);
			ModItems.exoskeletonBodyID = config.getItem("Item ID of Exoskeleton Chestplate", 9742).getInt(9742);
			ModItems.exoskeletonLegsID = config.getItem("Item ID of Exoskeleton Leggings", 9743).getInt(9743);
			ModItems.exoskeletonBootsID = config.getItem("Item ID of Exoskeleton Boots", 9744).getInt(9744);
			// available: 9745-9754
			
			ModItems.fossilClubID = config.getItem("Item ID of Caveman's Club", 9755).getInt(9755);
			ModItems.waspSwordID = config.getItem("Item ID of Wasp Sword", 9756).getInt(9756);
			ModItems.maxSpeedBowID = config.getItem("Item ID of MaxSpeed Bow", 9757).getInt(9757);
			// available: 9758-9770
			
			ModItems.compoundGogglesID = config.getItem("Item ID of Compound Goggles", 9771).getInt(9771);
			ModItems.sprintLeggingsID = config.getItem("Item ID of Sprint Leggings", 9772).getInt(9772);
			ModItems.jumpBootsID = config.getItem("Item ID of Jump Boots", 9773).getInt(9773);
			
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
			ErebusMod.shouldDoVersionCheck = config.get(Configuration.CATEGORY_GENERAL, "Should do version check?", true).getBoolean(true);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Erebus has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			config.save();
		}
	}
}
