package net.custolobby.plugin.utility;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class WordsChatFilter implements Listener {

    private CustoLobby plugin;

    public WordsChatFilter(CustoLobby plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onFilter(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        FileConfiguration messages = plugin.getMessages();
        FileConfiguration chat = plugin.getChat();
        boolean isSetTrue = chat.getBoolean("enable-filter");
        if(isSetTrue){
            List<String> filter_list = chat.getStringList("filter-list");
            for(int i = 0 ; i < filter_list.size(); i++){
                String filters = filter_list.get(i);
                if(event.getMessage().equalsIgnoreCase(filters)){
                    event.setCancelled(true);

                    List<String> filter_message = messages.getStringList("messages.blocked-words");
                    for(int c = 0 ; c < filter_message.size(); c++){
                        String filter = Color.translate(filter_message.get(c));
                        player.sendMessage(filter);
                    }
                }
            }
        }
    }
}
