package erebus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockAmber;
import erebus.block.BlockBambooCrate;
import erebus.block.BlockErebusGrass;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.block.BlockFern;
import erebus.block.BlockFiddlehead;
import erebus.block.BlockHollowLog;
import erebus.block.BlockInsectRepellent;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;
import erebus.block.BlockOreFossil;
import erebus.block.BlockPetrifiedCraftingTable;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockPortalErebus;
import erebus.block.BlockQuickSand;
import erebus.block.BlockRedGem;
import erebus.block.BlockSaplingErebus;
import erebus.block.BlockSilk;
import erebus.block.BlockSpiderSpawner;
import erebus.block.BlockStairsErebus;
import erebus.block.BlockThorns;
import erebus.block.BlockTurnip;
import erebus.block.BlockUmberFurnace;
import erebus.block.BlockUmberPaver;
import erebus.block.BlockUmberstone;
import erebus.block.BlockWallErebus;
import erebus.item.block.ItemBlockAmber;
import erebus.item.block.ItemBlockBamboo;
import erebus.item.block.ItemBlockErebusOreExtras;
import erebus.item.block.ItemBlockLeavesErebus;
import erebus.item.block.ItemBlockLogErebus1;
import erebus.item.block.ItemBlockLogErebus2;
import erebus.item.block.ItemBlockPlanksErebus;
import erebus.item.block.ItemBlockRedGem;
import erebus.item.block.ItemBlockUmberOre;
import erebus.item.block.ItemBlockUmberStone;
import erebus.item.block.ItemBlockUmberpaver;
import erebus.item.block.ItemBlockWallErebus;
import erebus.item.block.ItemSapling;
import erebus.tileentity.TileEntityHollowLog;

/**
 * @author ProPercivalalb
 */
public class ModBlocks {

	// PORTAL
	public static BlockPortalErebus portalErebus;		public static int portalErebusID;

	// TERRAIN AND ORES
	public static Block umberstone;         			public static int umberstoneID;
	public static Block umberOreBlock;     				public static int umberOreBlockID;
	public static Block oreFossil;      				public static int oreFossilID;
	public static Block redGem;       					public static int redGemID;
	public static Block blockAmber;      				public static int blockAmberID;
	public static Block quickSand;      				public static int quickSandID;
	public static Block erebusOreExtra;      			public static int erebusOreExtraID;

	// WOOD
	public static Block logErebusGroup1;            	public static int logErebusGroup1ID;
	public static Block logErebusGroup2;             	public static int logErebusGroup2ID;
	public static Block planksErebus;             		public static int planksErebusID;
	public static BlockLeavesErebus leavesErebus;  		public static int leavesErebusID;
	public static Block erebusSapling;     				public static int erebusSaplingID;
	public static Block hollowLogAcacia;    			public static int hollowLogAcaciaID;

	// UNDERGROWTH
	public static Block erebusGrass;     				public static int erebusGrassID;
	public static Block thorns;       					public static int thornsID;
	public static BlockFern fern;      					public static int fernID;
	public static Block blockTurnip;     				public static int blockTurnipID;
	public static Block fiddlehead;      				public static int fiddleheadID;

	// DECORATIONS AND UTILITIES
	public static Block blockSilk;      				public static int blockSilkID;
	public static Block mirBrick;      					public static int mirBrickID;
	public static Block petrifiedWoodPlanks;   			public static int petrifiedWoodPlanksID;
	public static Block petrifiedCraftingTable;  		public static int petrifiedCraftingTableID;
	public static Block bambooCrate;          			public static int bambooCrateID;
	public static Block umberFurnace;					public static int umberFurnaceID;
	public static Block umberFurnace_on;				public static int umberFurnace_onID;
	public static Block umberPaver;						public static int umberPaverID;

	// STAIRS, SLABS, WALLS
	public static Block[] umbercobbleStairs;			public static int[] umbercobbleStairsID;
	public static Block[] stairsAcacia;     			public static int[] stairsAcaciaID;
	public static Block petrifiedWoodStairs;  		  	public static int petrifiedWoodStairsID;

	public static Block wallErebus;						public static int wallErebusID;

	// DUNGEONS
	public static Block spiderSpawner;     				public static int spiderSpawnerID;
	public static Block caveSpiderSpawner;    			public static int caveSpiderSpawnerID;

