package net.custolobby.plugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.utility.Permissions;

public class VanishManager implements CommandExecutor {

	ArrayList<Player> invisible_list = new ArrayList<>();
	
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
			
			if(command.getName().equalsIgnoreCase("vanish")) {
				if(player.hasPermission("custolobby.vanish") || player.isOp()) {
					if(config.getString("language").equals("English")) {
						if(invisible_list.contains(player)) {
							for(Player people : Bukkit.getOnlinePlayers()) {
								people.showPlayer(player);
							}
							invisible_list.remove(player);
							player.setPlayerListName(player.getDisplayName());
							player.sendMessage(Color.translate(lang_en.getString("messages.vanish-disable")));
						} else if(!invisible_list.contains(player)) {
							for(Player people : Bukkit.getOnlinePlayers()) {
								people.hidePlayer(player);
							}
							invisible_list.add(player);
							player.setPlayerListName(null);
							player.sendMessage(Color.translate(lang_es.getString("messages.vanish-enable")));
						}
					} else if(config.getString("language").equals("Spanish")) {
						if(invisible_list.contains(player)) {
							for(Player people : Bukkit.getOnlinePlayers()) {
								people.showPlayer(player);
							}
							invisible_list.remove(player);
							player.setPlayerListName(player.getDisplayName());
							player.sendMessage(Color.translate(lang_en.getString("messages.vanish-disable")));
						} else if(!invisible_list.contains(player)) {
							for(Player people : Bukkit.getOnlinePlayers()) {
								people.hidePlayer(player);
							}
							invisible_list.add(player);
							player.setPlayerListName(null);
							player.sendMessage(Color.translate(lang_es.getString("messages.vanish-enable")));
						}
					}
				} else {
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("vanish")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("vanish")));
					}
					return true;
				}
			}
		}
		return false;
	}

}
