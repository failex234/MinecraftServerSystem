package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
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
//TODO Migrate Strings to enum
public class CMD_ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(ServerSystem.PREFIX + "Your ping is " + ((CraftPlayer) sender).getHandle().ping + "ms");
            } else if (args[0].equals("help")) {
                sender.sendMessage("--------- Ping Help Menu ---------");
                sender.sendMessage("1. /ping <player> - Show latency of player");
                sender.sendMessage("2. /ping - Show your ping");
                sender.sendMessage("3. /ping help - Show this menu");
                sender.sendMessage("----------------------------------------");
            } else {
                if (sender.hasPermission("serversystem.ping.other")) {
                    Player p = Bukkit.getServer().getPlayer(args[0]);
                    sender.sendMessage("The ping of player " + args[0] + " is " + ((CraftPlayer) sender).getHandle().ping + "ms");
                } else {
                    sender.sendMessage("Your ping is " + ((CraftPlayer) sender).getHandle().ping + "ms");
                }
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(ServerSystem.PREFIX + "This is the console your latency should normally be around 0ms");
            } else {
                if (args[0].equals("help")) {
                    sender.sendMessage("--------- Ping Help Menu ---------");
                    sender.sendMessage("1. /ping <player> - Show latency of player");
                    sender.sendMessage("2. /ping help - Show this menu");
                    sender.sendMessage("----------------------------------------");
                } else {
                    CraftPlayer cp = (CraftPlayer) Bukkit.getServer().getPlayer(args[0]);
                    int ping = cp.getHandle().ping;
                    sender.sendMessage(ServerSystem.PREFIX + "The ping of player " + args[0] + " is " + ping + "ms");
                }
            }
        }
        return false;
    }
}
