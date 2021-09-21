package net.custolobby.plugin;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import net.custolobby.plugin.utility.BlacklistCommands;
import net.custolobby.plugin.utility.WordsChatFilter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.custolobby.plugin.actionbar.Actionbar;
import net.custolobby.plugin.actionbar.v1_8.Actionbar_1_8_R1;
import net.custolobby.plugin.actionbar.v1_8.Actionbar_1_8_R2;
import net.custolobby.plugin.actionbar.v1_8.Actionbar_1_8_R3;
import net.custolobby.plugin.actionbar.v1_9.Actionbar_1_9_R1;
import net.custolobby.plugin.actionbar.v1_9.Actionbar_1_9_R2;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.commands.CommandManager;
import net.custolobby.plugin.listeners.ActionbarPlayer;
import net.custolobby.plugin.listeners.HoverLinkMessages;
import net.custolobby.plugin.listeners.JoinPlayer;
import net.custolobby.plugin.listeners.QuitPlayer;
import net.custolobby.plugin.listeners.SettingsPlayer;
import net.custolobby.plugin.listeners.SoundPlayer;
import net.custolobby.plugin.listeners.SubtitlePlayer;
import net.custolobby.plugin.listeners.TitlePlayer;
import net.custolobby.plugin.scoreboard.CreateScoreboard;
import net.custolobby.plugin.title.Subtitle;
import net.custolobby.plugin.title.Title;
import net.custolobby.plugin.title.v1_8.Subtitle_1_8_R1;
import net.custolobby.plugin.title.v1_8.Subtitle_1_8_R2;
import net.custolobby.plugin.title.v1_8.Subtitle_1_8_R3;
import net.custolobby.plugin.title.v1_8.Title_1_8_R1;
import net.custolobby.plugin.title.v1_8.Title_1_8_R2;
import net.custolobby.plugin.title.v1_8.Title_1_8_R3;
import net.custolobby.plugin.title.v1_9.Subtitle_1_9_R1;
import net.custolobby.plugin.title.v1_9.Subtitle_1_9_R2;
import net.custolobby.plugin.title.v1_9.Title_1_9_R1;
import net.custolobby.plugin.title.v1_9.Title_1_9_R2;
import net.custolobby.plugin.utility.FireworkBuilder;
import net.custolobby.plugin.world.WorldProtection;

public class CustoLobby extends JavaPlugin {
	
	private static CustoLobby instance;
	
	private Actionbar actionbar;
	private Title title;
	private Subtitle subtitle;
	
	private FileConfiguration messages;
	private File messagesFile;
	
	private FileConfiguration scoreboard;
	private File scoreboardFile;
	
	private FileConfiguration chat;
	private File chatFile;
	
	int ticks = getScoreboard().getInt("Scoreboard.refresh");
	
	PluginDescriptionFile pdf = getDescription();
	
	public final String VERSION = pdf.getVersion();
	
	@Override
	public void onEnable() {
		CreateScoreboard createScoreboard = new CreateScoreboard(this);
		createScoreboard.create(ticks);

		loadMessages();
		loadScoreboard();
		loadChat();
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveDefaultConfig();
		saveResource("messages.yml", false);
		saveResource("chat.yml", false);
		saveResource("scoreboard.yml", false);
		
		importEvents();
		importCommands();
		
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fLoading components.."));
		Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &fVersion on Running: &a" + VERSION));
		
		if (setupVersions()) {

            Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &aThe plugin has been activated correctly!"));

        } else {

        	Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &cHas occurred an error to try activate the plugin"));
        	Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &eYour server version is not compatible with this plugin!"));

            Bukkit.getPluginManager().disablePlugin(this);
        }
		
	}
	
