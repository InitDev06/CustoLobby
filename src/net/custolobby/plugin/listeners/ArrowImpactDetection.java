package net.custolobby.plugin.listeners;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowImpactDetection implements Listener {
	
	@EventHandler
	public void onBow(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			Location location = event.getEntity().getLocation();
			
			player.teleport(location);
			player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, (float) -0.3);
		}
	}
}
