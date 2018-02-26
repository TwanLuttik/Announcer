package com.twanl.announcer.commands;

import com.twanl.announcer.Announcer;
import com.twanl.announcer.utils.ConfigManager;
import com.twanl.announcer.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor{

    private Announcer plugin = Announcer.getPlugin(Announcer.class);
    public ConfigManager cfgM;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("ac")) {
            if (args.length == 0) {
                if (p.hasPermission("announcer.ac")) {
                    p.sendMessage(Strings.DgrayBIS + "-----------------------------\n" +
                            Strings.gold + "              Commands\n" +
                            " \n" +
                            Strings.gold + "/ac reload " + Strings.white + "reload the config file.\n" +
                            Strings.gold + "/ac message " + Strings.white + "send a test message.\n" +
                            Strings.DgrayBIS + "-----------------------------");




                } else {
                    p.sendMessage(Strings.noPerm);
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission("announcer.reload")) {

                    plugin.saveDefaultConfig();
                    plugin.reloadConfig();
                    p.sendMessage(Strings.goldI + "Config File Reloaded Succsesfully!");
                    p.sendMessage(Strings.whiteI + "the broadcast TIME will not change, you have to restart the server! " + Strings.redI + "(will be added in the next version)");

                } else {
                    p.sendMessage(Strings.noPerm);
                }

            } else if (args[0].equalsIgnoreCase("message")) {
                if (p.hasPermission("announcer.message")) {
                    List<String> list = plugin.getConfig().getStringList("text");
                    String listString = String.join("" + Strings.reset + "\n", list);

                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "" + listString));
                }
            }
        }

        return true;


    }
}
