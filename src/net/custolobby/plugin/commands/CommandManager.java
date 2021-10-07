package net.custolobby.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;
import net.custolobby.plugin.utility.Permissions;

public class CommandManager implements CommandExecutor {

	private CustoLobby plugin;
	
	public CommandManager(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		FileConfiguration config = CustoLobby.getConfigFile();
		FileConfiguration lang_en = CustoLobby.getLangEN();
		FileConfiguration lang_es = CustoLobby.getLangES();
		if(!(commandSender instanceof Player)) {
			if(command.getName().equalsIgnoreCase("custolobby")) {
				if(Bukkit.getConsoleSender().hasPermission("custolobby.admin") || Bukkit.getConsoleSender().isOp()) {
					if(args.length == 0) {
						if(config.getString("language").equals("English")) {
							Bukkit.getConsoleSender().sendMessage(Color.translate(lang_en.getString("messages.args")));
						} else if(config.getString("language").equals("Spanish")) {
							Bukkit.getConsoleSender().sendMessage(Color.translate(lang_es.getString("messages.args")));
						}
						return true;
					}
						
					if(args[0].equalsIgnoreCase("info")) {
						if(config.getString("language").equals("English")) {
							Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fVersion &a" + plugin.VERSION + " &frunning on &b" + Bukkit.getVersion()));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fDeveloped by &bAISimple"));
						} else if(config.getString("language").equals("Spanish")) {
							Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fVersion &a" + plugin.VERSION + " &ffuncionando en &b" + Bukkit.getVersion()));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &fDesarrollado por &bAISimple"));
						}
						return true;
					}
						
