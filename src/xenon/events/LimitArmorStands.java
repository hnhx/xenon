package xenon.events;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.EntityEffect;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import xenon.misc.Common;

/**
 *
 * @author hanna
 */
public class LimitArmorStands {
        public static void limit() {
            new BukkitRunnable() {
         @Override
         public void run() {
            for (World world: Bukkit.getServer().getWorlds())
            for (Chunk chunk : world.getLoadedChunks()) {
                int armorStandCount = 0;
                for (Entity entity : chunk.getEntities()) {
                    if (entity.getType() == EntityType.ARMOR_STAND) {
                        armorStandCount++;
                        if (armorStandCount > 10) {   
                            entity.playEffect(EntityEffect.DEATH);
                            entity.remove();
                        }
                    }
                }
            }
         
            }}.runTaskTimer(Common.getPlugin(), 0, 20);
        }
}
