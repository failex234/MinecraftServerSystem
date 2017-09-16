package de.failex.serversystem;

import de.failex.serversystem.commands.CMD_alwaysday;
import de.failex.serversystem.commands.CMD_gamemode;
import de.failex.serversystem.listener.Listeners;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerSystem extends JavaPlugin {

    //Get Plugin information from plugin.yml
    public final String NAME = this.getDescription().getName();
    public final String VERSION = this.getDescription().getVersion();
    public final String AUTHOR = this.getDescription().getAuthors().get(0);

    //Prefix of every message sent to any endpoint
    public static final String PREFIX = ChatColor.RED + "[" + ChatColor.YELLOW + "System" + ChatColor.RED + "] " + ChatColor.RESET;

    //Contains which player wants to teleport (requested to teleport) to another player
    public static HashMap<UUID, UUID> tpalist = new HashMap<>();

    //Contains every vanished player
    public static ArrayList<Player> vanish = new ArrayList<>();

    //Contains every command that should not get executed by a normal player
    public ArrayList<String> unwantedcmds = new ArrayList<>();

    //Contains every world where the daytime should not change
    public static ArrayList<World> alwaysday = new ArrayList<>();

    //Number of how many players are currently online on the server
    public static int onlineplayers;

    //Config for changing plugin settings and Playerdata File for storing homes and other stuff from players
    public static File config = new File("config.yml");
    public static File playerdata = new File("players.yml");


    @Override
    public void onEnable() {
        System.out.println("[" + NAME + "] Version " + VERSION + " by " + AUTHOR + " successfully enabled!");
        this.getCommand("alwaysday").setExecutor(new CMD_alwaysday());
        this.getCommand("gamemode").setExecutor(new CMD_gamemode());
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[" + NAME + "] Version " + VERSION + " by " + AUTHOR + " disabled!");
    }

    //Send player a message that the typed command is invalid / unknown
    public static void onUnknownCommand(Player p, String cmd) {
        p.sendMessage(PREFIX + ChatColor.RED + "Sorry, but the command \"" + cmd + "\" is not known :(");
    }

    public static void stopAlwaysDay(World w) {
        alwaysday.remove(w);
    }

    public static void startAlwaysDay(World w) {
        alwaysday.add(w);
    }
}
