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
	public void onSendCommand(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		FileConfiguration messages = CustoLobby.getInstance().getMessages();
		FileConfiguration chat = CustoLobby.getInstance().getChat();
		List<String> filter_list = chat.getStringList("filter-list");
		if(chat.getBoolean("enable-filter")) {
			for(int i=0; i < filter_list.size(); i++) {
				if(event.getMessage().equalsIgnoreCase(filter_list.get(i))) {
					List<String> filter_message = messages.getStringList("messages.blocked-words");
					for(int c=0; c < filter_message.size(); c++) {
						player.sendMessage(Color.translate(filter_message.get(i)));
						event.setCancelled(true);
					}
				}
			}
		}
	}

}
