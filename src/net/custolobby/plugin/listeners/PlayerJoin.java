package net.custolobby.plugin.listeners;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerJoin(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		if(player.hasPermission("custolobby.join") || player.isOp()) {
			event.setJoinMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.rank-join"))).replaceAll("%player_name%", 
					player.getName()));
		} else {
			event.setJoinMessage(null);
		}
	}
	
	@EventHandler
	public void motd(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		if(config.getBoolean("send-motd")) {
			List<String> lore = messages.getStringList("messages.motd");
			for(int i = 0 ; i < lore.size(); i ++) {
				String motd = Color.translate(lore.get(i));
				
				player.sendMessage(motd);
			}
		}
	}

}
