package net.custolobby.plugin.particles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.custolobby.plugin.CustoLobby;
import net.minecraft.server.v1_8_R2.EnumParticle;
import net.minecraft.server.v1_8_R2.PacketPlayOutWorldParticles;

public class Particles_1_8_R2 {
	
private CustoLobby plugin;
	
	public Particles_1_8_R2(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	public void createParticles() {
		for(Entity entity : Bukkit.getWorld(plugin.getConfig().getString("lobby-point.world")).getEntities()) {
			if(entity instanceof Player) {
				Player player = (Player) entity;
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						float red = 255;
						float green = 0;
						float blue = 10;
						Location location = player.getLocation();
						location.setY(location.getY() + 2);
						
						PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.NOTE, true, (float)location.getX
								(), (float)location.getY(), (float)location.getZ(), red, green, blue, (float)255, 0, 10);
						((CraftPlayer)player).getHandle().playerConnection.sendPacket(particles);
					}
				}.runTaskTimerAsynchronously(plugin, 0, 0);
			}
		}
	}

}
