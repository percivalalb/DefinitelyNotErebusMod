package erebus;

import erebus.item.ItemCompoundGoggles;
import erebus.item.ItemExoskeletonArmor;
import erebus.item.ItemJadeArmor;
import erebus.item.ItemMaterial;
import erebus.item.ItemMaxSpeedBow;
import erebus.item.ItemPaxel;
import erebus.item.ItemPortalActivator;
import erebus.item.ItemWeaponErebus;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author ProPercivalalb
 */
public class ModItems {

	public static Item erebusFood;					    public static int erebusFoodID = 2312;
	public static Item erebusMaterials;  				public static int erebusMaterialsID = 2313;
	
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
	public static Item jadeHoe;							public static int jadeHoeID;
    public static Item compoundEyes;                    public static int compoundEyesID; 
    public static Item compoundLens;                    public static int compoundLensID; 
    public static Item compoundGoggles;                 public static int compoundGogglesID; 
    
    public static void init() {
    	beetleLarvaRaw = (new ItemFood(beetleLarvaRawID, 2, 0.8F, false)).setPotionEffect(Potion.confusion.id, 30, 0, 0.75F).setUnlocalizedName("beetleLarvaRaw").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:larvaRaw");
		beetleLarvaCooked = (new ItemFood(beetleLarvaCookedID, 6, 0.8F, false)).setUnlocalizedName("beetleLarvaCooked").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:beetleLarvaCooked");exoskeletonPlate = (new Item(exoskeletonPlateID)).setUnlocalizedName("plateExo").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:plateExo");
		exoskeletonHelmet = new ItemExoskeletonArmor(exoskeletonHelmetID, ErebusMod.armorEXOSKELETON, 2, 0).setUnlocalizedName("helmetExo").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(exoskeletonBodyID, ErebusMod.armorEXOSKELETON, 2, 1).setUnlocalizedName("chestplateExo").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(exoskeletonLegsID, ErebusMod.armorEXOSKELETON, 2, 2).setUnlocalizedName("leggingsExo").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(exoskeletonBootsID, ErebusMod.armorEXOSKELETON, 2, 3).setUnlocalizedName("bootsExo").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:bootsExo");
		legCricket = (new ItemFood(legCricketID, 2, 0.8F, false)).setUnlocalizedName("cricketLegRaw").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:cricketLegRaw");
		legCricketCooked = (new ItemFood(legCricketCookedID, 8, 0.8F, false)).setUnlocalizedName("cricketLegCooked").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:cricketLegCooked");
		erebusMaterials = new ItemMaterial(erebusMaterialsID).setUnlocalizedName("erebusMaterials").setCreativeTab(ErebusMod.tabErebus);
		
		portalActivator = (new ItemPortalActivator(portalActivatorID)).setUnlocalizedName("portalActivator").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:portalActivator");
		fossilShard = (new Item(fossilShardID)).setUnlocalizedName("shardBone").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:shardBone");
		fossilClub = (new ItemWeaponErebus(fossilClubID, 72, 9, 12, Item.bone.itemID, fossilShard.itemID)).setUnlocalizedName("clubBone").setFull3D().setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:clubBone");
		maxSpeedBow = (new ItemMaxSpeedBow(maxSpeedBowID, 301, 5)).setUnlocalizedName("maxSpeedBow").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:maxSpeedBow");
		legTarantula = (new ItemFood(legTarantulaID, 2, 0.8F, false)).setUnlocalizedName("legTarantulaRaw").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:legTarantula");
		legTarantulaCooked = (new ItemFood(legTarantulaCookedID, 6, 1.0F, false).setUnlocalizedName("legTarantulaCooked").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:legTarantulaCooked"));		  
		turnip = (new ItemSeedFood(turnipID, 4, 0.6F, ModBlocks.blockTurnip.blockID, Block.tilledField.blockID)).setUnlocalizedName("turnips").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:turnips");
		flyWing = (new Item(flyWingID)).setUnlocalizedName("flyWing").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:flyWing");
		jadeHelmet = new ItemJadeArmor(jadeHelmetID, ErebusMod.armorJADE, 2, 0).setUnlocalizedName("helmetJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:helmetJade");
		jadeBody = new ItemJadeArmor(jadeBodyID, ErebusMod.armorJADE, 2, 1).setUnlocalizedName("chestplateJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:chestplateJade");
		jadeLegs = new ItemJadeArmor(jadeLegsID, ErebusMod.armorJADE, 2, 2).setUnlocalizedName("leggingsJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:leggingsJade");
		jadeBoots = new ItemJadeArmor(jadeBootsID, ErebusMod.armorJADE, 2, 3).setUnlocalizedName("bootsJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:bootsJade");
		jadeSword = new ItemSword(jadeSwordID, ErebusMod.toolJADE).setUnlocalizedName("swordJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:swordJade");
		jadePickaxe = new ItemPickaxe(jadePickaxeID, ErebusMod.toolJADE).setUnlocalizedName("pickaxeJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:pickaxeJade");
		jadeAxe = new ItemAxe(jadeAxeID, ErebusMod.toolJADE).setUnlocalizedName("axeJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:axeJade");
		jadeShovel = new ItemSpade(jadeShovelID, ErebusMod.toolJADE).setUnlocalizedName("shovelJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:shovelJade");
		jadePaxel = new ItemPaxel(jadePaxelID, ErebusMod.toolJADE).setUnlocalizedName("paxelJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:paxelJade");
		jadeHoe = new ItemHoe(jadeHoeID, ErebusMod.toolJADE).setUnlocalizedName("hoeJade").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:hoeJade");
		compoundGoggles = new ItemCompoundGoggles(compoundGogglesID, ErebusMod.armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:compoundGoggles"); 
        compoundEyes = (new Item(compoundEyesID)).setUnlocalizedName("compoundEyes").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:compoundEyes"); 
        compoundLens = (new Item(compoundLensID)).setUnlocalizedName("compoundLens").setCreativeTab(ErebusMod.tabErebus).func_111206_d("erebus:compoundLens"); 
    
		//Tools classes
		MinecraftForge.setToolClass(jadeAxe, "axe", 2);
		MinecraftForge.setToolClass(jadePickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(jadeShovel, "shovel", 2);
    }
}
