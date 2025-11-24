package org.Smok3ALot.SM_Core.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final JavaPlugin core;
    private final Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandManager(JavaPlugin core) {
        this.core = core;
    }

    // Command registrieren
    public void registerCommand(JavaPlugin plugin, String name, CommandExecutor executor) {
        PluginCommand cmd = plugin.getCommand(name);
        if (cmd != null) {
            cmd.setExecutor(executor);
            commands.put(name, executor);
            plugin.getLogger().info("Command registriert: /" + name);
        } else {
            plugin.getLogger().warning("Command nicht in plugin.yml gefunden: " + name);
        }
    }

    // Command entfernen
    public void unregisterCommand(String name) {
        commands.remove(name);
        core.getLogger().info("Command entfernt: /" + name);
    }

    // Alle Commands auflisten
    public Map<String, CommandExecutor> listCommands() {
        return commands;
    }
}
