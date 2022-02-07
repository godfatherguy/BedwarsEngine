package org.godfather.bedwars.manager.match.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.godfather.bedwars.manager.GameManager;

import java.util.Set;

public class ChatListener implements Listener {

    private GameManager gameManager;
    public ChatListener(GameManager gameManager){
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if(!gameManager.isInMatch(p)) return;
        Set<Player> players = gameManager.getPlayerMatch(p).getPlayerManager().getIngamePlayers();
        event.setCancelled(true);
        switch(gameManager.getPlayerMatch(p).getPhase()){
            case WAITING:
            case COUNTDOWN:
            case FASTCOUNTDOWN:
                if(p.hasPermission("bedwars.owner"))
                    players.forEach(player -> player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER " + ChatColor.DARK_RED + p.getName() + ChatColor.WHITE + ": " + ChatColor.GRAY + event.getMessage()));
                else players.forEach(player -> player.sendMessage(ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + event.getMessage()));
                break;
            case INGAME:
            case END:

                break;
        }
    }
}
