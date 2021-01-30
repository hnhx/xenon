package xenon.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent​;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author hanna
 */
public class CustomMessages implements Listener {
    @EventHandler
     public void PlayerDeathEvent​(PlayerDeathEvent​ event) {
         String pName = event.getEntity().getName();
         String dMessage = event.getDeathMessage().replace(pName + " ", "");
         event.setDeathMessage(String.format("§d%s §b%s",pName, dMessage));
     }
     
     @EventHandler
     public void PlayerJoinEvent(PlayerJoinEvent e) {
         e.setJoinMessage(String.format("§d%s §bjoined the server %s", e.getPlayer().getName(), (e.getPlayer().hasPlayedBefore() ? "" : "§dfor the first time")));
     }
     
     
}
