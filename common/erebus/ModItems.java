package erebus;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.item.ItemArmorGlider;
import erebus.item.ItemBambucket;
import erebus.item.ItemCavemanClub;
import erebus.item.ItemCompoundGoggles;
import erebus.item.ItemErebusFood;
import erebus.item.ItemErebusMaterial;
import erebus.item.ItemExoskeletonArmor;
import erebus.item.ItemJadeArmor;
import erebus.item.ItemJumpBoots;
import erebus.item.ItemMaxSpeedBow;
import erebus.item.ItemMetalIngots;
import erebus.item.ItemPaxel;
import erebus.item.ItemPortalActivator;
import erebus.item.ItemSprayCan;
import erebus.item.ItemSprintLeggings;
import erebus.item.ItemWandOfAnimation;
import erebus.item.ItemWaspDagger;

public class ModItems {

	//@formatter:off

	// BASIC MATERIALS
	public static Item portalActivator;					public static int portalActivatorID;
	public static Item erebusMaterials;  				public static int erebusMaterialsID;
	public static Item erebusFood;						public static int erebusFoodID;
	public static Item metalIngot;						public static int metalIngotID;
	public static Item bamBucket;						public static int bamBucketID;
	public static Item turnip;							public static int turnipID;
	public static Item sprayCan;						public static int sprayCanID;
	public static Item wandOfAnimation;					public static int wandOfAnimationID;

	// JADE STUFF
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

	// EXOSKELETON STUFF
	public static Item exoskeletonHelmet;				public static int exoskeletonHelmetID;
	public static Item exoskeletonBody;					public static int exoskeletonBodyID;
	public static Item exoskeletonLegs;					public static int exoskeletonLegsID;
	public static Item exoskeletonBoots;				public static int exoskeletonBootsID;

	// MISC WEAPONS
	public static Item fossilClub;						public static int fossilClubID;
	public static Item waspSword;						public static int waspSwordID;
	public static Item maxSpeedBow;						public static int maxSpeedBowID;
	public static Item waspDagger;						public static int waspDaggerID;

	// MISC ARMOR
	public static Item compoundGoggles;                 public static int compoundGogglesID;
	public static Item sprintLeggings;					public static int sprintLeggingsID;
	public static Item jumpBoots;						public static int jumpBootsID;
	public static Item armorGlider;						public static int armorGliderID;

	//@formatter:on

