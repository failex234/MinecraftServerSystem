package de.failex.serversystem.enums;

import de.failex.serversystem.ServerSystem;
import org.bukkit.ChatColor;

public enum Strings {
    PREFIX(ChatColor.RED + "[" + ChatColor.YELLOW + "System" + ChatColor.RED + "] " + ChatColor.RESET),
    UNKNOWN_COMMAND(PREFIX.getString() + ChatColor.RED + "Sorry, but the command \"%s\" is not known!"),
    ARG_UNREC(PREFIX.getString() + ChatColor.RED + "Argument not recognised!"),
    TRY_HELP(PREFIX.getString() + ChatColor.GREEN + "Try /%s help"),
    ALWAYSDAY_DISABLED(PREFIX.getString() + ChatColor.GREEN + "Alwaysday has been disabled for world %s"),
    ALWAYSDAY_ALREADY_DISABLED(PREFIX.getString() + "Alwaysday is already disabled for world %s"),
    ALWAYSDAY_ENABLED(PREFIX.getString() + ChatColor.GREEN + "Alwaysday has been enabled for world %s"),
    ALWAYSDAY_ALREADY_ENABLED(PREFIX.getString() + "Alwaysday is already enabled for world %s"),
    ALWAYSDAY_HELP_1(PREFIX.getString() + "--------- AlwaysDay Help Menu ---------"),
    ALWAYSDAY_HELP_2(PREFIX.getString() + "/alwaysday                    - toggle alwaysday for current world"),
    ALWAYSDAY_HELP_3(PREFIX.getString() + "/alwaysday <0|off|false|stop> - turn alwaysday off for current world"),
    ALWAYSDAY_HELP_4(PREFIX.getString() + "/alwaysday <1|on|true|start>  - turn alwaysday on for current world"),
    ALWAYSDAY_HELP_5(PREFIX.getString() + "----------------------------------------"),
    ;


    private String str;
    Strings(String str) {
        this.str = str;
    }

    public String getString() {
        return this.str;
    }

}
