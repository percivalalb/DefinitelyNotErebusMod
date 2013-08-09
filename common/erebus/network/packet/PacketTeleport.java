package erebus.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import erebus.core.teleport.TeleportClient;
import erebus.network.IPacket;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * @author ProPercivalalb
 **/
public class PacketTeleport implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player) {
		if (player instanceof EntityPlayerSP) {
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			int num = data.readInt();
			if (num == 0) {
				TeleportClient.timeInPortal = 0;
				TeleportClient.prevTimeInPortal = 0;
				TeleportClient.inPortal = false;
				TeleportClient.timeUntilPortal = 20;
			}
		}
	}

}
