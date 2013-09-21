package erebus.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAmber extends Block {
	@SideOnly(Side.CLIENT)
	private Icon blockAmber,glassAmber,brickAmber;
	@SideOnly(Side.CLIENT)
	private Icon[] connectedGlass;
	
	private final String[] connectedGlassStr = new String[]{
		"center","bottomleft","bottomright","topleft","topright","sidingleft","sidingright","sidingbottom","sidingtop",
		"sidestopbottom","sidesleftright","fullsideleft","fullsideright","fullsidebottom","fullsidetop"
	};

	public BlockAmber(int par1) {
		super(par1, Material.rock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
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
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side){
		int meta = world.getBlockMetadata(x,y,z);
		
		if (meta == 1){ // TODO work-in-progress
			boolean[] nearby;
			
			if (side == 0 || side == 1){ // bottom (y+), top (y-)
				nearby = new boolean[]{
					isGlass(world,x-1,y,z), isGlass(world,x+1,y,z), isGlass(world,x,y,z-1), isGlass(world,x,y,z+1)
				};
				
				if (!nearby[0] && !nearby[1] && !nearby[2] && !nearby[3])return glassAmber;
				else if (nearby[0] && nearby[1] && nearby[2] && nearby[3])return connectedGlass[0];
				else if (nearby[0] && !nearby[1] && nearby[2] && !nearby[3])return connectedGlass[2];
				else if (!nearby[0] && nearby[1] && !nearby[2] && nearby[3])return connectedGlass[3];
				else if (nearby[0] && !nearby[1] && !nearby[2] && nearby[3])return connectedGlass[4];
				else if (!nearby[0] && nearby[1] && nearby[2] && !nearby[3])return connectedGlass[5];
				else if (nearby[0] && nearby[1])return connectedGlass[9];
				else if (nearby[2] && nearby[3])return connectedGlass[10];
				else if (!nearby[0] && nearby[1] && !nearby[2] && !nearby[3])return connectedGlass[11];
				else if (nearby[0] && !nearby[1] && !nearby[2] && !nearby[3])return connectedGlass[12];
				else if (!nearby[0] && !nearby[1] && nearby[2] && !nearby[3])return connectedGlass[13];
				else if (!nearby[0] && !nearby[1] && !nearby[2] && nearby[3])return connectedGlass[14];
				
			}
			else if (side == 1){ // y-1
				//return brickAmber;
			}
			
		}
		
		return getIcon(side,meta);
	}
	
	private boolean isGlass(IBlockAccess world, int x, int y, int z){
		return world.getBlockId(x,y,z) == blockID && world.getBlockMetadata(x,y,z) == 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		switch (meta) {
			case 0:
				return this.blockAmber;
			case 1:
				return this.glassAmber;
			case 2:
				return this.brickAmber;
		}
		return null;
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.blockAmber = iconRegister.registerIcon("erebus:blockAmber");
		this.glassAmber = iconRegister.registerIcon("erebus:glassAmber");
		this.brickAmber = iconRegister.registerIcon("erebus:brickAmber");
		
		connectedGlass = new Icon[connectedGlassStr.length];
		for(int a=0; a<connectedGlassStr.length; a++){
			connectedGlass[a] = iconRegister.registerIcon("erebus:glassAmber_"+connectedGlassStr[a]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}
}
