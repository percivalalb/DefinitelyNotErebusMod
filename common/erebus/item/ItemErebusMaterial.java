package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

/**
 * @author ProPercivalalb
 */
public class ItemErebusMaterial extends Item {

	/** The icon path, not including "erebus:" as it is joined when registering icon **/
	public static final String[] iconPaths = new String[] {"plateExo", "jade", "shardBone", "bamboo", "compoundEyes", "compoundLens", "flyWing"};
	public static final Icon[] icons = new Icon[iconPaths.length];
	
	public ItemErebusMaterial(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
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
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
    }
    
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int i = par1ItemStack.getItemDamage();
        return super.getUnlocalizedName() + "." + i;
    }
}
