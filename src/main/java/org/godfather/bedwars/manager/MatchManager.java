package org.godfather.bedwars.manager;

import org.bukkit.entity.Player;
import org.godfather.bedwars.manager.match.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {

    private GameManager gameManager;
    private List<Match> matches;
    public MatchManager(GameManager gameManager){
        this.gameManager = gameManager;
        this.matches = new ArrayList<Match>();
    }

    public GameManager getGameManager(){
        return this.gameManager;
    }

    public List<Match> getMatches(){
        return this.matches;
    }

    public void addMatch(Match match){
        if(!this.matches.contains(match))
            this.matches.add(match);
    }

    public void removeMatch(Match match){
        if(this.matches.contains(match))
            this.matches.remove(match);
        //todo clear del match singolo (sets, ecc.)
    }
}
