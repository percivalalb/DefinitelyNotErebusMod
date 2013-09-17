package erebus.core.addon.buildcraft;

import buildcraft.transport.pipes.PipeItemsWood;

public class PipeItemWood extends PipeItemsWood {

	public PipeItemWood(int id) {
		super(id);
		this.solidIconIndex = PipeIconProvider.TYPE.PipeAllPetrifiedWoodSolid.ordinal();
		this.standardIconIndex = PipeIconProvider.TYPE.PipeItemPetrifiedWoodStandard.ordinal();
	}
}
