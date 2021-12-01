package com.gmail.desingdev.axhub.commands;

import com.gmail.desingdev.axhub.AxHub;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.util.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;

public class CommandManager implements Listener, CommandExecutor {

    private AxHub plugin;

    public CommandManager(AxHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player)) {
            if (c.getName().equalsIgnoreCase("overdeluxe")) {
                if (!plugin.getServer().getConsoleSender().hasPermission("overdeluxe.commandmanager") && !plugin.getServer().getConsoleSender().isOp()) {
                    plugin.getServer().getConsoleSender().sendMessage(Msg.color("&8[&9OverDeluxe&8] &cYou don't have permission!"));

                } else if (a.length == 1) {
                    if (!a[0].equalsIgnoreCase("reload") && !a[0].equalsIgnoreCase("recargar")) {
                        if (a[0].equalsIgnoreCase("info")) {
                            List<String> cmd = AxHub.getConfigMsg().getStringList("cmd-description");
                            for (int i = 0; i < cmd.size(); i++) {
                                String description = cmd.get(i);

                                description = Msg.color(description).replaceAll("<var1>", plugin.getDescription().getVersion()).replaceAll("<var2>", "01-12-2020");

                                plugin.getServer().getConsoleSender().sendMessage(description);
                            }
                        }
                    } else {
                        AxHub.getConfigManager().getConfig("settings");
                        AxHub.getConfigManager().getConfig("messages");
                        AxHub.getConfigManager().getConfig("chat");
                        plugin.getServer().getConsoleSender().sendMessage(Msg.color("&8[&9OverDeluxe&8] &eThe plugin has been reloaded correctly."));
                    }
                } else {
                    plugin.getServer().getConsoleSender().sendMessage(Msg.color("&8[&9OverDeluxe&8] &eExclusive of Axolotl Network."));
                }
            }
        } else {
            Player player = (Player) s;

            String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));

            if (c.getName().equalsIgnoreCase("overdeluxe")) {
                if (!player.hasPermission("overdeluxe.commandmanager") && !player.isOp()) {
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("cmd-message"))).replaceAll("%prefix%", pf));

                } else if (a.length == 1) {
                    if (!a[0].equalsIgnoreCase("reload") && !a[0].equalsIgnoreCase("recargar")) {
                        if (a[0].equalsIgnoreCase("info")) {
                            List<String> cmd = AxHub.getConfigMsg().getStringList("cmd-description");
                            for (int i = 0; i < cmd.size(); i++) {
                                String description = cmd.get(i);

                                description = Msg.color(PlaceholderAPI.setPlaceholders(player, description).replaceAll("<var1>", plugin.getDescription().getVersion()).replaceAll("<var2>", "01-12-2020"));

                                player.sendMessage(description);
                            }
                        }
                    } else {
                        AxHub.getConfigManager().getConfig("settings");
                        AxHub.getConfigManager().getConfig("messages");
                        AxHub.getConfigManager().getConfig("chat");
                        player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("cmd-reload"))).replaceAll("%prefix%", pf));
                    }
                } else {
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("cmd-unknown-args"))).replaceAll("%prefix%", pf));
                }
            }
        }
        return false;
    }
}