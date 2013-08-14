package erebus.network.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import erebus.core.teleport.TeleportClient;
import erebus.inventory.ContainerColossalCrate;
import erebus.network.IPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * @author ProPercivalalb
 **/
public class PacketColossalCratePage implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		if (player instanceof EntityPlayerMP) {
			int page = data.readInt();
			EntityPlayerMP playerMP = (EntityPlayerMP)player;
			if(playerMP.openContainer instanceof ContainerColossalCrate) {
				ContainerColossalCrate crate = (ContainerColossalCrate)playerMP.openContainer;
				crate.changePage(page);
			}
		}
	}

}
