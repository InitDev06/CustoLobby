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

public class GetLocation implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public GetLocation(CustoLobby plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration messages = plugin.getMessages();
		FileConfiguration config = plugin.getConfig();
		if(!(commandSender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.must-be-player")));
			return false;
			
		} else {
			Player player = (Player) commandSender;
			
			if(command.getName().equalsIgnoreCase("lobby")) {
				if(player.hasPermission("custolobby.lobby") || player.isOp()) {
					if(!config.contains("lobby-point.world")) {
						player.sendMessage(Color.translate(messages.getString("messages.lobby-unknown")));
					} else {
						Double x = Double.valueOf(config.getInt("lobby-point.x"));
						Double y = Double.valueOf(config.getInt("lobby-point.y"));
						Double z = Double.valueOf(config.getInt("lobby-point.z"));
						
						Float yaw = Float.valueOf(config.getInt("lobby-point.yaw"));
						Float pitch = Float.valueOf(config.getInt("lobby-point.pitch"));
						
						World world = plugin.getServer().getWorld(config.getString("lobby-point.world"));
						
						Location location = new Location(world, x, y, z, yaw, pitch);
						
						player.teleport(location);
						player.sendMessage(Color.translate(messages.getString("messages.lobby-teleport")));
						return true;
					}
				} else {
					player.sendMessage(Color.translate(messages.getString("messages.missing-permission")));
					return true;
				}
			}
		}
		return false;
	}
}
