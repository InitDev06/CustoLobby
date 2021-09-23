package net.custolobby.plugin.utility;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import net.custolobby.plugin.CustoLobby;

public class WorldProtection implements Listener {
	
	private CustoLobby plugin;
	
	public WorldProtection(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		FileConfiguration config = plugin.getConfig();
		Player player = event.getPlayer();
		if(config.getBoolean("disable-build")) {
			event.setCancelled(true);
		} else if(player.hasPermission("custolobby.build") || player.isOp()) {
			event.setCancelled(false);
			event.setBuild(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		FileConfiguration config = plugin.getConfig();
		Player player = event.getPlayer();
		if(config.getBoolean("disable-build")) {
			event.setCancelled(true);
		} else if(player.hasPermission("custolobby.build") || player.isOp()) {
			event.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onDamaged(EntityDamageEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-damage")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void damagedByEntity(EntityDamageByEntityEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-damage")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void damagedByBlock(EntityDamageByBlockEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-damage")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-hunger")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-weather")) {
			World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
			
			event.setCancelled(true);
			world.setThundering(false);
			world.setWeatherDuration(0);
		}
	}
	
	@EventHandler
	public void onSpawnEntities(EntitySpawnEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-entities")) {
			World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
			
			event.setCancelled(true);
			world.setAnimalSpawnLimit(0);
			world.setMonsterSpawnLimit(0);
			world.setAmbientSpawnLimit(0);
		}
	}
	
	@EventHandler
	public void onFall(PlayerMoveEvent event) {
		FileConfiguration config = plugin.getConfig();
		Double x = Double.valueOf(config.getInt("lobby-point.x"));
		Double y = Double.valueOf(config.getInt("lobby-point.y"));
		Double z = Double.valueOf(config.getInt("lobby-point.z"));
		
		Float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
		Float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
		
		World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
		
		Location location = new Location(world, x, y, z, yaw, pitch);
		if(event.getTo().getY() < 0) {
			if(config.contains("lobby-point.world")) {
				event.getPlayer().teleport(location);
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		FileConfiguration config = plugin.getConfig();
		if(config.getBoolean("disable-interact")) {
			event.setCancelled(true);
		}
	}
	
}
