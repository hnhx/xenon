package xenon.events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xenon.misc.Common;

/**
 *
 * @author hanna
 */
public class ElytraflyPatch implements Listener {
    
    @EventHandler
     public void PlayerJoinEvent(PlayerJoinEvent e) {
         e.getPlayer().setGliding(false);
         elytraPatch(e.getPlayer());
     }
    
       public void elytraPatch(Player p) {
         new BukkitRunnable(){
             
             Location prevLocation = p.getLocation();
            protected List<Double> distancesFlown = new ArrayList<Double>();
            Location glideStarted = null;
            
            @Override
            public void run(){
                
                if (!p.isOnline()) { 
                    this.cancel();
                }
                
                Location curLocation = p.getLocation();
                double curDistanceFlown = curLocation.distanceSquared(prevLocation);
                
                if (p.isGliding() && !p.isInWater()) {
                  if (glideStarted == null) glideStarted = curLocation;  
                    
                  if (distancesFlown.size() == 10) {

                         HashSet<Double> fDistanceFlown = new HashSet<Double>(distancesFlown);
                         if (8 >= fDistanceFlown.size() ) {
                            p.teleport(glideStarted);
                            distancesFlown.clear();
                            p.setGliding(false);
                            //p.sendMessage("§cElytra hacks are not allowed!");
                            //p.kickPlayer("§b§l[§d§lXENON§b§l]\n\n§bKick reason: §dElytra related");
                         }
                         
                    
                        glideStarted = curLocation;
                   }
                     if (distancesFlown.size() == 20) {
                      distancesFlown.clear();
                    } else {
                       distancesFlown.add(Double.parseDouble(String.format("%.6g%n", curDistanceFlown)));
                    }
                    
                } else {
                    glideStarted = null;
                }
                prevLocation = curLocation;
           
            }
        }.runTaskTimer(Common.getPlugin(), 0, 3);
     }
     
}
