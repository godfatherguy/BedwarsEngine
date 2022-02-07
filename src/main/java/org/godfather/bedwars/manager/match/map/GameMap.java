package org.godfather.bedwars.manager.match.map;

import org.bukkit.World;

public interface GameMap {
    boolean load();
    void unload();
    boolean restoreFromSource();
    boolean isLoaded();
    World getWorld();
}
