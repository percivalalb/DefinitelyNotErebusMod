package erebus.structures;

final class StructureSpiderTunnelsPieceWeight3 extends StructureSpiderTunnelsPieceWeight
{
    StructureSpiderTunnelsPieceWeight3(Class par1Class, int par2, int par3)
    {
        super(par1Class, par2, par3);
    }

    public boolean canSpawnMoreStructuresOfType(int par1)
    {
        return super.canSpawnMoreStructuresOfType(par1) && par1 > 5;
    }
}
