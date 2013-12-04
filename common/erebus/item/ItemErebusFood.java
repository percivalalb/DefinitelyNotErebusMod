package erebus.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemErebusFood extends ItemFood {

	public static final String[] iconPaths = new String[] {
		"larvaRaw", "beetleLarvaCooked", "grasshopperLegRaw", "grasshopperLegCooked", "legTarantula", "legTarantulaCooked",
		"bambooSoup", "melonade", "melonadeSparkly", "larvaeOnStick"
	};

	public static final short
	dataLarvaRaw = 0, dataLarvaCooked = 1, dataGrasshopperLegRaw = 2, dataGrasshopperLegCooked = 3, dataLegTarantula = 4, dataLegTarantulaCooked = 5,
	dataBambooSoup = 6, dataMelonade = 7, dataMelonadeSparkly = 8, dataLarvaeOnStick = 9;

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusFood(int id) {
		super(id, 3, 0.5F, false);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	public int getHealAmount(ItemStack stack, World world, EntityPlayer player) {
		switch (stack.getItemDamage()) {
			case dataLarvaRaw:
				return 2;
			case dataLarvaCooked:
				return 6;
			case dataGrasshopperLegRaw:
				return 2;
			case dataGrasshopperLegCooked:
				return 8;
			case dataLegTarantula:
				return 2;
			case dataLegTarantulaCooked:
				return 6;
			case dataBambooSoup:
				return 4;
			case dataMelonade:
				return 3;
			case dataMelonadeSparkly:
				return 6;
			case dataLarvaeOnStick:
				return 18;
			default:
				return 0;
		}
	}

	public float getSaturationModifier(ItemStack stack, World world, EntityPlayer player) {
		switch (stack.getItemDamage()) {
			case dataLarvaRaw:
				return 0.65F;
			case dataLarvaCooked:
				return 0.85F;
			case dataGrasshopperLegRaw:
				return 0.8F;
			case dataGrasshopperLegCooked:
				return 0.9F;
			case dataLegTarantula:
				return 0.6F;
			case dataLegTarantulaCooked:
				return 1.0F;
			case dataBambooSoup:
				return 1.0F;
			case dataMelonade:
				return 0.75F;
			case dataMelonadeSparkly:
				return 0.85F;
			case dataLarvaeOnStick:
				return 2.0F;
			default:
				return 0.0F;
		}
	}

	public PotionEffect getPotionEffect(ItemStack stack, World world, EntityPlayer player) {
		switch (stack.getItemDamage()) {
			case dataLarvaRaw:
				return world.rand.nextFloat() > 0.75 ? new PotionEffect(Potion.confusion.id, 30 * 20, 0) : null;
			case dataMelonadeSparkly:
				return new PotionEffect(Potion.regeneration.id, 110, 0);
			default:
				return null;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		switch (stack.getItemDamage()) {
			case dataMelonade:
			case dataMelonadeSparkly:
				return EnumAction.drink;
			default:
				return EnumAction.eat;
		}
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		--stack.stackSize;
		player.getFoodStats().addStats(getHealAmount(stack, world, player), getSaturationModifier(stack, world, player));
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		onFoodEaten(stack, world, player);

		int damage=stack.getItemDamage();
		Item item=null;
		
		if (damage == dataBambooSoup)
			item=Item.bowlEmpty;
		else if (damage == dataMelonade || damage == dataMelonadeSparkly)
			item=Item.glassBottle;
		else return stack;
		
		if (stack.stackSize != 0)
			player.inventory.addItemStackToInventory(new ItemStack(item));
		
		return stack.stackSize == 0 ? new ItemStack(item) : stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		PotionEffect effect = this.getPotionEffect(stack, world, player);
		if (!world.isRemote && effect != null)
			player.addPotionEffect(effect);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
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
		for (int a = 0; a < iconPaths.length; a++)
			par3List.add(new ItemStack(par1, 1, a));
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = par1ItemStack.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}