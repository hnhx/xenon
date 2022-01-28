package xenon.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 *
 * @author hanna
 */
public class HelpCommandExecutor implements Listener, CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labe, String[] args) {
        Player p = (Player) sender;
        p.sendMessage("§d/kill §bKills you\n"
                + "§d/stats §bServer information\n"
                + "§d/discord §bLink to the discord server\n"
                + "§d/tps §bTPS from the last 1m, 5m, 15m\n"
                + "§d/help §bThis command\n"
                + "§d/joindate (player) §bJoindate of a player");
        return true;
    }
}
