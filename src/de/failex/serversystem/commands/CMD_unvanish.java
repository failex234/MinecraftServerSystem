package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
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
            Player current = (Player) sender;

            //Check if player is vanished
            if (!ServerSystem.vanish.contains(current)) {
                current.sendMessage(Strings.VANISH_NOT_VANISHED.getString());
                return false;
            }

            //Remove player from vanish arraylist
            ServerSystem.vanish.remove(current);

            //Make player visible again
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p != current) p.showPlayer(current);
            }

            //Notify player
            current.sendMessage(Strings.VAHISH_UNVANISHED.getString());
            return true;
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "unvanish");
            return false;
        }
    }
}
