package erebus.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockErebusOre;
import erebus.core.helper.LogHelper;

public class ThaumcraftIntegration implements IModIntegration{
	@Override
	public String getModId(){
		return "Thaumcraft";
	}

	@Override
	public void integrate(){
		registerBlock(ModBlocks.blockAmber, 0, Aspect.STONE,2, Aspect.CRYSTAL,2, Aspect.SLIME,1);
		registerBlock(ModBlocks.blockAmber, 1, Aspect.STONE,2, Aspect.CRYSTAL,5);
		registerBlock(ModBlocks.blockAmber, 2, Aspect.STONE,2, Aspect.CRYSTAL,2, Aspect.SLIME,1);
		
		// wood, walls, slabs, stairs
		
		registerBlock(ModBlocks.blockSilk, -1, Aspect.CLOTH,4);
		registerBlock(ModBlocks.mirBrick, -1, Aspect.STONE,2, Aspect.MIND,1);
		registerBlock(ModBlocks.spiderSpawner, -1, Aspect.BEAST,5, Aspect.TAINT,2, Aspect.WEAPON,2, Aspect.SOUL,3, Aspect.POISON,1);
		registerBlock(ModBlocks.caveSpiderSpawner, -1, Aspect.BEAST,5, Aspect.TAINT,2, Aspect.WEAPON,2, Aspect.SOUL,3, Aspect.POISON,1);
		registerBlock(ModBlocks.thorns, -1, Aspect.PLANT,2, Aspect.WEAPON,1);
		registerBlock(ModBlocks.fern, -1, Aspect.PLANT,2, Aspect.SEED, 2);
		// tall grass
		registerBlock(ModBlocks.quickSand, -1, Aspect.TRAP,4, Aspect.DEATH,2, Aspect.EARTH,2);
		registerBlock(ModBlocks.ghostSand, -1, Aspect.TRAP,4, Aspect.VOID,3);
		registerBlock(ModBlocks.hollowLogAcacia, -1, Aspect.SLIME,1, Aspect.TREE,3);
		
		// ores
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataJade, Aspect.GREED,4, Aspect.CRYSTAL,3, Aspect.EARTH,2);
		registerBlock(ModBlocks.umberOreBlock, BlockErebusOre.dataPetrifiedWood, Aspect.EARTH,1, Aspect.TREE,2, Aspect.STONE,2);
		registerBlock(ModBlocks.oreFossil, -1, Aspect.DEATH,1, Aspect.BEAST,1, Aspect.EARTH,1);
		registerBlock(ModBlocks.umberstone, 0, Aspect.EARTH,2, Aspect.STONE,2);
		registerBlock(ModBlocks.umberstone, 1, Aspect.EARTH,1, Aspect.STONE,2);
		registerBlock(ModBlocks.umberstone, 2, Aspect.EARTH,1, Aspect.STONE,2, Aspect.PLANT,2);
		registerBlock(ModBlocks.umberstone, 3, Aspect.EARTH,1, Aspect.STONE,2, Aspect.BEAST,1);
		registerBlock(ModBlocks.umberstone, 4, Aspect.STONE,2);
		
		registerBlock(ModBlocks.bambooCrop, -1, Aspect.PLANT,2, Aspect.CROP,2, Aspect.CRAFT,1);
		registerBlock(ModBlocks.bambooCrate, -1, Aspect.PLANT,4, Aspect.VOID,3, Aspect.CRAFT,2);
		registerBlock(ModBlocks.redGem, 0, Aspect.LIGHT,2, Aspect.CRYSTAL,3, Aspect.ENERGY,2);
		registerBlock(ModBlocks.redGem, 1, Aspect.LIGHT,2, Aspect.CRYSTAL,2, Aspect.MECHANISM,2);
		registerBlock(ModBlocks.redGem, 2, Aspect.LIGHT,2, Aspect.CRYSTAL,2, Aspect.MECHANISM,2);
		