	public static void init() {
		// constructor, set full 3D, creative tab (null), unlocalized name,
		// texture name

		portalActivator = new ItemPortalActivator(portalActivatorID).setUnlocalizedName("portalActivator").setTextureName("erebus:portalActivator");
		erebusMaterials = new ItemErebusMaterial(erebusMaterialsID).setUnlocalizedName("erebusMaterials");
		erebusFood = new ItemErebusFood(erebusFoodID).setUnlocalizedName("erebusFood");
		if (ErebusMod.activateExtraOres)
			metalIngot = new ItemMetalIngots(metalIngotID);
		bamBucket = new ItemBambucket(bamBucketID).setUnlocalizedName("bamBucket");
		turnip = new ItemSeedFood(turnipID, 4, 0.6F, ModBlocks.blockTurnip.blockID, Block.tilledField.blockID).setUnlocalizedName("turnips").setTextureName("erebus:turnips");
		sprayCan = new ItemSprayCan(sprayCanID).setUnlocalizedName("sprayCan").setTextureName("erebus:sprayCan");
		wandOfAnimation = new ItemWandOfAnimation(wandOfAnimationID).setUnlocalizedName("wandOfAnimation");

		jadeHelmet = new ItemJadeArmor(jadeHelmetID, 0).setUnlocalizedName("helmetJade").setTextureName("erebus:helmetJade");
		jadeBody = new ItemJadeArmor(jadeBodyID, 1).setUnlocalizedName("chestplateJade").setTextureName("erebus:chestplateJade");
		jadeLegs = new ItemJadeArmor(jadeLegsID, 2).setUnlocalizedName("leggingsJade").setTextureName("erebus:leggingsJade");
		jadeBoots = new ItemJadeArmor(jadeBootsID, 3).setUnlocalizedName("bootsJade").setTextureName("erebus:bootsJade");
		jadeSword = new ItemSword(jadeSwordID, ErebusMod.toolJADE).setUnlocalizedName("swordJade").setTextureName("erebus:swordJade");
		jadePickaxe = new ItemPickaxe(jadePickaxeID, ErebusMod.toolJADE).setUnlocalizedName("pickaxeJade").setTextureName("erebus:pickaxeJade");
		jadeAxe = new ItemAxe(jadeAxeID, ErebusMod.toolJADE).setUnlocalizedName("axeJade").setTextureName("erebus:axeJade");
		jadeShovel = new ItemSpade(jadeShovelID, ErebusMod.toolJADE).setUnlocalizedName("shovelJade").setTextureName("erebus:shovelJade");
		jadePaxel = new ItemPaxel(jadePaxelID, ErebusMod.toolJADEPAXEL).setUnlocalizedName("paxelJade").setTextureName("erebus:paxelJade");
		jadeHoe = new ItemHoe(jadeHoeID, ErebusMod.toolJADE).setUnlocalizedName("hoeJade").setTextureName("erebus:hoeJade");

		exoskeletonHelmet = new ItemExoskeletonArmor(exoskeletonHelmetID, 0).setUnlocalizedName("helmetExo").setTextureName("erebus:helmetExo");
		exoskeletonBody = new ItemExoskeletonArmor(exoskeletonBodyID, 1).setUnlocalizedName("chestplateExo").setTextureName("erebus:chestplateExo");
		exoskeletonLegs = new ItemExoskeletonArmor(exoskeletonLegsID, 2).setUnlocalizedName("leggingsExo").setTextureName("erebus:leggingsExo");
		exoskeletonBoots = new ItemExoskeletonArmor(exoskeletonBootsID, 3).setUnlocalizedName("bootsExo").setTextureName("erebus:bootsExo");

		fossilClub = new ItemCavemanClub(fossilClubID).setFull3D().setUnlocalizedName("clubBone").setTextureName("erebus:clubBone");
		waspSword = new ItemSword(waspSwordID, ErebusMod.toolJADE).setUnlocalizedName("waspSword");
		maxSpeedBow = new ItemMaxSpeedBow(maxSpeedBowID).setUnlocalizedName("maxSpeedBow").setTextureName("erebus:maxSpeedBow");
		waspDagger = new ItemWaspDagger(waspDaggerID).setUnlocalizedName("waspDagger");

		compoundGoggles = new ItemCompoundGoggles(compoundGogglesID, ErebusMod.armorEXOSKELETON, 2, 0).setUnlocalizedName("compoundGoggles").setTextureName("erebus:compoundGoggles");
		sprintLeggings = new ItemSprintLeggings(sprintLeggingsID, ErebusMod.armorEXOSKELETON, 2).setUnlocalizedName("sprintLeggings").setTextureName("erebus:sprintLeggings");
		jumpBoots = new ItemJumpBoots(jumpBootsID, ErebusMod.armorEXOSKELETON, 3).setUnlocalizedName("jumpBoots").setTextureName("erebus:jumpBoots");
		armorGlider = new ItemArmorGlider(armorGliderID, 1).setUnlocalizedName("armorGlider").setTextureName("erebus:armorGlider");

		// Creative tabs
		ErebusMod.tabErebusItem.add(portalActivator, erebusMaterials, erebusFood, turnip, wandOfAnimation);
		if (ErebusMod.activateExtraOres)
			ErebusMod.tabErebusItem.add(metalIngot);

		ErebusMod.tabErebusGear.add(bamBucket, sprayCan);
		ErebusMod.tabErebusGear.add(jadeHelmet, jadeBody, jadeLegs, jadeBoots, jadeSword, jadePickaxe, jadeAxe, jadeShovel, jadePaxel, jadeHoe);
		ErebusMod.tabErebusGear.add(exoskeletonHelmet, exoskeletonBody, exoskeletonLegs, exoskeletonBoots);
		ErebusMod.tabErebusGear.add(fossilClub, waspSword, waspDagger, maxSpeedBow);
		ErebusMod.tabErebusGear.add(compoundGoggles, sprintLeggings, jumpBoots, armorGlider);

		// Tool classes
		MinecraftForge.setToolClass(jadeAxe, "axe", 2);
		MinecraftForge.setToolClass(jadePickaxe, "pickaxe", 2);
		MinecraftForge.setToolClass(jadeShovel, "shovel", 2);

		GameRegistry.registerItem(portalActivator, "erebus.portalActivator");
		GameRegistry.registerItem(erebusMaterials, "erebus.erebusMaterials");
		GameRegistry.registerItem(erebusFood, "erebus.erebusFood");
		if (ErebusMod.activateExtraOres)
			GameRegistry.registerItem(metalIngot, "erebus.metalIngot");
		GameRegistry.registerItem(bamBucket, "erebus.bamBucket");
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(bamBucket, 1, 1), new ItemStack(bamBucket, 1, 0));
		GameRegistry.registerItem(turnip, "erebus.turnips");
		GameRegistry.registerItem(sprayCan, "erebus.sprayCan");
		GameRegistry.registerItem(wandOfAnimation, "erebus.wandOfAnimation");

		GameRegistry.registerItem(jadeHelmet, "erebus.helmetJade");
		GameRegistry.registerItem(jadeBody, "erebus.chestplateJade");
		GameRegistry.registerItem(jadeLegs, "erebus.leggingsJade");
		GameRegistry.registerItem(jadeBoots, "erebus.bootsJade");
		GameRegistry.registerItem(jadeSword, "erebus.swordJade");
		GameRegistry.registerItem(jadePickaxe, "erebus.pickaxeJade");
		GameRegistry.registerItem(jadeAxe, "erebus.axeJade");
		GameRegistry.registerItem(jadeShovel, "erebus.shovelJade");
		GameRegistry.registerItem(jadePaxel, "erebus.paxelJade");
		GameRegistry.registerItem(jadeHoe, "erebus.hoeJade");

		GameRegistry.registerItem(exoskeletonHelmet, "erebus.helmetExo");
		GameRegistry.registerItem(exoskeletonBody, "erebus.chestplateExo");
		GameRegistry.registerItem(exoskeletonLegs, "erebus.leggingsExo");
		GameRegistry.registerItem(exoskeletonBoots, "erebus.bootsExo");

		GameRegistry.registerItem(fossilClub, "erebus.clubBone");
		GameRegistry.registerItem(waspSword, "erebus.waspSword");
		GameRegistry.registerItem(maxSpeedBow, "erebus.maxSpeedBow");
		GameRegistry.registerItem(waspDagger, "erebus.waspDagger");

		GameRegistry.registerItem(compoundGoggles, "erebus.compoundGoggles");
		GameRegistry.registerItem(sprintLeggings, "erebus.sprintLeggings");
		GameRegistry.registerItem(jumpBoots, "erebus.jumpBoots");
		GameRegistry.registerItem(armorGlider, "erebus.armorGlider");
	}
}
