package de.failex.serversystem.commands;


import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Show ping of a connected player
 * <p>
 *     Permissions:
 *      - serversystem.ping.other
 * </p>
 */
//TODO Rework is needed to struture this command like the others!
public class CMD_ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(Strings.PING_NOTICE.getString().replace("%d",((CraftPlayer) sender).getHandle().ping + ""));
            } else if (args[0].equals("help")) {
                if (sender.hasPermission("serversystem.ping.other")) {
                    sender.sendMessage(Strings.PING_HELP_TITLE.getString());
                    sender.sendMessage(Strings.PING_HELP_1.getString());
                    sender.sendMessage(Strings.PING_HELP_2.getString());
                    sender.sendMessage(Strings.PING_HELP_3.getString());
                    sender.sendMessage(Strings.PING_HELP_FOOTER.getString());
                } else {
                    sender.sendMessage(Strings.PING_HELP_TITLE.getString());
                    sender.sendMessage(Strings.PING_HELP_1_UNPREV.getString());
                    sender.sendMessage(Strings.PING_HELP_2_UNPREV.getString());
                    sender.sendMessage(Strings.PING_HELP_FOOTER.getString());
                }
            } else {
                if (sender.hasPermission("serversystem.ping.other")) {
                    Player p;
                    p = Bukkit.getServer().getPlayer(args[0]);
                    sender.sendMessage(Strings.PING_NOTICE_OTHER.getString().replace("%s", args[0]).replace("%d",((CraftPlayer) p).getHandle().ping + ""));
                } else {
                    sender.sendMessage(Strings.PING_NOTICE.getString().replace("%d",((CraftPlayer) sender).getHandle().ping + ""));
                }
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(Strings.PING_CONSOLE.getString());
            } else {
                if (args[0].equals("help")) {
                    sender.sendMessage(Strings.PING_HELP_TITLE.getString() + "");
                    sender.sendMessage(Strings.PING_HELP_1.getString());
                    sender.sendMessage(Strings.PING_HELP_2.getString());
                    sender.sendMessage(Strings.PING_HELP_3.getString());
                    sender.sendMessage(Strings.PING_HELP_FOOTER.getString());
                } else {
                    CraftPlayer cp = (CraftPlayer) Bukkit.getServer().getPlayer(args[0]);
                    int ping = cp.getHandle().ping;
                    sender.sendMessage(Strings.PING_NOTICE_OTHER.getString().replace("%s", args[0]).replace("%d", ping + ""));
                }
            }
        }
        return false;
    }
}
