package net.custolobby.plugin.listeners;

import org.bukkit.GameMode;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.custolobby.plugin.CustoLobby;

public class PlayerSettings implements Listener {
	
	private CustoLobby plugin;
	
	public PlayerSettings(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setLevel(0);
		player.setExp(0);
		player.setGameMode(GameMode.ADVENTURE);
		
		if(config.getBoolean("give-speed")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0));
		}
		
	}
	
}