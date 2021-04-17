package network.manu.loginspots;

import network.manu.loginspots.commands.LoginSpotsCommand;
import network.manu.loginspots.listeners.LoginListener;
import network.manu.loginspots.runnables.BelowMinYResetRunnable;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginSpots extends JavaPlugin {
    public static JavaPlugin plugin;
    public static FileConfiguration config;
    public static Map<Player, Location> playerSpot = new HashMap<>();
    public static Map<Location, Integer> spotUsage = new HashMap<>();

    @Override
    public void onEnable() {
        this.getCommand("loginspots").setExecutor(new LoginSpotsCommand());

        getServer().getPluginManager().registerEvents(new LoginListener(), this);

        this.saveDefaultConfig();

        config = this.getConfig();
        for (Location spot : (ArrayList<Location>)config.get("loginspots")) {
            spotUsage.put(spot, 0);
        }
        plugin = this;

        new BelowMinYResetRunnable().runTaskTimer(this, 0, 1);
    }

    @Override
    public void onDisable() {

    }
}