	// MISC
	public static Block insectRepellent;
	public static int insectRepellentID;
	public static void init() {
		// Block declaration: constructor, hardness, resistance, light value, light opacity, step sound, creative tab, unlocalized name, texture name
		// FIXME Remove the creative tab before release!!!
		portalErebus = (BlockPortalErebus) new BlockPortalErebus(portalErebusID, 221).setHardness(-1F).setLightValue(1.0F).setStepSound(Block.soundGlassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("portalErebus");

		umberstone = new BlockUmberstone(umberstoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("umberstone");
		umberOreBlock = new BlockErebusOre(umberOreBlockID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("oreBlockU");
		oreFossil = new BlockOreFossil(oreFossilID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("oreFossilU").setTextureName("erebus:oreFossil_U");
		redGem = new BlockRedGem(redGemID).setHardness(0.3F).setLightValue(1F).setStepSound(Block.soundGlassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("redGem");
		blockAmber = new BlockAmber(blockAmberID).setHardness(1.5F).setResistance(10.0F).setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("blockAmber");
		quickSand = new BlockQuickSand(quickSandID).setHardness(28F).setStepSound(Block.soundSandFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("quickSand").setTextureName("erebus:quickSand");
		erebusOreExtra = new BlockErebusOreExtras(erebusOreExtraID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("erebusOreExtras");

		logErebusGroup1 = new BlockLogErebus(logErebusGroup1ID, 0).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("logErebus1");
		logErebusGroup2 = new BlockLogErebus(logErebusGroup2ID, 1).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("logErebus2");
		planksErebus = new BlockPlanksErebus(planksErebusID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("planksErebus");
		leavesErebus = (BlockLeavesErebus)new BlockLeavesErebus(leavesErebusID).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("leavesErebus");
		erebusSapling = new BlockSaplingErebus(erebusSaplingID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("sapling_mahogany");
		hollowLogAcacia = new BlockHollowLog(hollowLogAcaciaID, TileEntityHollowLog.class).setHardness(0.7F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("hollowLogAcacia").setTextureName("erebus:log_acacia");

		erebusGrass = new BlockErebusGrass(erebusGrassID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("erebusTallGrass").setTextureName("erebus:tallgrass");
		thorns = new BlockThorns(thornsID).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("thorns").setTextureName("erebus:thorns");
		fern = (BlockFern) new BlockFern(fernID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("erebusFern");
		blockTurnip = new BlockTurnip(blockTurnipID).setUnlocalizedName("turnips");
		fiddlehead = new BlockFiddlehead(fiddleheadID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("erebusFiddlehead");

		blockSilk = new BlockSilk(blockSilkID).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("blockSilk").setTextureName("erebus:blockSilk");
		mirBrick = new Block(mirBrickID, Material.rock).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("mirbrick").setTextureName("erebus:mirbrick");
		petrifiedWoodPlanks = new Block(petrifiedWoodPlanksID, Material.rock).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("petrifiedWoodPlanks").setTextureName("erebus:petrifiedWoodPlanks");
		petrifiedCraftingTable = new BlockPetrifiedCraftingTable(petrifiedCraftingTableID).setHardness(2.5F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("petrifiedCraftingTable");
		bambooCrate = new BlockBambooCrate(bambooCrateID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("bamboo");
		umberFurnace = new BlockUmberFurnace(umberFurnaceID, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("umberFurnaceOFF");
		umberFurnace_on = new BlockUmberFurnace(umberFurnace_onID, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberFurnaceON");
		umberPaver = new BlockUmberPaver(umberPaverID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("umberPaver");

		umbercobbleStairs = new Block[BlockUmberstone.iconPaths.length];
		for (int i = 0; i < umbercobbleStairs.length; i++)
			umbercobbleStairs[i] = new BlockStairsErebus(umbercobbleStairsID[i], umberstone, i).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("umbercobbleStairs" + i);
		stairsAcacia = new Block[BlockPlanksErebus.plankTypes.length];
		for(int i = 0; i < BlockPlanksErebus.plankTypes.length; i++)
			stairsAcacia[i] = new BlockStairsErebus(stairsAcaciaID[i], planksErebus, i).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("stairsPlanks" + i);
		petrifiedWoodStairs = new BlockStairsErebus(petrifiedWoodStairsID, petrifiedWoodPlanks, 0).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("petrifiedWoodStairs");

		wallErebus = new BlockWallErebus(wallErebusID, umberstone).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("wallErebus");

		insectRepellent = new BlockInsectRepellent(insectRepellentID, Material.air).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("insectRepellent");

		// Left a couple of comments on the TileEntitySpawner class to help you guys out since I won't be here this weekend
		// Create them using any mob, just pass in the name as it shows in the mob's class.
		// <3 Love you guys in the face. <3
		spiderSpawner = new BlockSpiderSpawner(spiderSpawnerID, "Spider").setUnlocalizedName("spiderSpawner").setTextureName("erebus:spiderSpawner");
		caveSpiderSpawner = new BlockSpiderSpawner(caveSpiderSpawnerID, "CaveSpider").setUnlocalizedName("caveSpiderSpawner").setTextureName("erebus:spiderSpawner");


		// Registering blocks
		GameRegistry.registerBlock(portalErebus, "erebus.portal");

		GameRegistry.registerBlock(umberstone, ItemBlockUmberStone.class, "erebus.umberstone");
		GameRegistry.registerBlock(umberOreBlock, ItemBlockUmberOre.class, "erebus.oreBlockU");
		GameRegistry.registerBlock(oreFossil, "erebus.oreFossil");
		GameRegistry.registerBlock(redGem, ItemBlockRedGem.class, "erebus.redGem");
		GameRegistry.registerBlock(blockAmber, ItemBlockAmber.class, "erebus.blockAmber");
		GameRegistry.registerBlock(quickSand, "erebus.quickSand");
		if(ErebusMod.activateExtraOres)
			GameRegistry.registerBlock(erebusOreExtra, ItemBlockErebusOreExtras.class, "erebus.erebusOreExtras");

		GameRegistry.registerBlock(logErebusGroup1, ItemBlockLogErebus1.class, "erebus.logErebus1");
		GameRegistry.registerBlock(logErebusGroup2, ItemBlockLogErebus2.class, "erebus.logErebus2");
		GameRegistry.registerBlock(planksErebus, ItemBlockPlanksErebus.class, "erebus.planksErebus");
		GameRegistry.registerBlock(leavesErebus, ItemBlockLeavesErebus.class, "erebus.leavesErebus");
		GameRegistry.registerBlock(erebusSapling, ItemSapling.class, "erebus.erebusSapling");
		GameRegistry.registerBlock(hollowLogAcacia, "erebus.hollowLogAcacia");

		GameRegistry.registerBlock(erebusGrass, "erebus.erebusGrass");
		GameRegistry.registerBlock(thorns, "erebus.thorns");
		GameRegistry.registerBlock(fern, "erebus.fern");
		GameRegistry.registerBlock(blockTurnip, "erebus.blockTurnip");
		GameRegistry.registerBlock(fiddlehead, "erebus.fiddlehead");

		GameRegistry.registerBlock(blockSilk, "erebus.blockSilk");
		GameRegistry.registerBlock(mirBrick, "erebus.mirBrick");
		GameRegistry.registerBlock(petrifiedWoodPlanks, "erebus.petrifiedWoodPlanks");
		GameRegistry.registerBlock(petrifiedCraftingTable, "erebus.petrifiedCraftingTable");
		GameRegistry.registerBlock(bambooCrate, ItemBlockBamboo.class, "erebus.bamboo");
		GameRegistry.registerBlock(umberFurnace, "erebus.umberFurnaceOff");
		GameRegistry.registerBlock(umberFurnace_on, "erebus.umberFurnaceOn");
		GameRegistry.registerBlock(umberPaver, ItemBlockUmberpaver.class, "erebus.umberpaver");

		for (int i = 0; i < umbercobbleStairs.length; i++)
			GameRegistry.registerBlock(umbercobbleStairs[i], "erebus.umbercobbleStairs" + i);
		for (int i = 0; i < stairsAcacia.length; i++)
			GameRegistry.registerBlock(stairsAcacia[i], "erebus.stairsAcacia" + i);
		GameRegistry.registerBlock(petrifiedWoodStairs, "erebus.petrifiedWoodStairs");

		GameRegistry.registerBlock(wallErebus, ItemBlockWallErebus.class, "erebus.wallErebus");
		GameRegistry.registerBlock(insectRepellent, "erebus.blockInsectRepellent");

		GameRegistry.registerBlock(spiderSpawner, "erebus.spiderSpawner");
		GameRegistry.registerBlock(caveSpiderSpawner, "erebus.caveSpiderSpawner");

		// Mining levels
		MinecraftForge.setBlockHarvestLevel(blockAmber, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(oreFossil, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(mirBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(spiderSpawner, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(caveSpiderSpawner, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberstone, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberPaver, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(wallErebus, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 3, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 5, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 6, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(quickSand, "shovel", 2);

		if(ErebusMod.activateExtraOres) {
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 0, "pickaxe", 1);
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 1, "pickaxe", 1);
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 2, "pickaxe", 2);
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 3, "pickaxe", 2);
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 4, "pickaxe", 1);
		}
	}
}
