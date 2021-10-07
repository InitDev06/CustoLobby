package net.custolobby.plugin.utility;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class ChatFilter implements Listener {
	
	@EventHandler
	public void onWords(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration chat = CustoLobby.getChatFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		if(config.getString("language").equals("English")) {
			if(chat.getBoolean("enable-filter")) {
				if(player.hasPermission("custolobby.filter.bypass") || player.isOp()) {
					return;
				}
				List<String> filter_list = chat.getStringList("filter-list");
	            for(int i = 0 ; i < filter_list.size(); i++){
	                String filters = filter_list.get(i);
	                if(event.getMessage().equalsIgnoreCase(filters)){
	                    event.setCancelled(true);

	                    List<String> filter_message = lang_en.getStringList("messages.blocked-words");
	                    for(int c = 0 ; c < filter_message.size(); c++){
	                        String filter = Color.translate(filter_message.get(c));
	                        player.sendMessage(filter);
	                    }
	                }
	            }
			}
		} else if(config.getString("language").equals("Spanish")) {
			if(chat.getBoolean("enable-filter")) {
				if(player.hasPermission("custolobby.filter.bypass") || player.isOp()) {
					return;
				}
				List<String> filter_list = chat.getStringList("filter-list");
	            for(int i = 0 ; i < filter_list.size(); i++){
	                String filters = filter_list.get(i);
	                if(event.getMessage().equalsIgnoreCase(filters)){
	                    event.setCancelled(true);

	                    List<String> filter_message = lang_es.getStringList("messages.blocked-words");
	                    for(int c = 0 ; c < filter_message.size(); c++){
	                        String filter = Color.translate(filter_message.get(c));
	                        player.sendMessage(filter);
	                    }
	                }
	            }
			}
		}
	}

}
