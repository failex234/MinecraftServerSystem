package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Teleports one player to sender
 * <p>
 *     Permissions:
 *     - serversystem.tphere
 * </p>
 */
public class Tphere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.tphere")) {
            if (args.length == 0) {
                sender.sendMessage(Strings.TPHERE_NO_ARGS.getString());
                return false;
            }

            Player victim = Bukkit.getPlayer(args[0]);

            //Check if player is online / exists
            if (victim == null) {
                sender.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                return false;
            }

            Player current = (Player) sender;
            victim.teleport(current);
            current.sendMessage(Strings.TP_TELEPORTING.getString());

            return true;
        } else {
            ServerSystem.onUnknownCommand((Player)sender, "tpahere");
            return false;
        }

    }
}
