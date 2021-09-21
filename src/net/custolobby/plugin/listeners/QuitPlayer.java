package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class QuitPlayer implements Listener {
	
	private CustoLobby plugin;
	
	public QuitPlayer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		FileConfiguration messages = plugin.getMessages();
		if(player.hasPermission("custolobby.quit") || player.isOp()) {
			event.setQuitMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.rank-quit"))).replaceAll
					("%player_name%", player.getName()));
		}
	}

}