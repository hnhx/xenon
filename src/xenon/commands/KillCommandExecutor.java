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
public class KillCommandExecutor implements Listener, CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labe, String[] args) {
        Player p = (Player) sender;
        p.setHealth(0);
        return true;
    }
}
