package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
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
            if (ServerSystem.onlineplayers > 1) {
                p.sendMessage(ServerSystem.PREFIX + ServerSystem.onlineplayers + " players are currently online!");
            } else {
                p.sendMessage(ServerSystem.PREFIX + ServerSystem.onlineplayers + " player is currently online!");
            }
            return true;
        }

        if (ServerSystem.onlineplayers > 1 || ServerSystem.onlineplayers == 0) {
            sender.sendMessage(ServerSystem.PREFIX + ServerSystem.onlineplayers + " players are currently online!");
        } else {
            sender.sendMessage(ServerSystem.PREFIX + ServerSystem.onlineplayers + " player is currently online!");
        }
        return true;
    }
}
