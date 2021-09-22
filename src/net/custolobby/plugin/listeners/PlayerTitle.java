package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.title.Titles;

public class PlayerTitle implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerTitle(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		if(config.getBoolean("build-title")) {
			
			int fadeIn = Integer.valueOf(config.getInt("title.fadeIn"));
			int stay = Integer.valueOf(config.getInt("title.stay"));
			int fadeOut = Integer.valueOf(config.getInt("title.fadeOut"));
			
			String title = Color.translate(messages.getString("messages.title").replaceAll("%player_name%", player.getName()));
			String subtitle = Color.translate(messages.getString("messages.subtitle").replaceAll("%player_name%", player.getName()));
			
			Titles.buildTitle(player.getPlayer(), fadeIn, stay, fadeOut, title, subtitle);
		}
	}

}
