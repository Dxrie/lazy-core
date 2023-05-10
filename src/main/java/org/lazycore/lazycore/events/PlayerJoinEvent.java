package org.lazycore.lazycore.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.lazycore.lazycore.FileConfigurator;
import org.lazycore.lazycore.Lazy_core;

import java.util.Map;

public class PlayerJoinEvent implements Listener {
    Lazy_core plugin;
    public static ItemStack[] armorContent;
    public static ItemStack[] inventoryContent;
    public static Location currentLocation;
    public static Location worldSpawn;


    public PlayerJoinEvent(Lazy_core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
    }


    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {event.getPlayer().sendMessage("test");
        Player player = event.getPlayer();
        currentLocation = player.getLocation();
        worldSpawn = player.getWorld().getSpawnLocation();
        armorContent = player.getInventory().getArmorContents();
        inventoryContent = player.getInventory().getContents();

        for (int i = 0; i < Lazy_core.invisiblePlayers.size(); i++) {
            player.hidePlayer(plugin, Lazy_core.invisiblePlayers.get(i));
        }

        player.teleport(worldSpawn);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 3));
        player.getInventory().clear();

        Lazy_core.notLoggedIn.add(player);

        player.setInvulnerable(true);

        if (FileConfigurator.getCredentials().contains(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "/login {password}");
        } else {
            player.sendMessage(ChatColor.RED + "/register {password}");
        }
    }
}
