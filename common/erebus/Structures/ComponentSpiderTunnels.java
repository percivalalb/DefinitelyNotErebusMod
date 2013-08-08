package erebus.structures;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.EnumDoor;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

abstract class ComponentSpiderTunnels extends StructureComponent
{
    protected ComponentSpiderTunnels(int par1)
    {
        super(par1);
    }

    /**
     * builds a door of the enumerated types (empty opening is a door)
     */
    protected void placeDoor(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumDoor par4EnumDoor, int par5, int par6, int par7)
    {
        switch (EnumDoorHelper.doorEnum[par4EnumDoor.ordinal()])
        {
            case 1:
            default:
            	this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6 + 1, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6 + 1, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6 + 1, par7, par3StructureBoundingBox);
                break;
            case 2:
            	this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5, par6 + 1, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6 + 1, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5 + 2, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6 + 1, par7, par3StructureBoundingBox);
                break;
            case 3:
            	 this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5, par6 + 1, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6 + 2, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6 + 2, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5 + 2, par6 + 2, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5 + 2, par6 + 1, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6, par7, par3StructureBoundingBox);
                 this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5 + 1, par6 + 1, par7, par3StructureBoundingBox);
                break;
            case 4:
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5, par6 + 1, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 1, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5 + 2, par6 + 2, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6 + 1, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, par5 + 2, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.web.blockID, 0, par5 + 1, par6, par7, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 8, par5 + 1, par6 + 1, par7, par3StructureBoundingBox);
        }
    }

    protected EnumDoor getRandomDoor(Random par1Random)
    {
        int var2 = par1Random.nextInt(5);

        switch (var2)
        {
            case 0:
            case 1:
            default:
                return EnumDoor.OPENING;
            case 2:
                return EnumDoor.WOOD_DOOR;
            case 3:
                return EnumDoor.GRATES;
            case 4:
                return EnumDoor.IRON_DOOR;
        }
    }

    /**
     * Gets the next component in any cardinal direction
     */
    protected StructureComponent getNextComponentNormal(ComponentSpiderTunnelsStairs2 par1ComponentSpiderTunnelsStairs2, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.maxZ + 1, this.coordBaseMode, this.getComponentType());
            case 1:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.coordBaseMode, this.getComponentType());
            case 2:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.minZ - 1, this.coordBaseMode, this.getComponentType());
            case 3:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.coordBaseMode, this.getComponentType());
            default:
                return null;
        }
    }

    /**
     * Gets the next component in the +/- X direction
     */
    protected StructureComponent getNextComponentX(ComponentSpiderTunnelsStairs2 par1ComponentSpiderTunnelsStairs2, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType());
            case 1:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType());
            case 2:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType());
            case 3:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType());
            default:
                return null;
        }
    }

    /**
     * Gets the next component in the +/- Z direction
     */
    protected StructureComponent getNextComponentZ(ComponentSpiderTunnelsStairs2 par1ComponentSpiderTunnelsStairs2, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType());
            case 1:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
            case 2:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType());
            case 3:
                return StructureSpiderTunnelsPieces.getNextValidComponentAccess(par1ComponentSpiderTunnelsStairs2, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
            default:
                return null;
        }
    }

    /**
     * returns false if the Structure Bounding Box goes below 10
     */
    protected static boolean canSpiderTunnelsGoDeeper(StructureBoundingBox par0StructureBoundingBox)
    {
        return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
    }
}
