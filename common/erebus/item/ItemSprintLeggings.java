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

<<<<<<< HEAD
	public ItemSprintLeggings(int i, EnumArmorMaterial enumarmormaterial, int k) {
		super(i, enumarmormaterial, 2, k);
=======
	public ItemSprintLeggings(int i, EnumArmorMaterial enumarmormaterial,
	int j, int k) {
		super(i, enumarmormaterial, j, k);
>>>>>>> 2c4ee7cc22d4569d9a3fddd1188503b3bc1f11d9
		setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
<<<<<<< HEAD
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
=======
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
	int layer) {
>>>>>>> 2c4ee7cc22d4569d9a3fddd1188503b3bc1f11d9
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