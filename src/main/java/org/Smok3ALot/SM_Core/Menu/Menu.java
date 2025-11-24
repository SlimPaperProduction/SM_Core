package org.Smok3ALot.SM_Core.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private final String title;
    private final int rows;
    private final Map<Integer, MenuButton> buttons = new HashMap<>();
    private final ItemStack filler;
    private Inventory inventory;

    public Menu(String title, int rows) {
        this.title = title;
        this.rows = rows;
        this.filler = createFiller();
    }

    private ItemStack createFiller() {
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public void setButton(int slot, MenuButton button) {
        buttons.put(slot, button);
    }

    public void open(Player player) {
        // Klassisches Bukkit-Inventar
        inventory = Bukkit.createInventory(null, rows * 9, title);

        // Erst mit Filler füllen
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, filler);
        }

        // Buttons einsetzen
        for (Map.Entry<Integer, MenuButton> entry : buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        }

        // Öffnen
        player.openInventory(inventory);
    }

    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        int slot = event.getRawSlot();

        if (buttons.containsKey(slot)) {
            event.setCancelled(true); // GANZ wichtig: erneut canceln
            buttons.get(slot).onClick(player);
        } else {
            event.setCancelled(true); // auch Filler blockieren
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }
}
