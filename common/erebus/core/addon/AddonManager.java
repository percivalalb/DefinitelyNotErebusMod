package erebus.core.addon;

import erebus.core.addon.buildcraft.BuildcraftAddon;
import erebus.core.addon.forgemultipart.ForgeMultipartAddon;
import net.minecraftforge.event.EventBus;

/**
 * @author ProPercivalalb
 */
public class AddonManager {

	private static final EventBus EVENT_BUS	= new EventBus();
	
	public static void registerAddons() {
		EVENT_BUS.register(new BuildcraftAddon());
		EVENT_BUS.register(new ForgeMultipartAddon());
	}
	
	public static void runRegisteredAddons() {
		EVENT_BUS.post(new AddonEvent.Pre());
		EVENT_BUS.post(new AddonEvent.Init());
		EVENT_BUS.post(new AddonEvent.Post());
	}
}
