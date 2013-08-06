package erebus.Structures;

import java.util.List;
import java.util.Random;

import erebus.mod_Erebus;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.EnumDoor;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;

public class ComponentSpiderTunnelsEntrance extends ComponentSpiderTunnels
{
	public static final WeightedRandomChestContent[] spiderTunnelsChestContents = new WeightedRandomChestContent[] {
		new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 5), 
		new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 2, 8, 8), 
		new WeightedRandomChestContent(mod_Erebus.exoskeletonPlate.itemID, 0, 1, 5, 5), 
		new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 3, 15), 
		new WeightedRandomChestContent(Item.spiderEye.itemID, 0, 1, 3, 3), 
		new WeightedRandomChestContent(Item.glowstone.itemID, 0, 2, 8, 8), 
		new WeightedRandomChestContent(Item.flint.itemID, 0, 1, 4, 5)};
    
	private boolean hasMadeChest;
    private final boolean field_75024_a;
    private final EnumDoor doorType;

    public ComponentSpiderTunnelsEntrance(int par1, Random par2Random, int par3, int par4)
    {
        super(par1);
        this.field_75024_a = true;
        this.coordBaseMode = par2Random.nextInt(4);
        this.doorType = EnumDoor.OPENING;

        switch (this.coordBaseMode)
        {
            case 0:
            case 2:
                this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 5 - 1, 94, par4 + 5 - 1);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 5 - 1, 94, par4 + 5 - 1);
        }
    }

    public ComponentSpiderTunnelsEntrance(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
    {
        super(par1);
        this.field_75024_a = false;
        this.coordBaseMode = par4;
        this.doorType = this.getRandomDoor(par2Random);
        this.boundingBox = par3StructureBoundingBox;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        if (this.field_75024_a)
        {
            StructureSpiderTunnelsPieces.setComponentType(ComponentSpiderTunnelsCrossing.class);
        }

        this.getNextComponentNormal((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, 1, 1);
    }

    /**
     * performs some checks, then gives out a fresh Stairs component
     */
    public static ComponentSpiderTunnelsEntrance getSpiderTunnelsStairsComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -7, 0, 5, 11, 5, par5);
        return canSpiderTunnelsGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentSpiderTunnelsEntrance(par6, par1Random, var7, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (this.isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
        {
            return false;
        }
        else
        {
            /*this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 10, 4, true, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            this.placeDoor(par1World, par2Random, par3StructureBoundingBox, this.doorType, 1, 7, 0);
            this.placeDoor(par1World, par2Random, par3StructureBoundingBox, EnumDoor.OPENING, 1, 1, 4);
            
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, mod_Erebus.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            
            if (par2Random.nextInt(2) == 0)
            {
            	this.placeBlockAtCurrentPosition(par1World, mod_Erebus.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            }
            if (par2Random.nextInt(2) == 0)
            {
            	this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            }
            if (par2Random.nextInt(2) == 0)
            {
            	this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            }
            if (par2Random.nextInt(2) == 0)
            {
            	this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(6), 1 + par2Random.nextInt(3), par3StructureBoundingBox);
            }
            
            if (par2Random.nextInt(4) == 0)
            {
            	//Chest
                {
                    int var13 = this.getYWithOffset(1);
                    int var10 = this.getXWithOffset(2, 1);
                    int var11 = this.getZWithOffset(2, 1);

                    if (par3StructureBoundingBox.isVecInside(var10, var13, var11))
                    {
                        this.hasMadeChest = true;
                        par1World.setBlockWithNotify(var10, var13, var11, Block.chest.blockID);
                        TileEntityChest var12 = (TileEntityChest)par1World.getBlockTileEntity(var10, var13, var11);

                        if (var12 != null)
                        {
                        	WeightedRandomChestContent.generateChestContents(par2Random, spiderTunnelsChestContents,  var12, spiderTunnelsChestContents.length);
                        }
                    }
                }
                
            }
            
            */
            return true;
            //1 - 3 = 1 + par2Random.nextInt(3)
            //1 - 6 = 1 + par2Random.nextInt(6)
            //1 - 3 = 1 + par2Random.nextInt(3)
        }
    }
}
