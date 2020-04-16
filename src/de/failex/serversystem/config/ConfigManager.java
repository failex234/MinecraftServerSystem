package de.failex.serversystem.config;

import de.failex.serversystem.ServerSystem;

import java.io.IOException;

public class ConfigManager {

    /**
     * Add / modify entry in config
     * @param key the key to modify / add
     * @param value new value / initial value of key
     */
    public static void set(String key, String value) {
        ServerSystem.system.getConfig().set(key, value);
        ServerSystem.system.saveConfig();
        ServerSystem.system.reloadConfig();
    }

    /**
     * Get value from config
     * @param key what key to get from the config
     * @return value from key, null if non-existent
     */
    public static String get(String key) {
        if (ServerSystem.system.getConfig().get(key) == null) {
            return null;
        }
        return (String) ServerSystem.system.getConfig().get(key);
    }

    /**
     * Add / modify parameter of player / to player
     * @param player set the parameter of which player
     * @param key what key to set
     * @param value what value to set
     */
    public static void setPlayer(String player, String key, String value) {
        ServerSystem.system.playerdatacfg.set(player + "." + key, value);
        try {
            ServerSystem.system.playerdatacfg.save(ServerSystem.system.playerdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerSystem.system.reloadConfig();
    }

    /**
     * Get value of any playerdata
     * @param player from what player to get the data from
     * @param key the data to get
     * @return the data from the player, null if non-existent
     */
    public static String getFromPlayer(String player, String key) {
        return (String) ServerSystem.system.playerdatacfg.get(player + "." + key);
    }

}
