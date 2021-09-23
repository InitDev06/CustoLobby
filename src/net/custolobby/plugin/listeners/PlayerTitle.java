package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.connorlinfoot.titleapi.TitleAPI;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class PlayerTitle implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerTitle(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		if(config.getBoolean("build-titles")) {
			
			int fadeIn = Integer.valueOf(config.getInt("title.fadeIn"));
			int stay = Integer.valueOf(config.getInt("title.stay"));
			int fadeOut = Integer.valueOf(config.getInt("title.fadeOut"));
			
			String title = Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.title").replaceAll("%player_name%"
					, player.getName())));
			String subtitle = Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.subtitle").replaceAll(
					"%player_name%", player.getName())));
			
			TitleAPI.sendFullTitle(player.getPlayer(), fadeIn, stay, fadeOut, title, subtitle);
		}
	}

}
