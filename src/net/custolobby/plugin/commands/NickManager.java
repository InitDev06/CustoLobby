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

public class NickManager implements CommandExecutor {

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
			
			if(command.getName().equalsIgnoreCase("nick")) {
				if(player.hasPermission("custolobby.nick") || player.isOp()) {
					if(args.length == 0) {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate(lang_en.getString("messages.nick-args")));
						} else if(config.getString("language").equals("Spanish")) {	
							player.sendMessage(Color.translate(lang_es.getString("messages.nick-args")));
						}
						return true;
					}
					
					String new_name = args[0];
					if(args[0].equals(new_name)) {
						if(config.getString("language").equals("English")) {
							player.setCustomNameVisible(true);
							player.setCustomName(new_name);
							player.setDisplayName(new_name);
							player.setPlayerListName(new_name);
							player.sendMessage(Color.translate(lang_en.getString("messages.new-nick")).replaceAll("%new_nick%", new_name));
						} else if(config.getString("language").equals("Spanish")) {
							player.setCustomNameVisible(true);
							player.setCustomName(new_name);
							player.setDisplayName(new_name);
							player.setPlayerListName(new_name);
							player.sendMessage(Color.translate(lang_es.getString("messages.new-nick")).replaceAll("%new_nick%", new_name));
						}
						return true;
					}
					
					if(args[0].equals(player.getName())) {
						if(config.getString("language").equals("English")) {
							player.setCustomNameVisible(false);
							player.setDisplayName(player.getName());
							player.setPlayerListName(player.getName());
							player.sendMessage(Color.translate(lang_en.getString("messages.reset-nick")));
						} else if(config.getString("language").equals("Spanish")) {
							player.setCustomNameVisible(false);
							player.setDisplayName(player.getName());
							player.setPlayerListName(player.getName());
							player.sendMessage(Color.translate(lang_es.getString("messages.reset-nick")));
						}
						return true;
					}
					
				} else {
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("nick")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("nick")));
					}
					return true;
				}
			}
		}
		return false;
	}

}
