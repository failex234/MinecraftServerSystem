package de.failex.serversystem;

import de.failex.serversystem.config.ConfigManager;

public class Utils {

    public static int[] getPlayerCoordinates(String type, String playername) {
        int[] coords = new int[3];
        coords[0] = Integer.parseInt(ConfigManager.getFromPlayer(playername, type + ".X"));
        coords[1] = Integer.parseInt(ConfigManager.getFromPlayer(playername, type + ".Y"));
        coords[2] = Integer.parseInt(ConfigManager.getFromPlayer(playername, type + ".Z"));

        return coords;
    }
}
