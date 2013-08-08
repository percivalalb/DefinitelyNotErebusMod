package erebus.block;

import erebus.core.proxy.CommonProxy;
import erebus.api.Properties;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class BlockCaveSpiderSpawner extends BlockContainer
{
    public BlockCaveSpiderSpawner(int par1, int par2)
    {
        super(par1, Material.web);
    }

    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityCaveSpiderSpawner();
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        int var8 = 15 + par1World.rand.nextInt(15) + par1World.rand.nextInt(15);
        this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return 0;
    }
}
