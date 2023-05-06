package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.setHealth(player.getHealthScale());

                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            } else if (args.length >= 1) {
                for (String getName : args) {
                    Player trialPlayer = Bukkit.getPlayer(getName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.setHealth(trialPlayer.getHealthScale());

                        for (PotionEffect effect : trialPlayer.getActivePotionEffects()) {
                            trialPlayer.removePotionEffect(effect.getType());
                        }
                    }
                }
            }
        } else {
            if (args.length >= 1) {
                for (String getName : args) {
                    Player trialPlayer = Bukkit.getPlayer(getName);

                    if (trialPlayer instanceof Player) {
                        trialPlayer.setHealth(trialPlayer.getHealthScale());

                        for (PotionEffect effect : trialPlayer.getActivePotionEffects()) {
                            trialPlayer.removePotionEffect(effect.getType());
                        }
                    }
                }
            }
        }

        return true;
    }
}