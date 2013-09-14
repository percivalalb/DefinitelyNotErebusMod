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
		exoskeletonHelmet = new ItemExoskeletonArmor(exoskeletonHelmetID, ErebusMod.armorEXOSKELETON, 2, 0).setUnlocalizedName("helmetExo").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(exoskeletonBodyID, ErebusMod.armorEXOSKELETON, 2, 1).setUnlocalizedName("chestplateExo").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(exoskeletonLegsID, ErebusMod.armorEXOSKELETON, 2, 2).setUnlocalizedName("leggingsExo").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(exoskeletonBootsID, ErebusMod.armorEXOSKELETON, 2, 3).setUnlocalizedName("bootsExo").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:bootsExo");
		erebusMaterials = new ItemErebusMaterial(erebusMaterialsID).setUnlocalizedName("erebusMaterials").setCreativeTab(ErebusMod.tabErebusItem);
		erebusFood = new ItemErebusFood(erebusFoodID).setUnlocalizedName("erebusFood").setCreativeTab(ErebusMod.tabErebusItem);
		bamBucket = new ItemBambucket(bamBucketID).setUnlocalizedName("bamBucket").setCreativeTab(ErebusMod.tabErebusGear);
		
		portalActivator = (new ItemPortalActivator(portalActivatorID)).setUnlocalizedName("portalActivator").setCreativeTab(ErebusMod.tabErebusItem).setTextureName("erebus:portalActivator");
		fossilClub = (new ItemWeaponErebus(fossilClubID, 72, 9, 12, Item.bone.itemID)).setUnlocalizedName("clubBone").setFull3D().setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:clubBone");
		maxSpeedBow = (new ItemMaxSpeedBow(maxSpeedBowID, 301, 5)).setUnlocalizedName("maxSpeedBow").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:maxSpeedBow");
		turnip = (new ItemSeedFood(turnipID, 4, 0.6F, ModBlocks.blockTurnip.blockID, Block.tilledField.blockID)).setUnlocalizedName("turnips").setCreativeTab(ErebusMod.tabErebusItem).setTextureName("erebus:turnips");
		jadeHelmet = new ItemJadeArmor(jadeHelmetID, ErebusMod.armorJADE, 2, 0).setUnlocalizedName("helmetJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:helmetJade");
		jadeBody = new ItemJadeArmor(jadeBodyID, ErebusMod.armorJADE, 2, 1).setUnlocalizedName("chestplateJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:chestplateJade");
		jadeLegs = new ItemJadeArmor(jadeLegsID, ErebusMod.armorJADE, 2, 2).setUnlocalizedName("leggingsJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:leggingsJade");
		jadeBoots = new ItemJadeArmor(jadeBootsID, ErebusMod.armorJADE, 2, 3).setUnlocalizedName("bootsJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:bootsJade");
		jadeSword = new ItemSword(jadeSwordID, ErebusMod.toolJADE).setUnlocalizedName("swordJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:swordJade");
		jadePickaxe = new ItemPickaxe(jadePickaxeID, ErebusMod.toolJADE).setUnlocalizedName("pickaxeJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:pickaxeJade");
		jadeAxe = new ItemAxe(jadeAxeID, ErebusMod.toolJADE).setUnlocalizedName("axeJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:axeJade");
		jadeShovel = new ItemSpade(jadeShovelID, ErebusMod.toolJADE).setUnlocalizedName("shovelJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:shovelJade");
		jadePaxel = new ItemPaxel(jadePaxelID, ErebusMod.toolJADE).setUnlocalizedName("paxelJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:paxelJade");
		jadeHoe = new ItemHoe(jadeHoeID, ErebusMod.toolJADE).setUnlocalizedName("hoeJade").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:hoeJade");
		compoundGoggles = new ItemCompoundGoggles(compoundGogglesID, ErebusMod.armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setCreativeTab(ErebusMod.tabErebusGear).setTextureName("erebus:compoundGoggles"); 
		waspSword = new ItemSword(waspSwordID, ErebusMod.toolJADE).setUnlocalizedName("waspSword").setCreativeTab(ErebusMod.tabErebusGear);
		
		//Tools classes
		MinecraftForge.setToolClass(jadeAxe, "axe", 2);
		MinecraftForge.setToolClass(jadePickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(jadeShovel, "shovel", 2);
    }
}
