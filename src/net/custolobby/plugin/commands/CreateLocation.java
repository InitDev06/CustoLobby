package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class CreateLocation implements CommandExecutor {

	private CustoLobby plugin;
	
	public CreateLocation(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration messages = plugin.getMessages();
		if(!(commandSender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.must-be-player")));
			return false;
			
		} else {
			Player player = (Player) commandSender;
			
			if(command.getName().equalsIgnoreCase("setlobby")) {
				if(player.hasPermission("custolobby.setlobby") || !player.isOp()) {
					FileConfiguration config = plugin.getConfig();
					
					Location location = player.getLocation();
					
					double x = location.getX();
					double y = location.getY();
					double z = location.getZ();
					
					float yaw = location.getYaw();
					float pitch = location.getPitch();
					
					String world = location.getWorld().getName();
					
					config.set("lobby-point.world", world);
					
					config.set("lobby-point.x", x);
					config.set("lobby-point.y", y);
					config.set("lobby-point.z", z);
					
					config.set("lobby-point.yaw", yaw);
					config.set("lobby-point.pitch", pitch);
					
					plugin.saveConfig();
					
					player.sendMessage(Color.translate(messages.getString("messages.lobby-setup")));
				} else {
					player.sendMessage(Color.translate(messages.getString("messages.missing-permission")));
					return true;
				}
			}
		}
		return false;
	}


}
