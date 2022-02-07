package org.godfather.bedwars.manager.match;

import org.bukkit.Bukkit;
import org.godfather.bedwars.Main;
import org.godfather.bedwars.manager.MatchManager;
import org.godfather.bedwars.manager.match.items.LeaveBed;
import org.godfather.bedwars.manager.match.items.spectator.PlayerSearcher;
import org.godfather.bedwars.manager.match.map.MapManager;
import org.godfather.bedwars.manager.match.map.generators.GeneratorManager;
import org.godfather.bedwars.manager.match.players.PlayerManager;
import org.godfather.bedwars.manager.match.players.team.TeamManager;
import org.godfather.bedwars.manager.match.runnables.Countdown;
import org.godfather.bedwars.manager.match.runnables.FastCountdown;

public class Match {

    private MatchManager matchManager;
    private MapManager mapManager;
    private PlayerManager playerManager;
    private TeamManager teamManager;
    private GeneratorManager generatorManager;
    private MatchPhases phase;
    private MatchType type;
    private int maxPlayers;
    private int requiredPlayers;
    private MatchScoreboard scoreboard;
    private Countdown countdown;
    private FastCountdown fastCountdown;
    public Match(MatchManager matchManager, MatchType type){
        this.matchManager = matchManager;
        this.mapManager = new MapManager(this);
        this.playerManager = new PlayerManager(this);
        this.teamManager = new TeamManager(this);
        this.generatorManager = new GeneratorManager(this);
        this.phase = MatchPhases.LOADING;
        this.type = type;
        this.countdown = new Countdown(this);
        this.fastCountdown = new FastCountdown(this);
        switch(type){
            case SOLO:
                this.maxPlayers=8;
                this.requiredPlayers=3;
                break;
            case DOUBLE:
            case SQUAD:
                this.maxPlayers=16;
                this.requiredPlayers=8;
                break;
            case TRIPLE:
                this.maxPlayers=12;
                this.requiredPlayers=6;
                break;
        }
        this.scoreboard = new MatchScoreboard(this);
        getScoreboard().start();
        setupListeners();
    }

    public MatchManager getMatchManager(){
        return this.matchManager;
    }
    public MapManager getMapManager(){
        return this.mapManager;
    }
    public PlayerManager getPlayerManager(){
        return this.playerManager;
    }
    public TeamManager getTeamManager(){
        return this.teamManager;
    }
    public MatchScoreboard getScoreboard(){
        return this.scoreboard;
    }
    public MatchType getMatchType(){
        return this.type;
    }
    public int getMaxPlayers(){
        return this.maxPlayers;
    }
    public int getRequiredPlayers(){
        return this.requiredPlayers;
    }

    public MatchPhases getPhase(){
        return this.phase;
    }
    public void setPhase(MatchPhases phase){
        this.phase = phase;

        switch(phase){
            case LOADING:
                break;
            case WAITING:
                break;
            case COUNTDOWN:
                this.countdown.start();
                break;
            case FASTCOUNTDOWN:
                this.fastCountdown.start();
                break;
            case INGAME:
                break;
            case END:
                break;
        }
    }

    public void setupListeners(){
        Bukkit.getServer().getPluginManager().registerEvents(new LeaveBed(this), Main.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerSearcher(this), Main.plugin);
    }
}
