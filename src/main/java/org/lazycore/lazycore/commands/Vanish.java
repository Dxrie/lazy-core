package org.lazycore.lazycore.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.lazycore.lazycore.Lazy_core;

public class Vanish implements CommandExecutor {

    Lazy_core plugin;

    public Vanish(Lazy_core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (Lazy_core.invisiblePlayers.contains(player)) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(plugin, player);

                        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, (Component) null);

                        playerJoinEvent.setJoinMessage(null);
                        players.sendMessage(playerJoinEvent.getJoinMessage());
                    }

                    Lazy_core.invisiblePlayers.remove(player);
                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now visible to the whole server");
                } else {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (!(players.isOp())) {
                            players.hidePlayer(plugin, player);
                        } else {
                            players.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Player " + player.getName() + " has enabled vanish.");
                        }

                        PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(player, (Component) null);
                        playerQuitEvent.setQuitMessage(null);
                        players.sendMessage(playerQuitEvent.getQuitMessage());
                    }

                    Lazy_core.invisiblePlayers.add(player);
                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now invisible to the whole server");
                }
            } else if (args.length == 1) {
                Player trialPlayer = Bukkit.getPlayer(args[0]);

                if (trialPlayer instanceof Player) {
                    if (Lazy_core.invisiblePlayers.contains(trialPlayer)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.showPlayer(plugin, trialPlayer);

                            PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(trialPlayer, (Component) null);

                            playerJoinEvent.setJoinMessage(null);
                            players.sendMessage(playerJoinEvent.getJoinMessage());
                        }

                        Lazy_core.invisiblePlayers.remove(trialPlayer);
                        trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now visible to the whole server");
                    } else {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players.isOp())) {
                                players.hidePlayer(plugin, trialPlayer);
                            } else {
                                players.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Player " + trialPlayer.getName() + " has enabled vanish.");
                            }

                            PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(trialPlayer, (Component) null);
                            playerQuitEvent.setQuitMessage(null);

                            players.sendMessage(playerQuitEvent.getQuitMessage());
                        }

                        Lazy_core.invisiblePlayers.add(trialPlayer);
                        trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now invisible to the whole server");
                    }
                }
            }
        } else {
            Bukkit.getLogger().info("[ Lazy Core ] Console can't use this command.");
        }

        return true;
    }
}
