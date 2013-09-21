package erebus.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import erebus.ErebusMod;
import erebus.ModItems;

public class ItemSprintLeggings extends ItemArmor {

	private static boolean forwardKeyDown = false;
	private static boolean leftKeyDown = false;
	private static boolean rightKeyDown = false;
	private static boolean backKeyDown = false;

	public ItemSprintLeggings(int i, EnumArmorMaterial enumarmormaterial,
			int j, int k) {
		super(i, enumarmormaterial, j, k);
		this.setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			int layer) {
		if (stack.itemID == ModItems.sprintLeggings.itemID) {
			return "erebus:textures/armor/cent_1.png";
		} else
			return null;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player,
			ItemStack itemStack) {
		forwardKeyDown = Keyboard
				.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode);
		leftKeyDown = Keyboard
				.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft.keyCode);
		rightKeyDown = Keyboard
				.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindRight.keyCode);
		backKeyDown = Keyboard
				.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindBack.keyCode);
		if (forwardKeyDown || leftKeyDown || rightKeyDown || backKeyDown) {
			if (player.onGround) {
				player.motionX *= 1.66699999910593033D;
				player.motionZ *= 1.66699999910593033D;
			}
		}
	}
}