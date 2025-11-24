package org.Smok3ALot.SM_Core.Config;

import org.Smok3ALot.SM_Core.SM_Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final SM_Core core;
    private final Map<String, FileConfiguration> configs = new HashMap<>();
    private final Map<String, File> files = new HashMap<>();

    public ConfigManager(SM_Core core) {
        this.core = core;
    }

    // Config registrieren
    public void registerConfig(JavaPlugin plugin, String moduleName, String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        configs.put(moduleName, config);
        files.put(moduleName, file);
    }

    // Config abrufen
    public FileConfiguration getConfig(String moduleName) {
        return configs.get(moduleName);
    }

    // Config speichern
    public void saveConfig(String moduleName) {
        try {
            configs.get(moduleName).save(files.get(moduleName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Config neu laden
    public void reloadConfig(String moduleName) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(files.get(moduleName));
        configs.put(moduleName, config);
    }
}
