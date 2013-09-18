package erebus.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockErebusGrass extends BlockTallGrass
{
	public BlockErebusGrass(int par1)
	{
		super(par1);
	}
	
	@Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return ((soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this)));
    }
	
	@Override
	/**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        
    }
	/*public Icon getIcon(int par1, int par2)
    {
        return this.blockIcon;
    }*/
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }

	@Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
    }
	
	@Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return 1;
    }
}
