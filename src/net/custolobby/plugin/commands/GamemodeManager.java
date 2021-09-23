package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class GamemodeManager implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public GamemodeManager(CustoLobby plugin) {
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
			
			if(command.getName().equalsIgnoreCase("gm")) {
				if(player.hasPermission("custolobby.gm") || player.isOp()) {
					if(args.length == 0) {
						player.sendMessage(Color.translate(messages.getString("messages.gm-usage")));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("survival") || args[0].equals("0")) {
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage(Color.translate(messages.getString("messages.gm-survival")));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("creative") || args[0].equals("1")) {
						player.setGameMode(GameMode.CREATIVE);
						player.sendMessage(Color.translate(messages.getString("messages.gm-creative")));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("adventure") || args[0].equals("2")) {
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage(Color.translate(messages.getString("messages.gm-adventure")));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("spectator") || args[0].equals("3")) {
						player.setGameMode(GameMode.SPECTATOR);
						player.sendMessage(Color.translate(messages.getString("messages.gm-spectator")));
						return true;
					}
					
					player.sendMessage(Color.translate(messages.getString("messages.gm-null")));
					return true;
					
				} else {
					player.sendMessage(Color.translate(messages.getString("messages.missing-permission")));
					return true;
				}
			}
		}
		return false;
	}

}
