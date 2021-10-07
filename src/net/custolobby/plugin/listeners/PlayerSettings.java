package net.custolobby.plugin.listeners;

import org.bukkit.GameMode;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

import net.custolobby.plugin.CustoLobby;

public class PlayerSettings implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = CustoLobby.getConfigFile();
		
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setLevel(0);
		player.setExp(0);
		player.setGameMode(GameMode.ADVENTURE);
		
		if(config.getBoolean("give-speed")) {
			if(!player.hasPotionEffect(PotionEffectType.SPEED)) {
				player.addPotionEffect(PotionEffectType.SPEED.createEffect(1000000, 0));
			}
		}
		
	}
	
}