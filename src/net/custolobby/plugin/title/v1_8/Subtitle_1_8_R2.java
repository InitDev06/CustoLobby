package net.custolobby.plugin.title.v1_8;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.custolobby.plugin.title.Subtitle;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle.EnumTitleAction;

public class Subtitle_1_8_R2 implements Subtitle {

	@Override
	public void sendSubtitle(Player player, String message, int fadeIn, int stay, int fadeOut) {
		
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, 
				ChatSerializer.a(message),fadeIn,stay,fadeOut);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
	}
	

}
