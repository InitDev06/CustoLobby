package net.custolobby.plugin.listeners;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ServerNetworks implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
        Player player = event.getPlayer();
        if(config.getString("language").equals("English")) {
        	if(config.getBoolean("send-networks")){
                String name1 = Color.translate(lang_en.getString("messages.social-networks.1.name"));
                String link1 = lang_en.getString("messages.social-networks.1.link");
                String description1 = Color.translate(lang_en.getString("messages.social-networks.1.description"));

                String name2 = Color.translate(lang_en.getString("messages.social-networks.2.name"));
                String link2 = lang_en.getString("messages.social-networks.2.link");
                String description2 = Color.translate(lang_en.getString("messages.social-networks.2.description"));

                String name3 = Color.translate(lang_en.getString("messages.social-networks.3.name"));
                String link3 = lang_en.getString("messages.social-networks.3.link");
                String description3 = Color.translate(lang_en.getString("messages.social-networks.3.description"));

                String name4 = Color.translate(lang_en.getString("messages.social-networks.4.name"));
                String link4 = lang_en.getString("messages.social-networks.4.link");
                String description4 = Color.translate(lang_en.getString("messages.social-networks.4.description"));

                TextComponent BUTTON1 = new TextComponent(name1);
                BUTTON1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link1));
                BUTTON1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description1).create()));

                TextComponent BUTTON2 = new TextComponent(name2);
                BUTTON2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link2));
                BUTTON2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description2).create()));

                TextComponent BUTTON3 = new TextComponent(name3);
                BUTTON3.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link3));
                BUTTON3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description3).create()));

                TextComponent BUTTON4 = new TextComponent(name4);
                BUTTON4.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link4));
                BUTTON4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description4).create()));

                BUTTON1.addExtra(BUTTON2);
                BUTTON1.addExtra(BUTTON3);
                BUTTON1.addExtra(BUTTON4);

                player.spigot().sendMessage(BUTTON1);
            } else if(config.getString("language").equals("Spanish")) {
            	String name1 = Color.translate(lang_es.getString("messages.social-networks.1.name"));
                String link1 = lang_es.getString("messages.social-networks.1.link");
                String description1 = Color.translate(lang_es.getString("messages.social-networks.1.description"));

                String name2 = Color.translate(lang_es.getString("messages.social-networks.2.name"));
                String link2 = lang_es.getString("messages.social-networks.2.link");
                String description2 = Color.translate(lang_es.getString("messages.social-networks.2.description"));

                String name3 = Color.translate(lang_es.getString("messages.social-networks.3.name"));
                String link3 = lang_es.getString("messages.social-networks.3.link");
                String description3 = Color.translate(lang_es.getString("messages.social-networks.3.description"));

                String name4 = Color.translate(lang_es.getString("messages.social-networks.4.name"));
                String link4 = lang_es.getString("messages.social-networks.4.link");
                String description4 = Color.translate(lang_es.getString("messages.social-networks.4.description"));

                TextComponent BUTTON1 = new TextComponent(name1);
                BUTTON1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link1));
                BUTTON1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description1).create()));

                TextComponent BUTTON2 = new TextComponent(name2);
                BUTTON2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link2));
                BUTTON2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description2).create()));

                TextComponent BUTTON3 = new TextComponent(name3);
                BUTTON3.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link3));
                BUTTON3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description3).create()));

                TextComponent BUTTON4 = new TextComponent(name4);
                BUTTON4.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link4));
                BUTTON4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(description4).create()));

                BUTTON1.addExtra(BUTTON2);
                BUTTON1.addExtra(BUTTON3);
                BUTTON1.addExtra(BUTTON4);

                player.spigot().sendMessage(BUTTON1);
            }
        } 
    }
}
