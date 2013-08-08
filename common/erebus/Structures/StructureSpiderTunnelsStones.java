package erebus.Structures;

import java.util.Random;

import erebus.ErebusMod;
import net.minecraft.block.Block;
import net.minecraft.world.gen.structure.StructurePieceBlockSelector;

class StructureSpiderTunnelsStones extends StructurePieceBlockSelector
{
    private StructureSpiderTunnelsStones() {}

    /**
     * picks Block Ids and Metadata (Silverfish)
     */
    public void selectBlocks(Random par1Random, int par2, int par3, int par4, boolean par5)
    /*{
        if (par5)
        {
            this.selectedBlockId = mod_Erebus.cobbleWebbed.blockID;
            float var6 = par1Random.nextFloat();

            if (var6 < 0.2F)
            {
            	this.selectedBlockId = Block.stone.blockID;
            }
            else if (var6 < 0.5F)
            {
            	this.selectedBlockId = Block.cobblestone.blockID;
            }
            else if (var6 < 0.55F)
            {
                this.selectedBlockId = Block.cobblestoneMossy.blockID;
            }
            else
            {
            	this.selectedBlockId = mod_Erebus.cobbleWebbed.blockID;
            }
        }
        else
        {
            this.selectedBlockId = 0;
            this.selectedBlockMetaData = 0;
        }
    }*/
    {
        if (par5)
        {
            this.selectedBlockId = ErebusMod.cobbleWebbed.blockID;
            float var6 = par1Random.nextFloat();

            if (var6 < 0.25F)
            {
            	this.selectedBlockId = Block.stone.blockID;
            }
            else if (var6 < 0.5F)
            {
            	this.selectedBlockId = Block.cobblestone.blockID;
            }
            else if (var6 < 0.75F)
            {
                this.selectedBlockId = Block.cobblestoneMossy.blockID;
            }
            else
            {
            	this.selectedBlockId = ErebusMod.cobbleWebbed.blockID;
            }
        }
        else
        {
            this.selectedBlockId = 0;
            this.selectedBlockMetaData = 0;
        }
    }

    StructureSpiderTunnelsStones(StructureSpiderTunnelsPieceWeight2 par1StructureSpiderTunnelsPieceWeight2)
    {
        this();
    }
}
