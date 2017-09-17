package de.failex.serversystem.enums;

import org.bukkit.ChatColor;

//TODO: Define Message Colors, Unify Help Menu Headers and Footers
public enum Strings {
    PREFIX(ChatColor.RED + "[" + ChatColor.YELLOW + "System" + ChatColor.RED + "] " + ChatColor.RESET),
    UNKNOWN_COMMAND(PREFIX.getString() + ChatColor.RED + "Sorry, but the command \"%s\" is not known!"),
    ONLY_PLAYER_COMMAND(PREFIX.getString() + "You need to be a player to execute this command!"),
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
    HOME_NOT_SET(PREFIX.getString() + "Your home has not been set yet!"),
    HOME_TELEPORTED(PREFIX.getString() + "Welcome Home!"),
    HOME_TPD_TO_PLAYERS_HOME(PREFIX.getString() + "Welcome at %s's House!"),
    HOME_NO_HOME_POINT_FOUND(PREFIX.getString() + "Sorry but %s has no home point yet!"),
    GAMEMODE_USAGE_RESTRICTED(PREFIX.getString() + "Gamemode Usage: /gamemode <0|1|2|3>"),
    GAMEMODE_MORE_INFO(PREFIX.getString() + "More usage information with /gamemode help"),
    GAMEMODE_USAGE_PERMITTED_1(PREFIX.getString() + "Gamemode Usage: /gamemode <0|1|2|3> {player}"),
    GAMEMODE_USAGE_PERMITTED_2(PREFIX.getString() + "The player argument is optional!"),
    GAMEMODE_INT_INVALID(PREFIX.getString() + ChatColor.RED + "Gamemode Number invalid!"),
    GAMEMODE_INVALID(PREFIX.getString() + ChatColor.RED + "Gamemode invalid!"),
    GAMEMODE_SET_TO(PREFIX.getString() + "Gamemode set to " + ChatColor.RED + "%s"),
    GAMEMODE_SET_TO_PLAYER(PREFIX.getString() + "Gamemode of %s changed to " + ChatColor.RED + "%g"),
    GAMEMODE_SET_BY(PREFIX.getString() + "Your Gamemode was changed by %s"),
    GAMEMODE_HELP_WITH(PREFIX.getString() + "Help with: /gamemode help"),
    GAMEMODE_HELP_TITLE("--------- Gamemode Help Menu ---------"),
    GAMEMODE_HELP_FOOTER("----------------------------------------"),
    GAMEMODE_HELP_1("1. /gamemode <0|1|2|3> - Set own gamemode"),
    GAMEMODE_HELP_RESTRICTED_2("2. /gamemode <su|cr|ad|sp> - Set own gamemode"),
    GAMEMODE_HELP_RESTRICTED_3("3. /gamemode help - Show this menu"),
    GAMEMODE_HELP_PERMITTED_2("2. /gamemode <su|cr|ad|sp>- Set own gamemode"),
    GAMEMODE_HELP_PERMITTED_3("3. /gamemode <0|1|2|3> <player> - Set gamemode of player"),
    GAMEMODE_HELP_PERMITTED_4("4. /gamemode <su|cr|ad|sp> <player> - Set gamemode of player"),
    GAMEMODE_HELP_PERMITTED_5("5. /gamemode help - Shows this menu"),
    SETHOME_HOME_SET(PREFIX.getString() + "Your home point was successfully set!"),
    SETHOME_HELP_TITLE("------------- SetHome Help Menu -------------"),
    SETHOME_HELP_1("1. /sethome - Sets your homepoint"),
    SETHOME_HELP_2("2. /sethome help - Shows this menu"),
    SETHOME_HELP_FOOTER("---------------------------------------------"),
    SETSPAWN_SPAWN_SET(PREFIX.getString() + "Spawn was successfully set!"),
    SETSPAWN_HELP_TITLE("----------- SetSpawn Help Menu -----------"),
    SETSPAWN_HELP_1("1. /setspawn - Set the world spawn to your position"),
    SETSPAWN_HELP_2("2. /setspawn help - Shows this menu"),
    SETSPAWN_HELP_FOOTER("-------------------------------------------"),
    ;


    private String str;
    Strings(String str) {
        this.str = str;
    }

    public String getString() {
        return this.str;
    }

}
