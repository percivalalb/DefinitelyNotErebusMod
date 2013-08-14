package erebus.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

/**
 * @author ProPercivalalb
 */
public class ItemMaterial extends Item {

	/** The icon path, not including "erebus:" as it is joined when registering icon **/
	public static final String[] iconPaths = new String[] {"", };
	public static final Icon[] icons = new Icon[iconPaths.length];
	
	public ItemMaterial(int id) {
		super(id);
	}
	
	  
    @Override
    public void registerIcons(IconRegister iconRegister) {
    	int i = 0;
    	for(String path : iconPaths) {
    		icons[i++] = iconRegister.registerIcon("erebus:" + path);
    	}
    }
    
    @Override
    public Icon getIconFromDamage(int meta) {
		if(meta < 0 || meta >= icons.length) return null;
		return icons[meta];
    }

}
