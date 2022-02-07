package org.godfather.bedwars.manager.lobby.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpawnLocation {

    public static Location getLocation(){
        return new Location(Bukkit.getWorld("world"), 0.0, 71, 0.0, (float) 90.0, (float) 0.0);
    }
}
