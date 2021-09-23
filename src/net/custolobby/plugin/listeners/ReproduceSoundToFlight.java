package net.custolobby.plugin.listeners;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.custolobby.plugin.CustoLobby;

public class ReproduceSoundToFlight implements Listener {
	
	private CustoLobby plugin;
	
	public ReproduceSoundToFlight(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void sound(AsyncPlayerChatEvent event) {
		FileConfiguration config = plugin.getConfig();
		Player player = event.getPlayer();
		if(config.getBoolean("sound-to-flight")) {
			if(event.getMessage().equalsIgnoreCase("/fly") || event.getMessage().equalsIgnoreCase("/flight")) {
				player.playSound(player.getLocation(), Sound.valueOf(config.getString("id")), 15, (float) -0.2);
			}
		}
	}

}
