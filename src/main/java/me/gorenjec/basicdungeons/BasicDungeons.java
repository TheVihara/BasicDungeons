package me.gorenjec.basicdungeons;

import me.gorenjec.basicdungeons.cache.InMemoryCache;
import me.gorenjec.basicdungeons.listener.PlayerJoinListener;
import me.gorenjec.basicdungeons.listener.PlayerQuitListener;
import me.gorenjec.basicdungeons.storage.SQLStorage;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasicDungeons extends JavaPlugin {
    private SQLStorage storage;
    private InMemoryCache cache;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.storage = new SQLStorage(this);
        this.cache = new InMemoryCache(this, this.storage);
        this.registerAll();
    }

    @Override
    public void onDisable() {
        cache.flush();
    }

    private void registerAll() {
        PluginManager pM = getServer().getPluginManager();
        pM.registerEvents(new PlayerJoinListener(cache), this);
        pM.registerEvents(new PlayerQuitListener(cache), this);
    }
}
