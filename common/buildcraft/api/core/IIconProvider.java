package buildcraft.api.core;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

/**
 * @author ProPercivalalb This class is only made to stop errors occurring in
 *         the eclipse workspace This file should not be included in the Erebus
 *         mod download
 */
public interface IIconProvider {

	Icon getIcon(int pipeIconIndex);

	void registerIcons(IconRegister iconRegister);

}
