package org.godfather.bedwars.manager.match.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.godfather.bedwars.manager.match.Match;

public class LeaveBed implements Listener {

    private Match match;
    public LeaveBed(Match match){
        this.match = match;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        ItemStack item = p.getInventory().getItemInHand();

        if(item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || !item.getItemMeta().getDisplayName().equals(getItem().getItemMeta().getDisplayName()))
            return;

        match.getMatchManager().getGameManager().setToLobby(p);
    }

    public static ItemStack getItem(){
        ItemStack item = new ItemStack(Material.BED);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Torna alla lobby");
        item.setItemMeta(meta);
        return item;
    }
}
