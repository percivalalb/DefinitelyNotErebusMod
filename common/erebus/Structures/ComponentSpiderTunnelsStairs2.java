package erebus.Structures;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.world.ChunkPosition;

public class ComponentSpiderTunnelsStairs2 extends ComponentSpiderTunnelsStairs
{
    public StructureSpiderTunnelsPieceWeight SpiderTunnelsPieceWeight;
    public ComponentSpiderTunnelsCavern SpiderTunnelsPortalRoom;
    public ArrayList field_75026_c = new ArrayList();

    public ComponentSpiderTunnelsStairs2(int par1, Random par2Random, int par3, int par4)
    {
        super(0, par2Random, par3, par4);
    }

    public ChunkPosition getCenter()
    {
        return this.SpiderTunnelsPortalRoom != null ? this.SpiderTunnelsPortalRoom.getCenter() : super.getCenter();
    }
}
