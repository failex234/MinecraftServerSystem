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
public class Time implements CommandExecutor {
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
                                sender.sendMessage(String.format(Strings.TIME_SET_TO.getString(), currworld.getName(), "" + ticks));
                                return true;
                            }
                            //TODO: Adjust time
                            catch (NumberFormatException e) {
                                switch (args[1]) {
                                    case "day":
                                        p.getWorld().setTime(0);
                                        p.sendMessage(String.format(Strings.TIME_SET_TO.getString(), p.getWorld().getName(), "daylight"));
                                        return true;
                                    case "night":
                                        p.getWorld().setTime(13400);
                                        p.sendMessage(String.format(Strings.TIME_SET_TO.getString(), p.getWorld().getName(), "nighttime"));
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
                                p.sendMessage(String.format(Strings.TIME_ADDED_TO.getString(), p.getWorld().getName(), "" + ticks));
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
                                    p.sendMessage(String.format(Strings.TIME_REMOVED.getString(), p.getWorld().getName(), ticks + ""));
                                    return true;
                                } else {
                                    p.getWorld().setTime(newticks);
                                    p.sendMessage(String.format(Strings.TIME_REMOVED.getString(), p.getWorld().getName(), ticks + ""));
                                    return true;
                                }
                            } catch (NumberFormatException e) {
                                sender.sendMessage(Strings.TIME_WRONG_FORMAT.getString());
                                return false;
                            }
                        default:
                            p.sendMessage(Strings.ARG_UNREC.getString());
                            p.sendMessage(String.format(Strings.TRY_HELP.getString(), "time"));
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
