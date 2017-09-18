package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.config.ConfigManager;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_setwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.setwarp")) {
            //Check if sender is a player
            if (!(sender instanceof Player)) {
                sender.sendMessage(Strings.ONLY_PLAYER_COMMAND.getString());
                return false;
            }
            Player p = (Player) sender;
            //Check if any argument is given
            if (args.length == 0) {
                p.sendMessage(Strings.SETWARP_NO_ARG.getString());
                return false;
            } else {
                switch (args[0]) {
                    case "help":
                        //Show help menu
                        p.sendMessage(Strings.SETWARP_HELP_TITLE.getString());
                        p.sendMessage(Strings.SETWARP_HELP_1.getString());
                        p.sendMessage(Strings.SETWARP_HELP_2.getString());
                        p.sendMessage(Strings.SETWARP_HELP_3.getString());
                        p.sendMessage(Strings.SETWARP_HELP_FOOTER.getString());
                        return true;
                    case "delete":
                        deleteWarp(args[0], p);
                        return true;
                    case "remove":
                        deleteWarp(args[0], p);
                        return false;
                    default:
                        String warp = args[0];
                        //Check if warp alreaady exists
                        if (ConfigManager.get(p.getWorld().getName() + "." + warp.toLowerCase() + ".X") == null) {
                            Location currLoc = p.getLocation();

                            //Save coordinates, pitch and yaw in variables
                            int x = currLoc.getBlockX();
                            int y = currLoc.getBlockY();
                            int z = currLoc.getBlockZ();
                            double yaw = currLoc.getYaw();
                            double pitch = currLoc.getPitch();

                            //Save to config
                            ConfigManager.set(p.getWorld().getName() + "." + warp.toLowerCase() + ".X", x + "");
                            ConfigManager.set(p.getWorld().getName() + "." + warp.toLowerCase() + ".Y", y + "");
                            ConfigManager.set(p.getWorld().getName() + "." + warp.toLowerCase() + ".Z", z + "");
                            ConfigManager.set(p.getWorld().getName() + "." + warp.toLowerCase() + ".pitch", pitch + "");
                            ConfigManager.set(p.getWorld().getName() + "." + warp.toLowerCase() + ".yaw", yaw + "");
                            ConfigManager.set("warps." + warp.toLowerCase() + ".world", p.getWorld().getName());

                            //Notify user
                            p.sendMessage(Strings.SETWARP_CREATED.getString().replace("%s", warp));

                            return true;
                        } else {
                            p.sendMessage(Strings.SETWARP_CANT_CREATE.getString().replace("%s", warp));

                            return false;
                        }
                }
            }

        } else {
            ServerSystem.onUnknownCommand((Player) sender, "setwarp");
            return false;
        }
    }

    private void deleteWarp(String warp, Player p) {
        //Get world of warp and check if warp exists
        String world = ConfigManager.get("warps." + warp.toLowerCase() + ".world");
        if (world == null) {
            //Notify player
            p.sendMessage(Strings.SETWARP_CANT_DELETE.getString().replace("%s", warp));
        } else {
            //Set all entries to null
            ConfigManager.set("warps." + warp.toLowerCase() + ".world", null);
            ConfigManager.set(world + "." + warp.toLowerCase() + ".X", null);
            ConfigManager.set(world + "." + warp.toLowerCase() + ".Y", null);
            ConfigManager.set(world + "." + warp.toLowerCase() + ".Z", null);
            ConfigManager.set(world + "." + warp.toLowerCase() + ".pitch", null);
            ConfigManager.set(world + "." + warp.toLowerCase() + ".yaw", null);

            //Notify player
            p.sendMessage(Strings.SETWARP_DELETED.getString().replace("%s", warp));
        }
    }
}
