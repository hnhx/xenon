package xenon.commands;


import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xenon.Main;

/**
 *
 * @author hanna
 */
public class DiscordCommandExecutor implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labe, String[] args) {
        Player p = (Player) sender;
        String discordLink;

        if (Main.getInstance().getConfiguration().getConfig().get("Discord") == null) {
            discordLink = "https://discord.gg/gHn8aqTR5j";

            Main.getInstance().getConfiguration().getConfig().set("Discord", "https://discord.gg/gHn8aqTR5j");
            Main.getInstance().getConfiguration().save();
        } else {
            discordLink = Main.getInstance().getConfiguration().getConfig().getString("Discord");
        }

        p.sendMessage("§bClick on the link to join: §d" + discordLink);
        return true;
    }
}
