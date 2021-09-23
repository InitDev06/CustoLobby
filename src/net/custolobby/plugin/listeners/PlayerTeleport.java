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
		Double x = Double.valueOf(config.getInt("lobby-point.x"));
		Double y = Double.valueOf(config.getInt("lobby-point.y"));
		Double z = Double.valueOf(config.getInt("lobby-point.z"));
		
		Float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
		Float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
		
		World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
		
		Location location = new Location(world, x, y, z, yaw, pitch);
		
		if(world != null) {
			player.teleport(location);
		}
		
	}

}
