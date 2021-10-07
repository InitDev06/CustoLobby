package net.custolobby.plugin.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class HelpCommand implements CommandExecutor {

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
			
			if(command.getName().equalsIgnoreCase("help")) {
				if(config.getString("language").equals("English")) {
					List<String> msg = lang_en.getStringList("messages.help-message");
					for(int i = 0 ; i < msg.size(); i++) {
						String help = Color.translate(msg.get(i));
						player.sendMessage(help);
					}
				} else if(config.getString("language").equals("Spanish")) {
					List<String> msg = lang_es.getStringList("messages.help-message");
					for(int i = 0 ; i < msg.size(); i++) {
						String help = Color.translate(msg.get(i));
						player.sendMessage(help);
					}
				}
			}
		}
		return false;
	}

}
