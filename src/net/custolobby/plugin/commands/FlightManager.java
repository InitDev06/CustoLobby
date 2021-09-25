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

public class FlightManager implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public FlightManager(CustoLobby plugin) {
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
			
			if(command.getName().equalsIgnoreCase("fly")) {
				if(!player.hasPermission("custolobby.fly") || player.isOp()) {
					player.sendMessage(Color.translate(messages.getString("messages.missing-permission")).replaceAll("%permission%", 
							"custolobby.fly"));
				} else {
					if(player.getAllowFlight()) {
						player.setAllowFlight(false);
						player.sendMessage(Color.translate(messages.getString("messages.flight-disable")));
						player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, (float) -0.2);
					} else if(!player.getAllowFlight()) {
						player.setAllowFlight(true);
						player.sendMessage(Color.translate(messages.getString("messages.flight-enable")));
						player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, (float) -0.2);
					}
					return true;
				}
			}
		}
		return false;
	}

}
