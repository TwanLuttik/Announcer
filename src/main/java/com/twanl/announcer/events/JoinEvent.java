package com.twanl.announcer.events;

import com.twanl.announcer.Announcer;
import com.twanl.announcer.utils.ConfigManager;
import com.twanl.announcer.utils.Strings;
import com.twanl.announcer.utils.UpdateChecker;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private Announcer plugin = Announcer.getPlugin(Announcer.class);
    public ConfigManager cfgM;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();


        if (plugin.getConfig().getBoolean("update_message")) {
            if (p.hasPermission("announcer.update")) {
                final Player p1 = e.getPlayer();
                final PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                final PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"Announcer is outdated!\",\"color\":\"red\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.spigotmc.org/resources/announcer.53787/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to download the newest version of Announcer\",\"color\":\"gold\"}]}}}"));

                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public UpdateChecker checker;

                    public void run() {
                        this.checker = new UpdateChecker(plugin);

                        if (this.checker.isConnected()) {
                            if (this.checker.hasUpdate()) {
                                p1.sendMessage(Strings.DgrayBS + "----------------------\n");
                                connection.sendPacket(packet);
                                p1.sendMessage(" \n" +
                                        Strings.white + "Your version: " + plugin.getDescription().getVersion() + "\n" +
                                        Strings.white + "Newest version: " + Strings.green + this.checker.getLatestVersion() + "\n" +
                                        Strings.DgrayBS + "----------------------");
                            } else {
                                p1.sendMessage(Strings.DgrayBS + "----------------------\n" +
                                        Strings.green + "Announcer is up to date.\n" +
                                        Strings.DgrayBS + "----------------------");
                            }
                        }
                    }
                }, 20);

            }
        }
    }



}
