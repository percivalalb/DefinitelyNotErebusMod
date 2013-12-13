package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class ItemBambucket extends Item {

	public Icon bambucket;
	public Icon waterBambucket;
	public Icon bambucketOfBeetleJuice;

	public ItemBambucket(int id) {
		super(id);
		maxStackSize = 16;
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (stack.getItemDamage() == 2) {
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
			return stack;
		}

		boolean flag = stack.getItemDamage() == 0;
		MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, flag);

		if (movingobjectposition == null)
			return stack;
		else if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
			int i = movingobjectposition.blockX;
			int j = movingobjectposition.blockY;
			int k = movingobjectposition.blockZ;

			if (!world.canMineBlock(player, i, j, k))
				return stack;

			if (stack.getItemDamage() == 0) {
				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
					return stack;

				if (world.getBlockMaterial(i, j, k) == Material.water && world.getBlockMetadata(i, j, k) == 0) {
					world.setBlockToAir(i, j, k);

					if (player.capabilities.isCreativeMode)
						return stack;

					if (--stack.stackSize <= 0)
						return new ItemStack(itemID, 1, 1);

					if (!player.inventory.addItemStackToInventory(new ItemStack(itemID, 1, 1)))
						player.dropPlayerItem(new ItemStack(itemID, 1, 1));

					return stack;
				}
			} else {
				if (stack.getItemDamage() < 0)
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

				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
					return stack;

				if (tryPlaceContainedLiquid(world, i, j, k, stack) && !player.capabilities.isCreativeMode)
					if (--stack.stackSize <= 0)
						return stack;
			}
		}
		return stack;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (stack.getItemDamage() == 2) {
			if (!player.capabilities.isCreativeMode)
				stack.stackSize--;

			if (!world.isRemote)
				player.curePotionEffects(new ItemStack(Item.bucketMilk));

			player.inventory.addItemStackToInventory(new ItemStack(ModItems.bamBucket));
			return stack;
		} else
			return super.onEaten(stack, world, player);
	}

	public boolean tryPlaceContainedLiquid(World par1World, int par2, int par3, int par4, ItemStack item) {
		Material material = par1World.getBlockMaterial(par2, par3, par4);
		boolean flag = !material.isSolid();
		if (!par1World.isRemote && item.getItemDamage() != 2)
			if (!par1World.isAirBlock(par2, par3, par4) && !flag)
				return false;
			else if (par1World.provider.isHellWorld && item.getItemDamage() == 1) {
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

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		int x;
		if (par1ItemStack.getItemDamage() == 2)
			x = 32;
		else
			x = super.getMaxItemUseDuration(par1ItemStack);
		return x;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		EnumAction x;
		if (par1ItemStack.getItemDamage() == 2)
			x= EnumAction.drink;
		else
			x= EnumAction.block;
		return x;
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		bambucket = iconRegister.registerIcon("erebus:bambucket");
		waterBambucket = iconRegister.registerIcon("erebus:bambucketWater");
		bambucketOfBeetleJuice = iconRegister.registerIcon("erebus:bambucketOfBeetleJuice");
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		switch (meta) {
			case 0:
				return bambucket;
			case 1:
				return waterBambucket;
			case 2:
				return bambucketOfBeetleJuice;
			default:
				return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = par1ItemStack.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}
