package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Total XP : " + ChatColor.RED + player.getTotalExperience());
            } else {
                if (player.isOp()) {
                    for (String playerName : args) {
                        Player trialPlayer = Bukkit.getPlayer(playerName);

                        if (trialPlayer instanceof Player) {
                            player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Total XP : " + ChatColor.RED + trialPlayer.getTotalExperience());
                        }
                    }
                }
            }
        } else {
            if (args.length == 0) {
                Bukkit.getLogger().info("[ Lazy Core ] Incorrect command usage. Supposed to be : /xp {playerName} ");
            } else {
                for (String playerName : args) {
                    Player trialPlayer = Bukkit.getPlayer(playerName);

                    if (trialPlayer instanceof Player) {
                        Bukkit.getLogger().info("[ Lazy Core ] Total XP : " + trialPlayer.getTotalExperience());
                    }
                }
            }
        }

        return true;
    }
}
