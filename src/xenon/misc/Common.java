package xenon.misc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author hanna
 */
public class Common {
    public static Plugin getPlugin() {
         return Bukkit.getServer().getPluginManager().getPlugin("xenon");
        }
}
