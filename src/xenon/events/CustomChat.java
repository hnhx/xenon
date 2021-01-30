package xenon.events;

import java.util.HashMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author hanna
 */
public class CustomChat implements Listener {
        @EventHandler
        public void onChat(AsyncPlayerChatEvent event) {
            event.setFormat("§d%s§b: §f%s");
            
            String message = event.getMessage();
            
            if (message.charAt(0) == '$') event.setMessage(lgbtColors(message));
            
            if (message.charAt(0) == '>') event.setMessage("§a" + message);
        }
        
        public static String lgbtColors(String textToConvert) {
        if (3 > textToConvert.length()) return textToConvert;
        
        HashMap<Character, String[]> colors = new HashMap<Character,String[]>();
        colors.put('g',new String[]{"§c", "§6", "§e", "§a", "§3", "§d"});
        colors.put('t',new String[]{"§b", "§d", "§f", "§d", "§b"});
        colors.put('b',new String[]{"§d", "§5", "§3"});
        colors.put('p',new String[]{"§d", "§e", "§b"});
        colors.put('n',new String[]{"§e", "§f", "§5", "§0"});
        colors.put('l',new String[]{"§c", "§e", "§f", "§d", "§5"});
        
        String[] chosenColors = colors.get(Character.toLowerCase(textToConvert.charAt(1)));
        if (chosenColors == null) return textToConvert;
        
        String convertedText = "";
        byte colorCount = 0;
        for (int i = 2; i < textToConvert.length(); i++) {
            char c = textToConvert.charAt(i);
            if (c == ' ') {
                convertedText += c;
                continue;
            }
            
            convertedText += chosenColors[colorCount] + c;
            
            if (colorCount >= chosenColors.length - 1) {
                colorCount = 0;
            } else {
                colorCount++;
            }
            
        }
        
        return convertedText;
    }
}
