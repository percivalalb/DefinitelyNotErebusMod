package erebus.world.loot;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LootItemStack implements IWeightProvider {
	private short itemID;
	private short minDamage = 0, maxDamage = 0;
	private byte minAmount = 1, maxAmount = 1;
	private short weight = 1;

	public LootItemStack(Block block) {
		this.itemID = (short) block.blockID;
	}

	public LootItemStack(Item item) {
		this.itemID = (short) item.itemID;
	}

	public LootItemStack setDamage(int min, int max) {
		this.minDamage = (short) Math.max(1, min);
		this.maxDamage = (short) Math.max(1, max);
		return this;
	}

	public LootItemStack setDamage(int damage) {
		this.minDamage = this.maxDamage = (short) damage;
		return this;
	}

	public LootItemStack setAmount(int min, int max) {
		this.minAmount = (byte) Math.max(1, min);
		this.maxAmount = (byte) Math.max(1, max);
		return this;
	}

	public LootItemStack setAmount(int amount) {
		this.minAmount = this.maxAmount = (byte) Math.max(1, amount);
		return this;
	}

	public LootItemStack setWeight(int weight) {
		this.weight = (short) Math.max(1, weight);
		return this;
	}

	@Override
	public short getWeight() {
		return weight;
	}

	public ItemStack getIS(Random rand) {
		return new ItemStack(itemID, rand.nextInt(maxAmount - minAmount + 1) + minAmount, rand.nextInt(maxDamage - minDamage + 1) + minDamage);
	}
}