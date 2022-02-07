package org.godfather.bedwars.manager.match.map;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.godfather.bedwars.manager.match.players.team.Teams;

public enum Maps {
    ASHFIRE,HOLLOW,LIGHTHOUSE,LOTUS,PERNICIOUS,ROOFTOP,WATERFALL,
    BOLETUM,EASTWOOD,LECTUS,SWASHBUCKLE;

    public String getName(){
        return WordUtils.capitalizeFully(this.toString().toLowerCase());
    }

    public Location getWaitingSpawn(){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                return new Location(world, 0.5, 123, 0.5, (float) 90.0, (float) 0.0);
            case HOLLOW:
                return new Location(world, 0.5, 118, 0.5, (float) 180.0, (float) 0.0);
            case BOLETUM:
                return new Location(world, 0.5, 118, 0.5, (float) 0.0, (float) 0.0);
        }
        return null;
    }

    public Location getSpectatorSpawn(){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                return new Location(world, 0.5, 82, 0.5, (float) 90.0, (float) 0.0);
            case HOLLOW:
                return new Location(world, 20.5, 87, 0.5, (float) -90.0, (float) 0.0);
            case BOLETUM:
                return new Location(world, 0.5, 105, 0.5, (float) 0.0, (float) 0.0);
        }
        return null;
    }

    public Location getTeamSpawn(Teams team){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                switch(team){
                    case RED:
                        return new Location(world, 51.5, 71, -87.5, (float) 90.0, (float) 0.0);
                    case WHITE:
                        return new Location(world, -87.5, 71, 51.5, (float) 180.0, (float) 0.0);
                    case BLUE:
                        return new Location(world, 88.5, 71, -50.5, (float) 0.0, (float) 0.0);
                    case YELLOW:
                        return new Location(world, 51.5, 71, 88.5, (float) 90.0, (float) 0.0);
                    case GREEN:
                        return new Location(world, 88.5, 71, 51.5, (float) 180.0, (float) 0.0);
                    case PINK:
                        return new Location(world, -87.5, 71, -50.5, (float) 0.0, (float) 0.0);
                    case GRAY:
                        return new Location(world, -50.5, 71, -87.5, (float) -90.0, (float) 0.0);
                    case AQUA:
                        return new Location(world, -50.5, 71, 88.5, (float) -90.0, (float) 0.0);
                }
                break;
            case HOLLOW:
                switch(team){
                    case RED:
                        return new Location(world, -28.5, 67, -80.5, (float) 0.0, (float) 0.0);
                    case WHITE:
                        return new Location(world, -27.5, 67, 81.5, (float) -180.0, (float) 0.0);
                    case BLUE:
                        return new Location(world, 28.5, 67, -80.5, (float) 0.0, (float) 0.0);
                    case YELLOW:
                        return new Location(world, 81.5, 67, 28.5, (float) 90.0, (float) 0.0);
                    case GREEN:
                        return new Location(world, 81.5, 67, -28.5, (float) 90.0, (float) 0.0);
                    case PINK:
                        return new Location(world, -80.5, 67, 29.5, (float) -90.0, (float) 0.0);
                    case GRAY:
                        return new Location(world, -80.5, 67, -27.5, (float) -90.0, (float) 0.0);
                    case AQUA:
                        return new Location(world, 29.5, 67, 81.5, (float) 180.0, (float) 0.0);
                }
                break;
            case BOLETUM:
                switch(team){
                    case RED:
                        return new Location(world, 79.5, 88, -1.5, (float) 90.0, (float) 0.0);
                    case BLUE:
                        return new Location(world, 0.5, 88, 79.5, (float) 180.0, (float) 0.0);
                    case YELLOW:
                        return new Location(world, -1.5, 88, -80.5, (float) 0.0, (float) 0.0);
                    case GREEN:
                        return new Location(world, -80.5, 88, 0.5, (float) -90.0, (float) 0.0);
                }
                break;
        }
        return null;
    }

    public Location getTeamBed(Teams team){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                switch(team){
                    case RED:
                        return new Location(world, 39.5, 71, -87.5);
                    case WHITE:
                        return new Location(world, -87.5, 71, 39.5);
                    case BLUE:
                        return new Location(world, 88.5, 71, -38.5);
                    case YELLOW:
                        return new Location(world, 39.5, 71, 88.5);
                    case GREEN:
                        return new Location(world, 88.5, 71, 39.5);
                    case PINK:
                        return new Location(world, -87.5, 71, -38.5);
                    case GRAY:
                        return new Location(world, -38.5, 71, -87.5);
                    case AQUA:
                        return new Location(world, -38.5, 71, 88.5);
                }
                break;
            case HOLLOW:
                switch(team){
                    case RED:
                        return new Location(world, -28.5, 67, -65.5);
                    case WHITE:
                        return new Location(world, -27.5, 67, 66.5);
                    case BLUE:
                        return new Location(world, 28.5, 67, -65.5);
                    case YELLOW:
                        return new Location(world, 66.5, 67, 28.5);
                    case GREEN:
                        return new Location(world, 66.5, 67, -28.5);
                    case PINK:
                        return new Location(world, -65.5, 67, 29.5);
                    case GRAY:
                        return new Location(world, -65.5, 67, -27.5);
                    case AQUA:
                        return new Location(world, 29.5, 67, 66.5);
                }
                break;
            case BOLETUM:
                switch(team){
                    case RED:
                        return new Location(world, 66.5, 89, -1.5);
                    case BLUE:
                        return new Location(world, 0.5, 89, 66.5);
                    case YELLOW:
                        return new Location(world, -1.5, 89, -67.5);
                    case GREEN:
                        return new Location(world, -67.5, 89, 0.5);
                }
                break;
        }
        return null;
    }

    public Location getTeamGenerators(Teams team){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                switch(team){
                    case RED:
                        return new Location(world, 54.5, 71, -87.5);
                    case WHITE:
                        return new Location(world, -87.5, 71, 54.5);
                    case BLUE:
                        return new Location(world, 88.5, 71, -53.5);
                    case YELLOW:
                        return new Location(world, 54.5, 71, 88.5);
                    case GREEN:
                        return new Location(world, 88.5, 71, 54.5);
                    case PINK:
                        return new Location(world, -87.5, 71, -53.5);
                    case GRAY:
                        return new Location(world, -53.5, 71, -87.5);
                    case AQUA:
                        return new Location(world, -53.5, 71, 88.5);
                }
                break;
            case HOLLOW:
                switch(team){
                    case RED:
                        return new Location(world, -28.5, 67, -83.5);
                    case WHITE:
                        return new Location(world, -27.5, 67, 84.5);
                    case BLUE:
                        return new Location(world, 28.5, 67, -83.5);
                    case YELLOW:
                        return new Location(world, 84.5, 67, 28.5);
                    case GREEN:
                        return new Location(world, 84.5, 67, -28.5);
                    case PINK:
                        return new Location(world, -83.5, 67, 29.5);
                    case GRAY:
                        return new Location(world, -83.5, 67, -27.5);
                    case AQUA:
                        return new Location(world, 29.5, 67, 84.5);
                }
                break;
            case BOLETUM:
                switch(team){
                    case RED:
                        return new Location(world, 82.5, 88, -1.5);
                    case BLUE:
                        return new Location(world, 0.5, 88, 82.5);
                    case YELLOW:
                        return new Location(world, -1.5, 88, -83.5);
                    case GREEN:
                        return new Location(world, -83.5, 88, 0.5);
                }
                break;
        }
        return null;
    }

    public Location getDiamondGenerators(short number){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                switch(number){
                    case 0:
                        return new Location(world, 0.5, 73, 87.5);
                    case 1:
                        return new Location(world, 87.5, 73, 0.5);
                    case 2:
                        return new Location(world, 0.5, 73, -86.5);
                    case 3:
                        return new Location(world, -86.5, 73, 0.5);
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Non esiste un generatore di diamanti in ordine " + number + "!");
                        return null;
                }
            case HOLLOW:
                switch(number){
                    case 0:
                        return new Location(world, 0.5, 67, 46.5);
                    case 1:
                        return new Location(world, 46.5, 67, 0.5);
                    case 2:
                        return new Location(world, 0.5, 67, -45.5);
                    case 3:
                        return new Location(world, -45.5, 67, 0.5);
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Non esiste un generatore di diamanti in ordine " + number + "!");
                        return null;
                }
            case BOLETUM:
                switch(number){
                    case 0:
                        return new Location(world, -42.5, 90, 43.5);
                    case 1:
                        return new Location(world, 43.5, 90, 43.5);
                    case 2:
                        return new Location(world, 43.5, 90, -42.5);
                    case 3:
                        return new Location(world, -42.5, 90, -42.5);
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Non esiste un generatore di diamanti in ordine " + number + "!");
                        return null;
                }
        }
        return null;
    }

    public Location getEmeraldGenerators(short number){
        World world = MapManager.getWorld();
        switch(this){
            case ASHFIRE:
                switch(number){
                    case 0:
                        return new Location(world, -18.5, 81, 19.5);
                    case 1:
                        return new Location(world, 19.5, 81, 19.5);
                    case 2:
                        return new Location(world, 19.5, 81, -18.5);
                    case 3:
                        return new Location(world, -18.5, 81, -18.5);
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Non esiste un generatore di smeraldi in ordine " + number + "!");
                        return null;
                }
            case HOLLOW:
                switch(number){
                    case 0:
                        return new Location(world, -20.5, 67, 21.5);
                    case 1:
                        return new Location(world, 21.5, 67, 21.5);
                    case 2:
                        return new Location(world, 21.5, 67, -20.5);
                    case 3:
                        return new Location(world, -20.5, 67, -20.5);
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Non esiste un generatore di smeraldi in ordine " + number + "!");
                        return null;
                }
            case BOLETUM:
                switch(number){
                    case 0:
                        return new Location(world, -10.5, 94, -11.5);
                    case 1:
                        return new Location(world, 9.5, 67, 12.5);
                    default:
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Non esiste un generatore di smeraldi in ordine " + number + "!");
                        return null;
                }
        }
        return null;
    }
}
