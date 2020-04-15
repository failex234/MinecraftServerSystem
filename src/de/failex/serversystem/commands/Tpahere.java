package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Request a player to teleport to the current position
 */
public class Tpahere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (args.length > 0) {
            //Get wanted player
            Player destination = Bukkit.getPlayer(args[0]);

            //Check if player exists
            if (destination == null) {
                sender.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                return false;
            }

            //Get sender
            Player requester = (Player) sender;

            //Save both players in hash map and set destination as key for later
            ServerSystem.tpalist.put(destination.getUniqueId(), requester.getUniqueId());
            ServerSystem.tpatype.put(destination.getUniqueId(), "tpahere");

            //Notify both players
            sender.sendMessage(String.format(Strings.TPA_SENDING.getString(), destination.getName()));
            destination.sendMessage(String.format(Strings.TPA_NOTIFICATION_LINE1.getString(), requester.getName()));

            return true;

        } else {
            sender.sendMessage(Strings.TPA_NO_NAME_GIVEN.getString());
            return false;
        }
    }
}
