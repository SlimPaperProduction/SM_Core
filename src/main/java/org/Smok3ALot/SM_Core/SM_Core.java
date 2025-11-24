package org.Smok3ALot.SM_Core;

import org.Smok3ALot.SM_Core.Cache.CacheManager;
import org.Smok3ALot.SM_Core.Command.CommandManager;
import org.Smok3ALot.SM_Core.Config.ConfigManager;
import org.Smok3ALot.SM_Core.Database.DatabaseManager;
import org.Smok3ALot.SM_Core.EventBus.EventBus;
import org.Smok3ALot.SM_Core.Logging.ErrorHandler;
import org.Smok3ALot.SM_Core.Menu.MenuManager;
import org.Smok3ALot.SM_Core.Message.MessageManager;
import org.Smok3ALot.SM_Core.Module.ModuleRegistry;
import org.Smok3ALot.SM_Core.Permission.PermissionManager;
import org.Smok3ALot.SM_Core.Player.EconomyManager;
import org.Smok3ALot.SM_Core.Player.PlayerDataManager;
import org.Smok3ALot.SM_Core.Task.TaskManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SM_Core extends JavaPlugin {

    private static SM_Core core;
    private DatabaseManager databaseManager;
    private PlayerDataManager playerDataManager;
    private PermissionManager permissionManager;
    private ConfigManager configManager;
    private CommandManager commandManager;
    private ModuleRegistry moduleRegistry;
    private CacheManager cacheManager;
    private TaskManager taskManager;
    private ErrorHandler errorHandler;
    private MenuManager menuManager;
    private MessageManager messageManager;
    private EventBus eventBus;
    private EconomyManager economyManager;

    @Override
    public void onEnable() {
        core = this;
        saveDefaultConfig();

        databaseManager = new DatabaseManager(
                core,
                getConfig().getString("database.user"),
                getConfig().getString("database.password"),
                getConfig().getString("database.name"),
                getConfig().getString("database.host"),
                String.format("%d", getConfig().getInt("database.port"))
        );
        playerDataManager = new PlayerDataManager(this);
        permissionManager = new PermissionManager(this);
        configManager = new ConfigManager(this);
        commandManager = new CommandManager(this);
        moduleRegistry = new ModuleRegistry(this);
        cacheManager = new CacheManager(this);
        taskManager = new TaskManager(this);
        errorHandler = new ErrorHandler(this, getLogger());
        menuManager = new MenuManager(this);
        messageManager = new MessageManager(this);
        eventBus = new EventBus();
        economyManager = new EconomyManager(this);

        getLogger().info("SM_Core loaded!");
    }

    public static SM_Core getInstance() { return core; }
    public DatabaseManager getDbManager() { return databaseManager; }
    public PlayerDataManager getPlayerDataManager() { return playerDataManager; }
    public PermissionManager getPermissionManager() { return permissionManager; }
    public ConfigManager getConfigManager() { return configManager; }
    public CommandManager getCommandManager() {return commandManager; }
    public ModuleRegistry getModuleRegistry() { return moduleRegistry; }
    public CacheManager getCacheManager() { return cacheManager; }
    public TaskManager getTaskManager() { return taskManager; }
    public ErrorHandler getErrorHandler() { return errorHandler; }
    public MenuManager getMenuManager() {return menuManager; }
    public MessageManager getMessageManager() {return messageManager;}
    public EventBus getEventBus() { return eventBus;}
    public EconomyManager getEconomyManager() { return economyManager; }
}

