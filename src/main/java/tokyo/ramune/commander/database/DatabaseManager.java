package tokyo.ramune.commander.database;

import tokyo.ramune.commander.CommanderPlugin;
import tokyo.ramune.commander.config.Config;

import javax.annotation.Nonnull;
import java.util.UUID;

public class DatabaseManager {
    private final Config database;

    public DatabaseManager() {
        database = new Config(CommanderPlugin.getInstance(), "database.yml");

        database.saveDefaultConfig();
    }

    public void setExecutedToday(@Nonnull UUID playerUniqueId, boolean value) {
        database.getConfig().set("database." + playerUniqueId, value);
        database.saveConfig();
    }

    public boolean isExecutedToday(@Nonnull UUID playerUniqueId) {
        return database.getConfig().getBoolean("database." + playerUniqueId, false);
    }

    public void reset() {
        database.getConfig().set("database", null);
        database.saveConfig();
    }
}
