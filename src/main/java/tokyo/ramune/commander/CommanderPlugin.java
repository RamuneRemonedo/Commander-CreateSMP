package tokyo.ramune.commander;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.commander.command.Commander;
import tokyo.ramune.commander.database.DatabaseManager;
import tokyo.ramune.commander.listener.PlayerJoinListener;
import tokyo.ramune.commander.schedule.ScheduleManager;

public final class CommanderPlugin extends JavaPlugin {
    private static CommanderPlugin instance;

    private DatabaseManager databaseManager;
    private ScheduleManager scheduleManager;
    private Commander commander;

    public static CommanderPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        databaseManager = new DatabaseManager();
        scheduleManager = new ScheduleManager();
        scheduleManager.start(databaseManager::reset);

        commander = new Commander();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        scheduleManager.stop();

        getLogger().info("The plugin has been disabled.");
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public Commander getCommander() {
        return commander;
    }
}
