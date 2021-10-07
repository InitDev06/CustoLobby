package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		FileConfiguration chat = CustoLobby.getChatFile();
		Player player = event.getPlayer();
		if(chat.getBoolean("enable-format")) {
			if(player.hasPermission("custolobby.chat") || player.isOp()) {
				event.setFormat(Color.translate(PlaceholderAPI.setPlaceholders(player, chat.getString("formats.groups.rank.prefix")))
						.replaceAll("%player_name%", player.getDisplayName())
						.replaceAll("%message%", event.getMessage())
						.replaceAll("%world_name%", player.getWorld().getName())
						.replaceAll("%level%", String.valueOf(player.getLevel())));
			} else {
				event.setFormat(Color.translate(PlaceholderAPI.setPlaceholders(player, chat.getString("formats.groups.default.prefix")))
						.replaceAll("%player_name%", player.getDisplayName())
						.replaceAll("%message%", event.getMessage())
						.replaceAll("%world_name%", player.getWorld().getName())
						.replaceAll("%level%", String.valueOf(player.getLevel())));
			}
		}
	}

}
