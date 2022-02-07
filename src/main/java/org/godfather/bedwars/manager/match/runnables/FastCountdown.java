package org.godfather.bedwars.manager.match.runnables;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.godfather.bedwars.Main;
import org.godfather.bedwars.manager.match.Match;
import org.godfather.bedwars.manager.match.MatchPhases;
import org.godfather.bedwars.manager.match.MatchScoreboard;
import org.godfather.bedwars.utils.Helper;

public class FastCountdown extends BukkitRunnable {

    @Override
    public void run(){
        if(match.getPhase() != MatchPhases.FASTCOUNTDOWN){
            this.cancel();
            return;
        }
        if(time == 0){
            this.cancel();
            match.setPhase(MatchPhases.INGAME);
            return;
        }
        for(Player p : match.getPlayerManager().getIngamePlayers()){
            Scoreboard board = p.getScoreboard();
            Objective obj = board.getObjective("dummy");
            MatchScoreboard.replaceScore(obj, 5, ChatColor.WHITE + "Inizio tra " + ChatColor.GREEN + time + "s");
        }
        if(time<= 10){
            match.getPlayerManager().getIngamePlayers().forEach(p -> Helper.sendTitle(p, "&c" + time, "", 1, 20, 1));
            match.getPlayerManager().getIngamePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1, 1));
            if(time <= 5) match.getPlayerManager().getIngamePlayers().forEach(p -> p.sendMessage(ChatColor.YELLOW + "Il gioco inizierà in " + ChatColor.RED + time + ChatColor.YELLOW + " secondi!"));
            else if(time%5 == 0) match.getPlayerManager().getIngamePlayers().forEach(p -> p.sendMessage(ChatColor.YELLOW + "Il gioco inizierà in " + ChatColor.GOLD + time + ChatColor.YELLOW + " secondi!"));
        }
        else if(time%10 == 0){
            match.getPlayerManager().getIngamePlayers().forEach(p -> p.sendMessage(ChatColor.YELLOW + "Il gioco inizierà in " + time + " secondi!"));
        }
        time--;
    }

    private Match match;
    private int time;
    public FastCountdown(Match match){
        this.match = match;
        time=20;
    }

    public void start(){
        FastCountdown task = new FastCountdown(match);
        task.runTaskTimer(Main.plugin, 0L, 20L);
    }
}
