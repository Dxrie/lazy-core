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

public class Register implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (FileConfigurator.getCredentials().contains(player.getUniqueId().toString())) {
                    player.sendMessage(ChatColor.RED + "You are already registered, please use /login {password}.");
                } else {
                    String password = args[0];
                    String UUID = player.getUniqueId().toString();

                    FileConfigurator.getCredentials().set(UUID, password);
                    FileConfigurator.saveCredentials();
                    FileConfigurator.reloadCredentials();

                    Lazy_core.notLoggedIn.remove(player);

                    player.setInvulnerable(false);

                    player.teleport(PlayerJoinEvent.currentLocation);
                    player.getInventory().setArmorContents(PlayerJoinEvent.armorContent);
                    player.getInventory().setContents(PlayerJoinEvent.inventoryContent);

                    for (PotionEffect effect : player.getActivePotionEffects()) {
                        player.removePotionEffect(effect.getType());
                    }

                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Your account has successfully been registered.");
                }
            } else {
                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ]" + ChatColor.RED + " Incorrect command usage. Supposed to be : /register {password}");
            }
        } else {
            Bukkit.getLogger().info("Only players are allowed to use this command.");
        }

        return true;
    }
}
