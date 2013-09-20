package erebus.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import com.google.common.io.ByteArrayDataInput;
import erebus.ErebusMod;
import erebus.network.IPacket;

public class PacketParticle implements IPacket{
	public static final byte BEETLE_LARVA_SQUISH = 0,
							 BEETLE_LARVA_EAT = 1;
	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data){
		ErebusMod.proxy.handleParticlePacket(manager,packet,player,data);
	}
}
