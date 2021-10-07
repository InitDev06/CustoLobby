package net.custolobby.plugin.nms.nms_versions.v1_9;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.custolobby.plugin.nms.NMS;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle.EnumTitleAction;

public class NMS_v1_9_R2 implements NMS {

	@Override
	public void sendTitle(Player player, String title, int fadeIn, int onScreen, int fadeOut) {
		PacketPlayOutTitle titles = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a(title), fadeIn, onScreen, fadeOut);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(titles);
	}
	
}