		registerBlock(ModBlocks.petrifiedWoodPlanks, -1, Aspect.TREE,2, Aspect.STONE,2);
		registerBlock(ModBlocks.petrifiedWoodStairs, -1, Aspect.TREE,2, Aspect.STONE,2, Aspect.TRAVEL,1);
		registerBlock(ModBlocks.petrifiedCraftingTable, -1, Aspect.TREE,2, Aspect.STONE,2, Aspect.CRAFT,4);
		
		registerBlock(ModBlocks.umberPaver, 0, Aspect.EARTH,1, Aspect.STONE,2);
		registerBlock(ModBlocks.umberPaver, 1, Aspect.EARTH,1, Aspect.STONE,2, Aspect.PLANT,2);
		registerBlock(ModBlocks.umberPaver, 2, Aspect.EARTH,1, Aspect.STONE,2, Aspect.BEAST,1);
		registerBlock(ModBlocks.umberFurnace, -1, Aspect.MECHANISM,3, Aspect.STONE,2, Aspect.FIRE,3);
		registerBlock(ModBlocks.fiddlehead, -1, Aspect.PLANT,2);
		registerBlock(ModBlocks.insectRepellent, -1, Aspect.POISON,1, Aspect.AURA,2);
		registerBlock(ModBlocks.bambooTorch, -1, Aspect.LIGHT,2, Aspect.PLANT,1);
		registerBlock(ModBlocks.erebusAltar, 0, Aspect.MAGIC,2, Aspect.ELDRITCH,3, Aspect.STONE,2);
		// lightning altar
		
		registerItem(ModItems.portalActivator, -1, Aspect.MAGIC,2, Aspect.ELDRITCH,3);
		
		registerItem(ModItems.exoskeletonHelmet, -1, Aspect.BEAST,1, Aspect.ARMOR,2);
		registerItem(ModItems.exoskeletonBody, -1, Aspect.BEAST,1, Aspect.ARMOR,5);
		registerItem(ModItems.exoskeletonLegs, -1, Aspect.BEAST,1, Aspect.ARMOR,4, Aspect.MAN,1, Aspect.MOTION,2);
		registerItem(ModItems.exoskeletonBoots, -1, Aspect.BEAST,1, Aspect.ARMOR,3, Aspect.TRAVEL,2);
		
		registerItem(ModItems.jadeHelmet, -1, Aspect.GREED,5, Aspect.CRYSTAL,3, Aspect.ARMOR,4);
		registerItem(ModItems.jadeBody, -1, Aspect.GREED,8, Aspect.CRYSTAL,4, Aspect.ARMOR,6);
		registerItem(ModItems.jadeLegs, -1, Aspect.GREED,7, Aspect.CRYSTAL,4, Aspect.ARMOR,5, Aspect.MOTION,2);
		registerItem(ModItems.jadeBoots, -1, Aspect.GREED,4, Aspect.CRYSTAL,2, Aspect.ARMOR,3, Aspect.TRAP,3);
		
		registerItem(ModItems.fossilClub, -1, Aspect.DEATH,2, Aspect.BEAST,1);
		registerItem(ModItems.maxSpeedBow, -1, Aspect.WEAPON,5, Aspect.DEATH,2, Aspect.AIR,3);
		
		registerItem(ModItems.turnip, -1, Aspect.HUNGER,2, Aspect.LIFE,2, Aspect.SEED,1, Aspect.CROP,1);
	}
	
	private void registerBlock(Block block, int metadata, Object...aspects){
		ThaumcraftApi.registerObjectTag(block.blockID, metadata, generateAspectList(aspects));
	}
	
	private void registerItem(Item item, int metadata, Object...aspects){
		ThaumcraftApi.registerObjectTag(item.itemID, metadata, generateAspectList(aspects));
	}
	
	private AspectList generateAspectList(Object...aspectData){
		int index=0;
		AspectList list=new AspectList();
		Aspect currentAspect=null;
		
		try{
			for(Object o:aspectData){
				if ((index&1)==0)currentAspect=(Aspect)o;
				else list.add(currentAspect,(Integer)o);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			LogHelper.logSevere("Oops - Thaumcraft integration went wrong.");
		}
		
		return list;
	}
}
