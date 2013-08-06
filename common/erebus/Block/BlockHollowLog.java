package erebus.Block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHollowLog extends BlockContainer// it has to extend BlockContainer or it wont read the tileentity file.
{
    public BlockHollowLog(int par1, Class class1) {
        super(par1, Material.wood);
    }

    /* Not needed : Invisible texture
    //Top and Side Textures
    private Icon tree_top;
    private Icon tree_side;
    String treeType = "acacia";
    
    public Icon getIcon(int par1, int par2)
    {
        int k = par2 & 12;
        int l = par2 & 3;
        return k == 4 && (par1 == 1 || par1 == 0) ? this.tree_top : (k == 0 && (par1 == 2 || par1 == 3) ? this.tree_top : (k == 4 && (par1 == 2 || par1 == 3) ? this.tree_top : this.tree_side));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.tree_top = par1IconRegister.registerIcon("log_" + treeType + "_top");
        this.tree_side = par1IconRegister.registerIcon("log_" + treeType);
    }
     */
    
    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    
    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return -1;
    }
 
    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        float f = 0.0625F; //1 pixel        
    	//bottom
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        
    	//top
        this.setBlockBounds(0.0F, 1.0F - f, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        
    	//east
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        
    	//south
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F + f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    @Override
    public int getRenderBlockPass() {
        return 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
    	return Item.stick.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1 + par1Random.nextInt(4);
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }
    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        TileEntityHollowLog tileentityhollowlog = new TileEntityHollowLog();
        return tileentityhollowlog;
    }
}
