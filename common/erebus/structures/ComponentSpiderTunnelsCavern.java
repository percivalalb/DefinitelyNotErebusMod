package erebus.structures;

import java.util.List;
import java.util.Random;

import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityTarantula;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.EnumDoor;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentSpiderTunnelsCavern extends ComponentSpiderTunnels
{
	private boolean hasSpawner;
	private boolean hasChest;

	public static final WeightedRandomChestContent[] spiderTunnelsBossChestContents = new WeightedRandomChestContent[] {
		new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 5), 
		new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 2, 8, 8), 
		new WeightedRandomChestContent(ModItems.erebusMaterials.itemID, 0, 1, 5, 5), 
		new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 3, 15), 
		new WeightedRandomChestContent(Item.spiderEye.itemID, 0, 1, 3, 3), 
		new WeightedRandomChestContent(Item.glowstone.itemID, 0, 2, 8, 8), 
		new WeightedRandomChestContent(Item.flint.itemID, 0, 1, 4, 5),
	
		new WeightedRandomChestContent(ModItems.maxSpeedBow.itemID, 0, 1, 1, 1)}; 
	
    
	public ComponentSpiderTunnelsCavern(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
    {
        super(par1);
        this.coordBaseMode = par4;
        this.boundingBox = par3StructureBoundingBox;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        if (par1StructureComponent != null)
        {
            ((ComponentSpiderTunnelsStairs2)par1StructureComponent).SpiderTunnelsPortalRoom = this;
        }
    }

    public static ComponentSpiderTunnelsCavern findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -1, 0, 11, 8, 16, par5);
        return canSpiderTunnelsGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentSpiderTunnelsCavern(par6, par1Random, var7, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 10, 7, 15, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
        this.placeDoor(par1World, par2Random, par3StructureBoundingBox, EnumDoor.GRATES, 4, 1, 0);
        byte var4 = 6;
        int var5;

        this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 1, 8, 2, 6, 8, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
        this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 8, 1, 8, 9, 6, 8, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
        this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 3, 6, 8, 7, 6, 8, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
        
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 8, 7, 5, 8, Block.web.blockID, 0, false);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 1, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 6, 1, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 2, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 2, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 4, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 4, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 5, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 6, 5, 8, par3StructureBoundingBox);
        
        //Small Eggs
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 8, 1, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 1, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 8, 2, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 3, 1, par3StructureBoundingBox);
        
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 2, 6, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 1, 6, 6, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 2, 5, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 1, 5, 6, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 1, 4, 7, par3StructureBoundingBox);
        
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 2, 1, 14, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 1, 1, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 2, 2, 14, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 1, 2, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 1, 3, 14, par3StructureBoundingBox);
        
        //Large Egg
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 6, 14, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 5, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 8, 5, 14, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 2, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 8, 2, 14, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 1, 14, par3StructureBoundingBox);
        
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 4, 12, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 8, 4, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 7, 4, 14, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 9, 3, 12, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 8, 3, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, ModBlocks.blockSpiderSilk.blockID, 0, 7, 3, 14, par3StructureBoundingBox);
                
        byte var14 = 2;
        byte var7 = 0;
        byte var8 = 3;
        byte var9 = 1;

        switch (this.coordBaseMode)
        {
            case 0:
                var14 = 0;
                var7 = 2;
                break;
            case 1:
                var14 = 1;
                var7 = 3;
                var8 = 0;
                var9 = 2;
            case 2:
            default:
                break;
            case 3:
                var14 = 3;
                var7 = 1;
                var8 = 0;
                var9 = 2;
        }

        //Egg 1
        {
            int var13 = this.getYWithOffset(1);
            int var10 = this.getXWithOffset(1, 14);
            int var11 = this.getZWithOffset(1, 14);

            if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
            {
                this.hasSpawner = true;
                if (par2Random.nextInt(5) == 0)
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.caveSpiderSpawner.blockID, 0, 0);
                }
                else
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.spiderSpawner.blockID, 0, 0);
                }
            }
        }

        //Egg 2
        {
            int var13 = this.getYWithOffset(1);
            int var10 = this.getXWithOffset(9, 1);
            int var11 = this.getZWithOffset(9, 1);

            if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
            {
                this.hasSpawner = true;
                if (par2Random.nextInt(5) == 0)
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.caveSpiderSpawner.blockID, 0, 0);
                }
                else
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.spiderSpawner.blockID, 0, 0);
                }
            }
        }

      //Egg 3
        {
            int var13 = this.getYWithOffset(6);
            int var10 = this.getXWithOffset(1, 7);
            int var11 = this.getZWithOffset(1, 7);

            if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
            {
                this.hasSpawner = true;
                if (par2Random.nextInt(5) == 0)
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.caveSpiderSpawner.blockID, 0, 0);
                }
                else
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.spiderSpawner.blockID, 0, 0);
                }
            }
        }

        //Large Egg
        {
            int var13 = this.getYWithOffset(2);
            int var10 = this.getXWithOffset(9, 14);
            int var11 = this.getZWithOffset(9, 14);

            if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
            {
                this.hasSpawner = true;
                if (par2Random.nextInt(5) == 0)
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.caveSpiderSpawner.blockID, 0, 0);
                }
                else
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.spiderSpawner.blockID, 0, 0);
                }
            }
        }
        {
            int var13 = this.getYWithOffset(5);
            int var10 = this.getXWithOffset(9, 14);
            int var11 = this.getZWithOffset(9, 14);

            if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
            {
                this.hasSpawner = true;
                if (par2Random.nextInt(5) == 0)
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.caveSpiderSpawner.blockID, 0, 0);
                }
                else
                {
                	par1World.setBlock(var10, var13, var11, ModBlocks.spiderSpawner.blockID, 0, 0);
                }
            }
        }
        
        //Chest
        {
            int var13 = this.getYWithOffset(1);
            int var10 = this.getXWithOffset(5, 14);
            int var11 = this.getZWithOffset(5, 14);

            if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
            {
                this.hasChest = true;
                par1World.setBlock(var10, var13, var11, Block.chest.blockID, 0, 0);
                TileEntityChest var12 = (TileEntityChest)par1World.getBlockTileEntity(var10, var13, var11);

                if (var12 != null)
                {
                	WeightedRandomChestContent.generateChestContents(par2Random, spiderTunnelsBossChestContents,  var12, spiderTunnelsBossChestContents.length);
                }
            }
        }
        
        int varA = this.getXWithOffset(5, 10);
        int varB = this.getYWithOffset(2);
        int varC = this.getZWithOffset(5, 10);

        if (par3StructureBoundingBox.isVecInside(varA, varB, varC))
        {
            EntityTarantula var11 = new EntityTarantula(par1World);
            var11.setLocationAndAngles((double)varA + 0.5D, (double)varB, (double)varC + 0.5D, 0.0F, 0.0F);
            par1World.spawnEntityInWorld(var11);
        }

        return true;
    }
}
