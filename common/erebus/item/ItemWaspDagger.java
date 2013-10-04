package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.entity.EntityWaspDagger;

public class ItemWaspDagger extends ItemSword {
	public ItemWaspDagger(int id) {
		super(id, ErebusMod.weaponWaspDagger);
		setCreativeTab(null);
		maxStackSize = 16;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!entityplayer.capabilities.isCreativeMode)
			itemstack.stackSize--;
		world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityWaspDagger(world, entityplayer));
		return itemstack;
	}
}