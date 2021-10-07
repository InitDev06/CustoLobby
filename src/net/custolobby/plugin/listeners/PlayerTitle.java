package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.nms.utility.NMSHelper;

public class PlayerTitle implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		Player player = event.getPlayer();
		if(config.getString("language").equals("English")) {
			if(config.getBoolean("build-title")) {
				
				int fadeIn = Integer.valueOf(config.getInt("title.fadeIn"));
				int stay = Integer.valueOf(config.getInt("title.stay"));
				int fadeOut = Integer.valueOf(config.getInt("title.fadeOut"));
				
				String title = Color.translate(PlaceholderAPI.setPlaceholders(player, lang_en.getString("messages.title").replaceAll("%player_name%"
						, player.getDisplayName())).replaceAll("%version%", CustoLobby.getInstance().VERSION));
				
				NMSHelper.getNMS().sendTitle(player, title, fadeIn, stay, fadeOut);
			}
		} else if(config.getString("language").equals("Spanish")) {
			if(config.getBoolean("build-title")) {
				
				int fadeIn = Integer.valueOf(config.getInt("title.fadeIn"));
				int stay = Integer.valueOf(config.getInt("title.stay"));
				int fadeOut = Integer.valueOf(config.getInt("title.fadeOut"));
				
				String title = Color.translate(PlaceholderAPI.setPlaceholders(player, lang_es.getString("messages.title").replaceAll("%player_name%"
						, player.getDisplayName())));
				
				NMSHelper.getNMS().sendTitle(player, title, fadeIn, stay, fadeOut);
			}
		}
	}

}
