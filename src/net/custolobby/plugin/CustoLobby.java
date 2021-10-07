package net.custolobby.plugin;

import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
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
import net.custolobby.plugin.config.Settings;
import net.custolobby.plugin.listeners.ClearToJoin;
import net.custolobby.plugin.listeners.PlayerChat;
import net.custolobby.plugin.listeners.PlayerJoin;
import net.custolobby.plugin.listeners.PlayerLeft;
import net.custolobby.plugin.listeners.PlayerSettings;
import net.custolobby.plugin.listeners.PlayerSound;
import net.custolobby.plugin.listeners.PlayerTeleport;
import net.custolobby.plugin.listeners.PlayerTitle;
import net.custolobby.plugin.listeners.ServerNetworks;
import net.custolobby.plugin.nms.utility.NMSHelper;
import net.custolobby.plugin.utility.ChatFilter;
import net.custolobby.plugin.utility.CommandsBlocked;
import net.custolobby.plugin.utility.FireworkBuilder;
import net.custolobby.plugin.utility.WorldProtection;
import net.custolobby.plugin.visual.PlayerBoard;

public class CustoLobby extends JavaPlugin  {
	
	private static CustoLobby instance;
	
	private static FileConfiguration config;
	private static FileConfiguration lang_en;
	private static FileConfiguration lang_es;
	private static FileConfiguration chat;
	private static FileConfiguration scoreboard;
	private static Settings s;
	
	PluginDescriptionFile pdf = getDescription();
	public String VERSION = pdf.getVersion();

	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fLoading components.."));
		NMSHelper.setupNMS();
		PlayerBoard playerBoard = new PlayerBoard();
		playerBoard.create();
		this.importEvents();
		this.importCommands();
		this.files();
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aLoaded Files & Components"));
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fLoading dependencies.."));
		this.verifyDependencies();
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aThe plugin has been enabled correctly!"));
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aDeveloped by &bAISimple &av&e" + VERSION));

	}
	
	@Override
	public void onDisable() { 
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &cDisabling plugin.."));
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &eSaving data..."));
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &cThe plugin has been disabled correctly!"));
		this.getServer().getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &aDeveloped by &bAISimple &av&e" + VERSION));
		
	}

	private void verifyDependencies() {
		if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fFounded dependency &bPlaceholderAPI"));
		} else {
			Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fNot founded dependency &bPlaceholderAPI"));
		}
			
	}
	
	private void files() {
		s = new Settings();
		s.create("config");
		s.create("chat");
		s.create("scoreboard");
		s.create("lang_en");
		s.create("lang_es");
		config = s.getConfig("config");
		chat = s.getConfig("chat");
		scoreboard = s.getConfig("scoreboard");
		lang_en = s.getConfig("lang_en");
		lang_es = s.getConfig("lang_es");
	}
	
	private void importEvents() {
		PluginManager pM = Bukkit.getPluginManager();
		pM.registerEvents(new FireworkBuilder(), this);
		pM.registerEvents(new PlayerSettings(), this);
		pM.registerEvents(new PlayerTitle(), this);
		pM.registerEvents(new PlayerSound(), this);
		pM.registerEvents(new PlayerTeleport(this), this);
		pM.registerEvents(new PlayerLeft(), this);
		pM.registerEvents(new ClearToJoin(), this);
		pM.registerEvents(new PlayerJoin(), this);
		pM.registerEvents(new ServerNetworks(), this);
		pM.registerEvents(new PlayerChat(), this);
		pM.registerEvents(new ChatFilter(), this);
		pM.registerEvents(new CommandsBlocked(), this);
		pM.registerEvents(new WorldProtection(this), this);
		
	}
	
	private void importCommands() {
		this.getCommand("tp").setExecutor(new TeleportManager());
		this.getCommand("vanish").setExecutor(new VanishManager());
		this.getCommand("nick").setExecutor(new NickManager());
		this.getCommand("custolobby").setExecutor(new CommandManager(this));
		this.getCommand("fly").setExecutor(new FlightManager());
		this.getCommand("setlobby").setExecutor(new CreateLocation());
		this.getCommand("lobby").setExecutor(new GetLocation(this));
		this.getCommand("gm").setExecutor(new GamemodeManager());
		this.getCommand("help").setExecutor(new HelpCommand());
		
	}
	
	public static CustoLobby getInstance() {
		return instance;
	}
	public static Settings getCfgManager() {
		return s;
	}
	public static FileConfiguration getConfigFile() {
		return config;
	}
	public static FileConfiguration getLangEN() {
		return lang_en;
	}
	public static FileConfiguration getLangES() {
		return lang_es;
	}
	public static FileConfiguration getChatFile() {
		return chat;
	}
	public static FileConfiguration getScorebFile() {
		return scoreboard;
	}
	public static void setConfigFile(FileConfiguration config) {
		CustoLobby.config = config;
	}
	public static void setLangEN(FileConfiguration lang_en) {
		CustoLobby.lang_en = lang_en;
	}
	public static void setLangES(FileConfiguration lang_es) {
		CustoLobby.lang_es = lang_es;
	}
	public static void setChatFile(FileConfiguration chat) {
		CustoLobby.chat = chat;
	}
	public static void setScorebFile(FileConfiguration scoreboard) {
		CustoLobby.scoreboard = scoreboard;
	}
}
