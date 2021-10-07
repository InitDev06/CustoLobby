package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.utility.Permissions;

public class FlightManager implements CommandExecutor {

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
			
			if(command.getName().equalsIgnoreCase("fly")) {
				if(!player.hasPermission("custolobby.fly") || player.isOp()) {
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("fly")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", 
								Permissions.permission("fly")));
					}
				} else {
					if(config.getString("language").equals("English")) {
						if(player.getAllowFlight()) {
							player.setAllowFlight(false);
							player.sendMessage(Color.translate(lang_en.getString("messages.flight-disable")));
							player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
						} else if(!player.getAllowFlight()) {
							player.setAllowFlight(true);
							player.sendMessage(Color.translate(lang_en.getString("messages.flight-enable")));
							player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
						}
					} else if(config.getString("language").equals("Spanish")) {
						if(player.getAllowFlight()) {
							player.setAllowFlight(false);
							player.sendMessage(Color.translate(lang_es.getString("messages.flight-disable")));
							player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
						} else if(!player.getAllowFlight()) {
							player.setAllowFlight(true);
							player.sendMessage(Color.translate(lang_es.getString("messages.flight-enable")));
							player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
						}
					}
					return true;
				}
				
				if(!player.hasPermission("custolobby.fly.other") || player.isOp()) {
					if(config.getString("language").equals("English")) {
						if(args.length == 1) {
							player.sendMessage(Color.translate(lang_en.getString("messages.missing-target")));
							return true;
						}
						
						Player target = Bukkit.getServer().getPlayer(args[1]);
						if(target == null) {
							player.sendMessage(Color.translate(lang_en.getString("messages.player-offline")));
						} else {
							if(target.getAllowFlight()) {
								target.setAllowFlight(false);
								target.sendMessage(Color.translate(lang_en.getString("messages.flight-disable")));
								target.playSound(target.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
								player.sendMessage(Color.translate(lang_en.getString("messages.flight-disable-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
							} else if(!target.getAllowFlight()) {
								target.setAllowFlight(true);
								target.sendMessage(Color.translate(lang_en.getString("messages.flight-enable")));
								target.playSound(target.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
								player.sendMessage(Color.translate(lang_en.getString("messages.flight-enable-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
							}
							return true;
						}
					} else if(config.getString("language").equals("Spanish")) {
						if(args.length == 1) {
							player.sendMessage(Color.translate(lang_es.getString("messages.missing-target")));
							return true;
						}
						
						Player target = Bukkit.getServer().getPlayer(args[1]);
						if(target == null) {
							player.sendMessage(Color.translate(lang_es.getString("messages.player-offline")));
						} else {
							if(target.getAllowFlight()) {
								target.setAllowFlight(false);
								target.sendMessage(Color.translate(lang_es.getString("messages.flight-disable")));
								target.playSound(target.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
								player.sendMessage(Color.translate(lang_es.getString("messages.flight-disable-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
							} else if(!target.getAllowFlight()) {
								target.setAllowFlight(true);
								target.sendMessage(Color.translate(lang_es.getString("messages.flight-enable")));
								target.playSound(target.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 0);
								player.sendMessage(Color.translate(lang_es.getString("messages.flight-enable-other")).replaceAll("%player_name%", 
										player.getDisplayName()));
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
