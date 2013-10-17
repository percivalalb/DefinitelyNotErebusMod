package erebus.core.handler;
import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import erebus.item.ItemArmorGlider;


public class KeyBindingHandler extends KeyHandler{
	public static KeyBinding glide = new KeyBinding("Glide", Keyboard.KEY_LMENU);
	public static KeyBinding[] arrayOfKeys = new KeyBinding[] { glide };
	public static boolean[] areRepeating = new boolean[] {false};
	public KeyBindingHandler() {
		super(arrayOfKeys, areRepeating);
	}
	@Override
	public String getLabel() {
		return "Erebus KeyBindings";
	}
	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (tickEnd)
			if(kb.keyCode == glide.keyCode)
				ItemArmorGlider.setGliding(true);
	}
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (tickEnd)
			ItemArmorGlider.setGliding(false);
	}
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}


