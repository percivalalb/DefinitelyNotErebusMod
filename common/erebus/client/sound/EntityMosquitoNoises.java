package erebus.client.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EntityMosquitoNoises {

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		try {
			// You will need to add a line like this for every single sound you
			// add.
			// The "yourmod:yoursound.ogg" adds the sound to the sound library.
			event.manager.soundPoolSounds.addSound("erebus:mosquito_sucking.ogg");
			event.manager.soundPoolSounds.addSound("erebus:mosquito_flying.ogg");
			event.manager.soundPoolSounds.addSound("erebus:mosquito_hit.ogg");
			event.manager.soundPoolSounds.addSound("erebus:mosquito_death.ogg");
		} catch (Exception e) {
			// This is just an error message you can add.
			System.err.println("There was a problem loading sounds. please report to the author");
		}
	}
}
