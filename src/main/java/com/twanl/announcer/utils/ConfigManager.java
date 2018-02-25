package com.twanl.announcer.utils;

import com.twanl.announcer.Announcer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {


    private Announcer plugin = Announcer.getPlugin(Announcer.class);

    //Files & Config Files

    public FileConfiguration configC;
    public File configF;
    //--------------------


    public void setup1() {
        configF = new File(plugin.getDataFolder(), "config.yml");
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }


        if (!configF.exists()) {
            try {
                configF.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(Strings.green + "The config.yml file has been created");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(Strings.red + "Could not create the config.yml file");
            }
        }

        configC = YamlConfiguration.loadConfiguration(configF);

    }


    public FileConfiguration getConfig() {
        return configC;

    }


    public void saveConfig() {
        configF = new File(plugin.getDataFolder(), "config.yml");

        try {
            configC.save(configF);
            Bukkit.getServer().getConsoleSender().sendMessage(Strings.green + "The config.yml file has been saved");

        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(Strings.red + "Could not save the config.yml file");

        }
    }


    public void reloadconfig() {
        configC = YamlConfiguration.loadConfiguration(configF);
        Bukkit.getServer().getConsoleSender().sendMessage(Strings.green + "The config.yml file has been reloaded");

    }


}