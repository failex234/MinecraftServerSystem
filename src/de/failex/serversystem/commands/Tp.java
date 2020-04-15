package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Teleports one player to another or
 * teleports sender to a player
 * <p>
 *     Permissions:
 *     - serversystem.tp
 *     - serversystem.tp.other
 * </p>
 */
public class Tp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.tp")) {
            if (args.length == 1) {
                //Get the wanted player
                Player destination = Bukkit.getPlayer(args[0]);

                if (destination == null) {
                    sender.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                    return false;
                }

                //Get sender
                Player start = (Player) sender;

                //Notify sender
                sender.sendMessage(Strings.TP_TELEPORTING.getString());

                 //Teleport sender to other player
                start.teleport(destination);

                return true;
            } else {
                switch (args.length) {
                    case 0:
                        sender.sendMessage(Strings.TP_HELP_TITLE.getString());
                        sender.sendMessage(Strings.TP_HELP_1.getString());
                        if (sender.hasPermission("serversystem.tp.other")) sender.sendMessage(Strings.TP_HELP_2_RESTRICTED.getString());
                        sender.sendMessage(Strings.TP_HELP_FOOTER.getString());
                        return true;
                    default:
                        //Get player a and b
                        Player a = Bukkit.getPlayer(args[0]);
                        Player b = Bukkit.getPlayer(args[1]);

                        //Check if all players exist / are online
                        if (a == null || b == null) {
                            sender.sendMessage(Strings.TP_PLAYERS_NOT_FOUND.getString());
                            return false;
                        }

                        //Teleport a to b
                        a.teleport(b);

                        //Notify sender
                        sender.sendMessage(String.format(Strings.TP_TELEPORTING_A_TO_B.getString(), a.getName(), b.getName()));
                        return true;
                }
            }
        } else {
            ServerSystem.onUnknownCommand((Player)sender, "tp");
            return false;
        }
    }
}
