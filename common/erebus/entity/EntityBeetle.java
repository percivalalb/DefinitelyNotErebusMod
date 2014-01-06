package erebus.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class EntityBeetle extends EntityCreature {

	public int skin = rand.nextInt(51);

	public EntityBeetle(World world) {
		super(world);
		setSize(0.9F, 0.9F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.6D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, Item.wheat.itemID, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(15.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	protected String getLivingSound() {
		return null;
	}

	@Override
	protected String getHurtSound() {
		return null;
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();

		if (is != null) {
			if (is.itemID == Item.bucketEmpty.itemID && !player.capabilities.isCreativeMode)
				if (is.stackSize-- == 1)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.bucketOfBeetleJuice));
				else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.bucketOfBeetleJuice)))
					player.dropPlayerItem(new ItemStack(ModItems.bucketOfBeetleJuice.itemID, 1, 0));

			if (is.itemID == ModItems.bamBucket.itemID && is.getItemDamage() == 0 && !player.capabilities.isCreativeMode)
				if (is.stackSize-- == 1)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.bamBucket, 1, 2));
				else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.bamBucket, 1, 2)))
					player.dropPlayerItem(new ItemStack(ModItems.bamBucket.itemID, 1, 2));

			return true;
		} else
			return super.interact(player);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + par2);
		for (int a = 0; a < var3; ++a)
			entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}
}
