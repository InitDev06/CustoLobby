package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class NickManager implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public NickManager(CustoLobby plugin) {
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
			
			if(command.getName().equalsIgnoreCase("nick")) {
				if(player.hasPermission("custolobby.nick") || player.isOp()) {
					if(args.length == 0) {
						player.sendMessage(Color.translate(messages.getString("messages.nick-args")));
						return true;
					}
					
					String new_name = args[0];
					if(args[0].equalsIgnoreCase(new_name)) {
						player.setCustomNameVisible(true);
						player.setCustomName(new_name);
						player.setDisplayName(new_name);
						player.setPlayerListName(new_name);
						player.sendMessage(Color.translate(messages.getString("messages.new-nick")).replaceAll("%new_nick%", new_name));
						return true;
					}
					
				} else {
					player.sendMessage(Color.translate(messages.getString("messages.missing-permission")).replaceAll("%permission%", 
							"custolobby.nick"));
					return true;
				}
			}
		}
		return false;
	}

}
