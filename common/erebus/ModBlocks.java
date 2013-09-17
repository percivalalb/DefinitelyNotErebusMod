package erebus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockAmber;
import erebus.block.BlockBambooCrate;
import erebus.block.BlockCaveSpiderSpawner;
import erebus.block.BlockCobbleWebbed;
import erebus.block.BlockErebusBrick;
import erebus.block.BlockErebusGrass;
import erebus.block.BlockErebusOre;
import erebus.block.BlockFern;
import erebus.block.BlockHollowLog;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;
import erebus.block.BlockMaggot;
import erebus.block.BlockMirBrick;
import erebus.block.BlockOreFossil;
import erebus.block.BlockPetrifiedCraftingTable;
import erebus.block.BlockPetrifiedWoodOre;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockPortalErebus;
import erebus.block.BlockQuickSand;
import erebus.block.BlockRedGem;
import erebus.block.BlockSaplingErebus;
import erebus.block.BlockScree;
import erebus.block.BlockSilk;
import erebus.block.BlockSpiderSilk;
import erebus.block.BlockSpiderSpawner;
import erebus.block.BlockStairsErebus;
import erebus.block.BlockThorns;
import erebus.block.BlockTurnip;
import erebus.block.BlockUmberstone;
import erebus.item.block.ItemBlockAmber;
import erebus.item.block.ItemBlockBamboo;
import erebus.item.block.ItemBlockLeavesErebus;
import erebus.item.block.ItemBlockLogErebus1;
import erebus.item.block.ItemBlockLogErebus2;
import erebus.item.block.ItemBlockPlanksErebus;
import erebus.item.block.ItemBlockRedGem;
import erebus.item.block.ItemBlockUmberOre;
import erebus.item.block.ItemBlockUmberStone;
import erebus.item.block.ItemSapling;
import erebus.tileentity.TileEntityBamboo;
import erebus.tileentity.TileEntityCaveSpiderSpawner;
import erebus.tileentity.TileEntityHollowLog;
import erebus.tileentity.TileEntitySpiderSpawner;

/**
 * @author ProPercivalalb
 */
public class ModBlocks {

	//public static Block mud;
	public static Block blockAmber;						public static int blockAmberID;
	public static BlockPortalErebus portalErebus;		public static int portalErebusID;
	public static Block logErebusGroup1;           		public static int logErebusGroup1ID;
	public static Block logErebusGroup2;           		public static int logErebusGroup2ID;
	public static Block planksErebus;           		public static int planksErebusID;
	public static BlockLeavesErebus leavesErebus;		public static int leavesErebusID;
	public static Block cobbleWebbed;					public static int cobbleWebbedID;
	public static Block oreFossil;						public static int oreFossilID;
	public static Block blockSilk;						public static int blockSilkID;
	public static Block stairsMahogany;					//public static int blockAmberID;
	public static Block stairsEucalyptus;				//public static int blockAmberID;
	public static Block mirBrick;						public static int mirBrickID;
	public static Block spiderSpawner;					public static int spiderSpawnerID;
	public static Block caveSpiderSpawner;				public static int caveSpiderSpawnerID;
	public static Block blockSpiderSilk;				public static int blockSpiderSilkID;
	public static Block thorns;							public static int thornsID;
	public static BlockFern fern;						public static int fernID;
	public static Block erebusSapling;					public static int erebusSaplingID;
	public static Block erebusGrass;					public static int erebusGrassID;
	public static Block quickSand;						public static int quickSandID;
	public static Block blockTurnip;					public static int blockTurnipID;
	public static Block dryScree;						//public static int blockAmberID;
	public static Block screeBricks;					//public static int blockAmberID;
	public static Block hollowLogAcacia;				public static int hollowLogAcaciaID;
	public static Block maggotLogAcacia;				//public static int blockAmberID;
	public static Block umberstone;					    public static int umberstoneID;
	public static Block umberOreBlock;					public static int umberOreBlockID;
	public static Block bambooCrate;			  	    public static int bambooCrateID;
	public static Block redGem;							public static int redGemID;
	public static Block petrifiedWoodOre;				public static int petrifiedWoodOreID;
	public static Block petrifiedWoodPlanks;			public static int petrifiedWoodPlanksID;
	public static Block petrifiedCraftingTable;			public static int petrifiedCraftingTableID;
	
