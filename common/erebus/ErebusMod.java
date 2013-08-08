package erebus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Block.BlockAmber;
import erebus.Block.BlockCaveSpiderSpawner;
import erebus.Block.BlockCobbleWebbed;
import erebus.Block.BlockErebus;
import erebus.Block.BlockErebusBrick;
import erebus.Block.BlockErebusGrass;
import erebus.Block.BlockErebusOre;
import erebus.Block.BlockFern;
import erebus.Block.BlockHollowLog;
import erebus.Block.BlockLeavesErebus;
import erebus.Block.BlockLogAcacia;
import erebus.Block.BlockLogEucalyptus;
import erebus.Block.BlockLogMahogany;
import erebus.Block.BlockMaggot;
import erebus.Block.BlockOreFossil;
import erebus.Block.BlockPlanksErebus;
import erebus.Block.BlockPortalErebus;
import erebus.Block.BlockQuickSand;
import erebus.Block.BlockRedstoneOre_U;
import erebus.Block.BlockSaplingErebus;
import erebus.Block.BlockScree;
import erebus.Block.BlockSilk;
import erebus.Block.BlockSpiderSilk;
import erebus.Block.BlockSpiderSpawner;
import erebus.Block.BlockStairsErebus;
import erebus.Block.BlockThorns;
import erebus.Block.BlockTurnip;
import erebus.Block.BlockUmberstone;
import erebus.Block.TileEntityCaveSpiderSpawner;
import erebus.Block.TileEntityHollowLog;
import erebus.Block.TileEntitySpiderSpawner;
import erebus.Entity.EntityBeetle;
import erebus.Entity.EntityBeetleLarva;
import erebus.Entity.EntityCentipede;
import erebus.Entity.EntityFly;
import erebus.Entity.EntityGreenfly;
import erebus.Entity.EntityMosquito;
import erebus.Entity.EntityTarantula;
import erebus.Entity.EntityVelvetWorm;
import erebus.Entity.EntityWasp;
import erebus.Item.ItemCompoundGoggles;
import erebus.Item.ItemExoskeletonArmor;
import erebus.Item.ItemJadeArmor;
import erebus.Item.ItemMaxSpeedBow;
import erebus.Item.ItemPaxel;
import erebus.Item.ItemPortalActivator;
import erebus.Item.ItemSapling;
import erebus.Item.ItemWeaponErebus;
import erebus.World.WorldProviderErebus;
import erebus.World.Biomes.BiomeGenCavern;
import erebus.World.Biomes.BiomeGenUndergroundDesert;
import erebus.World.Biomes.BiomeGenUndergroundJungle;
import erebus.World.Biomes.BiomeGenUndergroundSavannah;
import erebus.api.Properties;
import erebus.client.sound.EntityBeetleLarvaNoises;
import erebus.client.sound.EntityCentipedeNoises;
import erebus.client.sound.EntityFlyNoises;
import erebus.client.sound.EntityWaspNoises;
import erebus.core.handler.LocalizationHandler;
import erebus.core.proxy.CommonProxy;
import erebus.lib.Reference;

