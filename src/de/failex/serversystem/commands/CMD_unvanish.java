package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Unvanishes a vanished player
 * <p>
 *     Permissions:
 *     - serversystem.vanish
 * </p>
 */
public class CMD_unvanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.vanish")) {
            //Check if player is vanished
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "unvanish");
        }
    }
}
