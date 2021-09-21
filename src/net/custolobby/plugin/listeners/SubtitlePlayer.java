package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.title.Subtitle;

public class SubtitlePlayer implements Listener {
	
	private CustoLobby plugin;
	
	public SubtitlePlayer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	private Subtitle subtitle;
	
	@EventHandler
	public void onTitle(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		String message = Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.title")).replaceAll("%player_name%", player.getName()));
		int fadeIn = config.getInt("settings.title.fadeIn");
		int stay = config.getInt("settings.title.stay");
		int fadeOut = config.getInt("settings.title.fadeOut");
		boolean isSetTrue = config.getBoolean("settings.use-subtitles");
		if(isSetTrue) {
			subtitle.sendSubtitle(player.getPlayer(), message, fadeIn, stay, fadeOut);
		}
	}

}
