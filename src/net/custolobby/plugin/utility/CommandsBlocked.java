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
		FileConfiguration messages = CustoLobby.getInstance().getMessages();
		FileConfiguration chat = CustoLobby.getInstance().getChat();
		List<String> blocked_list = chat.getStringList("blacklist-cmds");
		for(int i=0; i < blocked_list.size(); i++) {
			if(chat.getBoolean("enable-blacklist")) {
				if(event.getMessage().equalsIgnoreCase(blocked_list.get(i))) {
					List<String> blocked_message = messages.getStringList("messages.blocked-command");
					for(int c=0; c < blocked_message.size(); c++) {
						player.sendMessage(Color.translate(blocked_message.get(i)));
						event.setCancelled(true);
					}
				}
			}
		}
	}

}
