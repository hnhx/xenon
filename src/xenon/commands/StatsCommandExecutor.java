package xenon.commands;

import xenon.misc.GetElapsedTime;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 *
 * @author hanna
 */
public class StatsCommandExecutor implements Listener, CommandExecutor {
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labe, String[] args) {
        Player p = (Player) sender;
        int allPlayers = Bukkit.getOfflinePlayers().length;
        long maxRam = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long usedRam =  Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long size = 0;
        for (World world : Bukkit.getWorlds()) {
            String worldName = world.getName();
            File worldFile = new File(worldName);
            size+= getDirectorySizeLegacy(worldFile);
            
        }
        
        File owFile = new File(Bukkit.getWorlds().get(0).getName());
        long creationTime = 0;
	try {
	    BasicFileAttributes attrs = Files.readAttributes(owFile.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            creationTime = time.toMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String serverAge = GetElapsedTime.get(creationTime).replace(" -", ", ");
        
        String readableSize = humanReadableByteCountBin(size);
        
        p.sendMessage(String.format("§bCurrent RAM usage: §d%s MB §b/ §d%s MB\n"
                + "§bCurrent server size: §d%s\n"
                + "§bThe server is %s §bold.\n"
                + "§bThere are §d%s §bindividual players who joined at least once.",usedRam,maxRam, readableSize, serverAge, allPlayers));
        return true;
    }
    
    
      public static long getDirectorySizeLegacy(File dir) {
      long length = 0;
      File[] files = dir.listFiles();
      if (files != null) {
          for (File file : files) {
              if (file.isFile())
                  length += file.length();
              else
                  length += getDirectorySizeLegacy(file);
          }
      }
      return length;
  }
      
  public static String humanReadableByteCountBin(long bytes) {
    long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
    if (absB < 1024) {
        return bytes + " B";
    }
    long value = absB;
      CharacterIterator ci = new StringCharacterIterator("KMGTPE");
    for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
        value >>= 10;
        ci.next();
    }
    value *= Long.signum(bytes);
    return String.format("%.1f %cB", value / 1024.0, ci.current());
}
    
}
