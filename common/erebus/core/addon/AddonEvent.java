package erebus.core.addon;

import net.minecraftforge.event.Event;

/**
 * @author ProPercivalalb
 * These events are fired during FML's @PostInit to manage mod addons
 */
public class AddonEvent extends Event {

	//Fired in order of appearance
	public static class Pre extends AddonEvent {}
	
	public static class Init extends AddonEvent {}
	
	public static class Post extends AddonEvent {}

}
