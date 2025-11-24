package org.Smok3ALot.SM_Core.Module;

import org.Smok3ALot.SM_Core.SM_Core;

import java.util.*;

public class ModuleRegistry {

    private SM_Core core;
    private final Map<String, ModuleInfo> modules = new HashMap<>();

    public ModuleRegistry(SM_Core core) {
        this.core = core;
    }

    // Modul registrieren
    public void registerModule(String name, String version) {
        modules.put(name.toLowerCase(), new ModuleInfo(name, version, true));
        System.out.println("[SM_Core] Modul registriert: " + name + " v" + version);
    }

    // Modul deregistrieren
    public void unregisterModule(String name) {
        modules.remove(name.toLowerCase());
        System.out.println("[SM_Core] Modul entfernt: " + name);
    }

    // Liste aller Module
    public Collection<ModuleInfo> listModules() {
        return modules.values();
    }

    // Modul-Info abrufen
    public ModuleInfo getModule(String name) {
        return modules.get(name.toLowerCase());
    }

    // Modul aktivieren/deaktivieren
    public void setActive(String name, boolean active) {
        ModuleInfo info = modules.get(name.toLowerCase());
        if (info != null) {
            info.setActive(active);
        }
    }

    // Innere Klasse für Modul-Infos
    public static class ModuleInfo {
        private final String name;
        private final String version;
        private boolean active;

        public ModuleInfo(String name, String version, boolean active) {
            this.name = name;
            this.version = version;
            this.active = active;
        }

        public String getName() { return name; }
        public String getVersion() { return version; }
        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
    }
}
