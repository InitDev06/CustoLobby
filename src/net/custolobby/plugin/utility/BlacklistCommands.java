package net.custolobby.plugin.utility;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class BlacklistCommands implements Listener {

    private CustoLobby plugin;

    public BlacklistCommands(CustoLobby plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlacklist(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        FileConfiguration messages = plugin.getMessages();
        FileConfiguration chat = plugin.getChat();
        boolean isSetTrue = chat.getBoolean("enable-blacklist");
        if(isSetTrue){
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
