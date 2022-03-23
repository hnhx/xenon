package xenon.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 *
 * @author hanna
 */
public class JoindateCommandExecutor implements Listener, CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labe, String[] args) {
        Player p = (Player) sender;
        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(p.getName());
        if (args.length != 0 ) {
            String target = args[0];
            targetPlayer = Bukkit.getOfflinePlayer(target);
        }
        
        long joinTime = targetPlayer.getFirstPlayed();
        if (joinTime == 0) {
            p.sendMessage("§cThat user never played here!");
           return false;
        }
        Date joinDate = new Date(joinTime);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String joinDateFormatted = String.format("§d%s §bjoin date: §d%s", targetPlayer.getName(), df.format(joinDate));
        p.sendMessage(joinDateFormatted);
        
        return true;
    }
}
