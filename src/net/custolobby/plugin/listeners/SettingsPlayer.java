package net.custolobby.plugin.listeners;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import net.custolobby.plugin.CustoLobby;
import net.custolobby.plugin.color.Color;

public class SettingsPlayer implements Listener {
	
	private CustoLobby plugin;
	
	public SettingsPlayer(CustoLobby plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSettings(PlayerJoinEvent event) {
		FileConfiguration config = plugin.getConfig();
		FileConfiguration messages = plugin.getMessages();
		Player player = event.getPlayer();
		boolean isSetTrue = config.getBoolean("settings.give-speed");
		boolean isSetTrue2 = config.getBoolean("settings.give-bow");
		String name = Color.translate(config.getString("settings.bow-name"));
		int slot = config.getInt("settings.bow-slot");
		
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setExp(0);
		player.setGameMode(GameMode.ADVENTURE);
		
		if(isSetTrue) {
			player.addPotionEffect(PotionEffectType.SPEED.createEffect(1000000, 1));
		}
		
		if(isSetTrue2) {
			ItemStack teleport_bow = new ItemStack(Material.BOW);
			teleport_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 666);
			
			ItemMeta meta = teleport_bow.getItemMeta();
			meta.setDisplayName(name);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			ArrayList<String> lore = new ArrayList<>();
			lore.add(Color.translate(messages.getString("messages.lore-bow")));
			meta.setLore(lore);
			teleport_bow.setItemMeta(meta);
			
			player.getInventory().setItem(slot, teleport_bow);
		}
	}

}
