package org.godfather.bedwars.manager.match.players.team;

import org.bukkit.ChatColor;

public enum Teams {
    RED,BLUE,GREEN,YELLOW,AQUA,WHITE,PINK,GRAY;

    public ChatColor getColor(){
        switch(this){
            case RED:
                return ChatColor.RED;
            case BLUE:
                return ChatColor.BLUE;
            case GREEN:
                return ChatColor.GREEN;
            case YELLOW:
                return ChatColor.YELLOW;
            case AQUA:
                return ChatColor.AQUA;
            case WHITE:
                return ChatColor.WHITE;
            case PINK:
                return ChatColor.LIGHT_PURPLE;
            case GRAY:
                return ChatColor.DARK_GRAY;
            default:
                return null;
        }
    }
}
