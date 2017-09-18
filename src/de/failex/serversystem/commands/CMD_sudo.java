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
public class CMD_sudo implements CommandExecutor {
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
                            sender.sendMessage(Strings.SUDO_NOT_ALLOWED_TO_SUDO.getString().replace("%s", sudo.getName()));
                            return false;
                        }

                        //Concat command + arguments into one string
                        String command = "";
                        for (String argument : args) {
                            if (args.equals(args[0])) continue;
                            command += argument + " ";
                        }

                        //Notifying player
                        sender.sendMessage(Strings.SUDO_RUNNING.getString().replace("%s", sudo.getName()).replace("%c", command));

                        //Run command as other user
                        sudo.performCommand(command);
                        return true;
                }
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "sudo");
            return false;
        }
    }
}
