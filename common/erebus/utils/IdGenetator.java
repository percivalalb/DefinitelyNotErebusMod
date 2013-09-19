package erebus.utils;

/**
 * Erebus
 * 
 * @author ganymedes01
 * 
 */

public class IdGenetator {
	
	private final int itemIDBase, blockIDbase;
	private int currentItemID, currentBlockID;
	
	public IdGenetator(int itemIDBase, int blockIDbase) {
		this.itemIDBase = itemIDBase - 1;
		this.blockIDbase = blockIDbase - 1;
		currentItemID = this.itemIDBase;
		currentBlockID = this.blockIDbase;
	}
	
	public int getNextItemID() {
		return currentItemID++;
	}
	
	public int getNextBlockID() {
		return currentBlockID++;
	}
}