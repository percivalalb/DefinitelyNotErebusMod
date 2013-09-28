package erebus.core.handler;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import erebus.core.teleport.TeleportServer;

public class ConnectionTeleportHandler implements IConnectionHandler {

	public HashMap<String, TeleportServer> serverPlayers = new HashMap<String, TeleportServer>();

	public void onTick(EntityPlayerMP player) {
		getPlayer(player.username).onTick();
	}

	public TeleportServer getPlayer(String username) {
		return serverPlayers.get(username);
	}

	public void addPlayer(TeleportServer player) {
		serverPlayers.put(player.getPlayer().username, player);
	}

	@Override
	public void playerLoggedIn(Player p, NetHandler netHandler, INetworkManager manager) {
		addPlayer(new TeleportServer((EntityPlayerMP) p));
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {

	}

	@Override
	public void connectionClosed(INetworkManager manager) {

	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {

	}
}