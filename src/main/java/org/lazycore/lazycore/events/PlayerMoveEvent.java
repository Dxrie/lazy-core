package org.lazycore.lazycore.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.lazycore.lazycore.Lazy_core;

public class PlayerMoveEvent implements Listener {
    public PlayerMoveEvent(Lazy_core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (Lazy_core.notLoggedIn.contains(player)) {
            event.setCancelled(true);
        }
    }
}
