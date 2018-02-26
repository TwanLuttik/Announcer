package com.twanl.announcer.events;

import com.twanl.announcer.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class BroadcastEvent extends BukkitRunnable {

    private final JavaPlugin plugin;

    public BroadcastEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void run() {
        List<String> worlds = new ArrayList<String>();
        worlds = plugin.getConfig().getStringList("disabled_worlds");

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (!worlds.contains(p.getWorld().getName())) {
                List<String> list = plugin.getConfig().getStringList("text");
                String listString = String.join("" + Strings.reset + "\n", list);

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "" + listString));
            }
        }
    }



}