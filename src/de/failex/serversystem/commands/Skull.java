package de.failex.serversystem.commands;


import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Gives player a skull of another player
 * <p>
 * Permissions:
 * - serversystem.skull
 * - serversystem.skull.other
 * </p>
 */
public class Skull implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender.hasPermission("serversystem.skull")) {
            switch (args.length) {
                case 1:
                    //Create a new skull and edit meta to set skull owner
                    ItemStack head = new ItemStack(Material.LEGACY_SKULL_ITEM, (byte) 3);
                    SkullMeta head_meta = (SkullMeta) head.getItemMeta();
                    head_meta.setOwner(args[0]);
                    head.setItemMeta(head_meta);

                    //Give Skull to player
                    Player current = (Player) sender;
                    current.getInventory().addItem(head);
                    current.sendMessage(Strings.SKULL_DONE.getString());
                    return true;
                case 2:
                    //Check if player has permissions
                    if (sender.hasPermission("serversystem.skull.other")) {
                        String playername = args[0];
                        Player target = Bukkit.getPlayer(args[1]);

                        //Check if player is online / exists
                        if (target == null) {
                            sender.sendMessage(Strings.TP_PLAYER_NOT_FOUND.getString());
                            return false;
                        }

                        //Create a new skull and edit meta to set skull owner
                        ItemStack head_other = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (byte) 3);
                        SkullMeta head_other_meta = (SkullMeta) head_other.getItemMeta();
                        head_other_meta.setOwner(playername);
                        head_other.setItemMeta(head_other_meta);

                        //Give item and notify players
                        target.getInventory().addItem(head_other);
                        target.sendMessage(String.format(Strings.SKULL_SENT_BY.getString(), sender.getName(), playername));
                        sender.sendMessage(String.format(Strings.SKULL_SENT_TO.getString(), playername, target.getName()));

                        return true;
                    } else {
                        //Run method normally when player doesn't have permissions
                        String[] playername = {args[0]};
                        this.onCommand(sender, cmd, arg, playername);
                        return true;
                    }
                default:
                    sender.sendMessage(Strings.SKULL_NO_ARGS.getString());
                    if (sender.hasPermission("serversystem.skull.other"))
                        sender.sendMessage(Strings.SKULL_NO_ARGS2.getString());
                    return true;
            }
        } else {
            ServerSystem.onUnknownCommand((Player) sender, "skull");
            return false;
        }
    }
}
