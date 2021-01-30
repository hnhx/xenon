package xenon.events;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author hanna
 */
public class BoatflyPatch implements Listener {
     @EventHandler
     public void onMove(PlayerMoveEvent event)  {
            Player p = event.getPlayer();
            if (p.isInsideVehicle()) {
                if(p.getVehicle().getType() ==  EntityType.BOAT){
                    Vehicle v = (Vehicle) p.getVehicle();
                    if (v.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                        v.eject();
                    }
                }
            }
     }
}
