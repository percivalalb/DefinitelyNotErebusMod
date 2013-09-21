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
 * @author ProPercivalalb, chylex
 */
public class ItemErebusMaterial extends Item {

	/**
	 * The icon path, not including "erebus:" as it is joined when registering
	 * icon
	 **/
	public static final String[] iconPaths = new String[] { "plateExo", "jade", "shardBone", "bamboo", "compoundEyes", "compoundLens", "flyWing", "itemPetrifiedWood", "biovelocity", "elasticFibre", "waspSting", "bambooShoot" };
	public static final short dataExoPlate = 0, dataJade = 1, dataBoneShard = 2, dataBamboo = 3, dataCompoundEyes = 4, dataCompoundLens = 5, dataFlyWing = 6, dataPetrifiedWood = 7, dataBioVelocity = 8, dataElasticFibre = 9, dataWaspString = 10, dataBambooShoot = 11;
	
	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusMaterial(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths) {
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int a=0; a<iconPaths.length; a++){
			par3List.add(new ItemStack(par1, 1, a));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = par1ItemStack.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}
