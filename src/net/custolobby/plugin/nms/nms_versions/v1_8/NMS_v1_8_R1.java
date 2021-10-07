package net.custolobby.plugin.nms.nms_versions.v1_8;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.custolobby.plugin.nms.NMS;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

public class NMS_v1_8_R1 implements NMS {

	@Override
	public void sendTitle(Player player, String title, int fadeIn, int onScreen, int fadeOut) {
		PacketPlayOutTitle titles = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a(title), fadeIn, onScreen, fadeOut);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(titles);
	}

}
