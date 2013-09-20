package erebus.block;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class BlockTurnip extends BlockCrops {
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public BlockTurnip(int par1) {
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if (par2 < 7) {
			if (par2 == 6) {
				par2 = 5;
			}

			return this.iconArray[par2 >> 1];
		} else {
			return this.iconArray[3];
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune){
		ArrayList<ItemStack> ret=new ArrayList<ItemStack>();

		int dropAmount=1;
		
		if (metadata>=7)dropAmount+=world.rand.nextInt(3);
		else if (metadata>=4)dropAmount+=world.rand.nextInt(2);

		for(int n=0; n<dropAmount+fortune; n++){
			ret.add(new ItemStack(getSeedItem(),1,0));
		}

		return ret;
	}

	@Override
	protected int getSeedItem() {
		return ModItems.turnip.itemID;
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
		return (soil != null && soil == Block.grass || soil == Block.tilledField || soil == Block.dirt);
	}

	@Override
	protected int getCropItem() {
		return ModItems.turnip.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[4];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("erebus:turnips_" + i);
		}
	}
}
