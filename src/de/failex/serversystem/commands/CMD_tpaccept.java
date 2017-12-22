package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Accepts a teleport request
 */
public class CMD_tpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        //Get current player
        Player current = (Player) sender;

        //Check for pending requests
        if (ServerSystem.tpalist.containsKey(current.getUniqueId())) {
            //Get player from list and then remove
            Player requester = Bukkit.getPlayer(ServerSystem.tpalist.get(current.getUniqueId()));
            ServerSystem.tpalist.remove(current.getUniqueId());

            //Check if requester is still online
            if(requester != null) {
                //Check if its a tpa or tpahere request
                if (ServerSystem.tpatype.get(current.getUniqueId()).equals("tpa")) {
                    ServerSystem.tpatype.remove(current.getUniqueId());
                    //Teleport and notify both players
                    requester.teleport(current);

                    sender.sendMessage(Strings.TPA_ACCEPTING_SENDER.getString());
                    requester.sendMessage(Strings.TPA_ACCEPTING_REQUESTER.getString().replace("%s", current.getName()));
                    return true;
                } else {
                    current.teleport(requester);

                    sender.sendMessage(Strings.TPA_ACCEPTING_SENDER.getString());
                    requester.sendMessage(Strings.TPA_ACCEPTING_REQUESTER.getString().replace("%s", current.getName()));
                    ServerSystem.tpatype.remove(current.getUniqueId());
                    return true;
                }
            } else {
                sender.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                return false;
            }
        } else {
            sender.sendMessage(Strings.TPA_NO_REQUESTS.getString());
            return true;
        }
    }
}
