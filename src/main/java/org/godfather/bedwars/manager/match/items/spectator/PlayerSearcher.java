package org.godfather.bedwars.manager.match.items.spectator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.godfather.bedwars.manager.match.Match;

public class PlayerSearcher implements Listener {

    private Match match;
    public PlayerSearcher(Match match){
        this.match = match;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        ItemStack item = p.getInventory().getItemInHand();

        if(item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || !item.getItemMeta().getDisplayName().equals(getItem().getItemMeta().getDisplayName()))
            return;

        p.openInventory(getInventory());
    }

    public static ItemStack getItem(){
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Cerca giocatore");
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        event.setCancelled(true);
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();

        if(event.getInventory() == null || event.getInventory().getTitle() == null || !event.getInventory().getTitle().equals(getInventory().getTitle()))
            return;
        ItemStack item = event.getCurrentItem();

        if(item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null)
            return;

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        p.teleport(Bukkit.getPlayerExact(meta.getOwner()));
        p.closeInventory();
    }

    public Inventory getInventory(){
        Inventory gui = Bukkit.createInventory(null, 27, "Cerca giocatori");
        int counter=0;
        for(Player p : match.getPlayerManager().getIngamePlayers()){
            ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwner(p.getName());
            meta.setDisplayName(match.getTeamManager().getPlayerTeam(p).getColor() + p.getName());
            head.setItemMeta(meta);
            gui.setItem(counter, head);
            counter++;
        }
        return gui;
    }
}
