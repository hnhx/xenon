package xenon.events;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xenon.Main;

/**
 *
 * @author hanna
 */
public class AntiNetherRoof implements Listener {
     @EventHandler
     public void onMove(PlayerMoveEvent event) {
            if (!playersToDamage.contains(event.getPlayer())) {
             World nether = Bukkit.getServer().getWorlds().get(1);
             if (event.getPlayer().getWorld() == nether) {
                if (event.getPlayer().getLocation().getY() >= 128 || 0 >= event.getPlayer().getLocation().getY()) {
                    playersToDamage.add(event.getPlayer());
                    netherRoofDamage(event.getPlayer());
                }
             }
            }
     }
     
             
     protected List<Player> playersToDamage = new ArrayList<Player>();
     public void netherRoofDamage(Player p) {
         new BukkitRunnable(){
            int netherDamage = 2;
            @Override
            public void run(){
                World nether = Bukkit.getServer().getWorlds().get(1);
                if (p.getWorld() == nether) {
                    if (p.getLocation().getY() >= 128 || 0 >= p.getLocation().getY()) {
                        p.damage(netherDamage);
                        netherDamage *= 1.5;
                    } else {
                        playersToDamage.remove(p);
                        this.cancel();
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
     }
}
