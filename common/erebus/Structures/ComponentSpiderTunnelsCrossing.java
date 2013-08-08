package erebus.Structures;

import java.util.List;
import java.util.Random;

import erebus.ErebusMod;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.EnumDoor;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentSpiderTunnelsCrossing extends ComponentSpiderTunnels
{
    protected final EnumDoor doorType;
    private boolean field_74996_b;
    private boolean field_74997_c;
    private boolean field_74995_d;
    private boolean field_74999_h;

    public ComponentSpiderTunnelsCrossing(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
    {
        super(par1);
        this.coordBaseMode = par4;
        this.doorType = this.getRandomDoor(par2Random);
        this.boundingBox = par3StructureBoundingBox;
        this.field_74996_b = par2Random.nextBoolean();
        this.field_74997_c = par2Random.nextBoolean();
        this.field_74995_d = par2Random.nextBoolean();
        this.field_74999_h = par2Random.nextInt(3) > 0;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        int var4 = 3;
        int var5 = 5;

        if (this.coordBaseMode == 1 || this.coordBaseMode == 2)
        {
            var4 = 8 - var4;
            var5 = 8 - var5;
        }

        this.getNextComponentNormal((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, 5, 1);

        if (this.field_74996_b)
        {
            this.getNextComponentX((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, var4, 1);
        }

        if (this.field_74997_c)
        {
            this.getNextComponentX((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, var5, 7);
        }

        if (this.field_74995_d)
        {
            this.getNextComponentZ((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, var4, 1);
        }

        if (this.field_74999_h)
        {
            this.getNextComponentZ((ComponentSpiderTunnelsStairs2)par1StructureComponent, par2List, par3Random, var5, 7);
        }
    }

    public static ComponentSpiderTunnelsCrossing findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -3, 0, 10, 9, 11, par5);
        return canSpiderTunnelsGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentSpiderTunnelsCrossing(par6, par1Random, var7, par5) : null;
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
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 9, 8, 10, true, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            this.placeDoor(par1World, par2Random, par3StructureBoundingBox, this.doorType, 4, 3, 0);

            if (this.field_74996_b)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 5, 3, 0, 0, false);
            }

            if (this.field_74995_d)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 3, 1, 9, 5, 3, 0, 0, false);
            }

            if (this.field_74997_c)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 7, 0, 7, 9, 0, 0, false);
            }

            if (this.field_74999_h)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 5, 7, 9, 7, 9, 0, 0, false);
            }

            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 10, 7, 3, 10, 0, 0, false);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 2, 1, 8, 2, 6, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 1, 7, 4, 2, 9, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 8, 1, 7, 8, 2, 9, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 1, 5, 7, 1, 6, false, par2Random, StructureSpiderTunnelsPieces.getSpiderTunnelsStones());
            
            //Randomising
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            
            //Randomising x 2
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 8, 3 + par2Random.nextInt(5), 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 1, par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3 + par2Random.nextInt(5), 9, par3StructureBoundingBox);
            
            //Floor
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 3, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            //Roof
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            this.placeBlockAtCurrentPosition(par1World, ErebusMod.cobbleWebbed.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stone.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 1 + par2Random.nextInt(8), 7, 1 + par2Random.nextInt(9), par3StructureBoundingBox);
            
            //Step
            /*this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 5 + par2Random.nextInt(3), 2, 5 + par2Random.nextInt(2), par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, 5 + par2Random.nextInt(3), 2, 5 + par2Random.nextInt(2), par3StructureBoundingBox);*/
                        
            return true;
        }
    }
}
