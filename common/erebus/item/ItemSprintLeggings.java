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

	private int armtick;
	private String texture;

	public ItemSprintLeggings(int i, EnumArmorMaterial enumarmormaterial, int k) {
		super(i, enumarmormaterial, 2, k);
		setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.itemID == ModItems.sprintLeggings.itemID) {
			if (armtick >= 0 && armtick <= 20)
				texture = "erebus:textures/armor/centipede_0.png";
			if (armtick > 20 && armtick <= 40)
				texture = "erebus:textures/armor/centipede_1.png";
			if (armtick > 40 && armtick <= 60)
				texture = "erebus:textures/armor/centipede_2.png";
		}
		return texture;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (player.isSprinting() && player.onGround) {
			player.motionX *= 1.66699999910593033D;
			player.motionZ *= 1.66699999910593033D;
		}
		armtick++;
		if (armtick > 60 || player.isSprinting())
			armtick = 0;
	}
}