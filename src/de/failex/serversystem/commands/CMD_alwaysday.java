package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * Sets current time in world to day and prevents any other daytime in this world
 * <p>
 * Permissions:
 * - serversystem.alwaysday
 * Toggle / Set alwaysday for world
 * </p>
 */
//TODO Write BukkitTask for alwaysday
public class CMD_alwaysday implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        //Only execute if player has the right permission or else show the unknown command message
        if (sender.hasPermission("serversystem.alwaysday")) {
            Player p = (Player) sender;
            //When no argument is given toggle the alwaysday status
            if (args.length == 0) {
                World current = p.getWorld();
                if (ServerSystem.alwaysday.contains(current)) {
                    ServerSystem.alwaysday.remove(current);
                    p.sendMessage(Strings.ALWAYSDAY_DISABLED.getString().replace("%s", current.getName()));
                } else {
                    ServerSystem.alwaysday.add(current);
                    ServerSystem.alwaysday.add(current);
                    p.sendMessage(Strings.ALWAYSDAY_ENABLED.getString().replace("%s", current.getName()));
                }
            } else {
                String status = args[0];

                if (status.equals("0") || status.equals("off") || status.equals("false") || status.equals("stop")) {
                    if (ServerSystem.alwaysday.contains(p.getWorld())) {
                        ServerSystem.stopAlwaysDay(p.getWorld());
                        p.sendMessage(Strings.ALWAYSDAY_DISABLED.getString().replace("%s", p.getWorld().getName()));
                    } else {
                        p.sendMessage(Strings.ALWAYSDAY_ALREADY_DISABLED.getString().replace("%s", p.getWorld().getName()));
                    }
                } else if (status.equals("1") || status.equals("on") || status.equals("true") || status.equals("start")) {
                    if (ServerSystem.alwaysday.contains(p.getWorld())) {
                        p.sendMessage(Strings.ALWAYSDAY_ALREADY_ENABLED.getString().replace("%s", p.getWorld().getName()));
                    } else {
                        ServerSystem.alwaysday.add(p.getWorld());
                        p.sendMessage(Strings.ALWAYSDAY_ENABLED.getString().replace("%s", p.getWorld().getName()));
                    }
                } else if (args[0].equals("help")) {
                    p.sendMessage(Strings.ALWAYSDAY_HELP_1.getString());
                    p.sendMessage(Strings.ALWAYSDAY_HELP_2.getString());
                    p.sendMessage(Strings.ALWAYSDAY_HELP_3.getString());
                    p.sendMessage(Strings.ALWAYSDAY_HELP_4.getString());
                    p.sendMessage(Strings.ALWAYSDAY_HELP_5.getString());
                } else {
                    p.sendMessage(Strings.ARG_UNREC.getString());
                    p.sendMessage(Strings.TRY_HELP.getString().replace("%s", "alwaysday"));
                }
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "alwaysday");
        }
        return true;
    }
}
