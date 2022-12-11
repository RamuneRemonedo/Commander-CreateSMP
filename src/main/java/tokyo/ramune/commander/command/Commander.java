package tokyo.ramune.commander.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.commander.CommanderPlugin;
import tokyo.ramune.commander.database.DatabaseManager;

import javax.annotation.Nonnull;

public class Commander {
    public Commander() {
    }

    public void runCommand(@Nonnull Player player) {
        DatabaseManager databaseManager = CommanderPlugin.getInstance().getDatabaseManager();

        if (databaseManager.isExecutedToday(player.getUniqueId()))
            return;

        CommanderPlugin plugin = CommanderPlugin.getInstance();

        String command =
                CommanderPlugin.getInstance().getConfig()
                        .getString("config.run-command", "tellraw {player_name} \"Hi server manager, §aCommander plugin is actually working now! §cPlease change the config\"");

        if (plugin.getConfig().getBoolean("config.run-as-player", true)) {
            command = command.replace("{player_name}", player.getName());
            player.performCommand(command);
        } else {
            command = command.replace("{player_name}", "");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
        }
        databaseManager.setExecutedToday(player.getUniqueId(), true);
    }
}
