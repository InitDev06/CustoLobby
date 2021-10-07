package net.custolobby.plugin.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class WorldProtection implements Listener {
	
	private CustoLobby plugin;
	
	public WorldProtection(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		Player player = event.getPlayer();
		boolean disableBuild = config.getBoolean("disable-build");
		if(config.getString("language").equals("English")) {
			if(disableBuild == true && player.hasPermission("custolobby.build") || player.isOp()) {
				return;
			}
			player.sendMessage(Color.translate(lang_en.getString("messages.not-build")));
			event.setCancelled(true);
		} else if(config.getString("language").equals("Spanish")) {
			if(disableBuild == true && player.hasPermission("custolobby.build") || player.isOp()) {
				return;
			}
			player.sendMessage(Color.translate(lang_es.getString("messages.not-build")));
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		Player player = event.getPlayer();
		boolean disableBuild = config.getBoolean("disable-build");
		if(config.getString("language").equals("English")) {
			if(disableBuild == true && player.hasPermission("custolobby.build") || player.isOp()) {
				return;
			}
			player.sendMessage(Color.translate(lang_en.getString("messages.not-break")));
			event.setCancelled(true);
		} else if(config.getString("language").equals("Spanish")) {
			if(disableBuild == true && player.hasPermission("custolobby.build") || player.isOp()) {
				return;
			}
			player.sendMessage(Color.translate(lang_es.getString("messages.not-break")));
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamaged(EntityDamageEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		if(config.getBoolean("disable-damage") && event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.
				DamageCause.ENTITY_ATTACK || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || event.getCause() == 
				EntityDamageEvent.DamageCause.SUFFOCATION || event.getCause() == EntityDamageEvent.DamageCause.SUICIDE || event.getCause() == 
				EntityDamageEvent.DamageCause.FALLING_BLOCK || event.getCause() == EntityDamageEvent.DamageCause.FIRE || event.getCause() == 
				EntityDamageEvent.DamageCause.PROJECTILE) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		if(config.getBoolean("disable-hunger")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		if(config.getBoolean("disable-weather")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSpawnEntities(EntitySpawnEvent event) {
		FileConfiguration config = CustoLobby.getConfigFile();
		if(config.getBoolean("disable-entities")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		FileConfiguration config = CustoLobby.getConfigFile();
		Double x = Double.valueOf(config.getInt("lobby-point.x"));
		Double y = Double.valueOf(config.getInt("lobby-point.y"));
		Double z = Double.valueOf(config.getInt("lobby-point.z"));
		
		Float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
		Float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
		
		World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
		
		Location location = new Location(world, x, y, z, yaw, pitch);
		if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
			entity.setFallDistance(0.0F);
			
			if(world == null) {
				return;
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> entity.teleport(location), 3L);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void evitDamageByEnder(EntityDamageEvent event) {
		if(event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
			event.setCancelled(true);
		}
	}
}
