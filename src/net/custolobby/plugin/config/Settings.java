package net.custolobby.plugin.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.custolobby.plugin.CustoLobby;

public class Settings {
	
	public Settings() {	
	}
	
	public void create(String name) {
		File configFile = new File(CustoLobby.getInstance().getDataFolder(), name + ".yml");
		if(!configFile.exists()) {
			CustoLobby.getInstance().saveResource(name + ".yml", false);
		}
	}
	
	public FileConfiguration getConfig(String fileName) {
		return YamlConfiguration.loadConfiguration(new File(CustoLobby.getInstance().getDataFolder(), fileName + ".yml"));
	}
	
	public File getFile(String fileName) {
		return new File(CustoLobby.getInstance().getDataFolder(), fileName + ".yml");
	}

}
