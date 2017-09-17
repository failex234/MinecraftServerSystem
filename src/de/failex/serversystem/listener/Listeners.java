package de.failex.serversystem.listener;

import de.failex.serversystem.ServerSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

//TODO Unknown Command Listener: Call onUnknownCommand in Main-CLass
public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //TODO Join Messages + MOTD, vanish players in arraylist
        //Vanish player on rejoin if not unvanished
        ServerSystem.onlineplayers++;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        //TODO Leave Message and more
        ServerSystem.onlineplayers--;
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
}
