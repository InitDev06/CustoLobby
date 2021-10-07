package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.utility.Permissions;

public class TeleportManager implements CommandExecutor {

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
			
			if(command.getName().equalsIgnoreCase("tp")) {
				if(player.hasPermission("custolobby.teleport") || player.isOp()) {
					if(args.length == 0) {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate(lang_en.getString("messages.missing-target")));
						} else if(config.getString("language").equals("Spanish")) {
							player.sendMessage(Color.translate(lang_es.getString("messages.missing-target")));
						}
						return true;
					}
					
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target == null) {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate(lang_en.getString("messages.player-offline")));
						} else if(config.getString("language").equals("Spanish")) {
							player.sendMessage(Color.translate(lang_es.getString("messages.player-offline")));
						}
					} else {
						if(config.getString("language").equals("English")) {
							player.teleport(target.getLocation());
							player.sendMessage(Color.translate(lang_en.getString("messages.teleported-to-target")).replaceAll("%target_name%", 
									target.getDisplayName()));
						} else if(config.getString("language").equals("Spanish")) {
							player.teleport(target.getLocation());
							player.sendMessage(Color.translate(lang_es.getString("messages.teleported-to-target")).replaceAll("%target_name%", 
									target.getDisplayName()));
						}
						return true;
					}
				} else {
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("teleport")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("teleport")));
					}
					return true;
				}
			}
		}
		return false;
	}

}
