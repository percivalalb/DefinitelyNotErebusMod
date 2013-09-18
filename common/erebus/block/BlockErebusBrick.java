package erebus.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockErebusBrick extends Block
{
    public BlockErebusBrick(int par1)
    {
        super(par1, Material.rock);
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        
    }
}
