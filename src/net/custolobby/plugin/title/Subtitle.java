package net.custolobby.plugin.title;

import org.bukkit.entity.Player;

public interface Subtitle {
	
	public void sendSubtitle(Player player, String message, int fadeIn, int stay, int fadeOut); 

}