	@Override
	public void onDisable() {
		saveDefaultConfig();
		saveResource("messages.yml", true);
		saveResource("chat.yml", true);
		saveResource("scoreboard.yml", true);
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
		} catch(UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}
	}
	
	public void loadMessages() {
		messagesFile = new File(this.getDataFolder(), "messages.yml");
		if(!messagesFile.exists()) {
			this.getMessages().options().copyDefaults(true);
		}
	}
	
	public FileConfiguration getScoreboard() {
		if(scoreboard == null) {
			reloadScoreboard();
		}
		return scoreboard;
	}
	
	public void reloadScoreboard() {
		if(scoreboard == null) {
			scoreboardFile = new File(getDataFolder(), "scoreboard.yml");
		}
		scoreboard = YamlConfiguration.loadConfiguration(scoreboardFile);
		Reader defScoreboardStream;
		try {
			defScoreboardStream = new InputStreamReader(this.getResource("scoreboard.yml"), "UTF8");
			if(defScoreboardStream != null) {
				YamlConfiguration defScoreboard = YamlConfiguration.loadConfiguration(defScoreboardStream);
				scoreboard.setDefaults(defScoreboard);
			}
		} catch(UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}
	}
	
	public void loadScoreboard() {
		scoreboardFile = new File(this.getDataFolder(), "scoreboard.yml");
		if(!scoreboardFile.exists()) {
			this.getScoreboard().options().copyDefaults(true);
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
		} catch(UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}
	}

	public void loadChat() {
		chatFile = new File(this.getDataFolder(), "chat.yml");
		if(!chatFile.exists()) {
			this.getChat().options().copyDefaults(true);
		}
	}
	
	public void importCommands() {
		this.getCommand("custolobby").setExecutor(new CommandManager(this));
	}
	
	public void importEvents() {
		PluginManager pM = Bukkit.getPluginManager();
		pM.registerEvents(new ActionbarPlayer(this), this);
		pM.registerEvents(new TitlePlayer(this), this);
		pM.registerEvents(new SubtitlePlayer(this), this);
		pM.registerEvents(new SoundPlayer(this), this);
		pM.registerEvents(new WorldProtection(this), this);
		pM.registerEvents(new FireworkBuilder(this), this);
		pM.registerEvents(new JoinPlayer(this), this);
		pM.registerEvents(new HoverLinkMessages(this), this);
		pM.registerEvents(new SettingsPlayer(this), this);
		pM.registerEvents(new WordsChatFilter(this), this);
		pM.registerEvents(new BlacklistCommands(this), this);
		pM.registerEvents(new QuitPlayer(this), this);
	}
	
	public Actionbar getActionbar() {
		return actionbar;
	}
	
	public Title getTitle() {
		return title;
	}
	
	public Subtitle getSubtitle() {
		return subtitle;
	}
	
	private boolean setupVersions() {

        String version;

        try {

            version = Bukkit.getBukkitVersion();

        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }

        Bukkit.getConsoleSender().sendMessage(Color.translate("&8[&eCustoLobby&8] &aYour server is running version &b" + version));

        if (version.equals("1.8-R0.1-SNAPSHOT")) {
            actionbar = new Actionbar_1_8_R1();
            title = new Title_1_8_R1();
            subtitle = new Subtitle_1_8_R1();
        }
        else if(version.equals("1.8.3-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_8_R2();
        	title = new Title_1_8_R2();
        	subtitle = new Subtitle_1_8_R2();
        }
        else if(version.equals("1.8.4-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_8_R3();
        	title = new Title_1_8_R3();
        	subtitle = new Subtitle_1_8_R3();
        }
        else if(version.equals("1.8.5-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_8_R3();
        	title = new Title_1_8_R3();
        	subtitle = new Subtitle_1_8_R3();
        }
        else if(version.equals("1.8.6-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_8_R3();
        	title = new Title_1_8_R3();
        	subtitle = new Subtitle_1_8_R3();
        }
        else if(version.equals("1.8.7-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_8_R3();
        	title = new Title_1_8_R3();
        	subtitle = new Subtitle_1_8_R3();
        }
        else if(version.equals("1.8.8-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_8_R3();
        	title = new Title_1_8_R3();
        	subtitle = new Subtitle_1_8_R3();
        }
        else if(version.equals("1.9-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_9_R1();
        	title = new Title_1_9_R1();
        	subtitle = new Subtitle_1_9_R1();
        }
        else if(version.equals("1.9.2-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_9_R2();
        	title = new Title_1_9_R2();
        	subtitle = new Subtitle_1_9_R2();
        }
        else if(version.equals("1.9.4-R0.1-SNAPSHOT")) {
        	actionbar = new Actionbar_1_9_R2();
        	title = new Title_1_9_R2();
        	subtitle = new Subtitle_1_9_R2();
        }
       
        return actionbar != null;
    }
	
	public static CustoLobby getInstance() { return instance; }

}
