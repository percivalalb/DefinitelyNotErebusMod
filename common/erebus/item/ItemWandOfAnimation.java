package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityAnimatedBlock;

public class ItemWandOfAnimation extends Item {
	public static int blockloot = 1; // As a default - just to stop any null
										// stuff
	public static int blockmeta = 1;

	public ItemWandOfAnimation(int id) {
		super(id);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par1ItemStack.stackSize == 0)
			return false;
		else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
			return false;
		else {
			int i1 = par3World.getBlockId(par4, par5, par6);
			int j1 = par3World.getBlockMetadata(par4, par5, par6);
			ItemWandOfAnimation.blockloot = i1;
			ItemWandOfAnimation.blockmeta = j1;
			if (!par3World.isRemote && Block.blocksList[blockloot] != null)
				if (changeableBlocks(ItemWandOfAnimation.blockloot)) {
					par3World.setBlock(par4, par5, par6, 0);
					EntityAnimatedBlock entityAnimatedBlock = new EntityAnimatedBlock(par3World);
					entityAnimatedBlock.setLocationAndAngles((double) par4 + 0.5F, par5, (double) par6 + 0.5F, 0.0F, 0.0F);
					par3World.spawnEntityInWorld(entityAnimatedBlock);
					return true;
				}
		}
		return par1ItemStack != null;
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon() {
		return itemIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon("engineofillapa:WandOfAnimation");
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	public boolean changeableBlocks(int par1) {
		if (par1 == Block.stone.blockID || par1 == Block.grass.blockID || par1 == Block.dirt.blockID || par1 == Block.cobblestone.blockID || par1 == Block.planks.blockID || par1 == Block.sand.blockID || par1 == Block.gravel.blockID || par1 == Block.oreGold.blockID || par1 == Block.oreIron.blockID ||
		par1 == Block.oreCoal.blockID || par1 == Block.wood.blockID || par1 == Block.sponge.blockID || par1 == Block.glass.blockID || par1 == Block.oreLapis.blockID || par1 == Block.blockLapis.blockID || par1 == Block.sandStone.blockID || par1 == Block.railPowered.blockID ||
		par1 == Block.railDetector.blockID || par1 == Block.cloth.blockID || par1 == Block.blockGold.blockID || par1 == Block.blockIron.blockID || par1 == Block.brick.blockID || par1 == Block.tnt.blockID || par1 == Block.bookShelf.blockID || par1 == Block.cobblestoneMossy.blockID ||
		par1 == Block.obsidian.blockID || par1 == Block.oreDiamond.blockID || par1 == Block.blockDiamond.blockID || par1 == Block.ladder.blockID || par1 == Block.rail.blockID || par1 == Block.oreRedstone.blockID || par1 == Block.snow.blockID || par1 == Block.ice.blockID ||
		par1 == Block.blockSnow.blockID || par1 == Block.cactus.blockID || par1 == Block.blockClay.blockID || par1 == Block.reed.blockID || par1 == Block.pumpkin.blockID || par1 == Block.netherrack.blockID || par1 == Block.slowSand.blockID || par1 == Block.glowStone.blockID ||
		par1 == Block.pumpkinLantern.blockID || par1 == Block.stoneBrick.blockID || par1 == Block.fenceIron.blockID || par1 == Block.melon.blockID || par1 == Block.mycelium.blockID || par1 == Block.netherBrick.blockID || par1 == Block.whiteStone.blockID || par1 == Block.redstoneLampIdle.blockID ||
		par1 == Block.redstoneLampActive.blockID || par1 == Block.oreEmerald.blockID || par1 == Block.blockEmerald.blockID || par1 == Block.blockRedstone.blockID || par1 == Block.oreNetherQuartz.blockID || par1 == Block.blockNetherQuartz.blockID || par1 == Block.railActivator.blockID)
			return true;
		else
			return false;
	}
}
