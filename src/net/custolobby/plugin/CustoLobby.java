package net.custolobby.plugin;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.commands.CommandManager;
import net.custolobby.plugin.commands.CreateLocation;
import net.custolobby.plugin.commands.FlightManager;
import net.custolobby.plugin.commands.GamemodeManager;
import net.custolobby.plugin.commands.GetLocation;
import net.custolobby.plugin.commands.HelpCommand;
import net.custolobby.plugin.commands.NickManager;
import net.custolobby.plugin.commands.TeleportManager;
import net.custolobby.plugin.commands.VanishManager;
import net.custolobby.plugin.listeners.PlayerChat;
import net.custolobby.plugin.listeners.PlayerJoin;
import net.custolobby.plugin.listeners.PlayerLeft;
import net.custolobby.plugin.listeners.PlayerSettings;
import net.custolobby.plugin.listeners.PlayerSound;
import net.custolobby.plugin.listeners.PlayerTeleport;
import net.custolobby.plugin.listeners.PlayerTitle;
import net.custolobby.plugin.listeners.ReproduceSoundToFlight;
import net.custolobby.plugin.listeners.ServerNetworks;
import net.custolobby.plugin.listeners.TabListServer;
import net.custolobby.plugin.utility.ChatFilter;
import net.custolobby.plugin.utility.CommandsBlocked;
import net.custolobby.plugin.utility.FireworkBuilder;

public class CustoLobby extends JavaPlugin {
	
	private static CustoLobby instance;
	
	private FileConfiguration messages;
	private File messagesFile;
	
	private FileConfiguration chat;
	private File chatFile;
	
	private FileConfiguration tablist;
	private File tablistFile;
	
	PluginDescriptionFile pdf = getDescription();
	
	public final String VERSION = pdf.getVersion();
	public final String SPIGOT = Bukkit.getBukkitVersion();
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &floading components.."));
		loadMessages();
		loadChat();
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &eloaded config.yml"));
		saveResource("messages.yml", false);
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &eloaded messages.yml"));
		saveResource("chat.yml", false);
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &eloaded chat.yml"));
		saveResource("tablist.yml", false);
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &eloaded tablist.yml"));
		
		importEvents();
		importCommands();
		
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fversion on running &a" + VERSION));
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &frunning on Spigot/Paper &b" + SPIGOT));

	}
	
	@Override
	public void onDisable() { 
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &cdisabling plugin.."));
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &esaving data..."));
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &6saved config.yml"));
		saveResource("messages.yml", true);
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &6saved messages.yml"));
		saveResource("chat.yml", true);
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &6saved chat.yml"));
		saveResource("tablist.yml", true);
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &6saved tablist.yml"));
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &cthe plugin has been disabled correctly!"));
		
	}
	
	public void importEvents() {
		PluginManager pM = Bukkit.getPluginManager();
		pM.registerEvents(new FireworkBuilder(this), this);
		pM.registerEvents(new PlayerSettings(this), this);
		pM.registerEvents(new PlayerTitle(this), this);
		pM.registerEvents(new PlayerSound(this), this);
		pM.registerEvents(new PlayerTeleport(this), this);
		pM.registerEvents(new ServerNetworks(this), this);
		pM.registerEvents(new PlayerLeft(this), this);
		pM.registerEvents(new PlayerJoin(this), this);
		pM.registerEvents(new PlayerChat(this), this);
		pM.registerEvents(new ChatFilter(), this);
		pM.registerEvents(new CommandsBlocked(), this);
		pM.registerEvents(new ReproduceSoundToFlight(this), this);
		pM.registerEvents(new TabListServer(this), this);
		
	}
	
	public void importCommands() {
		this.getCommand("tp").setExecutor(new TeleportManager(this));
		this.getCommand("vanish").setExecutor(new VanishManager(this));
		this.getCommand("nick").setExecutor(new NickManager(this));
		this.getCommand("custolobby").setExecutor(new CommandManager(this));
		this.getCommand("fly").setExecutor(new FlightManager(this));
		this.getCommand("setlobby").setExecutor(new CreateLocation(this));
		this.getCommand("lobby").setExecutor(new GetLocation(this));
		this.getCommand("gm").setExecutor(new GamemodeManager(this));
		this.getCommand("help").setExecutor(new HelpCommand(this));
		
	}
	
	public FileConfiguration getMessages() {
		if(messages == null) {
			reloadMessages();
		}
		return messages;
	}
	
	public void reloadMessages() {
		if(messages == null) {
			messagesFile = new File(getDataFolder(), "messages.yml");
		}
		messages = YamlConfiguration.loadConfiguration(messagesFile);
		Reader defMessageStream;
		try {
			defMessageStream = new InputStreamReader(this.getResource("messages.yml"), "UTF8");
			if(defMessageStream != null) {
				YamlConfiguration defMessages = YamlConfiguration.loadConfiguration(defMessageStream);
				messages.setDefaults(defMessages);
			}
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMessages() {
		messagesFile = new File(this.getDataFolder(), "messages.yml");
		if(!messagesFile.exists()) {
			this.getMessages().options().copyDefaults(true);
		}
	}
	
	public FileConfiguration getChat() {
		if(chat == null) {
			reloadChat();
		}
		return chat;
	}
	
	public void reloadChat() {
		if(chat == null) {
			chatFile = new File(getDataFolder(), "chat.yml");
		}
		chat = YamlConfiguration.loadConfiguration(chatFile);
		Reader defChatStream;
		try {
			defChatStream = new InputStreamReader(this.getResource("chat.yml"), "UTF8");
			if(defChatStream != null) {
				YamlConfiguration defChat = YamlConfiguration.loadConfiguration(defChatStream);
				chat.setDefaults(defChat);
			}
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public void loadChat() {
		chatFile = new File(this.getDataFolder(), "chat.yml");
		if(!chatFile.exists()) {
			this.getChat().options().copyDefaults(true);
		}
	}
	
	public FileConfiguration getTabList() {
		if(tablist == null) {
			reloadTabList();
		}
		return tablist;
	}
	
	public void reloadTabList() {
		if(tablist == null) {
			tablistFile = new File(getDataFolder(), "tablist.yml");
		}
		tablist = YamlConfiguration.loadConfiguration(tablistFile);
		Reader defTabStream;
		try {
			defTabStream = new InputStreamReader(this.getResource("tablist.yml"), "UTF8");
			if(defTabStream != null) {
				YamlConfiguration defTabList = YamlConfiguration.loadConfiguration(defTabStream);
				tablist.setDefaults(defTabList);
			}
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTabList() {
		tablistFile = new File(this.getDataFolder(), "tablist.yml");
		if(!tablistFile.exists()) {
			this.getTabList().options().copyDefaults(true);
		}
	}
	
	public static CustoLobby getInstance() {
		return instance;
	}

}
