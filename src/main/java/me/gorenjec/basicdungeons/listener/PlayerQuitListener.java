package me.gorenjec.basicdungeons.listener;

import me.gorenjec.basicdungeons.cache.InMemoryCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final InMemoryCache cache;

    public PlayerQuitListener(InMemoryCache cache) {
        this.cache = cache;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        cache.flushPlayer(player);
    }
}
