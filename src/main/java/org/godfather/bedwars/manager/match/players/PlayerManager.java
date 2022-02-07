package org.godfather.bedwars.manager.match.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.godfather.bedwars.manager.match.Match;
import org.godfather.bedwars.manager.match.items.LeaveBed;
import org.godfather.bedwars.manager.match.items.spectator.PlayerSearcher;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    private Match match;
    private Set<Player> ingamePlayers;
    private Set<Player> spectators;
    public PlayerManager(Match match){
        this.match = match;
        this.ingamePlayers = new HashSet<Player>();
        this.spectators = new HashSet<Player>();
    }

    public Set<Player> getIngamePlayers(){
        return this.ingamePlayers;
    }
    public Set<Player> getSpectators(){
        return this.spectators;
    }

    public void setPlayerInGame(Player p){
        getSpectators().remove(p);
        getIngamePlayers().add(p);
        p.teleport(match.getMapManager().getMap().getWaitingSpawn());
        p.getInventory().setItem(8, LeaveBed.getItem());
        for(Player players : Bukkit.getOnlinePlayers()){
            if(getIngamePlayers().contains(players)) continue;
            players.hidePlayer(p);
            p.hidePlayer(players);
        }
        getIngamePlayers().forEach(player -> player.sendMessage(ChatColor.GRAY + p.getName()
                + ChatColor.YELLOW + " è entrato (" + ChatColor.AQUA + match.getPlayerManager().getIngamePlayers().size()
                + ChatColor.YELLOW + "/" + ChatColor.AQUA + match.getMaxPlayers() + ChatColor.YELLOW + ")!"));
    }

    public void setPlayerSpectator(Player p){
        getIngamePlayers().remove(p);
        getSpectators().add(p);
        p.teleport(match.getMapManager().getMap().getSpectatorSpawn());
        p.getInventory().setItem(0, PlayerSearcher.getItem());
        p.getInventory().setItem(8, LeaveBed.getItem());
        for(Player players : Bukkit.getOnlinePlayers()){
            if(!getIngamePlayers().contains(players)){
                p.hidePlayer(players);
            }
            players.hidePlayer(p);
        }
        p.sendMessage(ChatColor.GRAY + "Sei entrato come spettatore!");
    }
}
