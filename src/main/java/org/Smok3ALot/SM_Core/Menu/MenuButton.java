package org.Smok3ALot.SM_Core.Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class MenuButton {

    private final ItemStack item;
    private final Consumer<Player> action;

    public MenuButton(Material material, String name, Consumer<Player> action) {
        this.item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        this.action = action;
    }

    public MenuButton(ItemStack item, String name, Consumer<Player> action) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        this.item = item;
        this.action = action;
    }

    public ItemStack getItem() {
        return item;
    }

    public void onClick(Player player) {
        action.accept(player);
    }
}