/**
 * @author ProPercivalalb, Dylan4Ever, (Others PUT NAMES HERE)
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(channels = {Reference.CHANNEL}, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketErebusHandler.class)
public class ErebusMod
{	
	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance(Reference.MOD_ID)
	public static ErebusMod instance; //The instance of the mod that will be defined, populated, and callable

	static EnumArmorMaterial armorEXOSKELETON = EnumHelper.addArmorMaterial("EXOSKELETON", 11, new int[] {2, 4, 3, 2}, 15);
	static EnumArmorMaterial armorJADE = EnumHelper.addArmorMaterial("JADE", 15, new int[] {3, 7, 5, 2}, 15);
	static EnumToolMaterial toolJADE = EnumHelper.addToolMaterial("JADE", 2, 863, 10.0F, 3.0F, 18);

	//public static Block mud;
	public static Block blockAmber;						public static int blockAmberID;
	public static Block brickAmber;						public static int brickAmberID;
	public static Block glassAmber;						public static int glassAmberID;
	public static BlockPortalErebus portalErebus;		public static int portalErebusID;
	public static Block woodMahogany;					public static int woodMahoganyID;
	public static BlockLeavesErebus leavesMahogany;		public static int leavesMahoganyID;
	public static Block woodEucalyptus;					public static int woodEucalyptusID;
	public static BlockLeavesErebus leavesEucalyptus;	public static int leavesEucalyptusID;
	public static Block cobbleWebbed;					public static int cobbleWebbedID;
	public static Block oreFossil;						public static int oreFossilID;
	public static Block blockSilk;						public static int blockSilkID;
	public static Block planksMahogany;					public static int planksMahoganyID;
	public static Block planksEucalyptus;				public static int planksEucalyptusID;
	public static Block stairsMahogany;					//public static int blockAmberID;
	public static Block stairsEucalyptus;				//public static int blockAmberID;
	public static Block mirBrick;						public static int mirBrickID;
	public static Block spiderSpawner;					public static int spiderSpawnerID;
	public static Block caveSpiderSpawner;				public static int caveSpiderSpawnerID;
	public static Block blockSpiderSilk;				public static int blockSpiderSilkID;
	public static Block thorns;							public static int thornsID;
	public static BlockFern fern;						public static int fernID;
	public static Block woodAcacia;						public static int woodAcaciaID;
	public static BlockLeavesErebus leavesAcacia;		public static int leavesAcaciaID;
	public static Block erebusSapling;					public static int erebusSaplingID;
	public static Block erebusGrass;					public static int erebusGrassID;
	public static Block planksAcacia;					public static int planksAcaciaID;
	public static Block quickSand;						public static int quickSandID;
	public static Block blockTurnip;					public static int blockTurnipID;
	public static Block dryScree;						//public static int blockAmberID;
	public static Block screeBricks;					//public static int blockAmberID;
	public static Block hollowLogAcacia;				public static int hollowLogAcaciaID;
	public static Block maggotLogAcacia;				//public static int blockAmberID;
	public static Block oreGold_U;                      public static int oreGold_UID;
	public static Block oreIron_U;                      public static int oreIron_UID;
	public static Block oreCoal_U;                      public static int oreCoal_UID;
	public static Block oreLapis_U;                     public static int oreLapis_UID;
	public static Block oreDiamond_U;                   public static int oreDiamond_UID;
	public static Block oreEmerald_U;                   public static int oreEmerald_UID;
	public static Block oreRedstone_U;                  public static int oreRedstone_UID;
	public static Block oreRedstoneGlowing_U;           public static int oreRedstoneGlowing_UID;
	public static Block oreJade_U;             			public static int oreJade_UID;
	public static Block umberstone;					    public static int umberstoneID;
	public static Block umbercobble;					public static int umbercobbleID;
	public static Block umbercobbleMossy;			   public static int umbercobbleMossyID;
	public static Block umbercobbleWebbed;			   public static int umbercobbleWebbedID;

	public static Item beetleLarvaRaw;					public static int beetleLarvaRawID;
	public static Item beetleLarvaCooked;				public static int beetleLarvaCookedID;
	public static Item legCricket;						public static int legCricketID;
	public static Item legCricketCooked;				public static int legCricketCookedID;
	public static Item exoskeletonPlate;				public static int exoskeletonPlateID;
	public static Item exoskeletonHelmet;				public static int exoskeletonHelmetID;
	public static Item exoskeletonBody;					public static int exoskeletonBodyID;
	public static Item exoskeletonLegs;					public static int exoskeletonLegsID;
	public static Item exoskeletonBoots;				public static int exoskeletonBootsID;
	public static Item portalActivator;					public static int portalActivatorID;
	public static Item fossilShard;						public static int fossilShardID;
	public static Item fossilClub;						public static int fossilClubID;
	public static Item maxSpeedBow;						public static int maxSpeedBowID;
	public static Item legTarantula;					public static int legTarantulaID;
	public static Item legTarantulaCooked;				public static int legTarantulaCookedID;
	public static Item turnip;							public static int turnipID;
	public static Item flyWing;							public static int flyWingID;
	public static Item jadeHelmet;						public static int jadeHelmetID;
	public static Item jadeBody;						public static int jadeBodyID;
	public static Item jadeLegs;						public static int jadeLegsID;
	public static Item jadeBoots;						public static int jadeBootsID;
	public static Item jadeSword;						public static int jadeSwordID;
	public static Item jadePickaxe;						public static int jadePickaxeID;
	public static Item jadeAxe;							public static int jadeAxeID;
	public static Item jadeShovel;						public static int jadeShovelID;
	public static Item jadePaxel;						public static int jadePaxelID;
    public static Item compoundEyes;                    public static int compoundEyesID; 
    public static Item compoundLens;                    public static int compoundLensID; 
    public static Item compoundGoggles;                 public static int compoundGogglesID; 
    
	
	public static CreativeTabs tabErebus;

	public static BiomeGenBase underjungle;				public static int jungleID;
	public static BiomeGenBase underdesert;				public static int desertID;
	public static BiomeGenBase undersavannah;			public static int savannahID;
	public static BiomeGenBase cavern;					public static int cavernID;

	public static int erebusDimensionID;

	public static PacketErebusHandler packeterebushandler = new PacketErebusHandler();
	static int startEntityId = 300; // used by  new beetle larva
	
	// This method is used to add sounds
	@EventHandler
	@SideOnly(Side.CLIENT)
	public void preInitClient(FMLPreInitializationEvent event)
	{
		// Create an Entity 'sound' class as below and this will add sounds to the pool
		MinecraftForge.EVENT_BUS.register(new EntityBeetleLarvaNoises());
		MinecraftForge.EVENT_BUS.register(new EntityWaspNoises());
		MinecraftForge.EVENT_BUS.register(new EntityFlyNoises());
		MinecraftForge.EVENT_BUS.register(new EntityCentipedeNoises());
	}
	
	@EventHandler
	public void preInitServer(FMLPreInitializationEvent event)  {
		
		//Loads the Languages into the game
		LocalizationHandler.loadLanguages();
		
		NetworkRegistry.instance().registerConnectionHandler(packeterebushandler);

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		blockAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber", 2501).getInt();		  
		brickAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber Bricks", 2502).getInt();		  
		glassAmberID = config.get(config.CATEGORY_BLOCK, "Block ID of Amber Glass", 2503).getInt();		  
		portalErebusID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Portal", 2504).getInt();		  
		woodMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Wood", 2505).getInt();		  
		leavesMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Leaves", 2506).getInt();		  
		woodEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Wood", 2507).getInt();		  
		leavesEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Leaves", 2508).getInt();		  
		cobbleWebbedID = config.get(config.CATEGORY_BLOCK, "Block ID of Webbed Cobblestone", 2509).getInt();		  
		oreFossilID = config.get(config.CATEGORY_BLOCK, "Block ID of Fossil Ore", 2510).getInt();		  
		blockSilkID = config.get(config.CATEGORY_BLOCK, "Block ID of Silk", 2511).getInt();		  
		planksMahoganyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mahogany Planks", 2512).getInt();		  
		planksEucalyptusID = config.get(config.CATEGORY_BLOCK, "Block ID of Eucalyptus Planks", 2513).getInt();		  
		mirBrickID = config.get(config.CATEGORY_BLOCK, "Block ID of Mirbrick", 2514).getInt();		  
		spiderSpawnerID = config.get(config.CATEGORY_BLOCK, "Block ID of Spider Spawners", 2515).getInt();		  
		caveSpiderSpawnerID = config.get(config.CATEGORY_BLOCK, "Block ID of Cave Spider Spawners", 2516).getInt();		  
		blockSpiderSilkID = config.get(config.CATEGORY_BLOCK, "Block ID of Spider's Silk", 2517).getInt();		  
		thornsID = config.get(config.CATEGORY_BLOCK, "Block ID of Thorns", 2518).getInt();		  
		fernID = config.get(config.CATEGORY_BLOCK, "Block ID of Ferns", 2519).getInt();		  
		woodAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Wood", 2520).getInt();		  
		leavesAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Leaves", 2521, "Block IDs must be below 4096").getInt();		  
		erebusSaplingID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Saplings", 2522).getInt();		  
		erebusGrassID = config.get(config.CATEGORY_BLOCK, "Block ID of Erebus Grass", 2523).getInt();		  
		planksAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Acacia Planks", 2524).getInt();		  
		quickSandID = config.get(config.CATEGORY_BLOCK, "Block ID of Quick Sand", 2525).getInt();		  
		blockTurnipID = config.get(config.CATEGORY_BLOCK, "Block ID of Turnips", 2526).getInt();		  
		hollowLogAcaciaID = config.get(config.CATEGORY_BLOCK, "Block ID of Hollow Log", 2527).getInt();
		oreGold_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Gold Ore", 2528).getInt();
		oreIron_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Iron Ore", 2529).getInt();
		oreCoal_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Coal Ore", 2530).getInt();
		oreLapis_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Lapis Ore", 2531).getInt();
		oreDiamond_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Diamond Ore", 2532).getInt();
		oreEmerald_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Emerald Ore", 2533).getInt();
		oreRedstone_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Redstone Ore", 2534).getInt();
		oreRedstoneGlowing_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Redstone Glowing Ore", 2535).getInt();
		oreJade_UID = config.get(config.CATEGORY_BLOCK, "Block ID of the Umberstone Jade Ore", 2536).getInt();

		umberstoneID = config.get(config.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Block IDs must be below 256").getInt();//ID MUST BE UNDER 256 ( Reason is that this block is the top layer (byte) of biome, and chunkprovider has the same problem.)
		
		umbercobbleID = config.get(config.CATEGORY_BLOCK, "Block ID of Umbercobble", 2537).getInt();
		umbercobbleMossyID = config.get(config.CATEGORY_BLOCK, "Block ID of Mossy Umbercobble", 2538).getInt();
		umbercobbleWebbedID = config.get(config.CATEGORY_BLOCK, "Block ID of Webbed Umbercobble", 2539).getInt();

		beetleLarvaRawID = config.get(config.CATEGORY_ITEM, "Item ID of Beetle Larva", 9701).getInt();		  
		beetleLarvaCookedID = config.get(config.CATEGORY_ITEM, "Item ID of Cooked Beetle Larva", 9702).getInt();
		legCricketID = config.get(config.CATEGORY_ITEM, "Item ID of Cricket Leg", 9703).getInt();
		legCricketCookedID = config.get(config.CATEGORY_ITEM, "Item ID of Cooked Cricket Leg", 9704).getInt();
		exoskeletonPlateID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Plate", 9705).getInt();
		exoskeletonHelmetID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Helmet", 9706).getInt();
		exoskeletonBodyID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Chestplate", 9707).getInt();
		exoskeletonLegsID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Leggings", 9708).getInt();
		exoskeletonBootsID = config.get(config.CATEGORY_ITEM, "Item ID of Exoskeleton Boots", 9709).getInt();
		portalActivatorID = config.get(config.CATEGORY_ITEM, "Item ID of Portal Activator", 9710).getInt();
		fossilShardID = config.get(config.CATEGORY_ITEM, "Item ID of Bone Shard", 9711, "Item IDs must be below 31745").getInt();
		fossilClubID = config.get(config.CATEGORY_ITEM, "Item ID of Caveman's Club", 9712).getInt();
		maxSpeedBowID = config.get(config.CATEGORY_ITEM, "Item ID of MaxSpeed Bow", 9713).getInt();
		legTarantulaID = config.get(config.CATEGORY_ITEM, "Item ID of Tarantula Leg", 9714).getInt();
		legTarantulaCookedID = config.get(config.CATEGORY_ITEM, "Item ID of Crispy Tarantula Leg", 9715).getInt();
		turnipID = config.get(config.CATEGORY_ITEM, "Item ID of Turnips", 9716).getInt();
		flyWingID = config.get(config.CATEGORY_ITEM, "Item ID of Fly Wing", 9717).getInt();
		jadeHelmetID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Helmet", 9718).getInt();
		jadeBodyID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Chestplate", 9719).getInt();
		jadeLegsID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Leggings", 9720).getInt();
		jadeBootsID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Boots", 9721).getInt();
		jadeSwordID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Sword", 9722).getInt();
		jadePickaxeID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Pickaxe", 9723).getInt();
		jadeAxeID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Axe", 9724).getInt();
		jadeShovelID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Shovel", 9725).getInt();
		jadePaxelID = config.get(config.CATEGORY_ITEM, "Item ID of Jade Paxel", 9726).getInt();
		compoundEyesID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Eyes", 9727).getInt(); 
        compoundLensID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Lens", 9728).getInt(); 
        compoundGogglesID = config.get(config.CATEGORY_ITEM, "Item ID of Compound Goggles", 9729).getInt(); 
		
		jungleID = config.get(config.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt();
		desertID = config.get(config.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt();
		savannahID = config.get(config.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt();
		cavernID = config.get(config.CATEGORY_GENERAL, "Biome ID of Cavern", 154, "Biome IDs must be below 256").getInt();

		erebusDimensionID = config.get(config.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt();
		config.save();

		tabErebus = new CreativeTabErebus(CreativeTabs.getNextID(), "erebus");

		DimensionManager.registerProviderType(erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(erebusDimensionID, erebusDimensionID);
		//VillagerRegistry.addExtraVillageComponents(ComponentVillageWatchtower.class, 20, MathHelper.getRandomIntegerInRange(par0Random, 0 + par1, 1 + par1));
	}

	@EventHandler  
	public void load(FMLInitializationEvent event) {

		//mud = new BlockMud(2501, 0).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("mud");
		portalErebus = (BlockPortalErebus)(new BlockPortalErebus(portalErebusID, 221)).setStepSound(Block.soundGlassFootstep).setHardness(-1F).setLightValue(1.0F).setUnlocalizedName("portalErebus").setCreativeTab(tabErebus);
		blockAmber = new BlockAmber(blockAmberID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("blockAmber").setCreativeTab(tabErebus);
		brickAmber = new BlockAmber(brickAmberID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("brickAmber").setCreativeTab(tabErebus);
		glassAmber = new BlockAmber(glassAmberID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("glassAmber").setCreativeTab(tabErebus);
		woodMahogany = new BlockLogMahogany(woodMahoganyID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("tree_mahogany").setCreativeTab(tabErebus);
		leavesMahogany = (BlockLeavesErebus)(new BlockLeavesErebus(leavesMahoganyID, 0)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("leaves_mahogany").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "leaves_mahogany");
		woodEucalyptus = new BlockLogEucalyptus(woodEucalyptusID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("tree_eucalyptus").setCreativeTab(tabErebus);
		leavesEucalyptus = (BlockLeavesErebus)(new BlockLeavesErebus(leavesEucalyptusID, 1)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("leaves_eucalyptus").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "leaves_eucalyptus");
		cobbleWebbed = new BlockCobbleWebbed(cobbleWebbedID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("cobbleWebbed").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "cobbleWebbed");
		oreFossil = new BlockOreFossil(oreFossilID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreFossil_U").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "oreFossil_U");
		blockSilk = new BlockSilk(blockSilkID, 70).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("blockSilk").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "blockSilk");
		planksMahogany = new BlockPlanksErebus(planksMahoganyID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wood_mahogany").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "wood_mahogany");
		planksEucalyptus = new BlockPlanksErebus(planksEucalyptusID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wood_eucalyptus").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "wood_eucalyptus");
		mirBrick = new BlockErebus(mirBrickID, Material.rock).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mirbrick").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "mirbrick");
		spiderSpawner = new BlockSpiderSpawner(spiderSpawnerID, 96).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("spiderSpawner").func_111022_d(Properties.TEX_PACkAGE + "spiderSpawner");
		caveSpiderSpawner = new BlockCaveSpiderSpawner(caveSpiderSpawnerID, 96).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("spiderSpawner").func_111022_d(Properties.TEX_PACkAGE + "spiderSpawner");
		blockSpiderSilk = new BlockSpiderSilk(blockSpiderSilkID).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("blockSpiderSilk").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "blockSpiderSilk");
		thorns = new BlockThorns(thornsID).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("thorns").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "thorns");
		fern = (BlockFern)(new BlockFern(fernID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusFern").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "erebusFern");
		woodAcacia = new BlockLogAcacia(woodAcaciaID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("tree_acacia").setCreativeTab(tabErebus);
		leavesAcacia = (BlockLeavesErebus)(new BlockLeavesErebus(leavesAcaciaID, 2)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("leaves_acacia").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "leaves_acacia");
		erebusSapling = new BlockSaplingErebus(erebusSaplingID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sapling_mahogany");
		erebusGrass = new BlockErebusGrass(erebusGrassID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tallgrass").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "tallgrass");
		planksAcacia = new BlockPlanksErebus(planksAcaciaID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wood_acacia").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "wood_acacia");
		quickSand = new BlockQuickSand(quickSandID).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("quickSand").setCreativeTab(tabErebus);
		blockTurnip = (new BlockTurnip(blockTurnipID)).setUnlocalizedName("turnips");
		oreGold_U = (new BlockErebusOre(oreGold_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreGoldU").func_111022_d(Properties.TEX_PACkAGE + "oreGoldU");
		oreIron_U = (new BlockErebusOre(oreIron_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreIronU").func_111022_d(Properties.TEX_PACkAGE + "oreIronU");
		oreCoal_U = (new BlockErebusOre(oreCoal_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreCoal_U").func_111022_d(Properties.TEX_PACkAGE + "oreCoal_U");
		oreLapis_U = (new BlockErebusOre(oreLapis_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreLapis_U").func_111022_d(Properties.TEX_PACkAGE + "oreLapis_U");
		oreDiamond_U = (new BlockErebusOre(oreDiamond_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreDiamond_U").func_111022_d(Properties.TEX_PACkAGE + "oreDiamond_U");
		oreEmerald_U = (new BlockErebusOre(oreEmerald_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreEmerald_U").func_111022_d(Properties.TEX_PACkAGE + "oreEmerald_U");
		oreRedstone_U = (new BlockRedstoneOre_U(oreRedstone_UID, false)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreRedstone_U").setCreativeTab(tabErebus);
		oreRedstoneGlowing_U = (new BlockRedstoneOre_U(oreRedstoneGlowing_UID, true)).setLightValue(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreRedstone_U");
		umberstone = new BlockUmberstone(umberstoneID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Umberstone");
		oreJade_U = (new BlockErebusOre(oreJade_UID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreJade").func_111022_d(Properties.TEX_PACkAGE + "oreJade");
		umbercobble = new BlockUmberstone(umbercobbleID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Umbercobble");
		umbercobbleMossy = new BlockUmberstone(umbercobbleMossyID, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("MossyUmbercobble");
		umbercobbleWebbed = new BlockCobbleWebbed(umbercobbleWebbedID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("cobbleUmberWebbed").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "cobbleUmberWebbed");
		
		dryScree = (new BlockScree(2550)).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("blockScree").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "blockScree");  
		screeBricks = (new BlockErebusBrick(2551)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("brickScree").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "brickScree");
		hollowLogAcacia = new BlockHollowLog(2552, TileEntityHollowLog.class).setHardness(0.4F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("log_acacia").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "log_acacia");
		maggotLogAcacia = new BlockMaggot(2553, 164).setHardness(0.4F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("log_acacia").setCreativeTab(tabErebus).func_111022_d(Properties.TEX_PACkAGE + "log_acacia");
		stairsMahogany = new BlockStairsErebus(2554, planksMahogany, 0).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("stairsMahogany").setCreativeTab(tabErebus);
		stairsEucalyptus = new BlockStairsErebus(2555, planksEucalyptus, 0).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("stairsEucalyptus").setCreativeTab(tabErebus);


		beetleLarvaRaw = (new ItemFood(beetleLarvaRawID, 2, 0.8F, false)).setPotionEffect(Potion.confusion.id, 30, 0, 0.75F).setUnlocalizedName("beetleLarvaRaw").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "larvaRaw");
		beetleLarvaCooked = (new ItemFood(beetleLarvaCookedID, 6, 0.8F, false)).setUnlocalizedName("beetleLarvaCooked").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "beetleLarvaCooked");exoskeletonPlate = (new Item(exoskeletonPlateID)).setUnlocalizedName("plateExo").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "plateExo");
		exoskeletonHelmet = new ItemExoskeletonArmor(exoskeletonHelmetID, armorEXOSKELETON, 2, 0).setUnlocalizedName("helmetExo").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(exoskeletonBodyID, armorEXOSKELETON, 2, 1).setUnlocalizedName("chestplateExo").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(exoskeletonLegsID, armorEXOSKELETON, 2, 2).setUnlocalizedName("leggingsExo").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(exoskeletonBootsID, armorEXOSKELETON, 2, 3).setUnlocalizedName("bootsExo").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "bootsExo");
		legCricket = (new ItemFood(legCricketID, 2, 0.8F, false)).setUnlocalizedName("cricketLegRaw").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "cricketLegRaw");
		legCricketCooked = (new ItemFood(legCricketCookedID, 8, 0.8F, false)).setUnlocalizedName("cricketLegCooked").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "cricketLegCooked");
		portalActivator = (new ItemPortalActivator(portalActivatorID)).setUnlocalizedName("portalActivator").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "portalActivator");
		fossilShard = (new Item(fossilShardID)).setUnlocalizedName("shardBone").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "shardBone");
		fossilClub = (new ItemWeaponErebus(fossilClubID, 72, 9, 12, Item.bone.itemID, fossilShard.itemID)).setUnlocalizedName("clubBone").setFull3D().setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "clubBone");
		maxSpeedBow = (new ItemMaxSpeedBow(maxSpeedBowID, 301, 5)).setUnlocalizedName("maxSpeedBow").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "maxSpeedBow");
		legTarantula = (new ItemFood(legTarantulaID, 2, 0.8F, false)).setUnlocalizedName("legTarantulaRaw").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "legTarantula");
		legTarantulaCooked = (new ItemFood(legTarantulaCookedID, 6, 1.0F, false).setUnlocalizedName("legTarantulaCooked").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "legTarantulaCooked"));		  
		turnip = (new ItemSeedFood(turnipID, 4, 0.6F, blockTurnip.blockID, Block.tilledField.blockID)).setUnlocalizedName("turnips").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "turnips");
		flyWing = (new Item(flyWingID)).setUnlocalizedName("flyWing").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "flyWing");
		jadeHelmet = new ItemJadeArmor(jadeHelmetID, armorJADE, 2, 0).setUnlocalizedName("helmetJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "helmetJade");
		jadeBody = new ItemJadeArmor(jadeBodyID, armorJADE, 2, 1).setUnlocalizedName("chestplateJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "chestplateJade");
		jadeLegs = new ItemJadeArmor(jadeLegsID, armorJADE, 2, 2).setUnlocalizedName("leggingsJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "leggingsJade");
		jadeBoots = new ItemJadeArmor(jadeBootsID, armorJADE, 2, 3).setUnlocalizedName("bootsJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "bootsJade");
		jadeSword = new ItemSword(jadeSwordID, toolJADE).setUnlocalizedName("swordJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "swordJade");
		jadePickaxe = new ItemPickaxe(jadePickaxeID, toolJADE).setUnlocalizedName("pickaxeJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "pickaxeJade");
		jadeAxe = new ItemAxe(jadeAxeID, toolJADE).setUnlocalizedName("axeJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "axeJade");
		jadeShovel = new ItemSpade(jadeShovelID, toolJADE).setUnlocalizedName("shovelJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "shovelJade");
		jadePaxel = new ItemPaxel(jadePaxelID, toolJADE).setUnlocalizedName("paxelJade").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "paxelJade");
		compoundGoggles = new ItemCompoundGoggles(compoundGogglesID, armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "compoundGoggles"); 
        compoundEyes = (new Item(compoundEyesID)).setUnlocalizedName("compoundEyes").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "compoundEyes"); 
        compoundLens = (new Item(compoundLensID)).setUnlocalizedName("compoundLens").setCreativeTab(tabErebus).func_111206_d(Properties.TEX_PACkAGE + "compoundLens"); 
		
		//Block Mining Levels
		MinecraftForge.setBlockHarvestLevel(blockAmber, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(brickAmber, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(glassAmber, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(cobbleWebbed, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(oreFossil, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(mirBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(screeBricks, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(oreCoal_U, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(oreLapis_U, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreDiamond_U, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(oreEmerald_U, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(oreRedstone_U, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(oreIron_U, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreGold_U, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(oreRedstoneGlowing_U, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(oreJade_U, "pickaxe", 2);

		MinecraftForge.setBlockHarvestLevel(quickSand, "shovel", 1);
		MinecraftForge.setBlockHarvestLevel(dryScree, "shovel", 0);
		
		//Tools classes
		MinecraftForge.setToolClass(jadeAxe, "axe", 2);
		MinecraftForge.setToolClass(jadePickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(jadeShovel, "shovel", 2);
		
		//GameRegistry.registerBlock(mud);		  
		GameRegistry.registerBlock(portalErebus, "erebus.portal");
		GameRegistry.registerBlock(blockAmber, "erebus.blockAmber");		  
		GameRegistry.registerBlock(brickAmber, "erebus.brickAmber");		  
		GameRegistry.registerBlock(glassAmber, "erebus.glassAmber");		  
		GameRegistry.registerBlock(woodMahogany, "erebus.woodMahogany");		  
		GameRegistry.registerBlock(leavesMahogany, "erebus.leavesMahogany");		  
		GameRegistry.registerBlock(woodEucalyptus, "erebus.woodEucalyptus");		  
		GameRegistry.registerBlock(leavesEucalyptus, "erebus.leavesEucalyptus");		  
		GameRegistry.registerBlock(cobbleWebbed, "erebus.cobbleWebbed");		  
		GameRegistry.registerBlock(oreFossil, "erebus.oreFossil");		  
		GameRegistry.registerBlock(blockSilk, "erebus.blockSilk");		  
		GameRegistry.registerBlock(planksMahogany, "erebus.planksMahogany");		  
		GameRegistry.registerBlock(planksEucalyptus, "erebus.planksEucalyptus");		  
		GameRegistry.registerBlock(mirBrick, "erebus.mirBrick");		  
		GameRegistry.registerBlock(spiderSpawner, "erebus.spiderSpawner");		  
		GameRegistry.registerBlock(caveSpiderSpawner, "erebus.caveSpiderSpawner");		  
		GameRegistry.registerBlock(blockSpiderSilk, "erebus.blockSpiderSilk");		  
		GameRegistry.registerBlock(thorns, "erebus.thorns");		  
		GameRegistry.registerBlock(fern, "erebus.fern");		  
		GameRegistry.registerBlock(woodAcacia, "erebus.woodAcacia");		  
		GameRegistry.registerBlock(leavesAcacia, "erebus.leavesAcacia");	  
		GameRegistry.registerBlock(erebusSapling, ItemSapling.class, "erebus.erebusSapling");		  
		GameRegistry.registerBlock(erebusGrass, "erebus.erebusGrass");		  
		GameRegistry.registerBlock(planksAcacia, "erebus.planksAcacia");		  
		GameRegistry.registerBlock(quickSand, "erebus.quickSand");
		GameRegistry.registerBlock(blockTurnip, "erebus.blockTurnip");
		//GameRegistry.registerBlock(dryScree, "erebus.dryScree");		  
		//GameRegistry.registerBlock(screeBricks, "erebus.screeBricks");		  
		GameRegistry.registerBlock(hollowLogAcacia, "erebus.hollowLogAcacia");		  
		//GameRegistry.registerBlock(maggotLogAcacia, "erebus.maggotLogAcacia");		  
		//GameRegistry.registerBlock(stairsMahogany);		  
		//GameRegistry.registerBlock(stairsEucalyptus);
		GameRegistry.registerBlock(umberstone, "erebus.umberstone");		  
		GameRegistry.registerBlock(oreGold_U, "erebus.oreGold_U");		  
		GameRegistry.registerBlock(oreIron_U, "erebus.oreIron_U");		  
		GameRegistry.registerBlock(oreCoal_U, "erebus.oreCoal_U");		  
		GameRegistry.registerBlock(oreLapis_U, "erebus.oreLapis_U");		  
		GameRegistry.registerBlock(oreDiamond_U, "erebus.oreDiamond_U");		  
		GameRegistry.registerBlock(oreEmerald_U, "erebus.oreEmerald_U");		  
		GameRegistry.registerBlock(oreRedstone_U, "erebus.oreRedstone_U");		  
		GameRegistry.registerBlock(oreRedstoneGlowing_U, "erebus.oreRedstoneGlowing_U");		  
		GameRegistry.registerBlock(oreJade_U, "erebus.oreJade_U");	
		GameRegistry.registerBlock(umbercobble, "erebus.cobbleUmber");
		GameRegistry.registerBlock(umbercobbleMossy, "erebus.cobbleUmberMossy");
		GameRegistry.registerBlock(umbercobbleWebbed, "erebus.cobbleUmberWebbed");

		//BIOMES ( After block registration or NullPointerException )
		underjungle = (new BiomeGenUndergroundJungle(jungleID).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F)).setBiomeName("Undergound Jungle");
		underdesert = (new BiomeGenUndergroundDesert(desertID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(2.2F, 0.2F)).setBiomeName("Volcanic Desert");
		undersavannah = (new BiomeGenUndergroundSavannah(savannahID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(4.2F, 0.05F)).setBiomeName("Subterranean Savannah");
		cavern = (new BiomeGenCavern(cavernID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(4.2F, 0.05F)).setBiomeName("Cavern");	  

		GameRegistry.addRecipe(new ItemStack(compoundLens, 1), new Object[] {"GGG", "GEG", "GGG", 'G', glassAmber, 'E', compoundEyes}); 
	    GameRegistry.addRecipe(new ItemStack(compoundGoggles, 1), new Object[] {"XXX", "OXO", "   ", 'O', compoundLens, 'X', exoskeletonPlate});  
		
		GameRegistry.addRecipe(new ItemStack(exoskeletonHelmet, 1), new Object[] {"sss", "s s", "   ", Character.valueOf('s'), exoskeletonPlate});
		GameRegistry.addRecipe(new ItemStack(exoskeletonBody, 1), new Object[] {"s s", "sss", "sss", Character.valueOf('s'), exoskeletonPlate});
		GameRegistry.addRecipe(new ItemStack(exoskeletonLegs, 1), new Object[] {"sss", "s s", "s s", Character.valueOf('s'), exoskeletonPlate});
		GameRegistry.addRecipe(new ItemStack(exoskeletonBoots, 1), new Object[] {"   ", "s s", "s s", Character.valueOf('s'), exoskeletonPlate});  
		
		GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', umbercobble});
		GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[] {"X", "#", "#", '#', Item.stick, 'X', umbercobble});
		GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', umbercobble});
		GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', umbercobble});
		GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[] {"X", "X", "#", '#', Item.stick, 'X', umbercobble});

		GameRegistry.addRecipe(new ItemStack(blockSilk, 1), new Object[] {"sss", "sss", "sss", Character.valueOf('s'), Item.silk});  
		GameRegistry.addRecipe(new ItemStack(brickAmber, 4), new Object[] {"ss", "ss", Character.valueOf('s'), blockAmber});  
		GameRegistry.addRecipe(new ItemStack(planksMahogany, 4), new Object[] {"#", '#', new ItemStack(woodMahogany)});  
		GameRegistry.addRecipe(new ItemStack(planksEucalyptus, 4), new Object[] {"#", '#', new ItemStack(woodEucalyptus)});  
		GameRegistry.addRecipe(new ItemStack(planksAcacia, 4), new Object[] {"#", '#', new ItemStack(woodAcacia)});  
		GameRegistry.addRecipe(new ItemStack(stairsMahogany, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(planksMahogany)});
		GameRegistry.addRecipe(new ItemStack(stairsEucalyptus, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(planksEucalyptus)});
		GameRegistry.addRecipe(new ItemStack(portalActivator, 1), new Object[] {"  O", " / ", "/  ", 'O', new ItemStack(Item.spiderEye), '/', new ItemStack(Item.stick)});
		GameRegistry.addRecipe(new ItemStack(Item.silk, 9), new Object[] {"#", '#', new ItemStack(blockSilk)});  
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 1, 15), new Object[] {"#", '#', new ItemStack(fossilShard, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 6, 15), new Object[] {"#", '#', new ItemStack(fossilClub, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {"T", "S", "F", 'F', new ItemStack(Item.feather, 1, 0), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(fossilShard, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(screeBricks, 4), new Object[] {"ss", "ss", Character.valueOf('s'), dryScree});  
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {"T", "S", "F", 'F', new ItemStack(flyWing, 1, 0), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(fossilShard, 1, 0)});  
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] {"T", "S", "F", 'F', new ItemStack(flyWing, 1, 0), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(Item.flint, 1, 0)});  

		GameRegistry.addSmelting(blockAmber.blockID, new ItemStack(glassAmber, 1), 0.3F);
		GameRegistry.addSmelting(beetleLarvaRaw.itemID, new ItemStack(beetleLarvaCooked, 1), 0.2F);
		GameRegistry.addSmelting(legCricket.itemID, new ItemStack(legCricketCooked, 1), 0.2F);
		GameRegistry.addSmelting(legTarantula.itemID, new ItemStack(legTarantulaCooked, 1), 0.2F);
		GameRegistry.addSmelting(umbercobble.blockID, new ItemStack(umberstone, 1), 0.2F);

		OreDictionary.registerOre("blockCobble", new ItemStack(umbercobble, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood",     new ItemStack(woodMahogany, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood",   new ItemStack(planksMahogany, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood",     new ItemStack(woodEucalyptus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood",   new ItemStack(planksEucalyptus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood",     new ItemStack(woodAcacia, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood",   new ItemStack(planksAcacia, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(erebusSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves",  new ItemStack(leavesMahogany, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves",  new ItemStack(leavesEucalyptus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves",  new ItemStack(leavesAcacia, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreGold_U", this.oreGold_U);
		OreDictionary.registerOre("oreIron_U", this.oreIron_U);
		OreDictionary.registerOre("oreLapis_U", this.oreLapis_U);
		OreDictionary.registerOre("oreDiamond_U", this.oreDiamond_U);
		OreDictionary.registerOre("oreRedstone_U", this.oreRedstone_U);
		OreDictionary.registerOre("oreEmerald_U", this.oreEmerald_U);

		proxy.registerRenderInformation();

		GameRegistry.addBiome(underjungle);
		GameRegistry.removeBiome(underjungle);  

		EntityRegistry.registerModEntity(EntityBeetleLarva.class, "BeetleLarva", 1, this, 40, 1, true);
		registerEntityEgg(EntityBeetleLarva.class, 0xD0FFA2, 0x693C00);
		
		EntityRegistry.registerModEntity(EntityWasp.class, "Wasp", 2, this, 40, 1, true);
		registerEntityEgg(EntityWasp.class, 0xFFFF00, 0x000000);
		
		EntityRegistry.registerModEntity(EntityCentipede.class, "Centipede", 3, this, 40, 1, true);
		registerEntityEgg(EntityCentipede.class, 0xFFFF00, 0x0000FF);
		
		// Mobs should be registered as ModEntity which means you can have as may ID's as you like
		// as they are specific to only this mod!!!
		// As above (Entity.class, "Name", unique mod ID, this mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		EntityRegistry.registerGlobalEntityID(EntityBeetle.class, "Beetle", ModLoader.getUniqueEntityId(), 894731, 256);
		EntityRegistry.registerGlobalEntityID(EntityFly.class, "Fly", ModLoader.getUniqueEntityId(), 894731, 192);
		//Removed for the moment... EntityRegistry.registerGlobalEntityID(EntityGreenfly.class, "Greenfly", ModLoader.getUniqueEntityId(), 894731, 512);
		EntityRegistry.registerGlobalEntityID(EntityMosquito.class, "Mosquito", ModLoader.getUniqueEntityId(), 894731, 512);
		EntityRegistry.registerGlobalEntityID(EntityTarantula.class, "Tarantula", ModLoader.getUniqueEntityId(), 894731, 512);	
		EntityRegistry.registerGlobalEntityID(EntityVelvetWorm.class, "VelvetWorm", ModLoader.getUniqueEntityId(), 894731, 000000);
	

		/*LanguageRegistry.instance().addStringLocalization("entity.Erebus Stuff.Mosquito.name", "en_US", "Mosquito");	    	
		  EntityRegistry.registerModEntity(EntityMosquito.class, "Mosquito", 17,  mod_Erebus.instance, 80, 3, true);
		  EntityList.IDtoClassMapping.put(815, EntityMosquito.class);
	      EntityList.entityEggs.put(Integer.valueOf(815), new EntityEggInfo(815, 0xB77A35, 0xD0D0D0));*/


		GameRegistry.registerTileEntity(TileEntitySpiderSpawner.class, "Spider Spawner");
		GameRegistry.registerTileEntity(TileEntityCaveSpiderSpawner.class, "Cave Spider Spawner");
		GameRegistry.registerTileEntity(TileEntityHollowLog.class, "Hollow Log");

		TickRegistry.registerTickHandler(new CommonErebusTickHandler(), Side.SERVER);
	}
	
	// This allows you to get rid of those nasty ModLoader methods and makes it easy to register spawn eggs
	public static int getUniqueEntityId()
	{
		do
		{
			startEntityId++;
		}
		while(EntityList.getStringFromID(startEntityId)!= null);
		return startEntityId;
	}

	public static void registerEntityEgg(Class <? extends Entity> entity, int primaryColor, int secondaryColor)
	{
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}
}


/*
@ForgeSubscribe
public void onUseBonemeal(BonemealEvent event)
{
	  MinecraftForge.EVENT_BUS.post(event);
	  if (event.ID == mod_Erebus.erebusSapling.blockID)
    {
        if (!event.world.isRemote)
        {
            if ((double)event.world.rand.nextFloat() < 0.45D)
            {
                ((BlockSaplingErebus)mod_Erebus.erebusSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
            }
        }
    }
}*/