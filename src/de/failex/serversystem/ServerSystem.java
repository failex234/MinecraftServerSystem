package de.failex.serversystem;

import de.failex.serversystem.commands.*;
import de.failex.serversystem.commands.System;
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

    //Contains which player wants to teleport (requested to teleport) to another player
    public static HashMap<UUID, UUID> tpalist = new HashMap<>();
    public static HashMap<UUID, String> tpatype = new HashMap<>();

    //Contains the last sender of a private message to make the reply command work
    public static HashMap<UUID, UUID> lastmsg = new HashMap<>();

    //Contains every vanished player
    public static ArrayList<Player> vanish = new ArrayList<>();

    //Contains every command that should not get executed by a normal player
    public static ArrayList<String> unwantedcmds = new ArrayList<>();

    //Contains every world where the daytime should not change
    public static ArrayList<World> alwaysday = new ArrayList<>();

    //Number of how many players are currently online on the server, gets incremented
    //and decremented by the PlayerJoin- and PlayerQuitEvent in the Listener class
    public static int onlineplayers;

    //Config for changing plugin settings and Playerdata File for storing homes and other stuff from players
    public File playerdata = new File(getDataFolder(), "players.yml");

    //FileConfigurations for config and playerdata file
    public FileConfiguration playerdatacfg = YamlConfiguration.loadConfiguration(playerdata);

    //Class-Variable for specific methods that you can't run without a static class variable
    public static ServerSystem system;


    //TODO: Don't run certain commands as console / restrict usage so console can't run them
    @Override
    public void onEnable() {
        system = this;
        java.lang.System.out.println("[" + NAME + "] Version " + VERSION + " by " + AUTHOR + " successfully enabled!");
        this.getCommand("alwaysday").setExecutor(new Alwaysday());
        this.getCommand("gamemode").setExecutor(new Gamemode());
        this.getCommand("online").setExecutor(new Online());
        this.getCommand("ping").setExecutor(new Ping());
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("msg").setExecutor(new Msg());
        this.getCommand("r").setExecutor(new Reply());
        this.getCommand("reply").setExecutor(new Reply());
        this.getCommand("sethome").setExecutor(new Sethome());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("setspawn").setExecutor(new Setspawn());
        this.getCommand("warp").setExecutor(new Warp());
        this.getCommand("setwarp").setExecutor(new Setwarp());
        this.getCommand("sudo").setExecutor(new Sudo());
        this.getCommand("system").setExecutor(new System());
        this.getCommand("time").setExecutor(new Time());
        this.getCommand("tp").setExecutor(new Tp());
        this.getCommand("tphere").setExecutor(new Tphere());
        this.getCommand("tpa").setExecutor(new Tpa());
        this.getCommand("tpahere").setExecutor(new Tpahere());
        this.getCommand("tpaccept").setExecutor(new Tpaccept());
        this.getCommand("vanish").setExecutor(new Vanish());
        this.getCommand("unvanish").setExecutor(new Unvanish());
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        java.lang.System.out.println("[" + NAME + "] Version " + VERSION + " by " + AUTHOR + " disabled!");
    }

    //Send player a message that the typed command is invalid / unknown
    public static void onUnknownCommand(Player p, String cmd) {
        p.sendMessage(String.format(Strings.UNKNOWN_COMMAND.getString(), cmd));
    }

    public static void stopAlwaysDay(World w) {
        alwaysday.remove(w);
    }

    public static void startAlwaysDay(World w) {
        alwaysday.add(w);
    }

}
