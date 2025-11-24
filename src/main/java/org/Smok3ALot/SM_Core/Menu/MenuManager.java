package org.Smok3ALot.SM_Core.Menu;

import org.Smok3ALot.SM_Core.SM_Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.HashMap;
import java.util.Map;

public class MenuManager implements Listener {

    private final SM_Core core;
    private final Map<String, Menu> menus = new HashMap<>();

    public MenuManager(SM_Core core) {
        this.core = core;
    }

    public void registerMenu(Menu menu) {
        menus.put(menu.getTitle(), menu);
    }

    public Menu getMenu(String title) {
        return menus.get(title);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        Menu menu = menus.get(title);

        if (menu != null) {
            // Blockiere ALLES
            event.setCancelled(true);

            // Zusätzliche Sicherheit: Shift-Klick & Hotbar
            if (event.isShiftClick() || event.getClick().isKeyboardClick()) {
                event.setCancelled(true);
                return;
            }

            // Nur Menü-Slots behandeln
            if (event.getRawSlot() < event.getInventory().getSize()) {
                event.setCancelled(true);
                menu.handleClick(event);
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        String title = event.getView().getTitle();
        if (menus.containsKey(title)) {
            event.setCancelled(true);
        }
    }
}
