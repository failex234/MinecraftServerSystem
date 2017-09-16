package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
public class CMD_gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.gamemode")) {
            Player p = (Player) sender;
            if (args.length == 0 && !sender.hasPermission("serversystem.gamemode.other")) {
                p.sendMessage(ServerSystem.PREFIX + "Gamemode Usage: /gamemode <0|1|2|3>");
                p.sendMessage(ServerSystem.PREFIX + "More usage information with /gamemode help");
                return true;
            } else if (args.length == 0) {
                p.sendMessage(ServerSystem.PREFIX + "Gamemode Usage: /gamemode <0|1|2|3> {player}");
                p.sendMessage(ServerSystem.PREFIX + "The player argument is optional!");
                p.sendMessage(ServerSystem.PREFIX + "More usage information with /gamemode help");
                return true;
            } else if (!sender.hasPermission("serversystem.gamemode.other")) {
                try {
                    int gamemode = Integer.parseInt(args[0]);
                    switch (gamemode) {
                        case 0:
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Survival");
                            break;
                        case 1:
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Creative");
                            break;
                        case 2:
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Adventure");
                            break;
                        case 3:
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Spectator");
                            break;
                        default:
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode Number invalid");
                            p.sendMessage(ServerSystem.PREFIX + "Help with: /gamemode help");
                    }
                }
                catch(NumberFormatException e) {
                    String gamemode = args[0];
                    if (gamemode.startsWith("su")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Survival");
                    } else if (gamemode.startsWith("cr")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Creative");
                    } else if (gamemode.startsWith("ad")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Adventure");
                    } else if (gamemode.startsWith("sp")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode set to " + ChatColor.RED + "Spectator");
                    } else {
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode invalid!");
                        p.sendMessage(ServerSystem.PREFIX + "Help with /gamemode help");
                    }
                    return false;
                }
            } else if(args.length >= 1 && args[0].equals("help") && !p.hasPermission("serversystem.gamemode.other")) {
                p.sendMessage("--------- Gamemode Help Menu ---------");
                p.sendMessage("1. /gamemode <0|1|2|3> - Set own gamemode");
                p.sendMessage("2. /gamemode <su|cr|ad|sp> - Set own gamemode");
                p.sendMessage("3. /gamemode help - Show this menu");
                p.sendMessage("----------------------------------------");
            } else if(args.length >= 1 && args[0].equals("help") && p.hasPermission("serversystem.gamemode.other")) {
                p.sendMessage("--------- Gamemode Help Menu ---------");
                p.sendMessage("1. /gamemode <0|1|2|3> - Set own gamemode");
                p.sendMessage("2. /gamemode <su|cr|ad|sp>- Set own gamemode");
                p.sendMessage("3. /gamemode <0|1|2|3> <player> - Set gamemode of player");
                p.sendMessage("4. /gamemode <su|cr|ad|sp> <player> - Set gamemode of player");
                p.sendMessage("5. /gamemode help - Show this menu");
                p.sendMessage("----------------------------------------");
            } else if (args.length == 2){
                try {
                    int gamemode = Integer.parseInt(args[0]);
                    Player other = Bukkit.getServer().getPlayer(args[1]);
                    switch (gamemode) {
                        case 0:
                            other.setGameMode(GameMode.SURVIVAL);
                            other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Survival by " + ChatColor.RED + p.getName());
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] + ChatColor.RED + "Survival");
                            break;
                        case 1:
                            other.setGameMode(GameMode.CREATIVE);
                            other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Creative by " + ChatColor.RED + p.getName());
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] +  ChatColor.RED + "Creative");
                            break;
                        case 2:
                            other.setGameMode(GameMode.ADVENTURE);
                            other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Adventure by " + ChatColor.RED + p.getName());
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] +  ChatColor.RED + "Adventure");
                            break;
                        case 3:
                            other.setGameMode(GameMode.SPECTATOR);
                            other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Spectator by " + ChatColor.RED + p.getName());
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] +  ChatColor.RED + "Spectator");
                            break;
                        default:
                            p.sendMessage(ServerSystem.PREFIX + "Gamemode Number invalid");
                            p.sendMessage(ServerSystem.PREFIX + "Help with: /gamemode help");
                    }
                }
                catch(NumberFormatException e) {
                    String gamemode = args[0];
                    Player other = Bukkit.getServer().getPlayer(args[1]);
                    if (gamemode.startsWith("su")) {
                        other.setGameMode(GameMode.SURVIVAL);
                        other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Survival by " + ChatColor.RED + p.getName());
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] + ChatColor.RED + "Survival");
                    } else if (gamemode.startsWith("cr")) {
                        other.setGameMode(GameMode.CREATIVE);
                        other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Creative by " + ChatColor.RED + p.getName());
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] + ChatColor.RED + "Creative");
                    } else if (gamemode.startsWith("ad")) {
                        other.setGameMode(GameMode.ADVENTURE);
                        other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Adventure by " + ChatColor.RED + p.getName());
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] + ChatColor.RED + "Adventure");
                    } else if (gamemode.startsWith("sp")) {
                        other.setGameMode(GameMode.SPECTATOR);
                        other.sendMessage(ServerSystem.PREFIX + "Your Gamemode was set to Spectator by " + ChatColor.RED + p.getName());
                        p.sendMessage(ServerSystem.PREFIX + "Gamemode of " + args[1] + ChatColor.RED + "Spectator");
                    } else {
                        other.sendMessage(ServerSystem.PREFIX + "Gamemode invalid!");
                        other.sendMessage(ServerSystem.PREFIX + "Help with /gamemode help");
                    }
                    return false;
                }
            } else {
                p.sendMessage(ServerSystem.PREFIX + ChatColor.RED + "Argument not recognised!");
                p.sendMessage(ServerSystem.PREFIX + ChatColor.GREEN + "Try /gamemode help");
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, cmd.getName());
        }
        return false;
    }
}
