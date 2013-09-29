package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBambucket extends Item {

	public Icon bambucket;
	public Icon waterBambucket;

	public ItemBambucket(int par1) {
		super(par1);
		maxStackSize = 16;
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		boolean flag = par1ItemStack.getItemDamage() == 0;
		MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);

		if (movingobjectposition == null)
			return par1ItemStack;
		else {
			if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
					return par1ItemStack;

				if (par1ItemStack.getItemDamage() == 0) {
					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
						return par1ItemStack;

					if (par2World.getBlockMaterial(i, j, k) == Material.water && par2World.getBlockMetadata(i, j, k) == 0) {
						par2World.setBlockToAir(i, j, k);

						if (par3EntityPlayer.capabilities.isCreativeMode)
							return par1ItemStack;

						if (--par1ItemStack.stackSize <= 0)
							return new ItemStack(itemID, 1, 1);

						if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(itemID, 1, 1)))
							par3EntityPlayer.dropPlayerItem(new ItemStack(itemID, 1, 1));

						return par1ItemStack;
					}
				} else {
					if (par1ItemStack.getItemDamage() < 0)
						return new ItemStack(itemID, 1, 0);

					if (movingobjectposition.sideHit == 0)
						--j;

					if (movingobjectposition.sideHit == 1)
						++j;

					if (movingobjectposition.sideHit == 2)
						--k;

					if (movingobjectposition.sideHit == 3)
						++k;

					if (movingobjectposition.sideHit == 4)
						--i;

					if (movingobjectposition.sideHit == 5)
						++i;

					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
						return par1ItemStack;

					if (tryPlaceContainedLiquid(par2World, i, j, k, par1ItemStack) && !par3EntityPlayer.capabilities.isCreativeMode)
						if (--par1ItemStack.stackSize <= 0)
							return par1ItemStack;
				}
			}

			return par1ItemStack;
		}
	}

	public boolean tryPlaceContainedLiquid(World par1World, int par2, int par3, int par4, ItemStack item) {
		Material material = par1World.getBlockMaterial(par2, par3, par4);
		boolean flag = !material.isSolid();

		if (!par1World.isAirBlock(par2, par3, par4) && !flag)
			return false;
		else {
			if (par1World.provider.isHellWorld && item.getItemDamage() == 1) {
				par1World.playSoundEffect(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

				for (int l = 0; l < 8; ++l)
					par1World.spawnParticle("largesmoke", par2 + Math.random(), par3 + Math.random(), par4 + Math.random(), 0.0D, 0.0D, 0.0D);
			} else {
				if (!par1World.isRemote && flag && !material.isLiquid())
					par1World.destroyBlock(par2, par3, par4, true);

				par1World.setBlock(par2, par3, par4, Block.waterMoving.blockID, 0, 3);
			}

			return true;
		}
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		bambucket = iconRegister.registerIcon("erebus:bambucket");
		waterBambucket = iconRegister.registerIcon("erebus:bambucketWater");
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		switch (meta) {
			case 0:
				return bambucket;
			case 1:
				return waterBambucket;
			default:
				return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = par1ItemStack.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}
