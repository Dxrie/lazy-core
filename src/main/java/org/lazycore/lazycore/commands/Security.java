package org.lazycore.lazycore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lazycore.lazycore.FileConfigurator;
import org.lazycore.lazycore.Lazy_core;

public class Security implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.GOLD + "/security changepass {username} {newpass}");
                    player.sendMessage(ChatColor.GOLD + "/securirty rmpass {username}");
                    player.sendMessage(ChatColor.GOLD + "/security reload");
                } else {
                    if (args[0].equals("changepass")) {
                        if (args.length == 3) {
                            String newPass = args[2];
                            Player trialPlayer = Bukkit.getPlayerExact(args[1]);

                            if (trialPlayer instanceof Player) {
                                String UUID = trialPlayer.getUniqueId().toString();

                                if (FileConfigurator.getCredentials().contains(UUID)) {
                                    FileConfigurator.getCredentials().set(UUID, newPass);
                                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] Successfully changed password for player " + trialPlayer.getName() + ".");
                                    if (trialPlayer.isOnline()) {
                                        trialPlayer.kickPlayer("Please rejoin the server.");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + "That player isn't registered.");
                                }
                            } else {
                                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + "Player not found.");
                            }
                        } else {
                            player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + "Incorrect command usage. Supposed to be : /security changepass {playerName} {newPassword}");
                        }
                    } else if (args[0].equals("rmpass")) {
                        Player trialPlayer = Bukkit.getPlayerExact(args[0]);

                        if (trialPlayer instanceof Player) {
                            String UUID = trialPlayer.getUniqueId().toString();

                            if (FileConfigurator.getCredentials().contains(UUID)) {
                                FileConfigurator.getCredentials().set(UUID, null);
                                FileConfigurator.saveCredentials();
                                FileConfigurator.reloadCredentials();

                                if (trialPlayer.isOnline()) {
                                    trialPlayer.kickPlayer("Please rejoin the server.");
                                }
                            } else {
                                player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + "That player isn't registered.");
                            }
                        } else {
                            player.sendMessage(ChatColor.YELLOW + "[ Lazy Core ] " + ChatColor.RED + "Player not found.");
                        }
                    } else if (args[0].equals("reload")) {
                        FileConfigurator.reloadCredentials();
                        player.sendMessage(ChatColor.GOLD + "Reloaded.");
                    } else {
                        player.sendMessage(ChatColor.GOLD + "/security changepass {username} {newpass}");
                        player.sendMessage(ChatColor.GOLD + "/securirty rmpass {username}");
                        player.sendMessage(ChatColor.GOLD + "/security reload");
                    }
                }
            } else {
                player.sendMessage("You don't have the required permission to use this command");
            }
        } else {
            if (args.length > 0) {
                if (args[0].equals("reload")) {
                    FileConfigurator.reloadCredentials();
                    Bukkit.getLogger().info("Reloaded.");
                } else {
                    Bukkit.getLogger().info("The command you're using is either a player only command or it's not available.");
                }
            } else {
                Bukkit.getLogger().info("/security reload");
            }
        }

        return true;
    }
}
