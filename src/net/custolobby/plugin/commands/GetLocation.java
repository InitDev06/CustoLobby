package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.utility.Permissions;

public class GetLocation implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public GetLocation(CustoLobby plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		if(!(commandSender instanceof Player)) {
			if(config.getString("language").equals("English")) {
				Bukkit.getConsoleSender().sendMessage(Color.translate(lang_en.getString("messages.must-be-player")));
			} else if(config.getString("language").equals("Spanish")) {
				Bukkit.getConsoleSender().sendMessage(Color.translate(lang_es.getString("messages.must-be-player")));
			}
			return false;
			
		} else {
			Player player = (Player) commandSender;
			
			if(command.getName().equalsIgnoreCase("lobby")) {
				if(player.hasPermission("custolobby.lobby") || player.isOp()) {
					if(config.contains("lobby-point.world")) {
						if(config.getString("language").equals("English")) {
							World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
							
							Double x = Double.valueOf(config.getInt("lobby-point.x"));
							Double y = Double.valueOf(config.getInt("lobby-point.y"));
							Double z = Double.valueOf(config.getInt("lobby-point.z"));
							
							Float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
							Float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
							
							Location location = new Location(world, x, y, z, yaw, pitch);
							
							player.teleport(location);
							player.sendMessage(Color.translate(lang_en.getString("messages.lobby-teleport")));
						} else if(config.getString("language").equals("Spanish")) {
							World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
							
							Double x = Double.valueOf(config.getInt("lobby-point.x"));
							Double y = Double.valueOf(config.getInt("lobby-point.y"));
							Double z = Double.valueOf(config.getInt("lobby-point.z"));
							
							Float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
							Float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
							
							Location location = new Location(world, x, y, z, yaw, pitch);
							
							player.teleport(location);
							player.sendMessage(Color.translate(lang_es.getString("messages.lobby-teleport")));
						}
					} else {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate(lang_en.getString("messages.lobby-unknown")));
						} else if(config.getString("language").equals("Spanish")) {
							player.sendMessage(Color.translate(lang_es.getString("messages.lobby-unknown")));
						}
						return true;
					}
				} else {
					if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("lobby")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("lobby")));
					}
					return true;
				}
			}
		}
		return false;
	}
}
