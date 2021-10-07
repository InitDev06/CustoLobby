package net.custolobby.plugin.visual;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.libs.FastBoard;

public class PlayerBoard {
	
	private final Map<UUID, FastBoard> boards = new HashMap<>();
	
	public void create() {
		Bukkit.getServer().getScheduler().runTaskTimer(CustoLobby.getInstance(), () -> {
			for(FastBoard fboard : boards.values() ) {
				for(Player p : Bukkit.getOnlinePlayers()) {
					onUpdate(fboard, p);
				}
			}
		}, 0, 20);
	}
	
	private void onUpdate(FastBoard fboard, Player p) {
		FileConfiguration sb = CustoLobby.getScorebFile();
		fboard.updateTitle(Color.translate(sb.getString("Scoreboard.title")));
		boards.put(p.getUniqueId(), fboard);
	}
	
	@SuppressWarnings("unused")
	private void refreshBoard(FastBoard fboard) {
		FileConfiguration sb = CustoLobby.getScorebFile();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		List<String> lines = sb.getStringList("Scoreboard.lines");
		for(int i = 0 ; i < lines.size(); i++) {
			fboard.updateLines(Color.translate(PlaceholderAPI.setPlaceholders(fboard.getPlayer(), lines.get(i)).replaceAll("%player_name%", 
					fboard.getPlayer().getDisplayName()).replaceAll("%world_name%", fboard.getPlayer().getName()).replaceAll("%level%", nf.format
							(fboard.getPlayer().getLevel())).replaceAll("%exp%", nf.format(
									fboard.getPlayer().getTotalExperience())).replaceAll("%connected%", String.valueOf(Bukkit.getOnlinePlayers().
											size()))));
		}
	}
}
