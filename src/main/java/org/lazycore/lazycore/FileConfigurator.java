package org.lazycore.lazycore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileConfigurator {
    public static File data = new File(Lazy_core.plugin.getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(data);

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void setup() {
        if (!data.exists()) {
            try {
                data.createNewFile();
            }catch (IOException e) {}
        }
    }

    public static void save() {
        try {
            config.save(data);
        } catch (IOException e) {}
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(data);
    }
}
