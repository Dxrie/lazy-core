package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "Set gamemode " + ChatColor.RED + "creative" + ChatColor.YELLOW + " for " + ChatColor.RED + player.getName());
                player.setGameMode(GameMode.CREATIVE);
            } else if (args.length >= 1) {
                for (String getName : args) {
                    Player trialPlayer = Bukkit.getPlayer(getName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.sendMessage(ChatColor.YELLOW + "Set gamemode " + ChatColor.RED + "creative" + ChatColor.YELLOW + " for " + ChatColor.RED + trialPlayer.getName());
                        trialPlayer.setGameMode(GameMode.CREATIVE);
                    }
                }
            }
        } else {
            if (args.length >= 1) {
                for (String getName : args) {
                    Player trialPlayer = Bukkit.getPlayer(getName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.sendMessage(ChatColor.YELLOW + "Set gamemode " + ChatColor.RED + "creative" + ChatColor.YELLOW + " for " + ChatColor.RED + trialPlayer.getName());
                        trialPlayer.setGameMode(GameMode.CREATIVE);
                    }
                }
            } else {
                Bukkit.getLogger().info("[ Lazy Core ] Incorrect command usage. Supposed to be : /gmc {playerName} ");
            }
        }

        return true;
    }
}