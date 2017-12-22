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
    PLAYERS_ONLINE(PREFIX.getString() + "%d players are currently online!"),
    PLAYER_ONLINE(PREFIX.getString() + "1 player is currently online!"),
    PING_NOTICE(PREFIX.getString() + "Your ping is %dms"),
    PING_NOTICE_OTHER(PREFIX.getString() + "The ping of %s is %dms"),
    PING_CONSOLE(PREFIX.getString() + "This is the console, your ping should normally be around 0ms"),
    PING_HELP_TITLE("--------- Ping Help Menu ---------"),
    PING_HELP_1("1. /ping <player> - Show latency of player"),
    PING_HELP_1_UNPREV("1. /ping - Show your ping"),
    PING_HELP_2("2. /ping - Show your ping"),
    PING_HELP_2_UNPREV("2. /ping help - Show this menu"),
    PING_HELP_3("3. /ping help - Show this menu"),
    PING_HELP_FOOTER("----------------------------------"),
    SETWARP_HELP_TITLE("----------- SetWarp Help Menu -----------"),
    SETWARP_HELP_1("1. /setwarp <warp> - Set the world spawn to your position"),
    SETWARP_HELP_2("2. /setwarp <delete|remove> <warp> - Remove warp"),
    SETWARP_HELP_3("3. /setwarp help - Shows this menu"),
    SETWARP_HELP_FOOTER("------------------------------------------"),
    SETWARP_CREATED(PREFIX.getString() + "Successfully created warp \"%s\" at your position"),
    SETWARP_CANT_CREATE(PREFIX.getString() + "The warp \"%s\" already exists!"),
    SETWARP_DELETED(PREFIX.getString() + "Successfully deleted warp \"%s\"!"),
    SETWARP_CANT_DELETE(PREFIX.getString() + "The warp \"%s\" doesn't exist!"),
    SETWARP_NO_ARG(PREFIX.getString() + "You forgot the name of your new warp point"),
    SUDO_MISSING_ARG(PREFIX.getString() + "You are missing a player or a command!"),
    SUDO_HELP_TITLE("----------- Sudo Help Menu -----------"),
    SUDO_HELP_1("1. /sudo <player> <command> - Force player to run command"),
    SUDO_HELP_2("2. /sudo help - Shows this menu"),
    SUDO_HELP_FOOTER("---------------------------------------"),
    SUDO_NOT_ALLOWED_TO_SUDO(PREFIX.getString() + "You're not allowed to sudo %s!"),
    SUDO_RUNNING(PREFIX.getString() + "Forcing %s to run %c..."),
    SUDO_PLAYER_NOT_ONLINE(PREFIX.getString() + "This player doesn't seem to be online!"),
    SYSTEM_INFO(PREFIX.getString() + "v.%s by %a"),
    SYSTEM_INFO_COMP_DATE(PREFIX.getString() + "Compiled on %s"),
    TIME_USAGE(PREFIX.getString() + "Usage: /time <set|add|rem> <day/1200>"),
    TIME_SET_TO(PREFIX.getString() + "Time in world %s set to %d"),
    TIME_ADDED_TO(PREFIX.getString() + "Added %s ticks to time in world %w"),
    TIME_REMOVED(PREFIX.getString() + "Removed %s ticks from time in world %w"),
    TIME_WRONG_FORMAT(PREFIX.getString() + "This is a wrong time format!"),
    TP_PLAYER_NOT_FOUND(PREFIX.getString() + "Player not found!"),
    TP_PLAYERS_NOT_FOUND(PREFIX.getString() + "Not all players found!"),
    TP_TELEPORTING(PREFIX.getString() + "Teleporting..."),
    TP_TELEPORTING_A_TO_B(PREFIX.getString() + "Teleporting %a to %b..."),
    TP_HELP_TITLE("--------------- TP Help Menu ---------------"),
    TP_HELP_1("1. /tp <player> - Teleport to player"),
    TP_HELP_2_RESTRICTED("2. /tp <a> <b> - Teleport player a to player b"),
    TP_HELP_FOOTER("---------------------------------------------"),
    TPA_NO_NAME_GIVEN(PREFIX.getString() + "Which player do you want to teleport to?"),
    TPA_SENDING(PREFIX.getString() + "Sending a request to %s"),
    TPA_NOTIFICATION_LINE1(PREFIX.getString() + "%s  wants to teleport to you"),
    TPA_NOTIFICATION_LINE2(PREFIX.getString() + "[Accept] or [Decline]"),
    TPA_NO_REQUESTS(PREFIX.getString() + "No pending requests!"),
    TPA_ACCEPTING_SENDER(PREFIX.getString() + "Accepting request"),
    TPA_ACCEPTING_REQUESTER(PREFIX.getString() + "%s accepted your request!"),
    TPA_DECLINED_SENDER(PREFIX.getString() + "Declining request"),
    TPA_DECLINED_REQUESTER(PREFIX.getString() + "%s declined your request!"),
    TPHERE_NO_ARGS(PREFIX.getString() + "You haven't specified whom to teleport to you!"),
    VANISH_NOW_VANISHED(PREFIX.getString() + "You're now vanished. Poof!"),
    VANISH_ALREADY_VANISHED(PREFIX.getString() + "You're already vanished!"),
    VANISH_NOT_VANISHED(PREFIX.getString() + "You're not vanished!"),
    VAHISH_UNVANISHED(PREFIX.getString() + "You're now visible again!"),
    WARP_NO_WARP_SET(PREFIX.getString() + "No warp points found"),
    WARP_AVAILABLE_WARPS(PREFIX.getString() + "Available warps: "),
    WARP_NOT_FOUND(PREFIX.getString() + "No such warp point!"),
    WARP_WARPED(PREFIX.getString() + "Warping to %s..."),
    ;


    private String str;
    Strings(String str) {
        this.str = str;
    }

    public String getString() {
        return this.str;
    }

}
