package erebus.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class StructureSpiderTunnelsPieces
{
    private static final StructureSpiderTunnelsPieceWeight[] pieceWeightArray = new StructureSpiderTunnelsPieceWeight[] {new StructureSpiderTunnelsPieceWeight(ComponentSpiderTunnelsStairs.class, 5, 10), new StructureSpiderTunnelsPieceWeight(ComponentSpiderTunnelsStraight.class, 5, 15), new StructureSpiderTunnelsPieceWeight(ComponentSpiderTunnelsCrossing.class, 5, 10), new StructureSpiderTunnelsPieceWeight3(ComponentSpiderTunnelsCavern.class, 20, 1), new StructureSpiderTunnelsPieceWeight3(ComponentSpiderTunnelsEntrance.class, 20, 1)};
    private static List structurePieceList;
    private static Class strongComponentType;
    static int totalWeight = 0;
    private static final StructureSpiderTunnelsStones SpiderTunnelsStones = new StructureSpiderTunnelsStones((StructureSpiderTunnelsPieceWeight2)null);
    private static final StructureSpiderTunnelsRandomStones SpiderTunnelsRandomStones = new StructureSpiderTunnelsRandomStones((StructureSpiderTunnelsPieceWeight2)null);

    /**
     * sets up Arrays with the Structure pieces and their weights
     */
    public static void prepareStructurePieces()
    {
        structurePieceList = new ArrayList();
        StructureSpiderTunnelsPieceWeight[] var0 = pieceWeightArray;
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            StructureSpiderTunnelsPieceWeight var3 = var0[var2];
            var3.instancesSpawned = 0;
            structurePieceList.add(var3);
        }

        strongComponentType = null;
    }

    private static boolean canAddStructurePieces()
    {
        boolean var0 = false;
        totalWeight = 0;
        StructureSpiderTunnelsPieceWeight var2;

        for (Iterator var1 = structurePieceList.iterator(); var1.hasNext(); totalWeight += var2.pieceWeight)
        {
            var2 = (StructureSpiderTunnelsPieceWeight)var1.next();

            if (var2.instancesLimit > 0 && var2.instancesSpawned < var2.instancesLimit)
            {
                var0 = true;
            }
        }

        return var0;
    }

    /**
     * translates the PieceWeight class to the Component class
     */
    private static ComponentSpiderTunnels getSpiderTunnelsComponentFromWeightedPiece(Class par0Class, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        Object var8 = null;

        if (par0Class == ComponentSpiderTunnelsStairs.class)
        {
            var8 = ComponentSpiderTunnelsStairs.getSpiderTunnelsStairsComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (par0Class == ComponentSpiderTunnelsCrossing.class)
        {
            var8 = ComponentSpiderTunnelsCrossing.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (par0Class == ComponentSpiderTunnelsCavern.class)
        {
            var8 = ComponentSpiderTunnelsCavern.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }

        else if (par0Class == ComponentSpiderTunnelsStraight.class)
        {
            var8 = ComponentSpiderTunnelsStraight.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }

        return (ComponentSpiderTunnels)var8;
    }

    private static ComponentSpiderTunnels getNextComponent(ComponentSpiderTunnelsStairs2 par0ComponentSpiderTunnelsStairs2, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        if (!canAddStructurePieces())
        {
            return null;
        }
        else
        {
            if (strongComponentType != null)
            {
                ComponentSpiderTunnels var8 = getSpiderTunnelsComponentFromWeightedPiece(strongComponentType, par1List, par2Random, par3, par4, par5, par6, par7);
                strongComponentType = null;

                if (var8 != null)
                {
                    return var8;
                }
            }

            int var13 = 0;

            while (var13 < 5)
            {
                ++var13;
                int var9 = par2Random.nextInt(totalWeight);
                Iterator var10 = structurePieceList.iterator();

                while (var10.hasNext())
                {
                    StructureSpiderTunnelsPieceWeight var11 = (StructureSpiderTunnelsPieceWeight)var10.next();
                    var9 -= var11.pieceWeight;

                    if (var9 < 0)
                    {
                        if (!var11.canSpawnMoreStructuresOfType(par7) || var11 == par0ComponentSpiderTunnelsStairs2.SpiderTunnelsPieceWeight)
                        {
                            break;
                        }

                        ComponentSpiderTunnels var12 = getSpiderTunnelsComponentFromWeightedPiece(var11.pieceClass, par1List, par2Random, par3, par4, par5, par6, par7);

                        if (var12 != null)
                        {
                            ++var11.instancesSpawned;
                            par0ComponentSpiderTunnelsStairs2.SpiderTunnelsPieceWeight = var11;

                            if (!var11.canSpawnMoreStructures())
                            {
                                structurePieceList.remove(var11);
                            }

                            return var12;
                        }
                    }
                }
            }

            StructureBoundingBox var14 = ComponentSpiderTunnelsCorridor.func_74992_a(par1List, par2Random, par3, par4, par5, par6);

            if (var14 != null && var14.minY > 1)
            {
                return new ComponentSpiderTunnelsCorridor(par7, par2Random, var14, par6);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent getNextValidComponent(ComponentSpiderTunnelsStairs2 par0ComponentSpiderTunnelsStairs2, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        if (par7 > 50)
        {
            return null;
        }
        else if (Math.abs(par3 - par0ComponentSpiderTunnelsStairs2.getBoundingBox().minX) <= 112 && Math.abs(par5 - par0ComponentSpiderTunnelsStairs2.getBoundingBox().minZ) <= 112)
        {
            ComponentSpiderTunnels var8 = getNextComponent(par0ComponentSpiderTunnelsStairs2, par1List, par2Random, par3, par4, par5, par6, par7 + 1);

            if (var8 != null)
            {
                par1List.add(var8);
                par0ComponentSpiderTunnelsStairs2.field_75026_c.add(var8);
            }

            return var8;
        }
        else
        {
            return null;
        }
    }

    static StructureComponent getNextValidComponentAccess(ComponentSpiderTunnelsStairs2 par0ComponentSpiderTunnelsStairs2, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        return getNextValidComponent(par0ComponentSpiderTunnelsStairs2, par1List, par2Random, par3, par4, par5, par6, par7);
    }

    static Class setComponentType(Class par0Class)
    {
        strongComponentType = par0Class;
        return par0Class;
    }

    static StructureSpiderTunnelsStones getSpiderTunnelsStones()
    {
        return SpiderTunnelsStones;
    }
    
    static StructureSpiderTunnelsRandomStones getSpiderTunnelsRandomStones()
    {
        return SpiderTunnelsRandomStones;
    }
}
