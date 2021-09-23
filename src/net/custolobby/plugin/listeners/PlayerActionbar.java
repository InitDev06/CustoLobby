package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ancash.actionbar.ActionBarAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class PlayerActionbar implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerActionbar(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onActionbar(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		if(config.getBoolean("build-actionbar")) {
			ActionBarAPI.sendActionBar(player.getPlayer(), Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString(
					"messages.actionbar"))).replaceAll("%player_name%", player.getName()));
		}
	}

}
