package net.custolobby.plugin.nms.utility;

import org.bukkit.Bukkit;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.nms.NMS;
import net.custolobby.plugin.nms.nms_versions.v1_10.NMS_v1_10_R1;
import net.custolobby.plugin.nms.nms_versions.v1_8.NMS_v1_8_R1;
import net.custolobby.plugin.nms.nms_versions.v1_8.NMS_v1_8_R2;
import net.custolobby.plugin.nms.nms_versions.v1_8.NMS_v1_8_R3;
import net.custolobby.plugin.nms.nms_versions.v1_9.NMS_v1_9_R1;
import net.custolobby.plugin.nms.nms_versions.v1_9.NMS_v1_9_R2;

public class NMSHelper {
	
	private static NMS nms;
	
	public static void setupNMS() {
		String version = null;
		
		try {
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch(ArrayIndexOutOfBoundsException exception) {
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &cSomething went wrong while loading NMS!"));
			Bukkit.getScheduler().cancelAllTasks();
			Bukkit.getPluginManager().disablePlugin(CustoLobby.getInstance());
		}
		
		if(version.equals("v1_8_R1")) {
			nms = new NMS_v1_8_R1();
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded &eNMS_v1_8_R1"));
		} else if(version.equals("v1_8_R2")) {
			nms = new NMS_v1_8_R2();
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded &eNMS_v1_8_R2"));
		} else if(version.equals("v1_8_R3")) {
			nms = new NMS_v1_8_R3();
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded &eNMS_v1_8_R3"));
		} else if(version.equals("v1_9_R1")) {
			nms = new NMS_v1_9_R1();
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded &eNMS_v1_9_R1"));
		} else if(version.equals("v1_9_R2")) {
			nms = new NMS_v1_9_R2();
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded &eNMS_v1_9_R2"));
		} else if(version.equals("v1_10_R1")) {
			nms = new NMS_v1_10_R1();
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded &eNMS_v1_10_R1"));
		} else {
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &eYour server version is not supported!"));
			Bukkit.getScheduler().cancelAllTasks();
			Bukkit.getPluginManager().disablePlugin(CustoLobby.getInstance());
		}
	}
	
	public static NMS getNMS() {
		return nms;
	}

}
