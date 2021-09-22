package net.custolobby.plugin.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.custolobby.plugin.CustoLobby;

public class PlayerTeleport implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerTeleport(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		Player player = event.getPlayer();
		if(config.contains("lobby-point.world")) {
			double x = Double.valueOf(config.getInt("lobby-point.x"));
			double y = Double.valueOf(config.getInt("lobby-point.y"));
			double z = Double.valueOf(config.getInt("lobby-point.z"));
			
			float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
			float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
			
			World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
			
			Location location = new Location(world, x, y, z, yaw, pitch);
			
			player.teleport(location);
		}
	}

}
