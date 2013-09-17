package erebus.core.addon.buildcraft;

public class PipeItemsPetrifiedWood extends buildcraft.transport.pipes.PipeItemsWood {

	public PipeItemsPetrifiedWood(int id) {
		super(id);
		this.solidIconIndex = PipeIconProvider.TYPE.PipeAllPetrifiedWoodSolid.ordinal();
		this.standardIconIndex = PipeIconProvider.TYPE.PipeItemPetrifiedWoodStandard.ordinal();
	}
}
