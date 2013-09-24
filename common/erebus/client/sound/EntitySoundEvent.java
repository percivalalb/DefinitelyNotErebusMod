package erebus.client.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EntitySoundEvent {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		try {
			// Squish sound
			event.manager.addSound("erebus:squish.ogg");

			// Beetle Larva
			event.manager.addSound("erebus:beetlelarvasound.ogg");
			event.manager.addSound("erebus:beetlelarvahurt.ogg");
			event.manager.addSound("erebus:beetlelarvasplat.ogg");
			event.manager.addSound("erebus:beetlelarvamunch.ogg");

			// Black Widow
			event.manager.addSound("erebus:blackwidowsound.ogg");
			event.manager.addSound("erebus:blackwidowhurt.ogg");
			event.manager.addSound("erebus:webslingthrow.ogg");
			event.manager.addSound("erebus:webslingsplat.ogg");

			// Centipede
			event.manager.addSound("erebus:CentipedeSound.ogg");
			event.manager.addSound("erebus:CentipedeHurt.ogg");
			event.manager.addSound("erebus:TunnelingSound.ogg");
			event.manager.addSound("erebus:CentipedeWalk.ogg");

			// Fly
			event.manager.addSound("erebus:FlySound.ogg");
			event.manager.addSound("erebus:FlyHurt.ogg");

			// Mosquito
			event.manager.addSound("erebus:mosquito_sucking.ogg");
			event.manager.addSound("erebus:mosquito_flying.ogg");
			event.manager.addSound("erebus:mosquito_hit.ogg");
			event.manager.addSound("erebus:mosquito_death.ogg");

			// Wasp
			event.manager.addSound("erebus:WaspSound.ogg");
			event.manager.addSound("erebus:WaspHurt.ogg");

			// Grasshopper & Locust
			event.manager.addSound("erebus:grasshoppersound.ogg");
			event.manager.addSound("erebus:grasshopperhurt.ogg");
			event.manager.addSound("erebus:locustsound.ogg");
			event.manager.addSound("erebus:locusthurt.ogg");
			event.manager.addSound("erebus:locustspawn.ogg");

			// Insect Repellent
			event.manager.addSound("erebus:SprayCanSound.ogg");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erebus had a problem loading its sounds. Please report it to the authors.");
		}
	}
}
