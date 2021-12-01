package com.gmail.desingdev.axhub.commands;

import com.gmail.desingdev.axhub.AxHub;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GamemodeManager implements Listener, CommandExecutor {

    private AxHub plugin;

    public GamemodeManager(AxHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player)) {
            if (c.getName().equalsIgnoreCase("gm")) {
                if (!plugin.getServer().getConsoleSender().hasPermission("overdeluxe.gamemode.others") && !plugin.getServer().getConsoleSender().isOp()) {
                    plugin.getServer().getConsoleSender().sendMessage(Msg.color("&8[&9OverDeluxeb&8] &cYou don't have permission."));

                } else if (a.length == 2) {
                    try {
                        String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));
                        Player target = Bukkit.getServer().getPlayer(a[0]);
                        if (!a[1].equalsIgnoreCase("survival") && !a[1].equalsIgnoreCase("0") && !a[1].equalsIgnoreCase("supervivencia")) {
                            if (!a[1].equalsIgnoreCase("creative") && !a[1].equalsIgnoreCase("1") && !a[1].equalsIgnoreCase("creativo")) {
                                if (!a[1].equalsIgnoreCase("adventure") && !a[1].equalsIgnoreCase("2") && !a[1].equalsIgnoreCase("aventura")) {
                                    if (a[1].equalsIgnoreCase("spectator") || a[1].equalsIgnoreCase("3") || a[1].equalsIgnoreCase("espectador")) {
                                        target.setGameMode(GameMode.SPECTATOR);
                                        plugin.getServer().getConsoleSender().sendMessage(Msg.color(AxHub.getConfigMsg().getString("gm-other-spectator")).replaceAll("%player%", target.getName()).replaceAll("%prefix%", pf));
                                    }
                                } else {
                                    target.setGameMode(GameMode.ADVENTURE);
                                    plugin.getServer().getConsoleSender().sendMessage(Msg.color(AxHub.getConfigMsg().getString("gm-other-adventure")).replaceAll("%player%", target.getName()).replaceAll("%prefix%", pf));
                                }
                            } else {
                                target.setGameMode(GameMode.CREATIVE);
                                plugin.getServer().getConsoleSender().sendMessage(Msg.color(AxHub.getConfigMsg().getString("gm-other-creative")).replaceAll("%player%", target.getName()).replaceAll("%prefix%", pf));
                            }
                        } else {
                            target.setGameMode(GameMode.SURVIVAL);
                            plugin.getServer().getConsoleSender().sendMessage(Msg.color(AxHub.getConfigMsg().getString("gm-other-survival")).replaceAll("%player%", target.getName()).replaceAll("%prefix%", pf));
                        }
                    } catch (Exception var0) {
                        String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));
                        plugin.getServer().getConsoleSender().sendMessage(Msg.color(AxHub.getConfigMsg().getString("player-offline")).replaceAll("%prefix%", pf));
                    }
                } else {
                    String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));
                    plugin.getServer().getConsoleSender().sendMessage(Msg.color("Correct Usage: /gm <player> <mode>"));
                }
            }
        } else {
            Player player = (Player) s;

            String pf = Msg.color(AxHub.getConfigFile().getString("prefix"));

            if(c.getName().equalsIgnoreCase("gm")){
                if(!player.hasPermission("overdeluxe.gamemode") && !player.isOp()) {
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-message"))).replaceAll("%prefix%", pf));

                } else if(a.length == 1) {
                    if (!a[0].equalsIgnoreCase("survival") && !a[0].equalsIgnoreCase("0") && !a[0].equalsIgnoreCase("supervivencia")) {
                        if (!a[0].equalsIgnoreCase("creative") && !a[0].equalsIgnoreCase("1") && !a[0].equalsIgnoreCase("creativo")) {
                            if (!a[0].equalsIgnoreCase("adventure") && !a[0].equalsIgnoreCase("2") && !a[0].equalsIgnoreCase("adventure")) {
                                if (a[0].equalsIgnoreCase("spectator") || a[0].equalsIgnoreCase("3") || a[0].equalsIgnoreCase("espectador")) {
                                    player.setGameMode(GameMode.SPECTATOR);
                                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-spectator"))).replaceAll("%prefix%", pf));
                                }
                            } else {
                                player.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-adventure"))).replaceAll("%prefix%", pf));
                            }
                        } else {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-creative"))).replaceAll("%prefix%", pf));
                        }
                    } else {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-survival"))).replaceAll("%prefix%", pf));
                    }
                } else if(!player.hasPermission("overdeluxe.gamemode.others") && !player.isOp()) {
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-others"))).replaceAll("%prefix%", pf));
                } else if(a.length == 2) {
                    try {
                        Player target = Bukkit.getServer().getPlayer(a[0]);
                        if (!a[1].equalsIgnoreCase("survival") && !a[1].equalsIgnoreCase("0") && !a[1].equalsIgnoreCase("supervivencia")) {
                            if (!a[1].equalsIgnoreCase("creative") && !a[1].equalsIgnoreCase("1") && !a[1].equalsIgnoreCase("creativo")) {
                                if (!a[1].equalsIgnoreCase("adventure") && !a[1].equalsIgnoreCase("2") && !a[1].equalsIgnoreCase("aventura")) {
                                    if (a[1].equalsIgnoreCase("spectator") || a[1].equalsIgnoreCase("3") || a[1].equalsIgnoreCase("espectador")) {
                                        target.setGameMode(GameMode.SPECTATOR);
                                        player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-other-spectator"))).replaceAll("%prefix%", pf).replaceAll("%player%", target.getName()));
                                    }
                                } else {
                                    target.setGameMode(GameMode.ADVENTURE);
                                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-other-adventure"))).replaceAll("%prefix%", pf).replaceAll("%player%", target.getName()));
                                }
                            } else {
                                target.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-other-creative"))).replaceAll("%prefix%", pf).replaceAll("%player%", target.getName()));
                            }
                        } else {
                            target.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-other-survival"))).replaceAll("%prefix%", pf).replaceAll("%player%", target.getName()));
                        }
                    } catch (Exception var0) {
                        player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("player-offline"))).replaceAll("%prefix%", pf));
                    }
                } else {
                    player.sendMessage(Msg.color(PlaceholderAPI.setPlaceholders(player, AxHub.getConfigMsg().getString("gm-usage"))).replaceAll("%prefix%", pf));
                }
            }
        }
        return false;
    }
}

