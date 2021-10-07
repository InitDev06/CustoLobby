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
			
			if(command.getName().equalsIgnoreCase("gm")) {
				if(config.getString("language").equals("English")) {
					if(player.hasPermission("custolobby.gamemode") || player.isOp()) {
						if(args.length == 0) {
							player.sendMessage(Color.translate(lang_en.getString("messages.gm-usage")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(Color.translate(lang_en.getString("messages.gm-survival")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(Color.translate(lang_en.getString("messages.gm-creative")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(Color.translate(lang_en.getString("messages.gm-adventure")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
							player.setGameMode(GameMode.SPECTATOR);
							player.sendMessage(Color.translate(lang_en.getString("messages.gm-spectator")));
							return true;
						}
						
						player.sendMessage(Color.translate(lang_en.getString("messages.gm-null")));
						return true;
						
					} else if(player.hasPermission("custolobby.gamemode.other") || player.isOp()) {
						if(args.length == 1) {
							player.sendMessage(Color.translate(lang_en.getString("messages.missing-target")));
							return true;
						}
						
						Player target = Bukkit.getServer().getPlayer(args[1]);
						if(target == null) {
							player.sendMessage(Color.translate(lang_en.getString("messages.player-offline")));
						} else {
							if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
								target.setGameMode(GameMode.SURVIVAL);
								target.sendMessage(Color.translate(lang_en.getString("messages.gm-survival")));
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-survival-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
								player.setGameMode(GameMode.CREATIVE);
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-creative")));
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-creative-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
								player.setGameMode(GameMode.ADVENTURE);
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-adventure")));
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-adventure-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
								player.setGameMode(GameMode.SPECTATOR);
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-spectator")));
								player.sendMessage(Color.translate(lang_en.getString("messages.gm-spectator-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							player.sendMessage(Color.translate(lang_en.getString("messages.gm-null")));
							return true;
						}
					} else {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")));
						return true;
					}
				} else if(config.getString("language").equals("Spanish")) {
					if(player.hasPermission("custolobby.gamemode") || player.isOp()) {
						if(args.length == 0) {
							player.sendMessage(Color.translate(lang_es.getString("messages.gm-usage")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(Color.translate(lang_es.getString("messages.gm-survival")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(Color.translate(lang_es.getString("messages.gm-creative")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(Color.translate(lang_es.getString("messages.gm-adventure")));
							return true;
						}
						
						if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
							player.setGameMode(GameMode.SPECTATOR);
							player.sendMessage(Color.translate(lang_es.getString("messages.gm-spectator")));
							return true;
						}
						
						player.sendMessage(Color.translate(lang_en.getString("messages.gm-null")));
						return true;
						
					} else if(player.hasPermission("custolobby.gamemode.other") || player.isOp()) {
						if(args.length == 1) {
							player.sendMessage(Color.translate(lang_es.getString("messages.missing-target")));
							return true;
						}
						
						Player target = Bukkit.getServer().getPlayer(args[1]);
						if(target == null) {
							player.sendMessage(Color.translate(lang_en.getString("messages.player-offline")));
						} else {
							if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
								target.setGameMode(GameMode.SURVIVAL);
								target.sendMessage(Color.translate(lang_es.getString("messages.gm-survival")));
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-survival-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
								player.setGameMode(GameMode.CREATIVE);
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-creative")));
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-creative-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
								player.setGameMode(GameMode.ADVENTURE);
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-adventure")));
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-adventure-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
								player.setGameMode(GameMode.SPECTATOR);
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-spectator")));
								player.sendMessage(Color.translate(lang_es.getString("messages.gm-spectator-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
								return true;
							}
							
							player.sendMessage(Color.translate(lang_es.getString("messages.gm-null")));
							return true;
						}
					} else {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")));
						return true;
					}
				}
			}
		return false;
		}
	
	}
}
