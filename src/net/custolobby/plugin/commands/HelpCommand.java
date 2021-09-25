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
	
	private CustoLobby plugin;
	
	public HelpCommand(CustoLobby plugin) {
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
			
			if(command.getName().equalsIgnoreCase("help")) {
				List<String> lore = messages.getStringList("messages.help-message");
				for(int i = 0 ; i < lore.size(); i++) {
					String help = Color.translate(lore.get(i));
					
					player.sendMessage(help);
				}
			}
		}
		return false;
	}

}
