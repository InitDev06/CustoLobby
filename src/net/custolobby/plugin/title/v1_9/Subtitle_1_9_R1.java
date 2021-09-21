package net.custolobby.plugin.title.v1_9;

import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.custolobby.plugin.title.Subtitle;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle.EnumTitleAction;

public class Subtitle_1_9_R1 implements Subtitle {

	@Override
	public void sendSubtitle(Player player, String message, int fadeIn, int stay, int fadeOut) {
		
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, 
				ChatSerializer.a(message),fadeIn,stay,fadeOut);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
	}
	

}
