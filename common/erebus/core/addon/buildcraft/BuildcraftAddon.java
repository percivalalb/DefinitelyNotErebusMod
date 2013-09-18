package erebus.core.addon.buildcraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.Loader;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.addon.AddonEvent;

/**
 * @author ProPercivalalb
 */
public class BuildcraftAddon {

	private static BuildcraftAPI API = new BuildcraftAPI(BuildcraftLib.MOD_NAME);

	public static Item pipeItemsPetrifiedWood;

	@ForgeSubscribe
	public void onPre(AddonEvent.FMLPre event) {
		if (!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
		pipeItemsPetrifiedWood = API.registerPipe(event.config.get(Configuration.CATEGORY_ITEM, "Item ID of Buildcraft Petrified Wood Pipe", 9800).getInt(), PipeItemsPetrifiedWood.class, "PetrifiedWood", ModBlocks.petrifiedWoodPlanks, Block.glass, ModBlocks.petrifiedWoodPlanks);
		pipeItemsPetrifiedWood.setCreativeTab(ErebusMod.tabErebusBlock);

		MinecraftForgeClient.registerItemRenderer(pipeItemsPetrifiedWood.itemID, API.getPipeRender());
	}

	@ForgeSubscribe
	public void onPre(AddonEvent.FMLInit event) {
		if (!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
	}

	@ForgeSubscribe
	public void onPre(AddonEvent.Pre event) {
		if (!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
	}

	@ForgeSubscribe
	public void onInit(AddonEvent.Init event) {
		if (!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
	}

	@ForgeSubscribe
	public void onPost(AddonEvent.Post event) throws Exception {
		if (!Loader.isModLoaded(BuildcraftLib.MOD_NAME))
			return;
		API.registerFacade(ModBlocks.umberstone.blockID, 0);
		API.registerFacade(ModBlocks.umberstone.blockID, 1);
		API.registerFacade(ModBlocks.umberstone.blockID, 2);
		API.registerFacade(ModBlocks.umberstone.blockID, 3);

		API.banConnection(PipeItemsPetrifiedWood.class);
		API.banConnection(PipeItemsPetrifiedWood.class, Class.forName("buildcraft.transport.pipes.PipeItemsWood"));
	}
}
