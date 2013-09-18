package erebus.network.packet;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import com.google.common.io.ByteArrayDataInput;
import erebus.core.teleport.TeleportClient;
import erebus.network.IPacket;

/**
 * @author ProPercivalalb
 **/
public class PacketTeleport implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		if (player instanceof EntityPlayerSP) {
			TeleportClient.timeInPortal = 0;
			TeleportClient.prevTimeInPortal = 0;
			TeleportClient.inPortal = false;
			TeleportClient.timeUntilPortal = 20;
		}
	}

}
