package org.godfather.bedwars.manager.match.map.generators;

import org.bukkit.Material;
import org.godfather.bedwars.manager.match.Match;
import org.godfather.bedwars.manager.match.map.generators.gen.DiamondRunnable;
import org.godfather.bedwars.manager.match.map.generators.gen.EmeraldRunnable;
import org.godfather.bedwars.manager.match.players.team.Teams;

public class GeneratorManager {

    private Match match;
    private DiamondRunnable diamondRunnable;
    private EmeraldRunnable emeraldRunnable;
    public GeneratorManager(Match match){
        this.match = match;
        this.diamond_level = 1;
        this.emerald_level = 1;
        this.diamondRunnable = new DiamondRunnable(this);
        this.emeraldRunnable = new EmeraldRunnable(this);
    }

    private int diamond_level;
    private int emerald_level;

    public Match getMatch(){
        return this.match;
    }

    public int getGenLevel(GeneratorType type){
        switch(type){
            case DIAMOND:
                return diamond_level;
            case EMERALD:
                return emerald_level;
        }
        return 0;
    }

    public void setGenLevel(GeneratorType type, int level){
        switch(type){
            case DIAMOND:
                this.diamond_level = level;

            case EMERALD:
                this.emerald_level = level;
        }
    }

    public int getGenTeamLevel(Teams team){
        return match.getTeamManager().getGenLevel(team);
    }

    public void setGenTeamLevel(Teams team, int level){
        match.getTeamManager().setGenLevel(team, level);
    }

    public void setup(){

    }
}
