package erebus.block;

import erebus.api.Properties;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author ProPercivalalb
 */
public class BlockBambooCrate extends BlockContainer {

	public BlockBambooCrate(int par1) {
		super(par1, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBambooCrate();
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "bambooCrate");
    }

	@Override
    public int getRenderType() {
    	return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
