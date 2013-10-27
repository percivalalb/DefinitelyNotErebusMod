package erebus.client.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class LightningAltarNoises {

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		try {
			// event.manager.soundPoolSounds.addSound("BugZapper/Sounds/BugZapperSound.ogg");
			// event.manager.soundPoolSounds.addSound("BugZapper/Sounds/BugZapperHurt.ogg");
			// event.manager.soundPoolSounds.addSound("BugZapper/Sounds/Squish.ogg");
		} catch (Exception e) {
			// this is just an error messege you can add.
			System.err.println("There was a problem loading sounds. please report to the author");
		}
	}
}
