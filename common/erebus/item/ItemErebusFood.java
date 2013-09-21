package erebus.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemErebusFood extends ItemFood {

	/**
	 * The icon path, not including "erebus:" as it is joined when registering
	 * icon
	 **/
	public static final String[] iconPaths = new String[] { "larvaRaw", "beetleLarvaCooked", "grasshopperLegRaw", "grasshopperLegCooked", "legTarantula", "legTarantulaCooked", "bambooSoup" };
	public static final short dataLarvaRaw = 0, dataLarvaCooked = 1, dataGrasshopperLegRaw = 2, dataGrasshopperLegCooked = 3, dataLegTarantula = 4, dataLegTarantulaCooked = 5, dataBambooSoup = 6;
	
	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusFood(int id) {
		super(id, 3, 0.5F, false);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	public int getHealAmount(ItemStack stack, World world, EntityPlayer player) {
		int meta = stack.getItemDamage();
		switch (meta) {
			case 0:
				return 2;
			case 1:
				return 6;
			case 2:
				return 2;
			case 3:
				return 8;
			case 4:
				return 2;
			case 5:
				return 6;
			case 6:
				return 4;
			default:
				return 0;
		}
	}

	public float getSaturationModifier(ItemStack stack, World world, EntityPlayer player) {
		int meta = stack.getItemDamage();
		switch (meta) {
			case 0:
				return 0.7F;
			case 1:
				return 0.9F;
			case 2:
				return 0.8F;
			case 3:
				return 0.9F;
			case 4:
				return 0.6F;
			case 5:
				return 1.0F;
			case 6:
				return 1.0F;
			default:
				return 0.0F;
		}
	}

	public PotionEffect getPotionEffect(ItemStack stack, World world, EntityPlayer player) {
		int meta = stack.getItemDamage();
		switch (meta) {
			case 0:
				if (world.rand.nextFloat() > 0.75F)
					return new PotionEffect(Potion.confusion.id, 30 * 20, 0);
			default:
				return null;
		}
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		--stack.stackSize;
		player.getFoodStats().addStats(getHealAmount(stack, world, player), getSaturationModifier(stack, world, player));
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(stack, world, player);
		return stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		PotionEffect effect = this.getPotionEffect(stack, world, player);
		if (!world.isRemote && effect != null) {
			player.addPotionEffect(effect);
		}
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
