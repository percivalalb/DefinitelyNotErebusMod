package erebus.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
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
		setCreativeTab(ErebusMod.tabErebusGear);
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
		if (is != null && is.itemID == itemID)
			ErebusMod.renderer.hasNightVisionEffect = true;
		if (Minecraft.getMinecraft().entityRenderer != ErebusMod.renderer)
			Minecraft.getMinecraft().entityRenderer = ErebusMod.renderer;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		EntityClientPlayerMP minecraft = Minecraft.getMinecraft().thePlayer;
		EntityPlayer player = minecraft;
		ItemStack is = player.inventory.armorInventory[3];
		if (is == null || is.itemID != itemID)
			ErebusMod.renderer.hasNightVisionEffect = false;
		if (Minecraft.getMinecraft().entityRenderer != ErebusMod.renderer)
			Minecraft.getMinecraft().entityRenderer = ErebusMod.renderer;
	}
}