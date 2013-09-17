package erebus.core.addon.buildcraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import buildcraft.api.core.IIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PipeIconProvider implements IIconProvider {

	public enum TYPE {

		PipeItemPetrifiedWoodStandard("pipeItemsPetrified"),
		PipeAllPetrifiedWoodSolid("pipeAllPetrified_solid");
		
		private final String iconTag;
		private Icon icon;

		private TYPE(String iconTag){
			this.iconTag = iconTag;
		}

		private void registerIcon(IconRegister iconRegister) {
			icon = iconRegister.registerIcon("erebus:buildcraft/" + iconTag);
		}

		public Icon getIcon() {
			return icon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int pipeIconIndex) {
		if (pipeIconIndex == -1)
			return null;
		return TYPE.values()[pipeIconIndex].icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (TYPE type : TYPE.values()) {
			type.registerIcon(iconRegister);
		}
	}
}