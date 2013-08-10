package erebus.core.teleport;

import java.util.Random;

import erebus.ErebusMod;
import erebus.core.handler.ConnectionTeleportHandler;
import erebus.world.TeleporterErebus;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.ModLoader;
import net.minecraft.world.Teleporter;

public class TeleportServer 
{
	private EntityPlayerMP player;
	public float prevTimeInPortal;
	public float timeInPortal;
	public boolean inPortal = false;
	public Random random = new Random();
	public int timeUntilPortal = 20;
	
	public TeleportServer(EntityPlayerMP player)
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
                player.playerNetServerHandler.sendPacketToPlayer(ConnectionTeleportHandler.getDataPacket(player));
                if (player.dimension == (byte)ErebusMod.erebusDimensionID)  {
                	player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, TeleporterErebus.TELEPORTER_TO_OVERWORLD);
                }
                else if (player.dimension == (byte)0)  {
                	player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ErebusMod.erebusDimensionID, TeleporterErebus.TELEPORTER_TO_EREBUS);
                }
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