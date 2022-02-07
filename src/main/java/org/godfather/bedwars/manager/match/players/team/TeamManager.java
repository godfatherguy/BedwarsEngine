package org.godfather.bedwars.manager.match.players.team;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.godfather.bedwars.manager.match.Match;

import java.util.HashSet;
import java.util.Set;

public class TeamManager {

    private Match match;
    public TeamManager(Match match){
        this.match = match;
    }

    private Set<Player> redTeam = new HashSet<>();
    private boolean redBed = true;
    private int redGen=1;
    private Set<Player> blueTeam = new HashSet<>();
    private boolean blueBed = true;
    private int blueGen=1;
    private Set<Player> greenTeam = new HashSet<>();
    private boolean greenBed = true;
    private int greenGen=1;
    private Set<Player> yellowTeam = new HashSet<>();
    private boolean yellowBed = true;
    private int yellowGen=1;
    private Set<Player> aquaTeam = new HashSet<>();
    private boolean aquaBed = true;
    private int aquaGen=1;
    private Set<Player> whiteTeam = new HashSet<>();
    private boolean whiteBed = true;
    private int whiteGen=1;
    private Set<Player> pinkTeam = new HashSet<>();
    private boolean pinkBed = true;
    private int pinkGen=1;
    private Set<Player> grayTeam = new HashSet<>();
    private boolean grayBed = true;
    private int grayGen=1;

    public Set<Player> getTeam(Teams team){
        switch(team){
            case RED:
                return this.redTeam;
            case BLUE:
                return this.blueTeam;
            case GREEN:
                return this.greenTeam;
            case YELLOW:
                return this.yellowTeam;
            case AQUA:
                return this.aquaTeam;
            case WHITE:
                return this.whiteTeam;
            case PINK:
                return this.pinkTeam;
            case GRAY:
                return this.grayTeam;
            default:
                return null;
        }
    } //ottieni un team dall'enum
    public void setPlayerInTeam(Player p, Teams team){
        switch(team){
            case RED:
                getTeam(Teams.RED).add(p);
                break;
            case BLUE:
                getTeam(Teams.BLUE).add(p);
                break;
            case GREEN:
                getTeam(Teams.GREEN).add(p);
                break;
            case YELLOW:
                getTeam(Teams.YELLOW).add(p);
                break;
            case AQUA:
                getTeam(Teams.AQUA).add(p);
                break;
            case WHITE:
                getTeam(Teams.WHITE).add(p);
                break;
            case PINK:
                getTeam(Teams.PINK).add(p);
                break;
            case GRAY:
                getTeam(Teams.GRAY).add(p);
                break;
        }
    } //imposta un giocatore in un team (con enum)
    public Teams getPlayerTeam(Player p){
        if(redTeam.contains(p)) return Teams.RED;
        else if(blueTeam.contains(p)) return Teams.BLUE;
        else if(greenTeam.contains(p)) return Teams.GREEN;
        else if(yellowTeam.contains(p)) return Teams.YELLOW;
        else if(aquaTeam.contains(p)) return Teams.AQUA;
        else if(whiteTeam.contains(p)) return Teams.WHITE;
        else if(pinkTeam.contains(p)) return Teams.PINK;
        else if(grayTeam.contains(p)) return Teams.GRAY;
        else return null;
     } //ottieni l'enum del team di un player
    public String getState(Teams team) {
        if(isBedActive(team)) return ChatColor.GREEN + "✔";
        else if(isTeamAlive(team)) return ChatColor.GREEN + String.valueOf(getTeamAlivePlayers(team));
        else return ChatColor.RED + "✘";
    } //ottieni lo stato in stringa del team (ACTIVE,ALIVE,DEAD)
    public void setBedState(Teams team, boolean state){
        switch(team){
            case RED:
                this.redBed = state;
            case BLUE:
                this.blueBed = state;
            case GREEN:
                this.greenBed = state;
            case YELLOW:
                this.yellowBed = state;
            case AQUA:
                this.aquaBed = state;
            case WHITE:
                this.whiteBed = state;
            case PINK:
                this.pinkBed = state;
            case GRAY:
                this.grayBed = state;
        }
    } //imposta lo stato del letto di un team
    public int getGenLevel(Teams team){
        switch(team){
            case RED:
                return redGen;
            case BLUE:
                return blueGen;
            case GREEN:
                return greenGen;
            case YELLOW:
                return yellowGen;
            case AQUA:
                return aquaGen;
            case WHITE:
                return whiteGen;
            case PINK:
                return pinkGen;
            case GRAY:
                return grayGen;
        }
        return 0;
    }
    public void setGenLevel(Teams team, int level){
        switch(team){
            case RED:
                this.redGen = level;
            case BLUE:
                this.blueGen = level;
            case GREEN:
                this.greenGen = level;
            case YELLOW:
                this.yellowGen = level;
            case AQUA:
                this.aquaGen = level;
            case WHITE:
                this.whiteGen = level;
            case PINK:
                this.pinkGen = level;
            case GRAY:
                this.grayGen = level;
        }
    }
    public boolean isBedActive(Teams team){
        switch(team){
            case RED:
                return redBed;
            case BLUE:
                return blueBed;
            case GREEN:
                return greenBed;
            case YELLOW:
                return yellowBed;
            case AQUA:
                return aquaBed;
            case WHITE:
                return whiteBed;
            case PINK:
                return pinkBed;
            case GRAY:
                return grayBed;
            default:
                return false;
        }
    } //controlla che il letto di un team sia presente
    public boolean isTeamAlive(Teams team) {
        return getTeam(team).stream().anyMatch(p -> p.getGameMode() != GameMode.SPECTATOR);
    } //controlla che il team abbia giocatori in vita
    public int getTeamAlivePlayers(Teams team){
        if(!isTeamAlive(team)) return 0;
        return (int) getTeam(team).stream().filter(p -> p.getGameMode() != GameMode.SPECTATOR).count();
    } //ottieni il numero di giocatori in vita nel team
}
