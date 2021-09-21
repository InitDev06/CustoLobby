package net.custolobby.plugin.title;

import org.bukkit.entity.Player;

public interface Title {
	
	public void sendTitle(Player player, String message, int fadeIn, int stay, int fadeOut);

}
