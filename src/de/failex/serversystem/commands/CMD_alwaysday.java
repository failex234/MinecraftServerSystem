package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * Sets current time in world to day and prevents any other daytime in this world
 * <p>
 *     Permissions:
 *     - serversystem.alwaysday
 *          Toggle / Set alwaysday for world
 * </p>
 */
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
                    p.sendMessage(ServerSystem.PREFIX + ChatColor.GREEN + "Alwaysday has been disabled for world " + current.getName());
                } else {
                    ServerSystem.alwaysday.add(current);
                    ServerSystem.alwaysday.add(current);
                    p.sendMessage(ServerSystem.PREFIX + ChatColor.GREEN + "Alwaysday has been enabled for world " + current.getName());
                }
            } else {
                String status = args[0];

                if (status.equals("0") || status.equals("off") || status.equals("false") || status.equals("stop")) {
                    if (ServerSystem.alwaysday.contains(p.getWorld())) {
                        ServerSystem.stopAlwaysDay(p.getWorld());
                        p.sendMessage(ServerSystem.PREFIX + ChatColor.GREEN + "Alwaysday has been disabled for world " + p.getWorld().getName());
                    } else {
                        p.sendMessage(ServerSystem.PREFIX + ChatColor.RED + "Alwaysday is already disabled for world " + p.getWorld().getName());
                    }
                } else if (status.equals("1") || status.equals("on") || status.equals("true") || status.equals("start")) {
                    if (ServerSystem.alwaysday.contains(p.getWorld())) {
                        p.sendMessage(ServerSystem.PREFIX + ChatColor.RED + "Alwaysday is already enabled for world " + p.getWorld().getName());
                    } else {
                        ServerSystem.alwaysday.add(p.getWorld());
                        p.sendMessage(ServerSystem.PREFIX + ChatColor.GREEN + "Alwaysday has been enabled for world " + p.getWorld().getName());
                    }
                } else if (args[0].equals("help")) {
                    p.sendMessage("--------- AlwaysDay Help Menu ---------");
                    p.sendMessage("/alwaysday                    - toggle alwaysday for current world");
                    p.sendMessage("/alwaysday <0|off|false|stop> - turn alwaysday off for current world");
                    p.sendMessage("/alwaysday <1|on|true|start>  - turn alwaysday on for current world");
                    p.sendMessage("----------------------------------------");
                } else {
                    p.sendMessage(ServerSystem.PREFIX + ChatColor.RED + "Argument not recognised!");
                    p.sendMessage(ServerSystem.PREFIX + ChatColor.GREEN + "Try /alwaysday help");
                }
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "alwaysday");
        }
        return true;
    }
}
