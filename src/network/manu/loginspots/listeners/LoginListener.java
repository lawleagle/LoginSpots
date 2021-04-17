package network.manu.loginspots.listeners;

import network.manu.loginspots.LoginSpots;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Map.Entry<Location, Integer> entry : LoginSpots.spotUsage.entrySet()) {
            Location spot = entry.getKey();
            Integer usage = entry.getValue();

            if (usage == 0) {
                LoginSpots.playerSpot.put(player, spot);
                LoginSpots.spotUsage.put(spot, 1);
                break;
            }
        }

        if (LoginSpots.playerSpot.containsKey(player)) {
            player.teleport(LoginSpots.playerSpot.get(player));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Location spot = LoginSpots.playerSpot.get(player);

        Integer spotUsage = LoginSpots.spotUsage.get(spot);
        LoginSpots.spotUsage.put(spot, spotUsage - 1);
    }

}
