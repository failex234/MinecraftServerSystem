package de.failex.serversystem.commands;

import de.failex.serversystem.config.ConfigManager;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Sets the home point of a player
 */
public class CMD_sethome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        //Check if command sender is a player
        if (sender instanceof Player) {
            //The casting is needed to use some essential methods like getLocation() on the sender
            Player p = (Player) sender;

            //Get current player position and save all three coordinates in the playerdata config
            Location currPos = p.getLocation();

            int x = currPos.getBlockX();
            int y = currPos.getBlockY();
            int z = currPos.getBlockZ();

            //Save coordinates to config
            ConfigManager.setPlayer(p.getName(), "HOME.X", x + "");
            ConfigManager.setPlayer(p.getName(), "HOME.Y", y + "");
            ConfigManager.setPlayer(p.getName(), "HOME.Z", z + "");

            //Notify player
            p.sendMessage(Strings.SETHOME_HOME_SET.getString());
            return true;
        } else {
            sender.sendMessage(Strings.ONLY_PLAYER_COMMAND.getString());
            return false;
        }
    }
}
