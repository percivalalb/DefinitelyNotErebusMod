package erebus.utils;

import java.awt.Color;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Erebus
 * 
 * @author ganymedes01
 * 
 */

public class Utils {

	public static final void dropStack(World world, int x, int y, int z, ItemStack stack) {
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			float f = 0.7F;
			double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, stack);
			entityitem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityitem);
		}
	}

	public static final int getColour(int R, int G, int B) {
		return new Color(R, G, B).getRGB() & 0x00ffffff;
	}

	public static final String CHAT_COLOUR_BLACK = "¤0";
	public static final String CHAT_COLOUR_DARKBLUE = "¤1";
	public static final String CHAT_COLOUR_DARKGREEN = "¤2";
	public static final String CHAT_COLOUR_DARKAQUA = "¤3";
	public static final String CHAT_COLOUR_DARKRED = "¤4";
	public static final String CHAT_COLOUR_DARKPURPLE = "¤5";
	public static final String CHAT_COLOUR_GOLD = "¤6";
	public static final String CHAT_COLOUR_GREY = "¤7";
	public static final String CHAT_COLOUR_DARKGREY = "¤8";
	public static final String CHAT_COLOUR_BLUE = "¤9";
	public static final String CHAT_COLOUR_GREEN = "¤a";
	public static final String CHAT_COLOUR_AQUA = "¤b";
	public static final String CHAT_COLOUR_RED = "¤c";
	public static final String CHAT_COLOUR_PUEPLE = "¤d";
	public static final String CHAT_COLOUR_YELLOW = "¤e";
	public static final String CHAT_COLOUR_WHITE = "¤f";
}