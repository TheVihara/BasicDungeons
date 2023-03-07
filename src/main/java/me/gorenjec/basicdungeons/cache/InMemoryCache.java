package me.gorenjec.basicdungeons.cache;

import me.gorenjec.basicdungeons.BasicDungeons;
import me.gorenjec.basicdungeons.models.BasicPlayer;
import me.gorenjec.basicdungeons.models.CustomMob;
import me.gorenjec.basicdungeons.models.Dungeon;
import me.gorenjec.basicdungeons.storage.SQLStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class InMemoryCache {
    private final BasicDungeons instance;
    private final SQLStorage storage;
    private Map<UUID, BasicPlayer> playerMap = new HashMap<>();
    private Map<String, Dungeon> dungeonMap = new HashMap<>();
    private List<CustomMob> customMobs = new ArrayList<>();

    public InMemoryCache(BasicDungeons instance, SQLStorage storage) {
        this.instance = instance;
        this.storage = storage;
    }

    public void cache() {

    }

    public void cachePlayer(UUID uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, (Runnable) -> {
            addPlayer(uuid, storage.getPlayer(uuid));
            instance.getLogger().info("Loaded player data for: " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());
        });
    }

    public void cachePlayer(Player player) {
        cachePlayer(player.getUniqueId());
    }

    public void flush() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            storage.updatePlayer(playerMap.get(player.getUniqueId()));
            playerMap.remove(player.getUniqueId());
        }
    }

    public void flushPlayer(UUID uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, (Runnable) -> {
            storage.updatePlayer(playerMap.get(uuid));
            playerMap.remove(uuid);
        });
    }

    public void flushPlayer(Player player) {
        flushPlayer(player.getUniqueId());
    }

    public BasicPlayer getPlayer(UUID uuid) {
        return playerMap.get(uuid);
    }

    public BasicPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public Dungeon getDungeon(String name) {
        return dungeonMap.get(name);
    }

    public void addPlayer(UUID uuid, BasicPlayer basicPlayer) {
        playerMap.put(uuid, basicPlayer);
    }

    public void addPlayer(Player player, BasicPlayer basicPlayer) {
        addPlayer(player.getUniqueId(), basicPlayer);
    }

    public void removePlayer(UUID uuid) {
        playerMap.remove(uuid);
    }

    public void removePlayer(Player player) {
        removePlayer(player.getUniqueId());
    }
}
