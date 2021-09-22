package net.custolobby.plugin.title;

import org.bukkit.entity.Player;

import com.connorlinfoot.titleapi.TitleAPI;

public class Titles {
	
	@SuppressWarnings("deprecation")
	public static void buildTitle(Player player, int fadeIn, int stay, int fadeOut, String message1, String message2) {
		TitleAPI.sendFullTitle(player, fadeIn, stay, fadeOut, message1, message2);
	}

}
