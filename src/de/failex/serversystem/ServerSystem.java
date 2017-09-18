package de.failex.serversystem;

import de.failex.serversystem.commands.*;
import de.failex.serversystem.enums.Strings;
import de.failex.serversystem.listener.Listeners;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
    public static final String PREFIX = Strings.PREFIX.getString();

    //Contains which player wants to teleport (requested to teleport) to another player
    public static HashMap<UUID, UUID> tpalist = new HashMap<>();

    //Contains every vanished player
    public static ArrayList<Player> vanish = new ArrayList<>();

    //Contains every command that should not get executed by a normal player
    public ArrayList<String> unwantedcmds = new ArrayList<>();

    //Contains every world where the daytime should not change
    public static ArrayList<World> alwaysday = new ArrayList<>();

    //Number of how many players are currently online on the server, gets incremented
    //and decremented by the PlayerJoin- and PlayerQuitEvent in the Listener class
    public static int onlineplayers;

    //Config for changing plugin settings and Playerdata File for storing homes and other stuff from players
    public static File config = new File("config.yml");
    public static File playerdata = new File("players.yml");

    //FileConfigurations for config and playerdata file
    public static FileConfiguration configcfg = YamlConfiguration.loadConfiguration(config);
    public static FileConfiguration playerdatacfg = YamlConfiguration.loadConfiguration(playerdata);

    //Class-Variable for specific methods that you can't run without a static class variable
    public static ServerSystem system;


    @Override
    public void onEnable() {
        system = this;
        System.out.println("[" + NAME + "] Version " + VERSION + " by " + AUTHOR + " successfully enabled!");
        this.getCommand("alwaysday").setExecutor(new CMD_alwaysday());
        this.getCommand("gamemode").setExecutor(new CMD_gamemode());
        this.getCommand("online").setExecutor(new CMD_online());
        this.getCommand("ping").setExecutor(new CMD_ping());
        this.getCommand("home").setExecutor(new CMD_home());
        this.getCommand("sethome").setExecutor(new CMD_sethome());
        this.getCommand("setspawn").setExecutor(new CMD_setspawn());
        this.getCommand("setwarp").setExecutor(new CMD_setwarp());
        this.getCommand("spawn").setExecutor(new CMD_spawn());
        this.getCommand("sudo").setExecutor(new CMD_sudo());
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[" + NAME + "] Version " + VERSION + " by " + AUTHOR + " disabled!");
    }

    //Send player a message that the typed command is invalid / unknown
    public static void onUnknownCommand(Player p, String cmd) {
        p.sendMessage(Strings.UNKNOWN_COMMAND.getString().replace("%s", cmd));
    }

    public static void stopAlwaysDay(World w) {
        alwaysday.remove(w);
    }

    public static void startAlwaysDay(World w) {
        alwaysday.add(w);
    }

}