					if(args[0].equalsIgnoreCase("help")) {
						if(config.getString("language").equals("English")) {
							Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &7Commands"));
							Bukkit.getConsoleSender().sendMessage(Color.translate(""));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/clobby <help> &7- &aShow this message."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/clobby <info> &7- &aShow the plugin information."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/flight &7- &aToggle your flight mode."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/gm &7- &aChange your gamemode."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/vanish &7- &aToggle your invisibility mode."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/setlobby &7- &aSave the spawn location."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/lobby &7- &aTeleport you to spawn."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/help &7- &aShow a help message for the users."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/nick <name> &7- &aChange your display name."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/tp <name> &7- &aTeleport to any player."));
						} else if(config.getString("language").equals("Spanish")) {
							Bukkit.getConsoleSender().sendMessage(Color.translate("&cCustoLobby &8| &7Comandos"));
							Bukkit.getConsoleSender().sendMessage(Color.translate(""));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/clobby <help> &7- &aMuestra este mensaje."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/clobby <info> &7- &aMuestra informacion sobre el complemento"));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/flight &7- &aAlterna tu modo de vuelo."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/gm &8- &aCambia tu modo de juego."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/vanish &7- &aAlterna tu modo de invisibilidad."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/setlobby &7- &aGuarda la ubicacion del vestibulo."));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/lobby &7- &aTeletransportate al vestibulo"));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/help &7- &aMuestra un mensaje de ayuda"));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/nick <name> &7- &aCambia tu nombre de usuario visible"));
							Bukkit.getConsoleSender().sendMessage(Color.translate("&8- &e/tp <name> &7- &aTeletransportate a algun jugador."));
						}
						return true;
					}
					
					if(config.getString("language").equals("English")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate(lang_en.getString("messages.unknown-args")));
					} else if(config.getString("language").equals("Spanish")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate(lang_es.getString("messages.unknown-args")));
					}
					return true;
						
				} else {
					if(config.getString("language").equals("English")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll(
								"%permission%", Permissions.permission("admin")));
					} else if(config.getString("language").equals("Spanish")) {
						Bukkit.getConsoleSender().sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll(
								"%permission%", Permissions.permission("admin")));
					}
					return true;
				}
			
			}
			
		} else {
			Player player = (Player) commandSender;
			
			if(command.getName().equalsIgnoreCase("custolobby")) {
				if(player.hasPermission("custolobby.admin") || player.isOp()) {
					if(args.length == 0) {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate(lang_en.getString("messages.args")));	
						} else if(config.getString("language").equals("Spanish")) {
							player.sendMessage(Color.translate(lang_es.getString("messages.args")));
						}
						return true;
					}
						
					if(args[0].equalsIgnoreCase("info")) {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate("&cCustoLobby &8| &fVersion &a" + plugin.VERSION + " &frunning on &b" + Bukkit.getVersion()));
							player.sendMessage(Color.translate("&cCustoLobby &8| &fDeveloped by &bAISimple"));
						} else if(config.getString("language").equals("Spanish")) {
							player.sendMessage(Color.translate("&cCustoLobby &8| &fVersion &a" + plugin.VERSION + " &ffuncionando en &b" + Bukkit.getVersion()));
							player.sendMessage(Color.translate("&cCustoLobby &8| &fDesarrollado por &bAISimple"));
						}
						return true;
					}
						
					if(args[0].equalsIgnoreCase("help")) {
						if(config.getString("language").equals("English")) {
							player.sendMessage(Color.translate("&cCustoLobby &8| &7Commands"));
							player.sendMessage(Color.translate(""));
							player.sendMessage(Color.translate("&8- &e/clobby <help> &7- &ashow this message."));
							player.sendMessage(Color.translate("&8- &e/clobby <info> &7- &ashow the plugin information."));
							player.sendMessage(Color.translate("&8- &e/flight &7- &atoggle your flight mode."));
							player.sendMessage(Color.translate("&8- &e/gm &7- &achange your gamemode."));
							player.sendMessage(Color.translate("&8- &e/vanish &7- &atoggle your invisibility mode."));
							player.sendMessage(Color.translate("&8- &e/setlobby &7- &asave the spawn location."));
							player.sendMessage(Color.translate("&8- &e/lobby &7- &ateleport you to spawn."));
							player.sendMessage(Color.translate("&8- &e/help &7- &ashow a help message for the users."));
							player.sendMessage(Color.translate("&8- &e/nick <name> &7- &achange your display name."));
							player.sendMessage(Color.translate("&8- &e/tp <name> &7- &ateleport to any player."));
						} else if(config.getString("language").equals("Spanish")) {
							player.sendMessage(Color.translate("&cCustoLobby &8| &7Comandos"));
							player.sendMessage(Color.translate(""));
							player.sendMessage(Color.translate("&8- &e/clobby <help> &7- &amuestra este mensaje."));
							player.sendMessage(Color.translate("&8- &e/clobby <info> &7- &amuestra informacion sobre el complemento"));
							player.sendMessage(Color.translate("&8- &e/flight &7- &aalterna tu modo de vuelo."));
							player.sendMessage(Color.translate("&8- &e/gm &8- &acambia tu modo de juego."));
							player.sendMessage(Color.translate("&8- &e/vanish &7- &aalterna tu modo de invisibilidad."));
							player.sendMessage(Color.translate("&8- &e/setlobby &7- &aguarda la ubicacion del vestibulo."));
							player.sendMessage(Color.translate("&8- &e/lobby &7- &ateletransportate al vestibulo"));
							player.sendMessage(Color.translate("&8- &e/help &7- &amuestra un mensaje de ayuda"));
							player.sendMessage(Color.translate("&8- &e/nick <name> &7- &acambia tu nombre de usuario visible"));
							player.sendMessage(Color.translate("&8- &e/tp <name> &7- &ateletransportate a algun jugador."));
						}
						return true;
					}
						
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.unknown-args")));
					} else if(config.getString("language").equals("Spanish")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.unknown-args")));
					}
					return true;
						
				} else {
					if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_en.getString("messages.missing-permission")).replaceAll("%permission%", Permissions.
								permission("admin")));
					} else if(config.getString("language").equals("English")) {
						player.sendMessage(Color.translate(lang_es.getString("messages.missing-permission")).replaceAll("%permission%", Permissions.
								permission("admin")));
					}
					return true;
				}
			}
		}
		return false;
	}

}
