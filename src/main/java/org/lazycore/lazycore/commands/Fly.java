package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.setAllowFlight(!(player.getAllowFlight()));
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Flying status is " + ChatColor.RED + player.getAllowFlight());
            } else {
                Bukkit.getLogger().info("[ Lazy Core ] Incorrect command usage. Supposed to be : /fly {playerName} ");
            }
        } else if (args.length >= 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                for (String playerName : args) {
                    Player trialPlayer = Bukkit.getPlayer(playerName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.setAllowFlight(!(trialPlayer.getAllowFlight()));
                        player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Flying status for " + trialPlayer.getName() + " is " + ChatColor.RED + trialPlayer.getAllowFlight());
                        trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Flying status is " + ChatColor.RED + trialPlayer.getAllowFlight());
                    }
                }
            } else {
                for (String playerName : args) {
                    Player trialPlayer = Bukkit.getPlayer(playerName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.setFlying(!(trialPlayer.isFlying()));
                        Bukkit.getLogger().info("[ Lazy Core ] Flying status for " + trialPlayer.getName() + " is " + trialPlayer.getAllowFlight());
                        trialPlayer.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Flying status is " + ChatColor.RED + trialPlayer.getAllowFlight());
                    }
                }
            }
        }

        return true;
    }
}
