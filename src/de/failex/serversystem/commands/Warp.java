package de.failex.serversystem.commands;

import de.failex.serversystem.config.ConfigManager;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Teleports a player to a warp point / shows all available warps
 */
public class Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (args.length == 0) {
            String allwarps = ConfigManager.get("warps.allwarps");

            //Check if any warp has been set
            if (allwarps == null || allwarps.isEmpty() || allwarps.equals(" ")) {
                sender.sendMessage(Strings.WARP_NO_WARP_SET.getString());
                return false;
            }

            sender.sendMessage(Strings.WARP_AVAILABLE_WARPS.getString() + allwarps);

            return true;
        } else {
            //Check if warp exists
            String world = ConfigManager.get("warps." + args[0].toLowerCase() + ".world");

            if (world == null || world.isEmpty() || world.equals(" ")) {
                sender.sendMessage(Strings.WARP_NOT_FOUND.getString());
                return false;
            }

            Player current = (Player) sender;

            //Turn warp points into a location
            double x = Float.parseFloat(ConfigManager.get("warps." + args[0] + ".X"));
            double y = Float.parseFloat(ConfigManager.get("warps." + args[0] + ".Y"));
            double z = Float.parseFloat(ConfigManager.get("warps." + args[0] + ".Z"));
            float yaw = Float.parseFloat(ConfigManager.get("warps." + args[0] + ".yaw"));
            float pitch = Float.parseFloat(ConfigManager.get("warps." + args[0] + ".pitch"));

            //Get players location and set warp points
            Location warp = current.getLocation();
            warp.setX(x);
            warp.setY(y);
            warp.setZ(z);
            warp.setPitch(pitch);
            warp.setYaw(yaw);

            //Teleport player to warp
            current.teleport(warp);

            //Notify player
            current.sendMessage(String.format(Strings.WARP_WARPED.getString(), args[0].toLowerCase()));

            return true;
        }
    }
}
