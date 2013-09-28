package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import erebus.ErebusMod;
import erebus.ModItems;

public class NightVisionHandler {

	@ForgeSubscribe
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entityLiving.worldObj.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.compoundGoggles && !ErebusMod.proxy.getClientNightVision())
				ErebusMod.proxy.setClientNightVision(true);
			else if (player.inventory.armorInventory[3] == null || player.inventory.armorInventory[3].getItem() != ModItems.compoundGoggles && ErebusMod.proxy.getClientNightVision())
				ErebusMod.proxy.setClientNightVision(false);
		}
	}
}
