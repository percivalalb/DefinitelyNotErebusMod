package erebus.entity;

import java.util.Random;

import erebus.ErebusMod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBeetle extends EntityUndergroundAnimal {

	public int skin = rand.nextInt(4);

	public EntityBeetle(World par1World) {
		super(par1World);
		this.setSize(0.9F, 0.9F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.2F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void func_110147_ax() {
	    super.func_110147_ax();
	    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
	    this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
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
		return null;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected int getDropItemId() {
		return ErebusMod.exoskeletonPlate.itemID;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = this.rand.nextInt(4) + this.rand.nextInt(1 + par2);
		int var4;
		for (var4 = 0; var4 < var3; ++var4)
		{
			this.dropItem(ErebusMod.exoskeletonPlate.itemID, 1);
		}
	}
}
