package erebus.core.handler;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.ModItems;

public class ConfigurationHandler {

	public static void loadConfig(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ModBlocks.blockAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber", 2501).getInt();		  
		ModBlocks.brickAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber Bricks", 2502).getInt();		  
		ModBlocks.glassAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber Glass", 2503).getInt();		  
		ModBlocks.portalErebusID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Portal", 2504).getInt();		  
		ModBlocks.woodMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Wood", 2505).getInt();		  
		ModBlocks.leavesMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Leaves", 2506).getInt();		  
		ModBlocks.woodEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Wood", 2507).getInt();		  
		ModBlocks.leavesEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Leaves", 2508).getInt();		  
		ModBlocks.cobbleWebbedID = config.get(config.CATEGORY_BLOCK, "Block ID of Webbed Cobblestone", 2509).getInt();		  
		ModBlocks.oreFossilID = config.get(config.CATEGORY_BLOCK, "Block ID of Fossil Ore", 2510).getInt();		  
		ModBlocks.blockSilkID = config.get(config.CATEGORY_BLOCK, "Block ID of Silk", 2511).getInt();		  
		ModBlocks.planksMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Planks", 2512).getInt();		  
		ModBlocks.planksEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Planks", 2513).getInt();		  
		ModBlocks.mirBrickID = config.get(config.CATEGORY_BLOCK, "Block ID of Mirbrick", 2514).getInt();		  
		ModBlocks.spiderSpawnerID = config.get(config.CATEGORY_BLOCK, "Block ID of Spider Spawners", 2515).getInt();		  
		ModBlocks.caveSpiderSpawnerID = config.get(config.CATEGORY_BLOCK, "Block ID of Cave Spider Spawners", 2516).getInt();		  
		ModBlocks.blockSpiderSilkID = config.get(config.CATEGORY_BLOCK, "Block ID of Spider's Silk", 2517).getInt();		  
		ModBlocks.thornsID = config.get(config.CATEGORY_BLOCK, "Block ID of Thorns", 2518).getInt();		  
		ModBlocks.fernID = config.get(config.CATEGORY_BLOCK, "Block ID of Ferns", 2519).getInt();		  
		ModBlocks.woodAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Wood", 2520).getInt();		  
		ModBlocks.leavesAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Leaves", 2521, "Block IDs must be below 4096").getInt();		  
		ModBlocks.erebusSaplingID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Saplings", 2522).getInt();		  
		ModBlocks.erebusGrassID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Grass", 2523).getInt();		  
		ModBlocks.planksAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Planks", 2524).getInt();		  
		ModBlocks.quickSandID = config.get(config.CATEGORY_BLOCK, "Block ID of Quick Sand", 2525).getInt();		  
		ModBlocks.blockTurnipID = config.get(config.CATEGORY_BLOCK, "Block ID of Turnips", 2526).getInt();		  
		ModBlocks.hollowLogAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Hollow Log", 2527).getInt();
		ModBlocks.oreGold_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Gold Ore", 2528).getInt();
		ModBlocks.oreIron_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Iron Ore", 2529).getInt();
		ModBlocks.oreCoal_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Coal Ore", 2530).getInt();
		ModBlocks.oreLapis_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Lapis Ore", 2531).getInt();
		ModBlocks.oreDiamond_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Diamond Ore", 2532).getInt();
		ModBlocks.oreEmerald_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Emerald Ore", 2533).getInt();
		ModBlocks.oreRedstone_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Redstone Ore", 2534).getInt();
		ModBlocks.oreRedstoneGlowing_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Redstone Glowing Ore", 2535).getInt();
		ModBlocks.oreJade_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Jade Ore", 2536).getInt();
		ModBlocks.umberstoneID = config.get(config.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Block IDs must be below 256").getInt();//ID MUST BE UNDER 256 ( Reason is that this block is the top layer (byte) of biome, and chunkprovider has the same problem.)
		ModBlocks.umbercobbleID = config.get(config.CATEGORY_BLOCK, "Block ID of Umbercobble", 2537).getInt();
		ModBlocks.umbercobbleMossyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mossy Umbercobble", 2538).getInt();
		ModBlocks.umbercobbleWebbedID = config.get(config.CATEGORY_BLOCK, "Block ID of Webbed Umbercobble", 2539).getInt();
		ModBlocks.bambooCrateID = config.get(config.CATEGORY_BLOCK, "Block ID of Bamboo Crate", 2540).getInt();

		ModItems.beetleLarvaRawID = config.get(config.CATEGORY_ITEM, "Item ID of Beetle Larva", 9701).getInt();		  
		ModItems.beetleLarvaCookedID = config.get(config.CATEGORY_ITEM, "Item ID of Cooked Beetle Larva", 9702).getInt();
		ModItems.legCricketID = config.get(config.CATEGORY_ITEM, "Item ID of Cricket Leg", 9703).getInt();
		ModItems.legCricketCookedID = config.get(config.CATEGORY_ITEM, "Item ID of Cooked Cricket Leg", 9704).getInt();
		ModItems.exoskeletonPlateID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Plate", 9705).getInt();
		ModItems.exoskeletonHelmetID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Helmet", 9706).getInt();
		ModItems.exoskeletonBodyID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Chestplate", 9707).getInt();
		ModItems.exoskeletonLegsID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Leggings", 9708).getInt();
		ModItems.exoskeletonBootsID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Boots", 9709).getInt();
		ModItems.portalActivatorID = config.get(config.CATEGORY_ITEM, "Item ID of Portal Activator", 9710).getInt();
		ModItems.fossilShardID = config.get(config.CATEGORY_ITEM, "Item ID of Bone Shard", 9711, "Item IDs must be below 31745").getInt();
		ModItems.fossilClubID = config.get(config.CATEGORY_ITEM, "Item ID of Caveman's Club", 9712).getInt();
		ModItems.maxSpeedBowID = config.get(config.CATEGORY_ITEM, "Item ID of MaxSpeed Bow", 9713).getInt();
		ModItems.legTarantulaID = config.get(config.CATEGORY_ITEM, "Item ID of Tarantula Leg", 9714).getInt();
		ModItems.legTarantulaCookedID = config.get(config.CATEGORY_ITEM, "Item ID of Crispy Tarantula Leg", 9715).getInt();
		ModItems.turnipID = config.get(config.CATEGORY_ITEM, "Item ID of Turnips", 9716).getInt();
		ModItems.flyWingID = config.get(config.CATEGORY_ITEM, "Item ID of Fly Wing", 9717).getInt();
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
		ModItems.compoundEyesID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Eyes", 9728).getInt(); 
		ModItems.compoundLensID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Lens", 9729).getInt(); 
		ModItems.compoundGogglesID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Goggles", 9730).getInt(); 
		
		ErebusMod.jungleID = config.get(config.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt();
		ErebusMod.desertID = config.get(config.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt();
		ErebusMod.savannahID = config.get(config.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt();
		ErebusMod.cavernID = config.get(config.CATEGORY_GENERAL, "Biome ID of Cavern", 154, "Biome IDs must be below 256").getInt();

		ErebusMod.erebusDimensionID = config.get(config.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt();
		config.save();
	}

}
