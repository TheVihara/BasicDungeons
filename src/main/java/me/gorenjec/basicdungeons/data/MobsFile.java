package me.gorenjec.basicdungeons.data;

import me.gorenjec.basicdungeons.BasicDungeons;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MobsFile {
    private final BasicDungeons instance;
    private final File pluginPath;
    private final File file;
    private FileConfiguration config;

    public MobsFile(BasicDungeons instance) {
        this.instance = instance;
        this.pluginPath = instance.getDataFolder();
        this.file = new File(pluginPath, "mobs.yml");
        if (!file.exists()) {
            instance.saveResource("mobs.yml", false);
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void cache() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getPluginPath() {
        return pluginPath;
    }
}
