package net.custolobby.plugin.actionbar.v_1_12;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.custolobby.plugin.actionbar.Actionbar;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class Actionbar_1_12_R1 implements Actionbar {

	@Override
	public void sendActionBar(Player player, String message) {
		
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat bar = new PacketPlayOutChat(icbc);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
	}
	
}
