package net.custolobby.plugin.actionbar.v1_10;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.custolobby.plugin.actionbar.Actionbar;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;

public class Actionbar_1_10_R1 implements Actionbar {

	@Override
	public void sendActionBar(Player player, String message) {
		
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}
	
}

