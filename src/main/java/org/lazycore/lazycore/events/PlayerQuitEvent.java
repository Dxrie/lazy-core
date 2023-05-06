package org.lazycore.lazycore.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.lazycore.lazycore.Lazy_core;

public class PlayerQuitEvent implements Listener {
    Lazy_core plugin;

    public PlayerQuitEvent(Lazy_core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
    }


    @EventHandler
    public void playerQuitEvent(org.bukkit.event.player.PlayerQuitEvent event) {
        Player player = event.getPlayer();

        for (int i = 0; i < Lazy_core.invisiblePlayers.size(); i++) {
            player.showPlayer(plugin, Lazy_core.invisiblePlayers.get(i));
        }
    }
}
