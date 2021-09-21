package net.custolobby.plugin.listeners;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.custolobby.plugin.CustoLobby;

public class SoundPlayer implements Listener {
	
	private CustoLobby plugin;
	
	public SoundPlayer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSound(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		Player player = event.getPlayer();
		boolean isSetTrue = config.getBoolean("settings.use-sounds");
		if(isSetTrue) {
			player.playSound(player.getLocation(), Sound.valueOf(config.getString("settings.sound-id")), 10, (float) -0.3);
		}
	}
	
}
