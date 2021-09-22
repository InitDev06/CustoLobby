package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class TeleportManager implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public TeleportManager(CustoLobby plugin) {
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
			
			if(command.getName().equalsIgnoreCase("tp")) {
				if(player.hasPermission("custolobby.teleport") || player.isOp()) {
					if(args.length == 0) {
						player.sendMessage(Color.translate(messages.getString("messages.missing-target")));
						return true;
					}
					
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target == null) {
						player.sendMessage(Color.translate(messages.getString("messages.player-offline")));
					} else {
						player.teleport(target.getLocation());
						player.sendMessage(Color.translate(messages.getString("messages.teleported-to-target")).replaceAll("%target_name%", target.getName()));
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
