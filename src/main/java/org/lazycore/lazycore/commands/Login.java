package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.lazycore.lazycore.FileConfigurator;
import org.lazycore.lazycore.Lazy_core;
import org.lazycore.lazycore.events.PlayerJoinEvent;

public class Login implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            int tries = 0;

            if (args.length == 1) {
                if (FileConfigurator.getCredentials().contains(player.getUniqueId().toString())) {
                    if (args[0].equals(FileConfigurator.getCredentials().get(player.getUniqueId().toString()))) {
                        Lazy_core.notLoggedIn.remove(player);

                        player.setInvulnerable(false);

                        player.teleport(PlayerJoinEvent.currentLocation);
                        player.getInventory().setArmorContents(PlayerJoinEvent.armorContent);
                        player.getInventory().setContents(PlayerJoinEvent.inventoryContent);

                        for (PotionEffect effect : player.getActivePotionEffects()) {
                            player.removePotionEffect(effect.getType());
                        }

                        player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Your account has successfully been logged in.");
                    } else {
                        if (tries < 5) {
                            player.sendMessage(ChatColor.RED + "Invalid password, please try again");
                            tries += 1;
                        } else {
                            player.kickPlayer("5 Times login failure");
                            tries = 0;
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You are not registered, please register your account first. /register {password}");
                }
            } else {
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ]" + ChatColor.RED + " Incorrect command usage. Supposed to be : /login {password}");
            }
        } else {
            Bukkit.getLogger().info("Only players are allowed to use this command.");
        }

        return true;
    }
}
