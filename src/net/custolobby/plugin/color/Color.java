package net.custolobby.plugin.color;

import org.bukkit.ChatColor;

public class Color {
	
	public static String translate(String in) {
		String out = ChatColor.translateAlternateColorCodes('&', in);
		return out;
	}

}
