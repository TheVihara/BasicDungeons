package me.gorenjec.basicdungeons.models;

import org.bukkit.entity.Player;

public class BasicPlayer {
    private final Player player;
    private int kills;
    private int deaths;
    private int level;
    private double xp;
    private int timePlayed;

    public BasicPlayer(Player player, int kills, int deaths, int level, double xp, int timePlayed) {
        this.player = player;
        this.kills = kills;
        this.deaths = deaths;
        this.level = level;
        this.xp = xp;
        this.timePlayed = timePlayed;
    }

    public Player getPlayer() {
        return player;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public void addKills(int kills) {
        this.kills = this.kills + kills;
    }

    public void addDeaths(int deaths) {
        this.deaths = this.deaths + deaths;
    }

    public void addLevel(int level) {
        this.level = this.level + level;
    }

    public void addXp(int xp) {
        this.xp = this.xp + xp;
    }
}
