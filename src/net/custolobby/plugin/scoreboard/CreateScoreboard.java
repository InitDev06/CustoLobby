package net.custolobby.plugin.scoreboard;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class CreateScoreboard {
	
	private CustoLobby plugin;
	
	public CreateScoreboard(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	int taskID;

	@SuppressWarnings("deprecation")
	public void create(int ticks) {
		BukkitScheduler scheduler = Bukkit.getScheduler();
		taskID = scheduler.scheduleAsyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					update(player);
				}
			}
		}, 0, ticks);
	}

	public void update(Player player) {
		FileConfiguration scoreboard = plugin.getScoreboard();
		if(scoreboard.getBoolean("Scoreboard.enable") == true) {
			
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard scoreb = manager.getNewScoreboard();
			Objective objective = scoreb.registerNewObjective("Test", "Dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			Team lines = scoreb.registerNewTeam("lines");
			List<String> lore = scoreboard.getStringList("Scoreboard.lines");
			for(int i = 15 ; i < 16; i++) {
				String strings = Color.translate(PlaceholderAPI.setPlaceholders(player, lore.get(i)).replaceAll("%player_name%", player.getName())
						.replaceAll("%level%", String.valueOf(player.getLevel())).replaceAll("%world_name%", player.getWorld().getName()).replaceAll("%connected%"
								, String.valueOf(Bukkit.getOnlinePlayers().size())));
				
				lines.addEntry(strings);
				Score score = objective.getScore(strings);
				score.setScore(lore.size()-(i));
			}
			lines.setSuffix("");
			lines.setPrefix("");
			String title = Color.translate(scoreboard.getString("Scoreboard.title"));
			objective.setDisplayName(title);
			
			player.setScoreboard(scoreb);
		}
	}

}
