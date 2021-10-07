package net.custolobby.plugin.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.utility.Permissions;

public class CreateLocation implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		if(!(commandSender instanceof Player)) {
			if(config.getString("language").equals("English")) {
				Bukkit.getConsoleSender().sendMessage(Color.translate(lang_en.getString("messages.must-be-player")));
			} else if(config.getString("language").equals("English")) {
				Bukkit.getConsoleSender().sendMessage(Color.translate(lang_es.getString("messages.must-be-player")));
			}
			return true;
			
		} else {
			Player player = (Player) commandSender;
			
			if(command.getName().equalsIgnoreCase("setlobby")) {
				if(player.hasPermission("custolobby.setlobby") || player.isOp()) {
					if(config.getString("language").equals("English")) {
						Location location = player.getLocation();
						
						double x = location.getBlockX();
						double y = location.getBlockY();
						double z = location.getBlockZ();
						
						float yaw = location.getYaw();
						float pitch = location.getPitch();
						
						String world = location.getWorld().getName();
						
						try {
							config.set("lobby-point.world", world);
							config.set("lobby-point.x", x);
							config.set("lobby-point.y", y);
							config.set("lobby-point.z", z);
							config.set("lobby-point.yaw", yaw);
							config.set("lobby-point.pitch", pitch);
							config.save(CustoLobby.getCfgManager().getFile("config"));
						} catch(IOException e) {
							player.sendMessage(Color.translate("&8[&eCustoLobby&8] &cas occurred an error to try of save the file!"));
							player.sendMessage(Color.translate("&8[&eCustoLobby&8] &crevise the console for more information!"));
							e.printStackTrace();
						}
						player.sendMessage(Color.translate(lang_en.getString("messages.lobby-setup")));
					} else if(config.getString("language").equals("Spanish")) {
						Location location = player.getLocation();
						
						double x = location.getBlockX();
						double y = location.getBlockY();
						double z = location.getBlockZ();
						
						float yaw = location.getYaw();
						float pitch = location.getPitch();
						
						String world = location.getWorld().getName();
						
						try {
							config.set("lobby-point.world", world);
							config.set("lobby-point.x", x);
							config.set("lobby-point.y", y);
							config.set("lobby-point.z", z);
							config.set("lobby-point.yaw", yaw);
							config.set("lobby-point.pitch", pitch);
							config.save(CustoLobby.getCfgManager().getFile("config"));
						} catch(IOException e) {
							player.sendMessage(Color.translate("&8[&eCustoLobby&8] &cha ocurrido un error al tratar de guardar el archivo"));
							player.sendMessage(Color.translate("&8[&eCustoLobby&8] &crevise la consola para mas informacion!"));
							e.printStackTrace();
						}
						player.sendMessage(Color.translate(lang_es.getString("messages.lobby-setup")));
					}
				} else {
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("setlobby")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("setlobby")));
					}
					return true;
				}
			}
		}
		return false;
	}


}
