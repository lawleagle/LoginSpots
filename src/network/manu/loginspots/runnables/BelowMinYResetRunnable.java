package network.manu.loginspots.runnables;

import network.manu.loginspots.LoginSpots;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Map;

public class BelowMinYResetRunnable extends BukkitRunnable {
    @Override
    public void run() {
        for (Map.Entry<Player, Location> entry : LoginSpots.playerSpot.entrySet()) {
            Player player = entry.getKey();
            Location location = entry.getValue();

            if (player.getLocation().getBlockY() < (Integer)LoginSpots.config.get("min-y")) {
                player.setFallDistance(0);
                player.teleport(location);
            }
        }
    }
}
