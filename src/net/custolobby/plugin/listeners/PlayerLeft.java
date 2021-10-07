package net.custolobby.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class PlayerLeft implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		Player player = event.getPlayer();
		if(config.getString("language").equals("English")) {
			if(player.hasPermission("custolobby.quit") || player.isOp()) {
				event.setQuitMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, lang_en.getString("messages.rank-quit")))
						.replaceAll("%player_name%", player.getDisplayName()));
			} else {
				event.setQuitMessage(null);
			}
			
			if(player.hasPotionEffect(PotionEffectType.SPEED)) {
				player.removePotionEffect(PotionEffectType.SPEED);
			}
		} else if(config.getString("language").equals("English")) {
			if(player.hasPermission("custolobby.quit") || player.isOp()) {
				event.setQuitMessage(Color.translate(PlaceholderAPI.setPlaceholders(player, lang_es.getString("messages.rank-quit")))
						.replaceAll("%player_name%", player.getDisplayName()));
			} else {
				event.setQuitMessage(null);
			}
			
			if(player.hasPotionEffect(PotionEffectType.SPEED)) {
				player.removePotionEffect(PotionEffectType.SPEED);
			}
		}
	}

}
