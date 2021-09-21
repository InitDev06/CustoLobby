package net.custolobby.plugin.actionbar.v1_8;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.custolobby.plugin.actionbar.Actionbar;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Actionbar_1_8_R3 implements Actionbar {

	@Override
	public void sendActionBar(Player player, String message) {
		
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}

}
