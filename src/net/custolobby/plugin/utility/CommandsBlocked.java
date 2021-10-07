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
	
	@EventHandler
	public void onSendCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		FileConfiguration chat = CustoLobby.getChatFile();
		if(config.getString("language").equals("English")) {
			if(chat.getBoolean("enable-blacklist")) {
				if(player.hasPermission("custolobby.blacklist.bypass") || player.isOp()) {
					return;
				}
				List<String> blacklist = chat.getStringList("blacklist-cmds");
	            for(int i = 0 ; i < blacklist.size(); i++){
	                String blocked_cmds = blacklist.get(i);
	                if(event.getMessage().equalsIgnoreCase(blocked_cmds)){
	                    event.setCancelled(true);

	                    List<String> blocked_message = lang_en.getStringList("messages.blocked-command");
	                    for(int c = 0 ; c < blocked_message.size(); c++){
	                        String blocked = Color.translate(blocked_message.get(c));
	                        player.sendMessage(blocked);
	                    }
	                }
	            }
			}
		} else if(config.getString("language").equals("Spanish")) {
			if(chat.getBoolean("enable-blacklist")) {
				if(player.hasPermission("custolobby.blacklist.bypass") || player.isOp()) {
					return;
				}
				List<String> blacklist = chat.getStringList("blacklist-cmds");
	            for(int i = 0 ; i < blacklist.size(); i++){
	                String blocked_cmds = blacklist.get(i);
	                if(event.getMessage().equalsIgnoreCase(blocked_cmds)){
	                    event.setCancelled(true);

	                    List<String> blocked_message = lang_es.getStringList("messages.blocked-command");
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
