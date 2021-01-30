package xenon.events;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 *
 * @author hanna
 */
public class RandomRespawn implements Listener {
      
     @EventHandler
     public void onRespawn(PlayerRespawnEvent event) {
         Player p = event.getPlayer();
       if (p.getBedSpawnLocation() == null) {
           World overWorld = Bukkit.getServer().getWorlds().get(0);
           List<Material> blacklistedBlocks = Arrays.asList(Material.AIR, Material.VOID_AIR);
           int radius = 1001;
           
           Boolean safeRespawn = false;
           Location potentialSpawn = null;
           Random r = new Random();
           do {
                int x = r.nextInt(radius);
                int z = r.nextInt(radius);
                
                for (int y = overWorld.getMaxHeight(); y>0; y--) {
                    potentialSpawn = new Location(overWorld, x,y,z);
                    Block currentBlock = potentialSpawn.getBlock();
                    if (!blacklistedBlocks.contains(currentBlock.getType())) {
                        safeRespawn = true;
                        event.setRespawnLocation(potentialSpawn);
                        break;
                    }
                    
                }
           } while (!safeRespawn);
       }
     } 
}
