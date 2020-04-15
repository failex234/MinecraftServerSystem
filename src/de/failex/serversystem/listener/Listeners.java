package de.failex.serversystem.listener;

import de.failex.serversystem.ServerSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.help.HelpTopic;
import org.bukkit.util.Vector;

//TODO Unknown Command Listener: Call onUnknownCommand in Main-CLass
public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //TODO Join Messages + MOTD, vanish players in arraylist
        if (ServerSystem.vanish.contains(e.getPlayer())) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.hasPermission("serversystem.vanish.bypass") & p != e.getPlayer()) {
                    p.hidePlayer(e.getPlayer());
                }
            }
        }
        ServerSystem.onlineplayers++;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        //TODO Leave Message and more, remove frop TPA List
        ServerSystem.onlineplayers--;

        ServerSystem.lastmsg.remove(e.getPlayer().getUniqueId());
        ServerSystem.tpalist.remove(e.getPlayer().getUniqueId());
        ServerSystem.tpatype.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    /*
     * Double-Jump Section
     */
    //TODO: Test and improve
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (!e.getPlayer().isFlying() && !e.getPlayer().getAllowFlight()) {
            e.getPlayer().setAllowFlight(true);
        }
    }

    @EventHandler
    public void onFlight(PlayerToggleFlightEvent e) {
        e.getPlayer().setFlying(false);
        e.getPlayer().setVelocity(new Vector(2, 2, 2));
    }

    @EventHandler
    public void onUnknownCommand(PlayerCommandPreprocessEvent e) {
        if (!e.isCancelled()) {
            Player player = e.getPlayer();
            String cmd = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            if (!player.hasPermission("serversystem.unwanted.bypass")) {
                if (ServerSystem.unwantedcmds.contains(cmd)) {
                    ServerSystem.onUnknownCommand(player, cmd);
                    e.setCancelled(true);
                }
            }
            if (topic == null) {
                ServerSystem.onUnknownCommand(player, cmd);
                e.setCancelled(true);
            }
        }
    }
}
