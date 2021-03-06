package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Shows some basic information about the server system
 * <p>
 *     Permissions:
 *     - serversystem.system
 * </p>
 */
public class System implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.system")) {
            try {
                sender.sendMessage(String.format(Strings.SYSTEM_INFO.getString(), ServerSystem.system.VERSION, ServerSystem.system.AUTHOR));
                Date compdate = new Date(new File(getClass().getClassLoader().getResource(getClass().getCanonicalName().replace('.', '/') + ".class").toURI()).lastModified());
                sender.sendMessage(String.format(Strings.SYSTEM_INFO_COMP_DATE.getString(), compdate.toString()));
                return true;
            } catch (URISyntaxException | NullPointerException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            ServerSystem.onUnknownCommand((Player)sender, "system");
            return false;
        }
    }
}
