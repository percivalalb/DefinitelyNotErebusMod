package erebus.core.addon.buildcraft;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.event.ForgeSubscribe;
import erebus.ModBlocks;
import erebus.core.addon.AddonEvent;

/**
 * @author ProPercivalalb
 */
public class BuildcraftAddon {

	private static BuildcraftAPI API = new BuildcraftAPI(BuildcraftLib.MOD_NAME);
	
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
	public void onPost(AddonEvent.Post event) {
		if(!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
		API.registerFacade(ModBlocks.umberstone.blockID, 0);
		API.registerFacade(ModBlocks.umberstone.blockID, 1);
		API.registerFacade(ModBlocks.umberstone.blockID, 2);
		API.registerFacade(ModBlocks.umberstone.blockID, 3);
		
	}
}
