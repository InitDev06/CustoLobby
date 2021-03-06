package net.custolobby.plugin.listeners;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.custolobby.plugin.CustoLobby;

public class PlayerSound implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		Player player = event.getPlayer();
		if(config.getBoolean("reproduce-sound")) {
			player.playSound(player.getLocation(), Sound.valueOf(config.getString("sound")), 15, (float) -0.2);
		}
	}

}
