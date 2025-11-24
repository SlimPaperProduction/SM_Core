package org.Smok3ALot.SM_Core.Task;

import org.Smok3ALot.SM_Core.SM_Core;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private final SM_Core core;
    private final Map<String, Integer> tasks = new HashMap<>();

    public TaskManager(SM_Core core) {
        this.core = core;
    }

    // Einmalige Aufgabe nach Delay
    public void scheduleLater(String name, Runnable task, long delayTicks) {
        int id = Bukkit.getScheduler().scheduleSyncDelayedTask(core, task, delayTicks);
        tasks.put(name, id);
    }

    // Wiederkehrende Aufgabe
    public void scheduleRepeating(String name, Runnable task, long delayTicks, long periodTicks) {
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(core, task, delayTicks, periodTicks);
        tasks.put(name, id);
    }

    // Aufgabe abbrechen
    public void cancel(String name) {
        Integer id = tasks.remove(name);
        if (id != null) {
            Bukkit.getScheduler().cancelTask(id);
        }
    }

    // Alle Aufgaben abbrechen
    public void cancelAll() {
        for (Integer id : tasks.values()) {
            Bukkit.getScheduler().cancelTask(id);
        }
        tasks.clear();
    }

    // Prüfen ob Task läuft
    public boolean isRunning(String name) {
        return tasks.containsKey(name);
    }
}
