package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModItems;

/**
 * @author Everyone involved
 */

public class ItemCompoundGoggles extends ItemArmor {

	public ItemCompoundGoggles(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
		setCreativeTab(null);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.compoundGoggles.itemID)
			return "erebus:textures/armor/goggles_1.png";
		else
			return null;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		ItemStack is = player.inventory.armorInventory[3];
		if (is != null && is.itemID == itemID) {
			ErebusMod.proxy.setClientNightVision(true);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if (entity instanceof EntityPlayer) {
			ItemStack is = ((EntityPlayer)entity).inventory.armorInventory[3];
			if (is == null || is.itemID != itemID) {
				ErebusMod.proxy.setClientNightVision(false);
			}
		}
	}
}