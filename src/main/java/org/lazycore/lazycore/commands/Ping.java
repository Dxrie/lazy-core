package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                int ping = player.getPing();

                if (ping <= 85) {
                    player.sendMessage(ChatColor.GREEN + "" + ping + " ms");
                } else if (ping > 85 && ping <= 150) {
                    player.sendMessage(ChatColor.YELLOW + "" + ping + " ms");
                } else {
                    player.sendMessage(ChatColor.RED + "" + ping + " ms");
                }
            } else if (args.length == 1) {
                if (player.isOp()) {
                    Player trialPlayer = Bukkit.getPlayer(args[0]);

                    if (trialPlayer instanceof Player) {
                        int ping = trialPlayer.getPing();

                        if (ping <= 85) {
                            player.sendMessage(ChatColor.GREEN + "" + ping + " ms");
                        } else if (ping > 85 && ping <= 150) {
                            player.sendMessage(ChatColor.YELLOW + "" + ping + " ms");
                        } else {
                            player.sendMessage(ChatColor.RED + "" + ping + " ms");
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ]" + ChatColor.RED + " You don't have the required permission to check other's ping.");
                }
            } else {
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ]" + ChatColor.RED + " Incorrect command usage. Supposed to be : /ping or /ping {playerName}");
            }
        } else {
            if (args.length == 1) {
                Player trialPlayer = Bukkit.getPlayer(args[0]);

                if (trialPlayer instanceof Player) {
                    int ping = trialPlayer.getPing();

                    Bukkit.getLogger().info(ping + " ms");
                }
            } else {
                Bukkit.getLogger().info("[ Lazy Core ] Incorrect command usage. Supposed to be : /ping {playerName}");
            }
        }

        return true;
    }
}
