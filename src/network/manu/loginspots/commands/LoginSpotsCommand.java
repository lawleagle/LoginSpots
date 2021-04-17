package network.manu.loginspots.commands;

import network.manu.loginspots.LoginSpots;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginSpotsCommand implements CommandExecutor {

    private void onCommand_list(CommandSender commandSender) {
        commandSender.sendMessage(LoginSpots.config.get("loginspots").toString());
    }
    private void onCommand_setMinY(CommandSender commandSender, int minY) {
        LoginSpots.config.set("min-y", minY);
        LoginSpots.plugin.saveConfig();
        commandSender.sendMessage("min-y set to " + minY);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            return false;
        }

        String arg0 = strings[0];

        if (commandSender instanceof ConsoleCommandSender) {
            switch (arg0) {
                case "list": {
                    if (strings.length != 1) {
                        return false;
                    }
                    onCommand_list(commandSender);
                } return true;

                case "set-min-y": {
                    if (strings.length != 2) {
                        return false;
                    }
                    // TODO(manu): Check for conversion errors.
                    int minY = Integer.parseInt(strings[1]);
                    onCommand_setMinY(commandSender, minY);
                } return true;

                default: {
                    return false;
                }
            }
        }

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            switch (arg0) {
                case "add": {
                    ArrayList<Location> loginspots = (ArrayList<Location>)LoginSpots.config.get("loginspots");

                    Location location = player.getLocation();
                    loginspots.add(location);

                    LoginSpots.config.set("loginspots", loginspots);
                    LoginSpots.spotUsage.put(location, 0);
                    LoginSpots.plugin.saveConfig();
                } return true;

                case "clear": {
                    ArrayList<Location> loginspots = new ArrayList<>();

                    LoginSpots.config.set("loginspots", loginspots);
                    LoginSpots.plugin.saveConfig();

                    LoginSpots.playerSpot.clear();
                    LoginSpots.spotUsage.clear();
                } return true;

                case "tp": {
                    if (LoginSpots.playerSpot.containsKey(player)) {
                        Location location = LoginSpots.playerSpot.get(player);
                        player.teleport(location);
                    }
                } return true;

                case "list": {
                    onCommand_list(commandSender);
                } return true;

                case "set-min-y": {
                    if (strings.length > 2) {
                        return false;
                    }
                    int minY;
                    if (strings.length == 2) {
                        minY = Integer.parseInt(strings[0]);
                    }
                    else {
                        minY = player.getLocation().getBlockY();
                    }
                    onCommand_setMinY(commandSender, minY);
                } return true;

                default: {
                } return false;
            }
        }
        commandSender.sendMessage("This shouldn't be reached. This command is handled when invoked by 'ConsoleCommandSender' or 'Player'");
        return false;
    }
}
