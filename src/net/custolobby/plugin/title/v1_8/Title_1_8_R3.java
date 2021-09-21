package net.custolobby.plugin.title.v1_8;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.custolobby.plugin.title.Title;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Title_1_8_R3 implements Title {

	@Override
	public void sendTitle(Player player, String message, int fadeIn, int stay, int fadeOut) {

		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, 
				ChatSerializer.a(message),fadeIn,stay,fadeOut);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
	}

}
