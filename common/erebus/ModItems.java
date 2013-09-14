package erebus;

import erebus.item.ItemBambucket;
import erebus.item.ItemCompoundGoggles;
import erebus.item.ItemErebusFood;
import erebus.item.ItemExoskeletonArmor;
import erebus.item.ItemJadeArmor;
import erebus.item.ItemErebusMaterial;
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
	public static Item bamBucket;						public static int bamBucketID = 2314;
	
	public static Item exoskeletonHelmet;				public static int exoskeletonHelmetID;
	public static Item exoskeletonBody;					public static int exoskeletonBodyID;
	public static Item exoskeletonLegs;					public static int exoskeletonLegsID;
	public static Item exoskeletonBoots;				public static int exoskeletonBootsID;
	public static Item portalActivator;					public static int portalActivatorID;
	public static Item fossilClub;						public static int fossilClubID;
	public static Item maxSpeedBow;						public static int maxSpeedBowID;
	public static Item turnip;							public static int turnipID;
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
    public static Item compoundGoggles;                 public static int compoundGogglesID; 
    public static Item waspSword;						public static int waspSwordID; 
    
    public static void init() {
    	// constructor, set full 3D, creative tab, unlocalized name, texture name
    	
		exoskeletonHelmet = new ItemExoskeletonArmor(exoskeletonHelmetID, 0).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("helmetExo").setTextureName("erebus:helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(exoskeletonBodyID, 1).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("chestplateExo").setTextureName("erebus:chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(exoskeletonLegsID, 2).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("leggingsExo").setTextureName("erebus:leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(exoskeletonBootsID, 3).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("bootsExo").setTextureName("erebus:bootsExo");
		erebusMaterials = new ItemErebusMaterial(erebusMaterialsID).setUnlocalizedName("erebusMaterials").setCreativeTab(ErebusMod.tabErebusItem);
		erebusFood = new ItemErebusFood(erebusFoodID).setCreativeTab(ErebusMod.tabErebusItem).setUnlocalizedName("erebusFood");
		bamBucket = new ItemBambucket(bamBucketID).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("bamBucket");
		
		portalActivator = (new ItemPortalActivator(portalActivatorID)).setCreativeTab(ErebusMod.tabErebusItem).setUnlocalizedName("portalActivator").setTextureName("erebus:portalActivator");
		fossilClub = (new ItemWeaponErebus(fossilClubID, 72, 9, 12, Item.bone.itemID)).setFull3D().setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("clubBone").setTextureName("erebus:clubBone");
		maxSpeedBow = (new ItemMaxSpeedBow(maxSpeedBowID, 301, 5)).setUnlocalizedName("maxSpeedBow").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:maxSpeedBow");
		turnip = (new ItemSeedFood(turnipID, 4, 0.6F, ModBlocks.blockTurnip.blockID, Block.tilledField.blockID)).setCreativeTab(ErebusMod.tabErebusItem).setUnlocalizedName("turnips").setTextureName("erebus:turnips");
		jadeHelmet = new ItemJadeArmor(jadeHelmetID, 0).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("helmetJade").setTextureName("erebus:helmetJade");
		jadeBody = new ItemJadeArmor(jadeBodyID, 1).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("chestplateJade").setTextureName("erebus:chestplateJade");
		jadeLegs = new ItemJadeArmor(jadeLegsID, 2).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("leggingsJade").setTextureName("erebus:leggingsJade");
		jadeBoots = new ItemJadeArmor(jadeBootsID, 3).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("bootsJade").setTextureName("erebus:bootsJade");
		jadeSword = new ItemSword(jadeSwordID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("swordJade").setTextureName("erebus:swordJade");
		jadePickaxe = new ItemPickaxe(jadePickaxeID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("pickaxeJade").setTextureName("erebus:pickaxeJade");
		jadeAxe = new ItemAxe(jadeAxeID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("axeJade").setTextureName("erebus:axeJade");
		jadeShovel = new ItemSpade(jadeShovelID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("shovelJade").setTextureName("erebus:shovelJade");
		jadePaxel = new ItemPaxel(jadePaxelID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("paxelJade").setTextureName("erebus:paxelJade");
		jadeHoe = new ItemHoe(jadeHoeID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("hoeJade").setTextureName("erebus:hoeJade");
		compoundGoggles = new ItemCompoundGoggles(compoundGogglesID, ErebusMod.armorEXOSKELETON, 2, 0).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("compoundGoggles").setTextureName("erebus:compoundGoggles"); 
		waspSword = new ItemSword(waspSwordID, ErebusMod.toolJADE).setCreativeTab(ErebusMod.tabErebusGear).setUnlocalizedName("waspSword");
		
		//Tools classes
		MinecraftForge.setToolClass(jadeAxe, "axe", 2);
		MinecraftForge.setToolClass(jadePickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(jadeShovel, "shovel", 2);
    }
}
