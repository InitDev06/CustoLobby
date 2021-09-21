package net.custolobby.plugin.title.v1_10;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.custolobby.plugin.title.Title;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle.EnumTitleAction;

public class Subtitle_1_10_R1 implements Title {

	@Override
	public void sendTitle(Player player, String message, int fadeIn, int stay, int fadeOut) {

		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, 
				ChatSerializer.a(message),fadeIn,stay,fadeOut);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
	}

}
