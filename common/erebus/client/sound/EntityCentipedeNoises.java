package erebus.client.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EntityCentipedeNoises {
    
	@ForgeSubscribe
    public void onSound(SoundLoadEvent event) {
        try {
        	//You will need to add a line like this for every single sound you add. 
        	//The "yourmod:yoursound.ogg" adds the sound to the sound library. 
            event.manager.soundPoolSounds.addSound("erebus:CentipedeSound.ogg");       
            event.manager.soundPoolSounds.addSound("erebus:CentipedeHurt.ogg");  
            event.manager.soundPoolSounds.addSound("erebus:squish.ogg");  
            event.manager.soundPoolSounds.addSound("erebus:TunnelingSound.ogg");  
            event.manager.soundPoolSounds.addSound("erebus:CentipedeWalk.ogg"); 
        } 
        catch (Exception e) {
        	//This is just an error message you can add. 
            System.err.println("There was a problem loading sounds. please report to the author");
        }
    }
}
