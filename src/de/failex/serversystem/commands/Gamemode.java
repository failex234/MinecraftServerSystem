package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Sets the gamemode of any player
 * <p>
 *     Permissions:
 *     - serversystem.gamemode
 *          Set gamemode of sender
 *     - serversystem.gamemode.other
 *          Set gamemode of everybody
 * </p>
 */
public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.gamemode")) {
            Player p = (Player) sender;
            //Show different usage information when the player has no permission to set the gm
            //of other players vs. when he has the permission
            if (args.length == 0 && !sender.hasPermission("serversystem.gamemode.other")) {
                p.sendMessage(Strings.GAMEMODE_USAGE_RESTRICTED.getString());
                p.sendMessage(Strings.GAMEMODE_MORE_INFO.getString());
                return true;
            } else if (args.length == 0) {
                p.sendMessage(Strings.GAMEMODE_USAGE_PERMITTED_1.getString());
                p.sendMessage(Strings.GAMEMODE_USAGE_PERMITTED_2.getString());
                p.sendMessage(Strings.GAMEMODE_MORE_INFO.getString());
                return true;
            } else if (!sender.hasPermission("serversystem.gamemode.other")) {
                try {
                    int gamemode = Integer.parseInt(args[0]);
                    //Set gamemode depending on given number
                    switch (gamemode) {
                        case 0:
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Survival"));
                            break;
                        case 1:
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Creative"));
                            break;
                        case 2:
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Adventure"));
                            break;
                        case 3:
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Spectator"));
                            break;
                        default:
                            p.sendMessage(Strings.GAMEMODE_INT_INVALID.getString());
                            p.sendMessage(Strings.GAMEMODE_HELP_WITH.getString());
                    }
                }
                catch(NumberFormatException e) {
                    //When an exception is thrown assume that player sent
                    //characters rather than a number and analyze the chars
                    String gamemode = args[0];
                    if (gamemode.startsWith("su")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Survival"));
                    } else if (gamemode.startsWith("c")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Creative"));
                    } else if (gamemode.startsWith("a")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Adventure"));
                    } else if (gamemode.startsWith("sp")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO.getString(), "Spectator"));
                    } else {
                        p.sendMessage(Strings.GAMEMODE_INVALID.getString());
                        p.sendMessage(Strings.GAMEMODE_HELP_WITH.getString());
                    }
                    return false;
                }
            } else if(args[0].equals("help") && !p.hasPermission("serversystem.gamemode.other")) {
                p.sendMessage(Strings.GAMEMODE_HELP_TITLE.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_1.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_RESTRICTED_2.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_RESTRICTED_3.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_FOOTER.getString());
            } else if(args[0].equals("help") && p.hasPermission("serversystem.gamemode.other")) {
                p.sendMessage(Strings.GAMEMODE_HELP_TITLE.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_1.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_PERMITTED_2.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_PERMITTED_3.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_PERMITTED_4.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_PERMITTED_5.getString());
                p.sendMessage(Strings.GAMEMODE_HELP_FOOTER.getString());
            } else if (args.length == 2){
                try {
                    int gamemode = Integer.parseInt(args[0]);
                    Player other = Bukkit.getServer().getPlayer(args[1]);

                    if (other == null) {
                        p.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                        return false;
                    }

                    switch (gamemode) {
                        case 0:
                            other.setGameMode(GameMode.SURVIVAL);
                            other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Survival"));
                            break;
                        case 1:
                            other.setGameMode(GameMode.CREATIVE);
                            other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Creative"));
                            break;
                        case 2:
                            other.setGameMode(GameMode.ADVENTURE);
                            other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Adventure"));
                            break;
                        case 3:
                            other.setGameMode(GameMode.SPECTATOR);
                            other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                            p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Spectator"));
                            break;
                        default:
                            p.sendMessage(Strings.GAMEMODE_INT_INVALID.getString());
                            p.sendMessage(Strings.GAMEMODE_HELP_WITH.getString());
                    }
                }
                catch(NumberFormatException e) {
                    String gamemode = args[0];
                    Player other = Bukkit.getServer().getPlayer(args[1]);

                    if (other == null) {
                        p.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                        return false;
                    }

                    if (gamemode.startsWith("su")) {
                        other.setGameMode(GameMode.SURVIVAL);
                        other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Survival"));
                    } else if (gamemode.startsWith("cr")) {
                        other.setGameMode(GameMode.CREATIVE);
                        other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Creative"));
                    } else if (gamemode.startsWith("ad")) {
                        other.setGameMode(GameMode.ADVENTURE);
                        other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Adventure"));
                    } else if (gamemode.startsWith("sp")) {
                        other.setGameMode(GameMode.SPECTATOR);
                        other.sendMessage(String.format(Strings.GAMEMODE_SET_BY.getString(), p.getName()));
                        p.sendMessage(String.format(Strings.GAMEMODE_SET_TO_PLAYER.getString(), other.getName(), "Spectator"));
                    } else {
                        other.sendMessage(Strings.GAMEMODE_INVALID.getString());
                        other.sendMessage(Strings.GAMEMODE_HELP_WITH.getString());
                    }
                    return false;
                }
            } else {
                p.sendMessage(Strings.ARG_UNREC.getString());
                p.sendMessage(String.format(Strings.TRY_HELP.getString(),"gamemode"));
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, cmd.getName());
        }
        return false;
    }
}
