package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.config.ConfigManager;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Set the spawn point of the world the player is currently in
 * <p>
 *      Permissions:
 *      - serversystem.setspawn
 * </p>
 */
public class CMD_setspawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.setspawn")) {
            //Check if command sender is a player
            if (sender instanceof Player) {
                if (args.length == 0) {
                    //The casting is needed to use some essential methods like getLocation() on the sender
                    Player p = (Player) sender;

                    //Get Location and save pitch, yaw, x-, x- and z coordinates to the config
                    Location newspawn = p.getLocation();

                    //All needed information for the spawn point
                    double yaw = newspawn.getYaw();
                    double pitch = newspawn.getPitch();
                    int x = newspawn.getBlockX();
                    int y = newspawn.getBlockY();
                    int z = newspawn.getBlockZ();

                    //Get players world and set spawn
                    World currWorld = p.getWorld();
                    currWorld.setSpawnLocation(x, y, z);

                    //Save spawn point to config (x,y,z,yaw and pitch)
                    ConfigManager.set("spawn." + currWorld.getName() + ".X", x + "");
                    ConfigManager.set("spawn." + currWorld.getName() + ".Y", y + "");
                    ConfigManager.set("spawn." + currWorld.getName() + ".Z", z + "");
                    ConfigManager.set("spawn." + currWorld.getName() + ".yaw", yaw + "");
                    ConfigManager.set("spawn." + currWorld.getName() + ".pitch", pitch + "");

                    //Notify player that spawn has been changed
                    p.sendMessage(Strings.SETSPAWN_SPAWN_SET.getString());

                } else {
                    switch (args[0]) {
                        case "help":
                            sender.sendMessage(Strings.SETSPAWN_HELP_TITLE.getString());
                            sender.sendMessage(Strings.SETSPAWN_HELP_1.getString());
                            sender.sendMessage(Strings.SETSPAWN_HELP_2.getString());
                            sender.sendMessage(Strings.SETSPAWN_HELP_FOOTER.getString());
                            return true;
                        default:
                            sender.sendMessage(Strings.ARG_UNREC.getString());
                            sender.sendMessage(Strings.TRY_HELP.getString().replace("%s", "setspawn"));
                            return false;
                    }
                }

            }
        } else {
            //Give the player a "unknown command" message
            ServerSystem.onUnknownCommand((Player) sender, "setspawn");
            return false;
        }
        return false;
    }
}
