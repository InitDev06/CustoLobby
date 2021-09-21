package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class CommandManager implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public CommandManager(CustoLobby plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration messages = plugin.getMessages();
		if(!(sender instanceof Player)) {
			if(command.getName().equalsIgnoreCase("custolobby")) {
				if(Bukkit.getConsoleSender().hasPermission("custolobby.manager") || Bukkit.getConsoleSender().isOp()) {
					if(args.length == 0) {
						Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.args")));
						return true;
					}
					if(args[0].equalsIgnoreCase("reload")) {
						if(args.length == 1) {
							Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.reload")));
							return true;
						}
						if(args[1].equalsIgnoreCase("config")) {
							plugin.reloadConfig();
							Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.config")));
							return true;
						}
						if(args[1].equalsIgnoreCase("messages")) {
							plugin.reloadMessages();
							Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.messages")));
							return true;
						}
						if(args[1].equalsIgnoreCase("chat")) {
							plugin.reloadChat();
							Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.chat")));
							return true;
						}
						if(args[1].equalsIgnoreCase("scoreboard")) {
							plugin.reloadScoreboard();
							Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.scoreboard")));
							return true;
						}
						Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.file-unknown")));
						return true;
					}
					if(args[0].equalsIgnoreCase("version")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fVersion &a" + plugin.VERSION + " &frunning on Spigot &b" + Bukkit.getBukkitVersion()));
						return true;
					}
					Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.unknown-args")));
					return true;
				}
			}
		
		} else {
			Player player = (Player) sender;
			
			if(command.getName().equalsIgnoreCase("custolobby")) {
				if(player.hasPermission("custolobby.manager") || player.isOp()) {
					if(args.length == 0) {
						player.sendMessage(Color.translate(messages.getString("messages.args")));
						return true;
					}
					if(args[0].equalsIgnoreCase("reload")) {
						if(args.length == 1) {
							player.sendMessage(Color.translate(messages.getString("messages.reload")));
							return true;
						}
						if(args[1].equalsIgnoreCase("config")) {
							plugin.reloadConfig();
							player.sendMessage(Color.translate(messages.getString("messages.config")));
							return true;
						}
						if(args[1].equalsIgnoreCase("messages")) {
							plugin.reloadMessages();
							player.sendMessage(Color.translate(messages.getString("messages.messages")));
							return true;
						}
						if(args[1].equalsIgnoreCase("chat")) {
							plugin.reloadChat();
							player.sendMessage(Color.translate(messages.getString("messages.chat")));
							return true;
						}
						if(args[1].equalsIgnoreCase("scoreboard")) {
							plugin.reloadScoreboard();
							player.sendMessage(Color.translate(messages.getString("messages.scoreboard")));
							return true;
						}
						player.sendMessage(Color.translate(messages.getString("messages.file-unknown")));
						return true;
					}
					if(args[0].equalsIgnoreCase("version")) {
						player.sendMessage(Color.translate(Color.translate("&8[&eCustoLobby&8] &fVersion &a" + plugin.VERSION + " &frunning on Spigot &b" + Bukkit.getBukkitVersion())));
						return true;
					}
					player.sendMessage(Color.translate(messages.getString("messages.unknown-args")));
					return true;
				}
			}
		}
		return false;
	}

}
