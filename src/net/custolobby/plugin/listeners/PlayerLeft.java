package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class PlayerLeft implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerLeft(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		if(player.hasPermission("custolobby.quit") || player.isOp()) {
			event.setQuitMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.rank-quit")))
					.replaceAll("%player_name%", player.getDisplayName()));
		} else {
			event.setQuitMessage(null);
		}
	}

}
