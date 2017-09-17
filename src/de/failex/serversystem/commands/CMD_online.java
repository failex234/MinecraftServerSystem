package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Shows count of currently online players
 */
public class CMD_online implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Just return the onlineplayers Integer from the main class
            if (ServerSystem.onlineplayers > 1) {
                p.sendMessage(Strings.PLAYERS_ONLINE.getString().replace("%d", ServerSystem.onlineplayers + ""));
            } else {
                p.sendMessage(Strings.PLAYER_ONLINE.getString());
            }
            return true;
        }

        if (ServerSystem.onlineplayers > 1 || ServerSystem.onlineplayers == 0) {
            sender.sendMessage(Strings.PLAYERS_ONLINE.getString().replace("%d", ServerSystem.onlineplayers + ""));
        } else {
            sender.sendMessage(Strings.PLAYER_ONLINE.getString());
        }
        return true;
    }
}
