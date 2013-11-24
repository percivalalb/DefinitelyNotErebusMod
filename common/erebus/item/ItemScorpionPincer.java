package erebus.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScorpionPincer;

public class ItemScorpionPincer extends ItemSword {

	public ItemScorpionPincer(int id) {
		super(id, ErebusMod.weaponScorpionPincer);
		maxStackSize = 1;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
		stack.damageItem(1, player);
		if (!(entity instanceof EntityScorpion)){
			double knockback = 0.5D;
			double direction = Math.toRadians(player.renderYawOffset);
			entity.addVelocity(-Math.sin(direction) * knockback, 0.25D, Math.cos(direction) * knockback);
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityScorpionPincer(world, entityplayer));
		entityplayer.swingItem();
		return itemstack;
	}
}
