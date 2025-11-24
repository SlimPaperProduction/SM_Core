package org.Smok3ALot.SM_Core.Message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.Smok3ALot.SM_Core.SM_Core;

import java.util.Map;

public class MessageManager {

    private final SM_Core core;
    private final String prefix;

    public MessageManager(SM_Core core) {
        this.core = core;
        this.prefix = ChatColor.translateAlternateColorCodes('&',
                core.getConfig().getString("messages.prefix", "&7[&bSmokings&7] "));
    }

    // Nachricht an Spieler senden
    public void send(Player player, String module, String message) {
        player.sendMessage(formatMessage(module, message));
    }

    // Nachricht an CommandSender (Konsole oder Spieler)
    public void send(CommandSender sender, String module, String message) {
        sender.sendMessage(formatMessage(module, message));
    }

    // Nachricht mit Platzhaltern
    public void send(Player player, String module, String message, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        player.sendMessage(formatMessage(module, message));
    }

    public void broadcast(String module, String message) {
        // Nachricht an alle Spieler
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player, module, message);
        }

        // Nachricht zusätzlich in die Konsole schreiben
        core.getLogger().info(formatMessage(module, message));
    }

    // Formatierung
    private String formatMessage(String module, String message) {
        return prefix + ChatColor.GOLD + "[" + module + "] " + ChatColor.RESET +
                ChatColor.translateAlternateColorCodes('&', message);
    }
}
