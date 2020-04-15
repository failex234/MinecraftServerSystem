package de.failex.serversystem.commands;

import de.failex.serversystem.ServerSystem;
import de.failex.serversystem.enums.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Reply implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        //TODO: Dont reply to a message when the original sender is offline!
        if (sender.hasPermission("serversystem.msg.reply")) {
            Player msgSender = (Player) sender;
            if (!ServerSystem.lastmsg.containsKey(msgSender.getUniqueId())) {
                msgSender.sendMessage(Strings.REPLY_NO_SENT.getString());

                return false;
            } else {
                UUID pUUID = msgSender.getUniqueId();
                Player msgReceiver = Bukkit.getPlayer(ServerSystem.lastmsg.get(pUUID));

                if (msgReceiver == null) {
                    ServerSystem.lastmsg.remove(pUUID);
                    msgSender.sendMessage(Strings.REPLY_SENDER_OFFLINE.getString());

                    return false;
                }

                if (args.length == 0) {
                    msgSender.sendMessage(Strings.REPLY_MISSING_MSG.getString());

                    return false;
                }

                StringBuffer sbMsg = new StringBuffer();

                for (int i = 0; i < args.length; i++) {
                    sbMsg.append(args[i]);
                }

                String msg = sbMsg.toString();

                msgReceiver.sendMessage(ChatColor.BLUE +  String.format("[%s => me]", msgSender.getName()) + ChatColor.RESET + msg);
                msgSender.sendMessage(ChatColor.BLUE + String.format("[me => %s]", msgReceiver.getName()) + ChatColor.RESET + msg);

                ServerSystem.lastmsg.remove(msgReceiver.getUniqueId());
                ServerSystem.lastmsg.put(msgReceiver.getUniqueId(), msgSender.getUniqueId());
            }
        } else {
            ServerSystem.onUnknownCommand((Player)sender, cmd.getName());
        }
        return false;
    }
}
