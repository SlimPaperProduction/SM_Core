package org.Smok3ALot.SM_Core.Database;

import org.Smok3ALot.SM_Core.SM_Core;

import java.sql.*;

public class DatabaseManager {

    private SM_Core coreInstance;
    private Connection connection;
    private String databaseUser;
    private String databasePassword;
    private String databaseName;
    private String databaseHost;
    private String databasePort;

    public DatabaseManager(SM_Core core, String dbUser, String dbPassword, String dbName, String dbHost, String dbPort) {
        this.coreInstance = core;
        this.databaseUser = dbUser;
        this.databasePassword = dbPassword;
        this.databaseName = dbName;
        this.databaseHost = dbHost;
        this.databasePort = dbPort;
        String url = "jdbc:mysql://" + this.databaseHost + ":" + this.databasePort + "/" + this.databaseName;
        try {
            connection = DriverManager.getConnection(url, this.databaseUser, this.databasePassword);
            this.coreInstance.getLogger().info("Database Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void reconnect() {
        String url = "jdbc:mysql://" + this.databaseHost + ":" + this.databasePort + "/" + this.databaseName + "?autoReconnect=true&useSSL=false";
        try {
            connection = DriverManager.getConnection(url, this.databaseUser, this.databasePassword);
            coreInstance.getLogger().info("Database Reconnected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tabelle erstellen
    public void db_CreateTable(String tableName, String tableParams) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableParams + ")";
        if (connection == null) {
            coreInstance.getLogger().severe("Keine Datenbankverbindung!");
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update / Insert
    public void db_Update(String sql, Object... params) {
        if (connection == null) {
            coreInstance.getLogger().severe("Keine Datenbankverbindung!");
            return;
        }
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select
    public <T> T db_Query(String sql, ResultSetHandler<T> handler, Object... params) {
        if (connection == null) return null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                return handler.handle(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
