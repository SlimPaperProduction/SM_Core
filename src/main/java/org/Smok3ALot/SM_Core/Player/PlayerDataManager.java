package org.Smok3ALot.SM_Core.Player;

import org.Smok3ALot.SM_Core.SM_Core;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataManager {

    private final SM_Core core;

    public PlayerDataManager(SM_Core core) {
        this.core = core;
        // Tabelle erstellen
        core.getDbManager().db_CreateTable(
                "player_data",
                "uuid VARCHAR(36), data_key VARCHAR(64), data_value TEXT, " +
                        "PRIMARY KEY (uuid, data_key)");
    }

    // Daten setzen
    public void setData(Player player, String key, String value) {
        core.getDbManager().db_Update(
                "INSERT INTO player_data (uuid, data_key, data_value) VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE data_value=?",
                player.getUniqueId().toString(), key, value, value
        );
    }

    // Daten abrufen
    public String getData(Player player, String key) {
        return core.getDbManager().db_Query(
                "SELECT data_value FROM player_data WHERE uuid=? AND data_key=?",
                rs -> {
                    if (rs != null && rs.next()) {
                        return rs.getString("data_value");
                    }
                    return null;
                },
                player.getUniqueId().toString(), key
        );
    }


    // Prüfen ob vorhanden
    public boolean hasData(Player player, String key) {
        return getData(player, key) != null;
    }

    // Daten löschen
    public void removeData(Player player, String key) {
        core.getDbManager().db_Update(
                "DELETE FROM player_data WHERE uuid=? AND data_key=?",
                player.getUniqueId().toString(), key
        );
    }

    // Alle Daten eines Spielers laden
    public Map<String, String> getAllData(Player player) {
        return core.getDbManager().db_Query(
                "SELECT data_key, data_value FROM player_data WHERE uuid=?",
                rs -> {
                    Map<String, String> data = new HashMap<>();
                    while (rs != null && rs.next()) {
                        data.put(rs.getString("data_key"), rs.getString("data_value"));
                    }
                    return data;
                },
                player.getUniqueId().toString()
        );
    }
}
