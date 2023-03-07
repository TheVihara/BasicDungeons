package me.gorenjec.basicdungeons.models;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class Dungeon {
    private List<Player> dungeonPlayers;
    private String name;
    private List<Region> spawnRegions;
    private List<CustomMob> customMobs;
    private List<Location> mobSpawnRegions;
    private CustomMob boss;
    private Location bossSpawnPoint;

    public Dungeon(String name, List<Region> spawnRegions, List<CustomMob> customMobs, Location bossSpawnPoint, CustomMob boss) {
        this.name = name;
        this.spawnRegions = spawnRegions;
        this.customMobs = customMobs;
        this.bossSpawnPoint = bossSpawnPoint;
        this.boss = boss;
    }
}
