package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;


public class Msg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender.hasPermission("serversystem.msg")) {
            //TODO: Remove from hashmap on disconnect
            if (args.length == 0) {
                sender.sendMessage(Strings.MSG_NO_ARGS.getString());
                return false;
            } else if (args.length == 1) {
                sender.sendMessage(Strings.MSG_NO_MSG.getString());
                return false;
            } else {
                Player msgReceiver = Bukkit.getPlayer(args[0]);

                if (msgReceiver == null) {
                    sender.sendMessage(Strings.MSG_WRONG_RECV.getString());
                    return false;
                }

                Player msgSender = (Player) sender;

                if (msgSender.getName().equals(msgReceiver.getName())) {
                    msgSender.sendMessage(Strings.MSG_NO_SELF_MSG.getString());

                    return false;
                }

                ServerSystem.lastmsg.remove(msgReceiver.getUniqueId());
                ServerSystem.lastmsg.put(msgReceiver.getUniqueId(), msgSender.getUniqueId());

                StringBuffer bfMsg = new StringBuffer();

                for (int i = 1; i < args.length; i++) {
                    bfMsg.append(args[i]);
                }

                String msg = bfMsg.toString();

                msgReceiver.sendMessage(ChatColor.BLUE +  String.format("[%s => me]", msgSender.getName()) + ChatColor.RESET + msg);
                msgSender.sendMessage(ChatColor.BLUE + String.format("[me => %s]", msgReceiver.getName()) + ChatColor.RESET + msg);

                return true;

            }
        } else {
            ServerSystem.onUnknownCommand((Player)sender, "msg");
            return false;
        }
    }
}
