package net.custolobby.plugin.title.v_1_12;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.custolobby.plugin.title.Title;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;

public class Title_1_12_R1 implements Title {
	
	@Override
	public void sendTitle(Player player, String message, int fadeIn, int stay, int fadeOut) {

		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, 
				ChatSerializer.a(message),fadeIn,stay,fadeOut);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
	}

}
