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
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		Player player = event.getPlayer();
		if(config.getString("language").equals("English")) {
			if(player.hasPermission("custolobby.join") || player.isOp()) {
				event.setJoinMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, lang_en.getString("messages.rank-join"))).replaceAll(
						"%player_name%", player.getName()));
			} else {
				event.setJoinMessage(null);
			}
		} else if(config.getString("language").equals("Spanish")) {
			if(player.hasPermission("custolobby.join") || player.isOp()) {
				event.setJoinMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, lang_es.getString("messages.rank-join"))).replaceAll(
						"%player_name%", player.getName()));
			} else {
				event.setJoinMessage(null);
			}
		}
	}
	
	@EventHandler
	public void motd(PlayerJoinEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		Player player = event.getPlayer();
		if(config.getString("language").equals("English")) {
			if(config.getBoolean("send-motd")) {
				List<String> lore = lang_en.getStringList("messages.motd");
				for(int i = 0 ; i < lore.size(); i ++) {
					String motd = Color.translate(lore.get(i).replaceAll("%version%", CustoLobby.getInstance().VERSION));
					
					player.sendMessage(motd);
				}
			}
		} else if(config.getString("language").equals("English")) {
			if(config.getBoolean("send-motd")) {
				List<String> lore = lang_es.getStringList("messages.motd");
				for(int i = 0 ; i < lore.size(); i ++) {
					String motd = Color.translate(lore.get(i).replaceAll("%version%", CustoLobby.getInstance().VERSION));
					
					player.sendMessage(motd);
				}
			}
		}
	}

}
