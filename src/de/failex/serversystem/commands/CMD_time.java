package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Set time in world
 * <p>
 * Permissions:
 * - serversystem.time
 * </p>
 */
public class CMD_time implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.time")) {
            //Check if sender is a player
            if (!(sender instanceof Player)) {
                sender.sendMessage(Strings.ONLY_PLAYER_COMMAND.getString());
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(Strings.TIME_USAGE.getString());
                    return true;
                } else {
                    Player p = (Player) sender;
                    switch (args[0]) {
                        case "set":
                            if (args.length == 1) {
                                sender.sendMessage(Strings.TIME_USAGE.getString());
                                return true;
                            }
                            int ticks = 0;
                            //Try to parse player input to see if input was a number or not
                            //TODO: Integer size check
                            
                            try {
                                ticks = Integer.parseInt(args[1]);
                                World currworld = p.getWorld();
                                currworld.setTime(ticks);
                                sender.sendMessage(Strings.TIME_SET_TO.getString().replace("%s", "" + ticks).replace("%w", currworld.getName()));
                                return true;
                            }
                            //TODO: Adjust time
                            catch (NumberFormatException e) {
                                switch (args[1]) {
                                    case "day":
                                        p.getWorld().setTime(12000);
                                        p.sendMessage(Strings.TIME_SET_TO.getString().replace("%s", "daylight").replace("%w", p.getWorld().getName()));
                                        return true;
                                    case "night":
                                        p.getWorld().setTime(20000);
                                        p.sendMessage(Strings.TIME_SET_TO.getString().replace("%s", "nighttime").replace("%w", p.getWorld().getName()));
                                        return true;
                                    default:
                                        p.sendMessage(Strings.TIME_WRONG_FORMAT.getString());
                                        return false;
                                }
                            }
                        case "add":
                            if (args.length == 1) {
                                sender.sendMessage(Strings.TIME_USAGE.getString());
                                return true;
                            }

                            //Check if argument is an integer
                            ticks = 0;

                            try {
                                ticks = Integer.parseInt(args[1]);
                                long currworldticks = p.getWorld().getTime();
                                long newticks = currworldticks + ticks;
                                p.getWorld().setTime(newticks);
                                p.sendMessage(Strings.TIME_ADDED_TO.getString().replace("%s", ticks + "").replace("%w", p.getWorld().getName()));
                                return true;
                            } catch (NumberFormatException e) {
                                sender.sendMessage(Strings.TIME_WRONG_FORMAT.getString());
                                return false;
                            }
                        case "rem":
                            if (args.length == 1) {
                                sender.sendMessage(Strings.TIME_USAGE.getString());
                                return true;
                            }

                            //Check if argument is an integer
                            ticks = 0;
                            try {
                                ticks = Integer.parseInt(args[1]);
                                long currworldticks = p.getWorld().getTime();
                                long newticks = currworldticks - ticks;
                                if (newticks < 0) {
                                    p.getWorld().setTime(0);
                                    p.sendMessage(Strings.TIME_REMOVED.getString().replace("%s", ticks + "").replace("%w", p.getWorld().getName()));
                                    return true;
                                } else {
                                    p.getWorld().setTime(newticks);
                                    p.sendMessage(Strings.TIME_REMOVED.getString().replace("%s", ticks + "").replace("%w", p.getWorld().getName()));
                                    return true;
                                }
                            } catch (NumberFormatException e) {
                                sender.sendMessage(Strings.TIME_WRONG_FORMAT.getString());
                                return false;
                            }
                        default:
                            p.sendMessage(Strings.ARG_UNREC.getString());
                            p.sendMessage(Strings.TRY_HELP.getString().replace("%s", "time"));
                            return false;
                    }
                }
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "time");
            return false;
        }
    }
}
