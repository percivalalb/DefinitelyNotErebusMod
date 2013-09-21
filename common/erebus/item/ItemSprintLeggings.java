package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemSprintLeggings extends ItemArmor {

	public ItemSprintLeggings(int i, EnumArmorMaterial enumarmormaterial, int k) {
		super(i, enumarmormaterial, 2, k);
		setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.sprintLeggings.itemID)
			return "erebus:textures/armor/cent_1.png";
		else
			return null;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (player.isSprinting() && player.onGround) {
			player.motionX *= 1.66699999910593033D;
			player.motionZ *= 1.66699999910593033D;
		}
	}
}