package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.setInvulnerable(!(player.isInvulnerable()));

                if (player.isInvulnerable()) {
                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now " + ChatColor.RED + "invulnerable");
                } else {
                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now " + ChatColor.RED + "not invulnerable");
                }
            } else {
                Bukkit.getLogger().info("[ Lazy Core ] Incorrect command usage. Supposed to be : /god {playerName} ");
            }
        } else if (args.length >= 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                for (String playerName : args) {
                    Player trialPlayer = Bukkit.getPlayer(playerName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.setInvulnerable(!(trialPlayer.isInvulnerable()));

                        if (trialPlayer.isInvulnerable()) {
                            trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now " + ChatColor.RED + "invulnerable");
                            player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + trialPlayer.getName() + ChatColor.YELLOW + " is now" + ChatColor.RED + " invulnerable");
                        } else {
                            trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now " + ChatColor.RED + "not invulnerable");
                            player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + trialPlayer.getName() + ChatColor.YELLOW + " is now" + ChatColor.RED + " not invulnerable");
                        }
                    }
                }
            } else {
                for (String playerName : args) {
                    Player trialPlayer = Bukkit.getPlayer(playerName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.setInvulnerable(!(trialPlayer.isInvulnerable()));

                        if (trialPlayer.isInvulnerable()) {
                            trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now " + ChatColor.RED + "invulnerable");
                            Bukkit.getLogger().info("[ Lazy Core ] " + trialPlayer.getName() + " is now" + " invulnerable");
                        } else {
                            trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] You are now " + ChatColor.RED + "not invulnerable");
                            Bukkit.getLogger().info("[ Lazy Core ] " + trialPlayer.getName() + " is now" + " not invulnerable");
                        }
                    }
                }
            }
        }

        return true;
    }
}
