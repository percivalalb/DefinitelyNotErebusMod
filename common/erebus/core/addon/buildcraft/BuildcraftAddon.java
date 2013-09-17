package erebus.core.addon.buildcraft;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.addon.AddonEvent;
import erebus.core.helper.ReflectionHelper;

/**
 * @author ProPercivalalb
 */
public class BuildcraftAddon {

	private static BuildcraftAPI API = new BuildcraftAPI(BuildcraftLib.MOD_NAME);
	
	public static Item pipeItemsWood;
	
	@ForgeSubscribe
	public void onPre(AddonEvent.Pre event) {
		if(!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
	}
	
	@ForgeSubscribe
	public void onInit(AddonEvent.Init event) {
		if(!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
	}

	@ForgeSubscribe
	public void onPost(AddonEvent.Post event) throws Exception {
		if(!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
		API.registerFacade(ModBlocks.umberstone.blockID, 0);
		API.registerFacade(ModBlocks.umberstone.blockID, 1);
		API.registerFacade(ModBlocks.umberstone.blockID, 2);
		API.registerFacade(ModBlocks.umberstone.blockID, 3);
		
		pipeItemsWood = API.registerPipe(600, PipeItemWood.class, "Wood 23odjs", Item.stick, Block.glass, Item.emerald);
		//pipeItemsWood.setCreativeTab(ErebusMod.tabErebusBlock);
	}
}