	public static void init() {
		// constructor, hardness, resistance, light value, light opacity, step sound, creative tab, unlocalized name, texture name
		
		//mud = new BlockMud(2501, 0).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("mud");
		portalErebus = (BlockPortalErebus)(new BlockPortalErebus(portalErebusID, 221)).setHardness(-1F).setLightValue(1.0F).setStepSound(Block.soundGlassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("portalErebus");
		blockAmber = new BlockAmber(blockAmberID).setHardness(1.5F).setResistance(10.0F).setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("blockAmber");
		cobbleWebbed = new BlockCobbleWebbed(cobbleWebbedID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundClothFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("cobbleWebbed").setTextureName("erebus:cobbleWebbed");
		oreFossil = new BlockOreFossil(oreFossilID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("oreFossilU").setTextureName("erebus:oreFossil_U");
		blockSilk = new BlockSilk(blockSilkID, 70).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("blockSilk").setTextureName("erebus:blockSilk");
		blockSpiderSilk = new BlockSpiderSilk(blockSpiderSilkID).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("blockSpiderSilk").setTextureName("erebus:blockSpiderSilk");
		mirBrick = new BlockMirBrick(mirBrickID, Material.rock).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("mirbrick").setTextureName("erebus:mirbrick");
		spiderSpawner = new BlockSpiderSpawner(spiderSpawnerID, 96).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("spiderSpawner").setTextureName("erebus:spiderSpawner");
		caveSpiderSpawner = new BlockCaveSpiderSpawner(caveSpiderSpawnerID, 96).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("caveSpiderSpawner").setTextureName("erebus:spiderSpawner");
		thorns = new BlockThorns(thornsID).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("thorns").setTextureName("erebus:thorns");
		fern = (BlockFern)(new BlockFern(fernID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("erebusFern");
		erebusSapling = new BlockSaplingErebus(erebusSaplingID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("sapling_mahogany");
		erebusGrass = new BlockErebusGrass(erebusGrassID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("erebusTallGrass").setTextureName("erebus:tallgrass");
		quickSand = new BlockQuickSand(quickSandID).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("quickSand");
		blockTurnip = (new BlockTurnip(blockTurnipID))/*.setCreativeTab(ErebusMod.tabErebusBlock)*/.setUnlocalizedName("turnips"); // TODO should this be in creative menu?
		umberOreBlock = new BlockErebusOre(umberOreBlockID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("oreBlockU");
		umberstone = new BlockUmberstone(umberstoneID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("umberstone");
		bambooCrate = new BlockBambooCrate(bambooCrateID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("bamboo");
		redGem = new BlockRedGem(redGemID).setHardness(0.3F).setLightValue(1F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("redGem");
		dryScree = (new BlockScree(2550)).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("blockScree").setTextureName("erebus:blockScree");  
		screeBricks = (new BlockErebusBrick(2551)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("brickScree").setTextureName("erebus:brickScree");
		hollowLogAcacia = new BlockHollowLog(2552, TileEntityHollowLog.class).setHardness(0.4F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("hollowLogAcacia").setTextureName("erebus:log_acacia");
		maggotLogAcacia = new BlockMaggot(2553, 164).setHardness(0.4F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("maggotLogAcacia").setTextureName("erebus:log_acacia");
		logErebusGroup1 = new BlockLogErebus(logErebusGroup1ID, 0).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("logErebus1");
		logErebusGroup2 = new BlockLogErebus(logErebusGroup2ID, 1).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("logErebus2");
		planksErebus = new BlockPlanksErebus(planksErebusID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("planksErebus");
		leavesErebus = (BlockLeavesErebus)(new BlockLeavesErebus(leavesErebusID, 0)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("leavesErebus");
		stairsMahogany = new BlockStairsErebus(2554, planksErebus, BlockLogErebus.dataMahogany).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("stairsMahogany");
		stairsEucalyptus = new BlockStairsErebus(2555, planksErebus, BlockLogErebus.dataEucalyptus).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("stairsEucalyptus");
		petrifiedWoodOre = new BlockPetrifiedWoodOre(petrifiedWoodOreID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("petrifiedWoodOre").setTextureName("erebus:petrifiedWoodore");
		petrifiedWoodPlanks = new Block(petrifiedWoodPlanksID, Material.rock).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("petrifiedWoodPlanks").setTextureName("erebus:petrifiedWoodPlanks");
		petrifiedCraftingTable = new BlockPetrifiedCraftingTable(petrifiedCraftingTableID).setHardness(2.5F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ErebusMod.tabErebusBlock).setUnlocalizedName("petrifiedCraftingTable");
		
		//GameRegistry.registerBlock(mud);		  
		GameRegistry.registerBlock(portalErebus, "erebus.portal");
		GameRegistry.registerBlock(blockAmber, ItemBlockAmber.class, "erebus.blockAmber");	  
		GameRegistry.registerBlock(cobbleWebbed, "erebus.cobbleWebbed");		  
		GameRegistry.registerBlock(oreFossil, "erebus.oreFossil");		  
		GameRegistry.registerBlock(blockSilk, "erebus.blockSilk");
		GameRegistry.registerBlock(mirBrick, "erebus.mirBrick");		  
		GameRegistry.registerBlock(spiderSpawner, "erebus.spiderSpawner");		  
		GameRegistry.registerBlock(caveSpiderSpawner, "erebus.caveSpiderSpawner");		  
		GameRegistry.registerBlock(blockSpiderSilk, "erebus.blockSpiderSilk");		  
		GameRegistry.registerBlock(thorns, "erebus.thorns");		  
		GameRegistry.registerBlock(fern, "erebus.fern");	  
		GameRegistry.registerBlock(erebusSapling, ItemSapling.class, "erebus.erebusSapling");		  
		GameRegistry.registerBlock(erebusGrass, "erebus.erebusGrass");		  
		GameRegistry.registerBlock(quickSand, "erebus.quickSand");
		GameRegistry.registerBlock(blockTurnip, "erebus.blockTurnip");
		GameRegistry.registerBlock(dryScree, "erebus.dryScree");		  
		GameRegistry.registerBlock(screeBricks, "erebus.screeBricks");		  
		GameRegistry.registerBlock(hollowLogAcacia, "erebus.hollowLogAcacia");		  
		//GameRegistry.registerBlock(maggotLogAcacia, "erebus.maggotLogAcacia");		  
		GameRegistry.registerBlock(stairsMahogany);		  
		GameRegistry.registerBlock(stairsEucalyptus);
		GameRegistry.registerBlock(umberstone, ItemBlockUmberStone.class, "erebus.umberstone");		  
		GameRegistry.registerBlock(umberOreBlock, ItemBlockUmberOre.class, "erebus.oreBlockU");		  	  
		GameRegistry.registerBlock(bambooCrate, ItemBlockBamboo.class, "erebus.bamboo");
		GameRegistry.registerBlock(redGem, ItemBlockRedGem.class, "erebus.redGem");
		GameRegistry.registerBlock(logErebusGroup1, ItemBlockLogErebus1.class, "erebus.logErebus1");
		GameRegistry.registerBlock(logErebusGroup2, ItemBlockLogErebus2.class, "erebus.logErebus2");
		GameRegistry.registerBlock(planksErebus, ItemBlockPlanksErebus.class, "erebus.planksErebus");
		GameRegistry.registerBlock(leavesErebus, ItemBlockLeavesErebus.class, "erebus.leavesErebus");
		GameRegistry.registerBlock(petrifiedWoodOre, "erebus.petrifiedWoodOre");
		GameRegistry.registerBlock(petrifiedWoodPlanks, "erebus.petrifiedWoodPlanks");
		GameRegistry.registerBlock(petrifiedCraftingTable, "erebus.petrifiedCraftingTable");
		
		//Block Mining Levels
		MinecraftForge.setBlockHarvestLevel(blockAmber, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(cobbleWebbed, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(oreFossil, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(mirBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(screeBricks, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 3, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 5, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 6, "pickaxe", 2);

		MinecraftForge.setBlockHarvestLevel(quickSand, "shovel", 1);
		MinecraftForge.setBlockHarvestLevel(dryScree, "shovel", 0);
		
		GameRegistry.registerTileEntity(TileEntitySpiderSpawner.class, "Spider Spawner (Erebus)");
		GameRegistry.registerTileEntity(TileEntityCaveSpiderSpawner.class, "Cave Spider Spawner (Erebus)");
		GameRegistry.registerTileEntity(TileEntityHollowLog.class, "Hollow Log (Erebus)");
		GameRegistry.registerTileEntity(TileEntityBamboo.class, "Bamboo Crate (Erebus)");
	}
}
