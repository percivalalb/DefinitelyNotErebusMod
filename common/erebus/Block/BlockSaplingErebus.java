package erebus.block;

import erebus.core.proxy.CommonProxy;
import erebus.ErebusMod;
import erebus.world.gen.WorldGenErebusHugeTree;
import erebus.world.gen.WorldGenErebusTrees;
import erebus.world.gen.WorldGenEucalyptus;
import erebus.world.gen.WorldGenSavannaTree;
import erebus.api.Properties;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaplingErebus extends BlockSapling
{
    Icon mahoganySapling;
    Icon eucalyptusSapling;
    Icon acaciaSapling;
    
    public BlockSaplingErebus(int par1)
    {
        super(par1);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(ErebusMod.tabErebus);
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return (soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            //if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                int var6 = par1World.getBlockMetadata(par2, par3, par4);

                this.growTree(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    @Override
    public Icon getIcon(int par1, int par2)
    {
        return par2 == 0 ? mahoganySapling : (par2 == 1 ? eucalyptusSapling : (par2 == 2 ? acaciaSapling : /** ELSE **/ mahoganySapling));
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;

        int var6 = par1World.getBlockMetadata(par2, par3, par4);
        Object var7 = null;
        int var8 = 0;
        int var9 = 0;
        boolean var10 = false;

        if (var6 == 1)
        {
        	var7 = new WorldGenEucalyptus(ErebusMod.woodEucalyptus.blockID, 2, ErebusMod.leavesEucalyptus.blockID, 2, 8 + par5Random.nextInt(4), 5, 8, Block.grass.blockID);
        }
        else if (var6 == 2)
        {
        	int size = par1World.rand.nextInt(3);
        	var7 = new WorldGenSavannaTree(size);
        }
        if (var6 == 0)
        {
            for (var8 = 0; var8 >= -1; --var8)
            {
                for (var9 = 0; var9 >= -1; --var9)
                {
                    if (this.isSameSapling(par1World, par2 + var8, par3, par4 + var9, 0) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9, 0) && this.isSameSapling(par1World, par2 + var8, par3, par4 + var9 + 1, 0) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9 + 1, 0))
                    {
                        var7 = new WorldGenErebusHugeTree(true, 20 + par5Random.nextInt(5), 0, 0, true, ErebusMod.woodMahogany.blockID, ErebusMod.leavesMahogany.blockID);
                        var10 = true;
                        break;
                    }
                }

                if (var7 != null)
                {
                    break;
                }
            }

            
        }

        if (var7 == null)
        {
            var9 = 0;
            var8 = 0;
            var7 = new WorldGenErebusTrees(true, 5, 0, 0, false, ErebusMod.woodMahogany.blockID, ErebusMod.leavesMahogany.blockID, ErebusMod.thorns.blockID);
        }
        
        if (var10)
        {
            par1World.setBlock(par2 + var8, par3, par4 + var9, 0);
            par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, 0);
            par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, 0);
            par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, 0);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, 0);
        }

        if (!((WorldGenerator)var7).generate(par1World, par5Random, par2 + var8, par3, par4 + var9))
        {
            if (var10)
            {
                par1World.setBlock(par2 + var8, par3, par4 + var9, this.blockID, var6, 3);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, this.blockID, var6, 3);
                par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, this.blockID, var6, 3);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, this.blockID, var6, 3);
            }
            else
            {
                par1World.setBlock(par2, par3, par4, this.blockID, var6, 3);
            }
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        /*par3List.add(new ItemStack(par1, 1, 3));*/ 
    }
    
    public void registerIcons(IconRegister par1IconRegister) {
        this.mahoganySapling = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "sapling_mahogany");
        this.eucalyptusSapling = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "sapling_eucalyptus");
        this.acaciaSapling = par1IconRegister.registerIcon(Properties.TEX_PACkAGE + "sapling_acacia");
    }
}
