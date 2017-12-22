package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Makes a player invisible
 * <p>
 *     Permissions:
 *     - serversystem.vanish
 *     - serversystem.vanish.bypass
 * </p>
 */
//TODO: Hide Player in every playerlist
public class CMD_vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.vanish")) {
            Player current = (Player) sender;

            //Hide current play for all players that don't have bypass permissions
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.hasPermission("serversystem.vanish.bypass")) p.hidePlayer(current);
            }
            //Add player to arraylist so he's vanished across relogs
            ServerSystem.vanish.add(current);
            current.sendMessage(Strings.VANISH_NOW_VANISHED.getString());

            return true;
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "vanish");
            return false;
        }
    }
}
