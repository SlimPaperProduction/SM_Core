package org.Smok3ALot.SM_Core.Permission;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.Smok3ALot.SM_Core.SM_Core;
import org.bukkit.entity.Player;

public class PermissionManager {

    private SM_Core core;
    private final LuckPerms luckPerms;

    public PermissionManager(SM_Core core) {
        this.core = core;
        this.luckPerms = LuckPermsProvider.get();
    }

    // Prüfen ob Spieler eine Permission hat
    public boolean hasPermission(Player player, String node) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return false;
        return user.getCachedData().getPermissionData().checkPermission(node).asBoolean();
    }

    // Permission setzen (z. B. für temporäre Rechte)
    public void setPermission(Player player, String node, boolean value) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return;
        Node permissionNode = Node.builder(node).value(value).build();
        user.data().add(permissionNode);
        luckPerms.getUserManager().saveUser(user);
    }
}
