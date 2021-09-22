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
	
	public CommandManager(CustoLobby plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration messages = CustoLobby.getInstance().getMessages();
		if(!(commandSender instanceof Player)) {
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
						
						Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.file-unknown")));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("info")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fversion &a" + plugin.VERSION + " &frunning on Spigot &b" + Bukkit.getBukkitVersion()));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fcreated by &bAISimple"));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("help")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fcommands:"));
						Bukkit.getConsoleSender().sendMessage(Color.translate(""));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/clobby <help> &8- &fshow this message."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/clobby <reload> &8- &freload the plugin files."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/clobby <info> &8- &fshow the plugin information."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/flight &8- &ftoggle your flight mode."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/gm &8- &fchange your gamemode."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/vanish &8- &ftoggle your invisibility mode."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/setlobby &8- &fsave the spawn location."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/lobby &8- &fteleport you to spawn."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/help &8- &fshow a help message for the users."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/nick <name> &8- &fchange your display name."));
						Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &b/tp <name> &8- &fteleport to any player."));
						return true;
					}
					
					Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.unknown-args")));
					return true;
					
				} else {
					Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.missing-permission")));
					return true;
				}
			}
			
		} else {
			Player player = (Player) commandSender;
			
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
						
						player.sendMessage(Color.translate(messages.getString("messages.file-null")));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("info")) {
						player.sendMessage(Color.translate("&8[&eCustoLobby&8] &fversion &a" + plugin.VERSION + " &frunning on Spigot &b" + Bukkit.getBukkitVersion()));
						player.sendMessage(Color.translate("&8[&eCustoLobby&8] &fcreated by &bAISimple"));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("help")) {
						player.sendMessage(Color.translate("&8[&eCustoLobby&8] &fcommands:"));
						player.sendMessage(Color.translate(""));
						player.sendMessage(Color.translate("&8- &b/clobby <help> &8- &fshow this message."));
						player.sendMessage(Color.translate("&8- &b/clobby <reload> &8- &freload the plugin files."));
						player.sendMessage(Color.translate("&8- &b/clobby <info> &8- &fshow the plugin information."));
						player.sendMessage(Color.translate("&8- &b/flight &8- &ftoggle your flight mode."));
						player.sendMessage(Color.translate("&8- &b/gm &8- &fchange your gamemode."));
						player.sendMessage(Color.translate("&8- &b/vanish &8- &ftoggle your invisibility mode."));
						player.sendMessage(Color.translate("&8- &b/setlobby &8- &fsave the spawn location."));
						player.sendMessage(Color.translate("&8- &b/lobby &8- &fteleport you to spawn."));
						player.sendMessage(Color.translate("&8- &b/help &8- &fshow a help message for the users."));
						player.sendMessage(Color.translate("&8- &b/nick <name> &8- &fchange your display name."));
						player.sendMessage(Color.translate("&8- &b/tp <name> &8- &fteleport to any player."));
						return true;
					}
					
					player.sendMessage(Color.translate(messages.getString("messages.unknown-args")));
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
