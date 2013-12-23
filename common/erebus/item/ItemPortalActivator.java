package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class ItemPortalActivator extends Item {

	public ItemPortalActivator(int i) {
		super(i);
		maxStackSize = 1;
		setMaxDamage(64);
		setFull3D();
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 == 0)
			--par5;

		if (par7 == 1)
			++par5;

		if (par7 == 2)
			--par6;

		if (par7 == 3)
			++par6;

		if (par7 == 4)
			--par4;

		if (par7 == 5)
			++par4;

		if (!player.canPlayerEdit(par4, par5, par6, par7, is))
			return false;
		else {
			int var11 = world.getBlockId(par4, par5, par6);

			if (var11 == 0) {
				world.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				onBlockAdded(world, par4, par5, par6);
			}

			is.damageItem(1, player);
			return true;
		}
	}

	public void onBlockAdded(World world, int par2, int par3, int par4) {
		ModBlocks.portalErebus.tryToCreatePortal(world, par2, par3, par4);
	}
}
