package xenon;



import xenon.commands.Discord;
import xenon.commands.Help;
import xenon.commands.Stats;
import xenon.commands.Joindate;
import xenon.commands.Kill;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xenon.events.AntiIllegals;
import xenon.events.AntiNetherRoof;
import xenon.events.AntiSpam;
import xenon.events.BoatflyPatch;
import xenon.events.CustomChat;
import xenon.events.CustomMessages;
import xenon.events.CustomTab;
import xenon.events.ElytraflyPatch;
import xenon.events.LimitArmorStands;
import xenon.events.RandomRespawn;

/**
 *
 * @author hanna
 */

  public class main extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
            getServer().getPluginManager().registerEvents(new AntiIllegals(), this);
            getServer().getPluginManager().registerEvents(new AntiNetherRoof(), this);
            getServer().getPluginManager().registerEvents(new AntiSpam(), this);
            getServer().getPluginManager().registerEvents(new BoatflyPatch(), this);
            getServer().getPluginManager().registerEvents(new CustomChat(), this);
            getServer().getPluginManager().registerEvents(new CustomMessages(), this);
            getServer().getPluginManager().registerEvents(new ElytraflyPatch(), this);
            getServer().getPluginManager().registerEvents(new RandomRespawn(), this);
            
            LimitArmorStands.limit();
            CustomTab.tab();
            
            this.getCommand("kill").setExecutor(new Kill());
            this.getCommand("help").setExecutor(new Help());
            this.getCommand("discord").setExecutor(new Discord());
            this.getCommand("stats").setExecutor(new Stats());
            this.getCommand("joindate").setExecutor(new Joindate());

        }
}