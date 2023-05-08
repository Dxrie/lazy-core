package org.lazycore.lazycore.commands;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.lazycore.lazycore.Lazy_core;

public class SellXP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Economy economy = Lazy_core.getEconomy();

            if (args.length == 1) {
                Integer sellXP = Integer.parseInt(args[0]);
                int currentXP = player.getTotalExperience();

                if (sellXP <= player.getTotalExperience()) {
                    player.setTotalExperience(0);
                    player.setLevel(0);
                    player.setExp(0);
                    player.giveExp(currentXP - sellXP, false);
                    economy.depositPlayer(player, sellXP * Lazy_core.plugin.getConfig().getInt("xpPrice"));
                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Successfully sold " + ChatColor.RED + sellXP + ChatColor.YELLOW + " XP(s) for " + ChatColor.RED + sellXP * Lazy_core.plugin.getConfig().getInt("xpPrice") + ChatColor.YELLOW + " in total.");
                }
            } else {
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + "Incorrect command usage. Supposed to be : /sellxp {totalAmount}");
            }
        } else {
            Bukkit.getLogger().info("[ Lazy Core ] Incorrect command usage. Supposed to be : /sellxp {totalAmount}");
        }

        return true;
    }
}
