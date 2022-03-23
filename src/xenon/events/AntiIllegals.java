package xenon.events;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import xenon.Main;

/**
 *
 * @author hanna
 */
public class AntiIllegals implements Listener {
     @EventHandler
     public void PlayerPickupItemEvent​(EntityPickupItemEvent e) {
         if (e.getEntity().getType() == EntityType.PLAYER) {
             UUID uuid = e.getEntity().getUniqueId();
             Player p = Bukkit.getPlayer(uuid);
             new BukkitRunnable(){
                @Override
                public void run(){
                 Inventory inv = p.getInventory();
                revert(inv);
                }
              }.runTaskLater(Main.getInstance(), 1);
         }
     }
        
    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e){
        Inventory inv = e.getInventory();
        revert(inv);
    }
    
    static List<Material> illegals = Arrays.asList(Material.BEDROCK, Material.END_PORTAL_FRAME, Material.SPAWNER);
    
    public void revert(Inventory inv) {
        for(ItemStack item : inv.getContents()) {
                if (item != null) {

                    // remove illegal items
                    if (illegals.contains(item.getType())) {
                        inv.remove(item);
                        continue;
                    }

                    // revert illegal enchantments
                    if (item.getEnchantments() != null) {
                        for(Map.Entry<Enchantment, Integer> enchantment : item.getEnchantments().entrySet()) {
                            Enchantment enchantmentType = enchantment.getKey();
                            int enchantmentLevel = enchantment.getValue();
                            int enchantmentMaxLevel = enchantmentType.getMaxLevel();
                            if (enchantmentLevel > enchantmentMaxLevel) {
                                item.removeEnchantment(enchantmentType);
                                item.addEnchantment(enchantmentType, enchantmentMaxLevel);
                            }
                        }
                    }
                }
            }
    }
}
