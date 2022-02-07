package org.godfather.bedwars.manager.match.map.generators.gen;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.godfather.bedwars.Main;
import org.godfather.bedwars.manager.match.map.Maps;
import org.godfather.bedwars.manager.match.map.generators.GeneratorManager;
import org.godfather.bedwars.manager.match.players.team.Teams;

public class SquadRunnable extends BukkitRunnable {

    @Override
    public void run(){
        Maps map = generatorManager.getMatch().getMapManager().getMap();
        if(time==0 || time%2==0){
            generatorManager.getMatch().getMapManager().getGameMap().getWorld().dropItem(map.getTeamGenerators(team), new ItemStack(Material.IRON_INGOT));
            for(int i=0; i<3; i++){
                generatorManager.getMatch().getMapManager().getGameMap().getWorld().dropItem(map.getDiamondGenerators((short) i), new ItemStack(Material.DIAMOND));
            }
            time=maxtime;
        }
        else time--;
    }

    private GeneratorManager generatorManager;
    private int maxtime;
    private int time;
    private int level;
    private Teams team;
    public SquadRunnable(GeneratorManager generatorManager, Teams team){
        this.generatorManager = generatorManager;
        this.team = team;
    }

    public void start(int time, int level){
        this.maxtime = time;
        this.time = time;
        this.level = level;
        SquadRunnable task = new SquadRunnable(generatorManager, team);
        task.runTaskTimer(Main.plugin, 0L, 20L);
    }

    public void stop(){
        this.stop();
    }
}
