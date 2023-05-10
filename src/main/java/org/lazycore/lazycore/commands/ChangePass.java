package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lazycore.lazycore.FileConfigurator;

public class ChangePass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2) {
                String UUID = player.getUniqueId().toString();

                if (FileConfigurator.getCredentials().contains(UUID)) {
                    String oldPass = args[0];
                    String newPass = args[1];

                    if (oldPass.equals(FileConfigurator.getCredentials().get(UUID))) {
                        FileConfigurator.getCredentials().set(UUID, newPass);
                        FileConfigurator.saveCredentials();
                        FileConfigurator.reloadCredentials();
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid password, please try again.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You are not registered, please register your account first. /register {password}");
                }
            } else {
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ]" + ChatColor.RED + " Incorrect command usage. Supposed to be : /changepass {oldPassword} {newPassword}");
            }
        } else {
            Bukkit.getLogger().info("Only players are allowed to use this command.");
        }

        return true;
    }
}
