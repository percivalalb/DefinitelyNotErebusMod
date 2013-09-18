package erebus.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import erebus.lib.Reference;
import erebus.network.packet.PacketColossalCratePage;
import erebus.network.packet.PacketTeleport;

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
