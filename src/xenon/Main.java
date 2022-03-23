package xenon;



import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import xenon.commands.DiscordCommandExecutor;
import xenon.commands.HelpCommandExecutor;
import xenon.commands.StatsCommandExecutor;
import xenon.commands.JoindateCommandExecutor;
import xenon.commands.KillCommandExecutor;
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
import xenon.misc.Config;

/**
 *
 * @author hanna
 */

  public class Main extends JavaPlugin implements Listener {

      private Config config;
      private static Main instance;

      @Override
      public void onLoad() {
          this.config = new Config();
      }

        @Override
        public void onEnable() {
          // Get an instance of the main class
          instance = this;

          // Initialize default config values if not set
          initConfig();

          // Register commands and listeners
          registerListener();
          commandRegistration();

          LimitArmorStands.limit();
          CustomTab.tab();
        }

        @Override
        public void onDisable() {
          // Save Config to file
          this.config.save();
        }

        private void registerListener() {
          // Registering Bukkit Listeners
            PluginManager pluginManager = Bukkit.getPluginManager();

            pluginManager.registerEvents(new AntiIllegals(), this);
            pluginManager.registerEvents(new AntiNetherRoof(), this);
            pluginManager.registerEvents(new AntiSpam(), this);
            pluginManager.registerEvents(new BoatflyPatch(), this);
            pluginManager.registerEvents(new CustomChat(), this);
            pluginManager.registerEvents(new CustomMessages(), this);
            pluginManager.registerEvents(new ElytraflyPatch(), this);
            pluginManager.registerEvents(new RandomRespawn(), this);
        }

        private void commandRegistration() {
          // Registering Bukkit commands
            this.getCommand("kill").setExecutor(new KillCommandExecutor());
            this.getCommand("help").setExecutor(new HelpCommandExecutor());
            this.getCommand("discord").setExecutor(new DiscordCommandExecutor());
            this.getCommand("stats").setExecutor(new StatsCommandExecutor());
            this.getCommand("joindate").setExecutor(new JoindateCommandExecutor());
        }

        private void initConfig() {
          // Set default values
          if (this.config.getConfig().get("Discord") != null) {
              return;
          }

          this.config.getConfig().set("Discord", "https://discord.gg/gHn8aqTR5j");
          this.config.save();
        }

    public Config getConfiguration() {
        return config;
    }

    public static Main getInstance() {
        return instance;
    }
}