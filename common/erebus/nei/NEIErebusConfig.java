package erebus.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import erebus.ModBlocks;
import erebus.lib.Reference;

public class NEIErebusConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.hideItem(ModBlocks.erebusGrass.blockID);
		API.hideItem(ModBlocks.portalErebus.blockID);
		API.hideItem(ModBlocks.blockTurnip.blockID);
		API.hideItem(ModBlocks.insectRepellent.blockID);
		API.hideItem(ModBlocks.umberFurnace_on.blockID);
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.MOD_VERSION;
	}
}

// Here: http://adf.ly/WAp6y