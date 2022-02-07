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

public class Countdown extends BukkitRunnable {

    @Override
    public void run(){
        if(match.getPhase() != MatchPhases.COUNTDOWN){
            this.cancel();
            return;
        }
        if(time == 0){
            this.cancel();
            match.setPhase(MatchPhases.FASTCOUNTDOWN);
            return;
        }
        for(Player p : match.getPlayerManager().getIngamePlayers()){
            Scoreboard board = p.getScoreboard();
            Objective obj = board.getObjective("dummy");
            MatchScoreboard.replaceScore(obj, 5, ChatColor.WHITE + "Inizio tra " + ChatColor.GREEN + (time + 20) + "s");
        }
        if(time%20 == 0){
            match.getPlayerManager().getIngamePlayers().forEach(p -> p.sendMessage(ChatColor.YELLOW + "Il gioco inizierà in " + (time + 20) + " secondi!"));
        }
        time--;
    }

    private Match match;
    private int time;
    public Countdown(Match match){
        this.match = match;
        this.time = 60;
    }

    public void start(){
        Countdown task = new Countdown(match);
        task.runTaskTimer(Main.plugin, 0L, 20L);
    }
}
