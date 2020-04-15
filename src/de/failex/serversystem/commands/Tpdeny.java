package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Denies a teleport request
 */
public class Tpdeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        //Get current player
        Player current = (Player) sender;

        //Check for pending requests
        if (ServerSystem.tpalist.containsKey(current.getUniqueId())) {
            //Get player from list and then remove
            Player requester = Bukkit.getPlayer(ServerSystem.tpalist.get(current.getUniqueId()));
            ServerSystem.tpalist.remove(current.getUniqueId());
            ServerSystem.tpatype.remove(current.getUniqueId());

            current.sendMessage(Strings.TPA_DECLINED_SENDER.getString());
            requester.sendMessage(String.format(Strings.TPA_DECLINED_REQUESTER.getString(), current.getName()));

            return true;
        } else {
            sender.sendMessage(Strings.TPA_NO_REQUESTS.getString());
            return true;
        }
    }
}
