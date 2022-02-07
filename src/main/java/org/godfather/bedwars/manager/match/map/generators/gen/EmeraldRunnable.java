package org.godfather.bedwars.manager.match.map.generators.gen;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.godfather.bedwars.Main;
import org.godfather.bedwars.manager.match.map.Maps;
import org.godfather.bedwars.manager.match.map.generators.GeneratorManager;
import org.godfather.bedwars.manager.match.map.generators.GeneratorType;

public class EmeraldRunnable extends BukkitRunnable {

    @Override
    public void run(){
        Maps map = generatorManager.getMatch().getMapManager().getMap();
        if(time==0){
            if(map == Maps.BOLETUM){
                for(int i=0; i<1; i++){
                    generatorManager.getMatch().getMapManager().getGameMap().getWorld().dropItem(map.getEmeraldGenerators((short) i), new ItemStack(Material.EMERALD));
                }
            }
            else{
                for(int i=0; i<3; i++){
                    generatorManager.getMatch().getMapManager().getGameMap().getWorld().dropItem(map.getEmeraldGenerators((short) i), new ItemStack(Material.EMERALD));
                }
            }
            time=maxtime;
        }
        else time--;
        if(map == Maps.BOLETUM){
            for(int i=0; i<1; i++){
                hologram(time, map.getEmeraldGenerators((short) i));
            }
        }
        else{
            for(int i=0; i<3; i++){
                hologram(time, map.getEmeraldGenerators((short) i));
            }
        }
    }

    private GeneratorManager generatorManager;
    private int maxtime;
    private int time;
    public EmeraldRunnable(GeneratorManager generatorManager){
        this.generatorManager = generatorManager;
    }

    public void start(int time){
        this.maxtime = time;
        this.time = time;
        EmeraldRunnable task = new EmeraldRunnable(generatorManager);
        task.runTaskTimer(Main.plugin, 0L, 20L);
    }

    public void stop(){
        this.stop();
    }

    private void hologram(int tempo, Location loc){
        EntityArmorStand stand1 = new EntityArmorStand(((CraftWorld) Bukkit.getWorld("world")).getHandle());
        stand1.setLocation(loc.getX(), loc.getY()+2, loc.getZ(), 0, 0);
        stand1.setCustomNameVisible(true);
        stand1.setGravity(false);
        stand1.setSmall(true);
        stand1.setInvisible(true);
        stand1.setCustomName(ChatColor.YELLOW + "L'item spawnerà in " + ChatColor.RED + tempo + "s");
        stand1.setEquipment(4, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_BLOCK)));
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand1);
        PacketPlayOutEntityEquipment pe = new PacketPlayOutEntityEquipment(stand1.getId(), 4, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_BLOCK)));

        EntityArmorStand stand2 = new EntityArmorStand(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        stand2.setLocation(loc.getX(), loc.getY()+2.3, loc.getZ(), 0, 0);
        stand2.setCustomNameVisible(true);
        stand2.setGravity(false);
        stand2.setSmall(true);
        stand2.setInvisible(true);
        stand2.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Smeraldo");
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving(stand2);

        EntityArmorStand stand3 = new EntityArmorStand(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        stand3.setLocation(loc.getX(), loc.getY()+2.6, loc.getZ(), 0, 0);
        stand3.setCustomNameVisible(true);
        stand3.setGravity(false);
        stand3.setSmall(true);
        stand3.setInvisible(true);
        stand3.setCustomName(ChatColor.YELLOW + "Livello " + ChatColor.RED + generatorManager.getGenLevel(GeneratorType.EMERALD));
        PacketPlayOutSpawnEntityLiving packet3 = new PacketPlayOutSpawnEntityLiving(stand3);

        for(Player p : Bukkit.getOnlinePlayers()){
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet3);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pe);
            new BukkitRunnable(){
                @Override
                public void run(){
                    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(stand1.getId());
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                    PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(stand2.getId());
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet2);
                    PacketPlayOutEntityDestroy packet3 = new PacketPlayOutEntityDestroy(stand3.getId());
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet3);
                }
            }.runTaskLater(Main.plugin, 20L);
        }
    }
}
