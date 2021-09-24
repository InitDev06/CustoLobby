package net.custolobby.plugin.utility;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class CommandsBlocked implements Listener {
	
	private CustoLobby plugin;
	
	public CommandsBlocked(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSendCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		FileConfiguration messages = plugin.getMessages();
		FileConfiguration chat = plugin.getChat();
		if(chat.getBoolean("enable-blacklist")) {
			if(!player.hasPermission("custolobby.blacklist.bypass") || !player.isOp()) {
				List<String> blacklist = chat.getStringList("blacklist-cmds");
	            for(int i = 0 ; i < blacklist.size(); i++){
	                String blocked_cmds = blacklist.get(i);
	                if(event.getMessage().equalsIgnoreCase(blocked_cmds)){
	                    event.setCancelled(true);

	                    List<String> blocked_message = messages.getStringList("messages.blocked-command");
	                    for(int c = 0 ; c < blocked_message.size(); c++){
	                        String blocked = Color.translate(blocked_message.get(c));
	                        player.sendMessage(blocked);
	                    }
	                }
	            }
			}
		}
	}

}
