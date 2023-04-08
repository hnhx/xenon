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
public class Discord implements Listener, CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labe, String[] args) {
        Player p = (Player) sender;
        p.sendMessage("§bClick on the link to join: §dhttps://discord.gg/gHn8aqTR5j");
        return true;
    }
}
