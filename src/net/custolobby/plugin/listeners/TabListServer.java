package net.custolobby.plugin.listeners;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class TabListServer implements Listener {
	
	private CustoLobby plugin;
	
	public TabListServer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	private boolean titlechanged = false;
	
	@EventHandler
	public void onTabList(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration tablist = plugin.getTabList();
		Player player = event.getPlayer();
		boolean isEnabled = config.getBoolean("settings.use-tablist");
		if(isEnabled) {
			PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
			new BukkitRunnable() {
				
				@Override
				public void run() {
					// TODO 
					try {
						Field a = packet.getClass().getDeclaredField("a");
	                    a.setAccessible(true);
	                    Field b = packet.getClass().getDeclaredField("b");
	                    b.setAccessible(true);

	                    List<String> header = tablist.getStringList("tablist.header");
	                    for(int i = 0 ; i < header.size(); i++) {
	                    	String header_lore = Color.translate(PlaceholderAPI.setPlaceholders(player, header.get(i)));
	                    	
	                    	Object header_list = new ChatComponentText(header_lore);
	                    	
	                    	if (titlechanged) {
		                        a.set(packet, header_list);
		                        titlechanged = false;

		                    } else {
		                        a.set(packet, header_list);
		                        titlechanged = true;
		                    }
	                    }
	                    
	                    List<String> footer_list = tablist.getStringList("tablist.footer");
	                    for(int i = 0 ; i < footer_list.size(); i++) {
	                    	String footer_lore = Color.translate(PlaceholderAPI.setPlaceholders(player, footer_list.get(i)));
	                    	
	                    	Object footer = new ChatComponentText(footer_lore);
	                    	b.set(packet, footer);
	                    }

	                    if (Bukkit.getOnlinePlayers().size() == 0) return;
	                    for (Player player : Bukkit.getOnlinePlayers()) {
	                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	                    }
					} catch(NoSuchFieldException | IllegalAccessException exception) {
						exception.printStackTrace();
					}
				}
			};
		}
	}

}
