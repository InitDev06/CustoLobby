package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.actionbar.Actionbar;
import net.custolobby.plugin.color.Color;

public class ActionbarPlayer implements Listener {
	
	private CustoLobby plugin;
	
	public ActionbarPlayer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	private Actionbar actionbar;

	@EventHandler
	public void onSendActionbar(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		boolean isSetTrue = config.getBoolean("settings.use-actionbar");
		if(isSetTrue) {
			actionbar.sendActionBar(player.getPlayer(), Color.translate(PlaceholderAPI.setPlaceholders(player, messages.getString("messages.actionbar"))).replaceAll("%player_name%", player.getName()));
		}
	}
	
}
