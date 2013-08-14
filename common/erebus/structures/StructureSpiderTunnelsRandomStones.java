package erebus.structures;

import java.util.Random;

import erebus.ErebusMod;
import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.structure.StructurePieceBlockSelector;

class StructureSpiderTunnelsRandomStones extends StructurePieceBlockSelector
{
    private StructureSpiderTunnelsRandomStones() {}

    /**
     * picks Block Ids and Metadata (Silverfish)
     */
    public void selectBlocks(Random par1Random, int par2, int par3, int par4, boolean par5)
    {
        if (par5)
        {
            this.selectedBlockId = ModBlocks.cobbleWebbed.blockID;
            float var6 = par1Random.nextFloat();

            if (var6 < 0.1F)
            {
            	this.selectedBlockId = Block.stone.blockID;
            }
            else if (var6 < 0.2F)
            {
            	this.selectedBlockId = Block.cobblestone.blockID;
            }
            else if (var6 < 0.3F)
            {
                this.selectedBlockId = Block.cobblestoneMossy.blockID;
            }
            else if (var6 < 0.5F)
            {
            	this.selectedBlockId = Block.web.blockID;
            }
            else if (var6 < 0.6F)
            {
            	this.selectedBlockId = Block.web.blockID;
            }
            else
            {
            	this.selectedBlockId = 0;
            }
        }
        else
        {
            this.selectedBlockId = 0;
            this.selectedBlockMetaData = 0;
        }
    }

    StructureSpiderTunnelsRandomStones(StructureSpiderTunnelsPieceWeight2 par1StructureSpiderTunnelsPieceWeight2)
    {
        this();
    }
}
