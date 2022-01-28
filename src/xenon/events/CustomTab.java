/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenon.events;

import java.lang.reflect.Field;

import net.minecraft.server.v1_16_R1.ChatComponentText;
import net.minecraft.server.v1_16_R1.MinecraftServer;
import net.minecraft.server.v1_16_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xenon.Main;
import xenon.misc.GetElapsedTime;

/**
 *
 * @author hanna
 */
public class CustomTab {
    public static void tab() {
        long startTime = System.currentTimeMillis();
            
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
            new BukkitRunnable() {
                @Override
                public void run() {
                      try {
                          Field header = packet.getClass().getDeclaredField("header");
                          header.setAccessible(true);

                          Field footer = packet.getClass().getDeclaredField("footer");
                          footer.setAccessible(true);
                          
                          
                          // PLAYERS
                          int onlinePlayers = Bukkit.getOnlinePlayers().size();
                          int maxPlayers = Bukkit.getMaxPlayers();
                         
                          
                          // TPS
                          double tps = MinecraftServer.getServer().recentTps[0];
                          double fixedTps = (tps > 20 ? 20 : tps);
                          
                          // Uptime
                          String uptime = "§bUptime: " + GetElapsedTime.get(startTime);
                          
                          for (Player p : Bukkit.getOnlinePlayers()) {
                                    String name = p.getName();
                                    
                                    int ping = ((CraftPlayer) p).getHandle().ping;
                              
                                    String headerMessage = String.format("§b§l§ka§r §d§lXenon §r§b§l§ka\n§d%s §b/ §d%s §bplayers\n", onlinePlayers, maxPlayers);
                                    Object headerObj = new ChatComponentText(headerMessage);

                                    String footerMessage = String.format("\n§bTPS: §d%.2f §b- Ping: §d%s\n%s\n§bDiscord: §d/discord\n\n§cElytra fly hacks are limited but not disabled.\n§cThis might change depending on the server's performance",fixedTps,ping,uptime);
                                    Object footerObj = new ChatComponentText(footerMessage);

                                    header.set(packet, headerObj);
                                    footer.set(packet, footerObj);
                              
                              
                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                          }
                         

                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                }

            }.runTaskTimer(Main.getInstance(), 0, 20);
    }
}
