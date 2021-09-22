package net.custolobby.plugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class VanishManager implements CommandExecutor {
	
	private CustoLobby plugin;
	
	public VanishManager(CustoLobby plugin) {
		this.plugin = plugin;
	}

	ArrayList<Player> invisible_list = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration messages = plugin.getMessages();
		if(!(commandSender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Color.translate(messages.getString("messages.must-be-player")));
			return false;
			
		} else {
			Player player = (Player) commandSender;
			
			if(command.getName().equalsIgnoreCase("vanish")) {
				if(player.hasPermission("custolobby.vanish") || player.isOp()) {
					if(invisible_list.contains(player)) {
						for(Player people : Bukkit.getOnlinePlayers()) {
							people.showPlayer(player);
						}
						invisible_list.remove(player);
						player.sendMessage(Color.translate(messages.getString("messages.vanish-disable")));
					} else if(!invisible_list.contains(player)) {
						for(Player people : Bukkit.getOnlinePlayers()) {
							people.hidePlayer(player);
						}
						invisible_list.add(player);
						player.sendMessage(Color.translate(messages.getString("messages.vanish-enable")));
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
