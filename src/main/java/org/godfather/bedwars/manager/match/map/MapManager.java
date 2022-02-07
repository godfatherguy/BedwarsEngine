package org.godfather.bedwars.manager.match.map;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.godfather.bedwars.Main;
import org.godfather.bedwars.manager.match.Match;
import org.godfather.bedwars.manager.match.MatchType;

import java.io.File;
import java.util.Random;

public class MapManager {

    private Match match;
    private Maps map;
    private GameMap gameMap;
    public static World world;

    public MapManager(Match match){
        this.match = match;
    }

    public void setMap(Maps map){
        this.map = map;

        Main.plugin.getDataFolder().mkdirs();
        File gameMapsFolder = new File(Main.plugin.getDataFolder(), "gameMaps");
        if(!gameMapsFolder.exists()){
            gameMapsFolder.mkdirs();
        }

        switch(map){
            case LOTUS:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Lotus", true);
                world = gameMap.getWorld();
                break;
            case HOLLOW:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Hollow", true);
                world = gameMap.getWorld();
                break;
            case LECTUS:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Lectus", true);
                world = gameMap.getWorld();
                break;
            case ASHFIRE:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Ashfire", true);
                world = gameMap.getWorld();
                break;
            case BOLETUM:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Boletum", true);
                world = gameMap.getWorld();
                break;
            case ROOFTOP:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Rooftop", true);
                world = gameMap.getWorld();
                break;
            case EASTWOOD:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Eastwood", true);
                world = gameMap.getWorld();
                break;
            case WATERFALL:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Waterfall", true);
                world = gameMap.getWorld();
                break;
            case LIGHTHOUSE:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Lighthouse", true);
                world = gameMap.getWorld();
                break;
            case PERNICIOUS:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Pernicious", true);
                world = gameMap.getWorld();
                break;
            case SWASHBUCKLE:
                gameMap = new LocalGameMap(match, gameMapsFolder, "Swashbuckle", true);
                world = gameMap.getWorld();
                break;
        }
    }

    public Maps getMap(){
        return this.map;
    }

    public GameMap getGameMap(){
        return this.gameMap;
    }

    public static World getWorld(){
        return world;
    }

    public void setRandomMap(){
        if(match.getMatchType() == MatchType.SOLO || match.getMatchType() == MatchType.DOUBLE){
            int random = new Random().nextInt(1);
            switch(random){
                case 0:
                    setMap(Maps.ASHFIRE);
                    break;
                case 1:
                    setMap(Maps.HOLLOW);
                    break;
            }
        }
        else{
            int random = new Random().nextInt(0);
            switch(random) {
                case 0:
                    setMap(Maps.BOLETUM);
                    break;
            }
        }
    }

    public void unloadMap(){
        world = null;
        gameMap.unload();
    }

    public void teleportToSpawn(Player p){
        p.teleport(getMap().getWaitingSpawn());
    }

    public void teleportToSpectator(Player p){
        p.teleport(getMap().getSpectatorSpawn());
    }
}
