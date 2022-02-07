package org.godfather.bedwars.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.godfather.bedwars.manager.lobby.locations.SpawnLocation;
import org.godfather.bedwars.manager.match.Match;
import org.godfather.bedwars.manager.match.MatchPhases;
import org.godfather.bedwars.utils.Helper;

public class GameManager {

    private MatchManager matchManager;
    private Tablist tablist;
    public GameManager(){
        this.matchManager = new MatchManager(this);
        this.tablist = new Tablist();
        tablist.start();
    }

    public MatchManager getMatchManager(){
        return this.matchManager;
    }

    public boolean isInMatch(Player p){
        boolean check=false;
        for(Match match : matchManager.getMatches()){
            if(match.getPlayerManager().getIngamePlayers().contains(p) || match.getPlayerManager().getSpectators().contains(p)){
                check=true;
                break;
            }
        }
        return check;
    }

    public Match getPlayerMatch(Player p){
        Match matcho = null;
        for(Match match : matchManager.getMatches()){
            if(match.getPlayerManager().getIngamePlayers().contains(p) || match.getPlayerManager().getSpectators().contains(p)){
                matcho=match;
                break;
            }
        }
        return matcho;
    }

    public void putPlayerInMatch(Player p, Match match){
        switch(match.getPhase()){
            case LOADING:
                setToLobby(p);
                p.sendMessage(ChatColor.RED + "C'è stato un problema. Aspetta un secondo!");
                break;
            case WAITING:
                match.getPlayerManager().setPlayerInGame(p);
                if(match.getPlayerManager().getIngamePlayers().size() >= match.getRequiredPlayers()){
                    if(match.getPlayerManager().getIngamePlayers().size() == match.getMaxPlayers())
                        match.setPhase(MatchPhases.FASTCOUNTDOWN);
                    else match.setPhase(MatchPhases.COUNTDOWN);
                }
                break;
            case COUNTDOWN:
                match.getPlayerManager().setPlayerInGame(p);
                if(match.getPlayerManager().getIngamePlayers().size() == match.getMaxPlayers()){
                    match.setPhase(MatchPhases.FASTCOUNTDOWN);
                }
                break;
            case FASTCOUNTDOWN:
                setToLobby(p);
                p.sendMessage(ChatColor.RED + "Il gioco è pieno! Riprova!");
                break;
            case INGAME:
                match.getPlayerManager().setPlayerSpectator(p);
                break;
        }
    }

    public void removePlayerFromMatch(Player p, Match match){
        switch(match.getPhase()){
            case WAITING:
                match.getPlayerManager().getIngamePlayers().remove(p);
                match.getPlayerManager().getIngamePlayers().forEach(player -> player.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " è uscito."));
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(isInMatch(players)) continue;
                    players.showPlayer(p);
                    p.showPlayer(players);
                }
                break;
            case COUNTDOWN:
                match.getPlayerManager().getIngamePlayers().remove(p);
                match.getPlayerManager().getIngamePlayers().forEach(player -> player.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " è uscito."));
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(isInMatch(players)) continue;
                    players.showPlayer(p);
                    p.showPlayer(players);
                }
                if(match.getPlayerManager().getIngamePlayers().size() < match.getRequiredPlayers()){
                    match.getPlayerManager().getIngamePlayers().forEach(player -> Helper.sendTitle(player, "&cServono più giocatori!", "", 10, 20, 10));
                    match.getPlayerManager().getIngamePlayers().forEach(player -> player.sendMessage(ChatColor.RED + "Servono più giocatori per cominciare."));
                    match.getPlayerManager().getIngamePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.VILLAGER_NO, 3, 3));
                    match.setPhase(MatchPhases.WAITING);
                }
                break;
            case FASTCOUNTDOWN:
                match.getPlayerManager().getIngamePlayers().remove(p);
                match.getPlayerManager().getIngamePlayers().forEach(player -> player.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " è uscito."));
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(isInMatch(players)) continue;
                    players.showPlayer(p);
                    p.showPlayer(players);
                }
                if(match.getPlayerManager().getIngamePlayers().size() < match.getMaxPlayers()){
                    match.setPhase(MatchPhases.COUNTDOWN);
                }
                break;
            case INGAME:
            case END:
                match.getPlayerManager().setPlayerSpectator(p);
                break;
        }
    }

    public void setToLobby(Player p){
        p.teleport(SpawnLocation.getLocation());
        if(isInMatch(p)){
            removePlayerFromMatch(p, getPlayerMatch(p));
        }
    }
}
