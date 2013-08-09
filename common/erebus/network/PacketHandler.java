package erebus.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import erebus.lib.Reference;
import erebus.network.packet.PacketTeleport;

/**
 * @author ProPercivalalb
 **/
public class PacketHandler implements IPacketHandler {

	private Map<String, IPacket> map = new Hashtable<String, IPacket>();
	
	public PacketHandler() {
        map.put(Reference.CHANNEL, new PacketTeleport());
	}
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if(map.containsKey(packet.channel)) { 
			map.get(packet.channel).handle(manager, packet, (EntityPlayer)player);
		}
	}
}
