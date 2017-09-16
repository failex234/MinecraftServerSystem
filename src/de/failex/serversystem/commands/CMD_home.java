package de.failex.serversystem.commands;

import de.failex.serversystem.config.ConfigManager;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Teleports players to their homes.
 * <p>
 *      Permissions:
 *      - serversystem.home.other
 *        Let's you teleport to homes of other players
 * </p>
 */
public class CMD_home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0 || !sender.hasPermission("serversystem.home.other")) {
                String pname = sender.getName();
                Player p = (Player) sender;
                try {
                    //Get coordinates of the home point, throw exception
                    //when there's no home and thus the config entry is null
                    int x = Integer.parseInt(ConfigManager.getFromPlayer(pname, "HOME.X"));
                    int y = Integer.parseInt(ConfigManager.getFromPlayer(pname, "HOME.Y"));
                    int z = Integer.parseInt(ConfigManager.getFromPlayer(pname, "HOME.Z"));

                    //Make a temporary location var to maintain yaw + pitch from the player
                    Location temp = p.getLocation();

                    //Set coordinates of location
                    temp.setX(x);
                    temp.setY(y);
                    temp.setZ(z);

                    //Teleport player to their home point
                    p.teleport(temp);
                    p.sendMessage(Strings.HOME_TELEPORTED.getString());


                } catch (Exception e) {
                    p.sendMessage(Strings.HOME_NOT_SET.getString());
                    return false;
                }
            } else {
                //Get string of demanded player
                String player = args[0];
                Player p = (Player) sender;

                try {
                    //Try to get coordinates, when an exception is thrown then either
                    //the player hasn't set a homepoint yet or he's non-existent
                    int x = Integer.parseInt(ConfigManager.getFromPlayer(player, "HOME.X"));
                    int y = Integer.parseInt(ConfigManager.getFromPlayer(player, "HOME.Y"));
                    int z = Integer.parseInt(ConfigManager.getFromPlayer(player, "HOME.Z"));

                    //Get current location of sender to maintain pitch and yaw
                    Location temp = p.getLocation();

                    //Set coordinates of location
                    temp.setX(x);
                    temp.setY(y);
                    temp.setZ(z);

                    //Teleport player to demanded home point
                    p.teleport(temp);
                    p.sendMessage(Strings.HOME_TPD_TO_PLAYERS_HOME.getString().replace("%s", player));
                } catch (Exception e) {
                    p.sendMessage(Strings.HOME_NO_HOME_POINT_FOUND.getString().replace("%s", player));
                    return false;
                }
            }
        }
        return false;
    }
}
