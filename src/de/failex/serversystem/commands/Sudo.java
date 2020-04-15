package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Force another user to run a command
 * <p>
 *      Permissions:
 *      - serversystem.sudo
 * </p>
 */
public class Sudo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        //Check if user is permitted to run command
        if (sender.hasPermission("serversystem.sudo")) {
            //Check if user has given enough arguments
            if (args.length < 2) {
                sender.sendMessage(Strings.SUDO_MISSING_ARG.getString());
                return false;
            } else {
                switch (args[0]) {
                    case "help":
                        //Send help menu
                        sender.sendMessage(Strings.SUDO_HELP_TITLE.getString());
                        sender.sendMessage(Strings.SUDO_HELP_1.getString());
                        sender.sendMessage(Strings.SUDO_HELP_2.getString());
                        sender.sendMessage(Strings.SUDO_HELP_FOOTER.getString());
                        return true;
                    default:
                        //Get player
                        Player sudo = Bukkit.getPlayer(args[0]);

                        //Test if player is online
                        if (sudo == null) {
                            sender.sendMessage(Strings.SUDO_PLAYER_NOT_ONLINE.getString());
                            return false;
                        }

                        //Test if sudo'd user doesn't have the permission serversystem.sudo
                        if (sudo.hasPermission("serversystem.sudo")) {
                            sender.sendMessage(String.format(Strings.SUDO_NOT_ALLOWED_TO_SUDO.getString(), sudo.getName()));
                            return false;
                        }

                        //Append command + arguments into one string
                        StringBuilder command = new StringBuilder("");
                        for (String argument : args) {
                            if (argument.equals(args[0])) continue;
                            command.append(argument);
                            command.append(" ");
                        }

                        //Notifying player
                        sender.sendMessage(String.format(Strings.SUDO_RUNNING.getString(), sudo.getName(), command));

                        //Run command as other user
                        sudo.performCommand(command.toString());
                        return true;
                }
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "sudo");
            return false;
        }
    }
}
