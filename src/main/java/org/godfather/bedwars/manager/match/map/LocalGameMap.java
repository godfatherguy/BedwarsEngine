package org.godfather.bedwars.manager.match.map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.godfather.bedwars.manager.match.Match;
import org.godfather.bedwars.utils.FileUtil;

import java.io.File;
import java.io.IOException;

public class LocalGameMap implements GameMap{

    private Match match;
    private final File sourceWorldFolder;
    private File activeWorldFolder;
    private World bukkitWorld;

    public LocalGameMap(Match match, File worldfolder, String worldname, boolean loadOnInit){
        this.sourceWorldFolder = new File(worldfolder, worldname);
        this.match = match;
        if(loadOnInit) load();
    }

    public boolean load(){
        if(isLoaded()) return true;

        switch(match.getMatchType()){
            case SOLO:
                this.activeWorldFolder = new File(Bukkit.getWorldContainer().getParentFile(),  "active_" + sourceWorldFolder.getName() + "_solo_" + System.currentTimeMillis());
                break;
            case DOUBLE:
                this.activeWorldFolder = new File(Bukkit.getWorldContainer().getParentFile(),  "active_" + sourceWorldFolder.getName() + "_double_" + System.currentTimeMillis());
                break;
            case TRIPLE:
                this.activeWorldFolder = new File(Bukkit.getWorldContainer().getParentFile(),  "active_" + sourceWorldFolder.getName() + "_triple_" + System.currentTimeMillis());
                break;
            case SQUAD:
                this.activeWorldFolder = new File(Bukkit.getWorldContainer().getParentFile(),  "active_" + sourceWorldFolder.getName() + "_squad_" + System.currentTimeMillis());
                break;
        }

        try{
            FileUtil.copy(sourceWorldFolder, activeWorldFolder);
        } catch(IOException e){
            e.printStackTrace();
        }

        this.bukkitWorld = Bukkit.createWorld(new WorldCreator(activeWorldFolder.getName()));
        if(bukkitWorld != null) this.bukkitWorld.setAutoSave(false);
        return isLoaded();
    }

    public void unload(){
        if(bukkitWorld != null) Bukkit.unloadWorld(bukkitWorld, false);
        if(activeWorldFolder != null) FileUtil.delete(activeWorldFolder);

        bukkitWorld = null;
        activeWorldFolder = null;
    }

    public boolean restoreFromSource(){
        unload();
        return load();
    }

    public boolean isLoaded(){
        return this.bukkitWorld != null;
    }

    public World getWorld() {
        return bukkitWorld;
    }
}
