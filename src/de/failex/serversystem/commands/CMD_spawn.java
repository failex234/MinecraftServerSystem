package de.failex.serversystem.commands;

import de.failex.serversystem.config.ConfigManager;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Teleports player to the spawn
 */
//TODO: Exception Catcher, maybe config is changed and Integer and Float can't parse that, maybe help menu stub
public class CMD_spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        //Check if sender is a player
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Check if the spawn has ever been set
            if (ConfigManager.get("spawn." + p.getWorld().getName() + ".X") == null) {
                p.teleport(p.getWorld().getSpawnLocation());

                return true;
            } else {
                //Use players location as a template for the new spawn location
                Location temp = p.getLocation();

                //Read from config and set location
                temp.setX(Integer.parseInt(ConfigManager.get("spawn." + p.getWorld().getName() + ".X")));
                temp.setY(Integer.parseInt(ConfigManager.get("spawn." + p.getWorld().getName() + ".Y")));
                temp.setZ(Integer.parseInt(ConfigManager.get("spawn." + p.getWorld().getName() + ".Z")));
                temp.setYaw(Float.parseFloat(ConfigManager.get("spawn." + p.getWorld().getName() + ".yaw")));
                temp.setPitch(Float.parseFloat(ConfigManager.get("spawn." + p.getWorld().getName() + ".pitch")));

                //Teleport player to location
                p.teleport(temp);

                return true;
            }
        } else {
            sender.sendMessage(Strings.ONLY_PLAYER_COMMAND.getString());
            return false;
        }
    }
}
