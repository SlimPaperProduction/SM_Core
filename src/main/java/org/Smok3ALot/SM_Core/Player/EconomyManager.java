package org.Smok3ALot.SM_Core.Player;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyManager {

    private final JavaPlugin plugin;
    private Economy economy;

    public EconomyManager(JavaPlugin plugin) {
        this.plugin = plugin;
        setupEconomy();
    }

    private void setupEconomy() {
        Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
        if (vault != null) {
            economy = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
            plugin.getLogger().info("Vault-Economy angebunden: " + economy.getName());
        } else {
            plugin.getLogger().warning("Vault nicht gefunden – Essentials Economy nicht verfügbar!");
        }
    }

    public boolean isAvailable() {
        return economy != null;
    }

    public double getBalance(Player player) {
        return isAvailable() ? economy.getBalance(player) : 0.0;
    }

    public boolean withdraw(Player player, double amount) {
        if (!isAvailable()) return false;

        OfflinePlayer offline = player; // Player implementiert OfflinePlayer
        if (economy.has(offline, amount)) {
            EconomyResponse resp = economy.withdrawPlayer(offline, amount);
            return resp.transactionSuccess();
        }
        return false;
    }

    public boolean deposit(Player player, double amount) {
        if (!isAvailable()) return false;

        OfflinePlayer offline = player;
        EconomyResponse resp = economy.depositPlayer(offline, amount);
        return resp.transactionSuccess();
    }
}
