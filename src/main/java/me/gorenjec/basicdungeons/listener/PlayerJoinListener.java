package me.gorenjec.basicdungeons.listener;

import me.gorenjec.basicdungeons.cache.InMemoryCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final InMemoryCache cache;

    public PlayerJoinListener(InMemoryCache cache) {
        this.cache = cache;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        cache.cachePlayer(player);
    }
}
