package erebus.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.network.PacketHandler;
import erebus.network.packet.PacketSound;

public class ItemErebusMaterial extends Item {

	public static final String[] iconPaths = new String[] {
		"plateExo", "jade", "shardBone", "bamboo", "compoundEyes", "compoundLens", "flyWing",
		"itemPetrifiedWood", "biovelocity", "elasticFibre", "waspSting", "bambooShoot",
		"redGem", "bioluminescence", "supernaturalvelocity", "altarFragment"
	};
	
	public static final short dataExoPlate = 0, dataJade = 1, dataBoneShard = 2, dataBamboo = 3, dataCompoundEyes = 4, dataCompoundLens = 5,
							  dataFlyWing = 6, dataPetrifiedWood = 7, dataBioVelocity = 8, dataElasticFibre = 9, dataWaspSting = 10, dataBambooShoot = 11,
							  dataRedGem = 12, dataBioluminescence = 13, dataSupernaturalVelocity = 14, altarFragment = 15;

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusMaterial(int id) {
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if (!world.isRemote){
			int damage=stack.getItemDamage();
			
			if (damage==dataBioVelocity||damage==dataSupernaturalVelocity){
				PotionEffect currentSpeed=player.getActivePotionEffect(Potion.moveSpeed);
				
				if (currentSpeed==null || (damage==dataBioVelocity && currentSpeed.getAmplifier()<1) || (damage==dataSupernaturalVelocity && currentSpeed.getAmplifier() < 3)){
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, damage==dataBioVelocity?280:210, damage==dataBioVelocity?1:3, true));
					PacketDispatcher.sendPacketToAllAround(player.posX,player.posY,player.posZ,32D,player.dimension,
						PacketHandler.buildPacket(3,PacketSound.SOUND_VELOCITY_USE,player.posX,player.posY,player.posZ,1.2F,1F)
					);
				}
				else return stack;
			}
			else return stack;
			
			if (!player.capabilities.isCreativeMode)--stack.stackSize;
		}
		
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
	}

	@Override
	@SideOnly(Side.CLIENT)
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
