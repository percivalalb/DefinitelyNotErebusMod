//Quintinity's Code
package erebus;
import java.io.*;
import java.util.HashMap;

import com.google.common.io.*;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import cpw.mods.fml.common.network.*;
import erebus.lib.Reference;

public class PacketErebusHandler implements IConnectionHandler, IPacketHandler
{
	public HashMap<String, TeleportServerErebus> serverPlayers = new HashMap<String, TeleportServerErebus>();

	public static Packet250CustomPayload getDataPacket(EntityPlayerMP player)
	{		
		try 
		{
	        ByteArrayOutputStream bytes = new ByteArrayOutputStream(13);
	        DataOutputStream data = new DataOutputStream(bytes);
	        data.writeInt(0);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = Reference.CHANNEL;
			packet.length = bytes.size();
			packet.data = bytes.toByteArray();
			packet.length = bytes.size();
			return packet;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void onTick(EntityPlayerMP player)
	{
		getPlayer(player.username).onTick();
	}
	
	public TeleportServerErebus getPlayer(String username)
	{
		return serverPlayers.get(username);
	}
	
	public void addPlayer(TeleportServerErebus player)
	{
		serverPlayers.put(player.getPlayer().username, player);
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p) 
	{
        if (packet.channel.equals(Reference.CHANNEL)) 
        {
			EntityPlayerSP player = (EntityPlayerSP)p;
			if (player instanceof EntityPlayerSP) 
			{
				ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
				int num = data.readInt();
				if (num == 0) 
				{
					TeleportErebusClient.timeInPortal = 0;
					TeleportErebusClient.prevTimeInPortal = 0;
					TeleportErebusClient.inPortal = false;
					TeleportErebusClient.timeUntilPortal = 20;
				}
			}
		}
	}

	@Override
	public void playerLoggedIn(Player p, NetHandler netHandler, INetworkManager manager)
	{
		EntityPlayerMP player = (EntityPlayerMP)p;
		addPlayer(new TeleportServerErebus(player));		
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) 
	{
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionClosed(INetworkManager manager) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
	{
		// TODO Auto-generated method stub
		
	}
}