package erebus.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class ItemMaxSpeedBow extends Item {

	private final int weaponEnchantibility;

	public static final String[] bowPullIconNameArray = new String[] { "maxSpeedBow_pull_0", "maxSpeedBow_pull_1", "maxSpeedBow_pull_2" };
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public ItemMaxSpeedBow(int id) {
		super(id);
		maxStackSize = 1;
		setMaxDamage(301);
		weaponEnchantibility = 5;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		int var6 = getMaxItemUseDuration(par1ItemStack) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
			return;
		var6 = event.charge;

		boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

		if (var5 || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)) {
			float var7 = var6 / 20.0F;
			var7 = (var7 * var7 + var7 * 2.0F) / 1.0F;

			if (var7 < 0.1D)
				return;

			if (var7 > 1.0F)
				var7 = 1.0F;

			EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);

			if (var7 == 1.0F)
				var8.setIsCritical(true);

			int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

			if (var9 > 0)
				var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);

			int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

			if (var10 > 0)
				var8.setKnockbackStrength(var10);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
				var8.setFire(100);

			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

			if (var5)
				var8.canBePickedUp = 2;
			else
				par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);

			if (!par2World.isRemote)
				par2World.spawnEntityInWorld(var8);
		}
	}

	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		boolean sneak = par3EntityPlayer.isSneaking();
		boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

		if (sneak == false) {
			if (var5 || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)) {
				EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, 1.0F * 2.0F);

				if (par2World.rand.nextInt(4) == 0)
					var8.setIsCritical(true);

				int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

				if (var9 > 0)
					var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);

				int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

				if (var10 > 0)
					var8.setKnockbackStrength(var10);

				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
					var8.setFire(100);

				par1ItemStack.damageItem(1, par3EntityPlayer);
				par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);

				if (var5)
					var8.canBePickedUp = 2;
				else
					par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);

				if (!par2World.isRemote)
					par2World.spawnEntityInWorld(var8);
			}
		}

		else if (sneak == true)
			par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	@Override
	public int getItemEnchantability() {
		return weaponEnchantibility;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		iconArray = new Icon[bowPullIconNameArray.length];

		for (int i = 0; i < iconArray.length; ++i)
			iconArray[i] = par1IconRegister.registerIcon("erebus:" + bowPullIconNameArray[i]);
	}

	@SideOnly(Side.CLIENT)
	public Icon func_94599_c(int par1) {
		return iconArray[par1];
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (usingItem != null && usingItem.getItem().itemID == ModItems.maxSpeedBow.itemID) {
			int k = usingItem.getMaxItemUseDuration() - useRemaining;
			if (k >= 6)
				return iconArray[2];
			if (k > 4)
				return iconArray[1];
			if (k > 0)
				return iconArray[0];
		}
		return itemIcon;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}
}
