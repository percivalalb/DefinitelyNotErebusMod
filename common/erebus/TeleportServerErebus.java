package erebus;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.ModLoader;
import net.minecraft.world.Teleporter;

public class TeleportServerErebus 
{
	private EntityPlayerMP player;
	public float prevTimeInPortal;
	public float timeInPortal;
	public boolean inPortal = false;
	public Random random = new Random();
	public int timeUntilPortal = 20;
	
	public TeleportServerErebus(EntityPlayerMP player)
	{
		this.player = player;
	}
	
	public EntityPlayerMP getPlayer()
	{
		return player;
	}
	
	public void sendPacket(Packet packet)
	{
		player.playerNetServerHandler.sendPacketToPlayer(packet);
	}
	
	public void onTick()
	{
        if (this.inPortal)
        {
        	timeInPortal += 0.0125F;
            if (this.timeInPortal >= 1.0F)
            {
            	this.timeInPortal = 1.0F;
                this.timeUntilPortal = 10;
                boolean var4 = false;
                byte dimension = 66;
                if (player.dimension == 66) 
                {
                	dimension = 0;
                }
                player.playerNetServerHandler.sendPacketToPlayer(PacketErebusHandler.getDataPacket(player));
                player.mcServer.getConfigurationManager().transferPlayerToDimension(player, dimension, new TeleporterErebus(player.mcServer.worldServerForDimension(dimension)));
             }
            this.inPortal = false;
        }
        else
        {
            if (this.timeInPortal > 0.0F)
            {
                this.timeInPortal -= 0.05F;
            }
            if (this.timeInPortal < 0.0F)
            {
                this.timeInPortal = 0.0F;
            }
        }
        if (this.timeUntilPortal > 0)
        {
            --this.timeUntilPortal;
        }
	}
	
    public void setInPortal()
    {
        if (timeUntilPortal > 0)
        {
            timeUntilPortal = 10;
        }
        else
        {
        	inPortal = true;
        }
    }
}