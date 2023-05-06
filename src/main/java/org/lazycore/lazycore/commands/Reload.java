package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.lazycore.lazycore.FileConfigurator;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {
                FileConfigurator.reload();
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] config.yml reload successful.");
            }
        } else {
            FileConfigurator.reload();
            Bukkit.getLogger().info("[ Lazy Core ] config.yml reload successful.");
        }

        return true;
    }
}
