package net.custolobby.plugin.utility;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import net.custolobby.plugin.CustoLobby;

public class FireworkBuilder implements Listener {
	
	private CustoLobby plugin;
	
	public FireworkBuilder(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void build(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		int power = config.getInt("firework-power");
		if(config.getBoolean("use-fireworks")) {
			Firework firework = (Firework) player.getLocation().getWorld().spawn(player.getLocation(), Firework.class);
			FireworkMeta fireworkMeta = firework.getFireworkMeta();
			fireworkMeta.addEffect(FireworkEffect.builder()
					.flicker(false)
					.trail(true)
					.with(FireworkEffect.Type.CREEPER)
					.withColor(Color.YELLOW)
					.withColor(Color.BLUE)
					.build());
			
			fireworkMeta.setPower(power);
			firework.setFireworkMeta(fireworkMeta);
		}
	}

}
