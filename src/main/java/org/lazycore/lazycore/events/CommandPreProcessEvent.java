package org.lazycore.lazycore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.lazycore.lazycore.Lazy_core;

public class CommandPreProcessEvent implements Listener {
    public CommandPreProcessEvent(Lazy_core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerCommandPreProcessEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String command = event.getMessage().split(" ")[0].substring(1);

        if (Lazy_core.notLoggedIn.contains(player)) {
            if (!(command.equalsIgnoreCase("login") || command.equalsIgnoreCase("register") || command.equalsIgnoreCase("changepass") || command.equalsIgnoreCase("changepw") || command.equalsIgnoreCase("changepassword"))) {
                event.setCancelled(true);
            }
        }
    }
}
