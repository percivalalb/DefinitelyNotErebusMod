package erebus.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import erebus.TeleportErebusClient;
import erebus.ErebusMod;
import erebus.api.Properties;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class BlockPortalErebus extends BlockBreakable
{

 public BlockPortalErebus(int i, int j)
 {
     super(i, Properties.TEX_PACkAGE + "portalErebus", Material.portal, false);
     this.setCreativeTab(CreativeTabs.tabBlock);
 }

 public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
 {
	 Side side = FMLCommonHandler.instance().getEffectiveSide();
     if(side == Side.SERVER)
     {
     	if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) 
     	{
			    EntityPlayerMP player = (EntityPlayerMP)entity;
			    ErebusMod.instance.packeterebushandler.getPlayer(player.username).setInPortal();	        	 
     	}
     }
  	 else if (side == Side.CLIENT && entity instanceof EntityPlayer)
  	 {
  		TeleportErebusClient.setInPortal();
  		((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 80, 0));     	
  	 }
 }
 
 public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
 {
     return null;
 }

 public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
 {
     if(iblockaccess.getBlockId(i - 1, j, k) != blockID && iblockaccess.getBlockId(i + 1, j, k) != blockID)
     {
         float f = 0.125F;
         float f2 = 0.5F;
         setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
     } else
     {
         float f1 = 0.5F;
         float f3 = 0.125F;
         setBlockBounds(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
     }
 }

 @Override
 public boolean isOpaqueCube()
 {
     return false;
 }

 @Override
 public boolean renderAsNormalBlock()
 {
     return false;
 }

 @Override
 public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
 {
     byte b0 = 0;
     byte b1 = 1;

     if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
     {
         b0 = 1;
         b1 = 0;
     }

     int i1;

     for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == this.blockID; --i1)
     {
         ;
     }

     if (par1World.getBlockId(par2, i1 - 1, par4) != Block.stoneBrick.blockID)
     {
         par1World.setBlockToAir(par2, par3, par4);
     }
     else
     {
         int j1;

         for (j1 = 1; j1 < 4 && par1World.getBlockId(par2, i1 + j1, par4) == this.blockID; ++j1)
         {
             ;
         }

         if (j1 == 3 && par1World.getBlockId(par2, i1 + j1, par4) == Block.stoneBrick.blockID)
         {
             boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
             boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

             if (flag && flag1)
             {
                 par1World.setBlockToAir(par2, par3, par4);
             }
             else
             {
                 if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != Block.stoneBrick.blockID || par1World.getBlockId(par2 - b0, par3, par4 - b1) != this.blockID) && (par1World.getBlockId(par2 - b0, par3, par4 - b1) != Block.stoneBrick.blockID || par1World.getBlockId(par2 + b0, par3, par4 + b1) != this.blockID))
                 {
                     par1World.setBlockToAir(par2, par3, par4);
                 }
             }
         }
         else
         {
             par1World.setBlockToAir(par2, par3, par4);
         }
     }
 }
 
 public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
 {
     byte b0 = 0;
     byte b1 = 0;

     if (par1World.getBlockId(par2 - 1, par3, par4) == Block.stoneBrick.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.stoneBrick.blockID)
     {
         b0 = 1;
     }

     if (par1World.getBlockId(par2, par3, par4 - 1) == Block.stoneBrick.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.stoneBrick.blockID)
     {
         b1 = 1;
     }

     if (b0 == b1)
     {
         return false;
     }
     else
     {
         if (par1World.getBlockId(par2 - b0, par3, par4 - b1) == 0)
         {
             par2 -= b0;
             par4 -= b1;
         }

         int l;
         int i1;

         for (l = -1; l <= 2; ++l)
         {
             for (i1 = -1; i1 <= 3; ++i1)
             {
                 boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                 if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
                 {
                     int j1 = par1World.getBlockId(par2 + b0 * l, par3 + i1, par4 + b1 * l);

                     if (flag)
                     {
                         if (j1 != Block.stoneBrick.blockID)
                         {
                             return false;
                         }
                     }
                     else if (j1 != 0 && j1 != Block.fire.blockID)
                     {
                         return false;
                     }
                 }
             }
         }

         for (l = 0; l < 2; ++l)
         {
             for (i1 = 0; i1 < 3; ++i1)
             {
                 par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, ErebusMod.portalErebus.blockID, 0, 2);
             }
         }

         return true;
     }
 }

 @SideOnly(Side.CLIENT)
 public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
 {
     if(iblockaccess.getBlockId(i, j, k) == blockID)
     {
         return false;
     } else
     {
         boolean flag = iblockaccess.getBlockId(i - 1, j, k) == blockID && iblockaccess.getBlockId(i - 2, j, k) != blockID;
         boolean flag1 = iblockaccess.getBlockId(i + 1, j, k) == blockID && iblockaccess.getBlockId(i + 2, j, k) != blockID;
         boolean flag2 = iblockaccess.getBlockId(i, j, k - 1) == blockID && iblockaccess.getBlockId(i, j, k - 2) != blockID;
         boolean flag3 = iblockaccess.getBlockId(i, j, k + 1) == blockID && iblockaccess.getBlockId(i, j, k + 2) != blockID;
         boolean flag4 = flag || flag1;
         boolean flag5 = flag2 || flag3;
         return !flag4 || l != 4 ? !flag4 || l != 5 ? !flag5 || l != 2 ? flag5 && l == 3 : true : true : true;
     }
 }

 @Override
 public int quantityDropped(Random random)
 {
     return 0;
 }
 
 @SideOnly(Side.CLIENT)
 public int getRenderBlockPass()
 {
     return 1;
 }
 
 @SideOnly(Side.CLIENT)
 public void randomDisplayTick(World world, int i, int j, int k, Random random)
 {
     if(random.nextInt(100) == 0)
     {
     //TODO    world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "portalambiance", 0.5F, random.nextFloat() * 0.4F + 0.8F);
     }
     for(int l = 0; l < 4; l++)
     {
         double d = (float)i + random.nextFloat();
         double d1 = (float)j + random.nextFloat();
         double d2 = (float)k + random.nextFloat();
         double d3 = 0.0D;
         double d4 = 0.0D;
         double d5 = 0.0D;
         int i1 = random.nextInt(2) * 2 - 1;
         d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
         d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
         d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;
         if(world.getBlockId(i - 1, j, k) != blockID && world.getBlockId(i + 1, j, k) != blockID)
         {
             d = (double)i + 0.5D + 0.25D * (double)i1;
             d3 = random.nextFloat() * 2.0F * (float)i1;
         } else
         {
             d2 = (double)k + 0.5D + 0.25D * (double)i1;
             d5 = random.nextFloat() * 2.0F * (float)i1;
         }
         world.spawnParticle("smoke", d, d1, d2, d3 / 4D, d4 / 4D, d5 / 4D);
     }
 }

}
