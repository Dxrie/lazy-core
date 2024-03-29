package org.lazycore.lazycore;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.lazycore.lazycore.commands.*;
import org.lazycore.lazycore.events.CommandPreProcessEvent;
import org.lazycore.lazycore.events.PlayerJoinEvent;
import org.lazycore.lazycore.events.PlayerMoveEvent;
import org.lazycore.lazycore.events.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

public final class Lazy_core extends JavaPlugin {

    public static ArrayList<Player> invisiblePlayers = new ArrayList<>();
    public static ArrayList<Player> notLoggedIn = new ArrayList<>();
    public static Lazy_core plugin;
    private static Economy econ = null;

    @Override
    public void onEnable() {
        plugin = Lazy_core.this;

        new org.lazycore.lazycore.commands.Vanish(this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        FileConfigurator.setup();
        FileConfigurator.credentialSetup();

        Bukkit.getLogger().info("[ Lazy Core ] Lazy Core Version 1.0 has been loaded.");
        Bukkit.getLogger().info("[ Lazy Core ] Please contact plugin developer if you encountered a problem.");

        getCommand("gma").setExecutor(new GMA());
        getCommand("gmc").setExecutor(new GMC());
        getCommand("gms").setExecutor(new GMS());
        getCommand("gmsp").setExecutor(new GMSP());
        getCommand("ping").setExecutor(new Ping());
        getCommand("heal").setExecutor(new Heal());
        getCommand("vanish").setExecutor(new Vanish(this));
        getCommand("fly").setExecutor(new Fly());
        getCommand("god").setExecutor(new GodMode());
        getCommand("sellxp").setExecutor(new SellXP());
        getCommand("reload").setExecutor(new Reload());
        getCommand("xp").setExecutor(new XP());
        getCommand("register").setExecutor(new Register());
        getCommand("login").setExecutor(new Login());
        getCommand("security").setExecutor(new Security());
        getCommand("changepass").setExecutor(new ChangePass());

        new PlayerJoinEvent(plugin);
        new PlayerMoveEvent(plugin);
        new CommandPreProcessEvent(plugin);
        new PlayerQuitEvent(plugin);

        if (!setupEconomy() ) {
            Bukkit.getLogger().info("No economy plugin found. Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[ Lazy Core ] Shutting Lazy Core Down...");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
