package com.gmail.desingdev.axhub.commands;

import com.gmail.desingdev.axhub.AxHub;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightManager implements CommandExecutor {

    private AxHub plugin;

    public FlightManager(AxHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player)) {
            String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));
            plugin.getServer().getConsoleSender().sendMessage(Msg.color(AxHub.getConfigMsg().getString("must-be-player")).replaceAll("%prefix%", pf));
            return false;

        } else {

            Player player = (Player) s;

            String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));

            if(player.hasPermission("overdeluxe.flight") || player.isOp()) {

                if (player.getAllowFlight()) {

                    player.setAllowFlight(false);
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("flight-disable"))).replaceAll("%prefix%", pf));
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, (float) 0.0);

                } else if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("flight-enable"))).replaceAll("%prefix%", pf));
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, (float) 0.0);
                    return true;
                }
            } else {
                player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("flight-message"))).replaceAll("%prefix%", pf));
                return true;
            }
        }
        return false;
    }
}
