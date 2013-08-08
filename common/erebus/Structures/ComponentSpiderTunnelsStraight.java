package erebus.Structures;

import java.util.List;
import java.util.Random;

import erebus.ErebusMod;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.EnumDoor;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentSpiderTunnelsStraight extends ComponentSpiderTunnels
{
    private final EnumDoor doorType;
    private final boolean expandsX;
    private final boolean expandsZ;

    public ComponentSpiderTunnelsStraight(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
    {
        super(par1);
        this.coordBaseMode = par4;
        this.doorType = this.getRandomDoor(par2Random);
        this.boundingBox = par3StructureBoundingBox;
        this.expandsX = par2Random.nextInt(2) == 0;
        this.expandsZ = par2Random.nextInt(2) == 0;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        this.getNextComponentNormal((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, 1, 1);

        if (this.expandsX)
        {
            this.getNextComponentX((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, 1, 2);
        }

        if (this.expandsZ)
        {
            this.getNextComponentZ((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, 1, 2);
        }
    }

    public static ComponentSpiderTunnelsStraight findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -1, 0, 5, 5, 7, par5);
        return canSpiderTunnelsGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentSpiderTunnelsStraight(par6, par1Random, var7, par5) : null;
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
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 4, 6, true, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            this.placeDoor(par1World, par2Random, par3StructureBoundingBox, this.doorType, 1, 1, 0);
            this.placeDoor(par1World, par2Random, par3StructureBoundingBox, EnumDoor.OPENING, 1, 1, 6);
            
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(5), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(5), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(5), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(5), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(5), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(3), 1 + par2Random.nextInt(5), par3StructureBoundingBox);
                        
            if (this.expandsX)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 2, 0, 3, 4, 0, 0, false);
            }

            if (this.expandsZ)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 2, 4, 3, 4, 0, 0, false);
            }

            return true;
        }
    }
}
