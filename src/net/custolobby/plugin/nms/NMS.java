package net.custolobby.plugin.nms;

import org.bukkit.entity.Player;

public interface NMS {
	
	void sendTitle(Player player, String title, int fadeIn, int onScreen, int fadeOut);
	
}
