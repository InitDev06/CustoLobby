package net.custolobby.plugin.listeners;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class JoinPlayer implements Listener {

	private CustoLobby plugin;
	
	public JoinPlayer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void onAnnounce(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration messages = plugin.getMessages();
		if(player.hasPermission("custolobby.join") || player.isOp()) {
			event.setJoinMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.rank-join")).replaceAll("%player_name%", player.getName())));
		}
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void onMotd(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration messages = plugin.getMessages();
		FileConfiguration config = plugin.getConfig();
		boolean isSetTrue = config.getBoolean("settings.use-motd");
		if(isSetTrue) {
			List<String> lore = messages.getStringList("messages.motd");
			for(int i = 0 ; i < lore.size(); i++) {
				String motd = Color.translate(lore.get(i));
				
				player.sendMessage(motd);
			}
		}
	}

}
