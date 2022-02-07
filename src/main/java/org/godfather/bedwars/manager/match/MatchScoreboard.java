package org.godfather.bedwars.manager.match;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.godfather.bedwars.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchScoreboard extends BukkitRunnable {

    @Override
    public void run(){
        switch(match.getPhase()){
            case WAITING:
                match.getPlayerManager().getIngamePlayers().forEach(p -> setScoreboardWaiting(p));
                break;
            case COUNTDOWN:
            case FASTCOUNTDOWN:
                match.getPlayerManager().getIngamePlayers().forEach(p -> setScoreboardCountdown(p));
                break;
            default:
                return;
        }
    }

    private Match match;
    public MatchScoreboard(Match match){
        this.match = match;
    }

    public void start(){
        MatchScoreboard task = new MatchScoreboard(match);
        task.runTaskTimer(Main.plugin, 0L, 10L);
    }

    public void setScoreboardWaiting(Player p){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("dummy", "dummy");
        obj.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "BEDWARS");
        replaceScore(obj, 10, ChatColor.GRAY + format.format(new Date()));
        replaceScore(obj, 9, "    ");
        replaceScore(obj, 8, ChatColor.WHITE + "Mappa: " + ChatColor.GREEN + match.getMapManager().getMap().getName());
        replaceScore(obj, 7, ChatColor.WHITE + "Giocatori: " + ChatColor.GREEN + match.getPlayerManager().getIngamePlayers().size() + "/" + match.getMaxPlayers());
        replaceScore(obj, 6, "   ");
        replaceScore(obj, 5, ChatColor.RED + "In attesa...");
        replaceScore(obj, 4, "  ");
        replaceScore(obj, 3, ChatColor.WHITE + "Modalità: " + WordUtils.capitalizeFully(match.getMatchType().toString().toLowerCase()));
        replaceScore(obj, 2, " ");
        replaceScore(obj, 1, ChatColor.YELLOW + "play.dynastywar.it");;
        if(obj.getDisplaySlot() != DisplaySlot.SIDEBAR) obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team owner = board.registerNewTeam("aOwner");
        owner.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER " + ChatColor.DARK_RED + "");
        Team giocatore = board.registerNewTeam("bGiocatore");
        giocatore.setPrefix(ChatColor.GRAY + "");

        for(Player player : match.getPlayerManager().getIngamePlayers()){
            if(player.hasPermission("bedwars.owner"))
                owner.addEntry(player.getName());
            else giocatore.addEntry(player.getName());
        }
        p.setScoreboard(board);
    }

    public void setScoreboardCountdown(Player p){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("dummy", "dummy");
        obj.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "BEDWARS");
        replaceScore(obj, 10, ChatColor.GRAY + format.format(new Date()));
        replaceScore(obj, 9, "    ");
        replaceScore(obj, 8, ChatColor.WHITE + "Mappa: " + ChatColor.GREEN + match.getMapManager().getMap().getName());
        replaceScore(obj, 7, ChatColor.WHITE + "Giocatori: " + ChatColor.GREEN + match.getPlayerManager().getIngamePlayers().size() + "/" + match.getMaxPlayers());
        replaceScore(obj, 6, "   ");
        replaceScore(obj, 5, ChatColor.WHITE + "Inizio in ");
        replaceScore(obj, 4, "  ");
        replaceScore(obj, 3, ChatColor.WHITE + "Modalità: " + WordUtils.capitalizeFully(match.getMatchType().toString().toLowerCase()));
        replaceScore(obj, 2, " ");
        replaceScore(obj, 1, ChatColor.YELLOW + "play.dynastywar.it");;
        if(obj.getDisplaySlot() != DisplaySlot.SIDEBAR) obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team owner = board.registerNewTeam("aOwner");
        owner.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER " + ChatColor.DARK_RED + "");
        Team giocatore = board.registerNewTeam("bGiocatore");
        giocatore.setPrefix(ChatColor.GRAY + "");

        for(Player player : match.getPlayerManager().getIngamePlayers()){
            if(player.hasPermission("bedwars.owner"))
                owner.addEntry(player.getName());
            else giocatore.addEntry(player.getName());
        }
        p.setScoreboard(board);
    }

    public static String getEntryFromScore(Objective o, int score) {
        if(o == null) return null;
        if(!hasScoreTaken(o, score)) return null;
        for (String s : o.getScoreboard().getEntries()) {
            if(o.getScore(s).getScore() == score) return o.getScore(s).getEntry();
        }
        return null;
    }

    public static boolean hasScoreTaken(Objective o, int score) {
        for (String s : o.getScoreboard().getEntries()) {
            if(o.getScore(s).getScore() == score) return true;
        }
        return false;
    }

    public static void replaceScore(Objective o, int score, String name) {
        if(hasScoreTaken(o, score)) {
            if(getEntryFromScore(o, score).equalsIgnoreCase(name)) return;
            if(!(getEntryFromScore(o, score).equalsIgnoreCase(name))) o.getScoreboard().resetScores(getEntryFromScore(o, score));
        }
        o.getScore(name).setScore(score);
    }
}
