package org.godfather.bedwars;

import org.bukkit.plugin.java.JavaPlugin;
import org.godfather.bedwars.manager.GameManager;
import org.godfather.bedwars.manager.match.listeners.ChatListener;

public class Main extends JavaPlugin {

    public static Main plugin;
    private GameManager gameManager;

    public void onEnable(){
        super.onEnable();
        plugin = this;
        saveDefaultConfig();
        gameManager = new GameManager();

        getServer().getPluginManager().registerEvents(new ChatListener(gameManager), this);
    }

    public void onDisable(){
        super.onDisable();

    }
}
