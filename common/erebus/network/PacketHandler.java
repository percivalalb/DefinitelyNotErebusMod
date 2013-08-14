package erebus.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import erebus.lib.Reference;
import erebus.network.packet.PacketTeleport;
import erebus.network.packet.PacketColossalCratePage;

/**
 * @author ProPercivalalb
 **/
public class PacketHandler implements IPacketHandler {

	private List<IPacket> map = new ArrayList<IPacket>();
	
	public PacketHandler() {
        map.add(new PacketTeleport());
        map.add(new PacketColossalCratePage());
	}
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if(packet.channel.equals(Reference.CHANNEL)) { 
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			map.get(data.readInt()).handle(manager, packet, (EntityPlayer)player, data);
		}
	}
}
