package com.twanl.announcer;

import com.twanl.announcer.commands.Commands;
import com.twanl.announcer.events.JoinEvent;
import com.twanl.announcer.utils.Strings;
import com.twanl.announcer.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Announcer extends JavaPlugin {



    private FileConfiguration config;
    private UpdateChecker checker;
    protected PluginDescriptionFile pdfFile = getDescription();
    private final String PluginVersionOn = ChatColor.GREEN + "(" + pdfFile.getVersion() + ")";
    private final String PluginVersionOff = ChatColor.RED + "(" + pdfFile.getVersion() + ")";


    public void onEnable() {


        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                getServer().getConsoleSender().sendMessage(Strings.green + "");
                getServer().getConsoleSender().sendMessage(Strings.green + "------------------------");
                getServer().getConsoleSender().sendMessage(Strings.red + "Announcer is outdated!");
                getServer().getConsoleSender().sendMessage(Strings.white + "Newest version: " + this.checker.getLatestVersion());
                getServer().getConsoleSender().sendMessage(Strings.white + "Your version: " + Strings.green + this.getDescription().getVersion());
                getServer().getConsoleSender().sendMessage("Please download the new version at https://www.spigotmc.org/resources/announcer.53787/");
                getServer().getConsoleSender().sendMessage(Strings.green + "------------------------");
                getServer().getConsoleSender().sendMessage(Strings.green + "");
            } else {
                getServer().getConsoleSender().sendMessage(Strings.green + "");
                getServer().getConsoleSender().sendMessage(Strings.green + "---------------------------------");
                getServer().getConsoleSender().sendMessage(Strings.green + "Announcer is up to date.");
                getServer().getConsoleSender().sendMessage(Strings.green + "---------------------------------");
                getServer().getConsoleSender().sendMessage(Strings.green + "");
            }
        }



        LOAD();
        message();
        Bukkit.getConsoleSender().sendMessage(Strings.logName + "Has been enabled " + PluginVersionOn);
    }

    public void onDisable() {
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(Strings.logName + "Has been enabled " + PluginVersionOff);
    }

    public void LOAD() {
        // Register listeners
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);

        // Register commands
        Commands commands = new Commands();
        getCommand("ac").setExecutor(commands);


        //LoadConfig
        getConfig().options().copyDefaults(true);
        saveConfig();
    }




    public void message() {
        this.config = getConfig();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {



            public void run() {

                List<String> list = getConfig().getStringList("text");
                String listString = String.join(""+ Strings.reset + "\n", list);

                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"" + listString));



            }
        }, 20L, 8000L);
    }




}
