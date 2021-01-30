package xenon.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author hanna
 */
public class AntiSpam implements Listener {
        HashMap<String, List<String>> playerMessages = new HashMap<String, List<String>>();
        @EventHandler
        public void onChat(AsyncPlayerChatEvent event) {
            
            String UUID = event.getPlayer().getUniqueId().toString();
            
            String newMessage = event.getMessage();
            List<String> messages = new ArrayList<String>();
            messages = playerMessages.get(UUID);
            List<String> newMessageWords = Arrays.asList(newMessage.split(" "));
            
            if (messages == null) {
                messages = new ArrayList<String>();
                messages.add(newMessage);
                playerMessages.put(UUID, messages);
            } else { 
            if (newMessage.length() > 6 && newMessageWords.size() > 2)  {
                for (String oldMessage : messages) {
                    
                    if (oldMessage.toLowerCase().equals(newMessage.toLowerCase())) {
                            event.getPlayer().sendMessage("§cYour message has been detected as spam!");
                            event.setCancelled(true);
                            break;
                    }
                    
                    List<String> oldMessageWords = Arrays.asList(oldMessage.split(" "));
                    
                    List<String> oldMessageWordsLower = new ArrayList<String>();
                    for (String msg : oldMessageWords) {
                        oldMessageWordsLower.add(msg.toLowerCase());
                    }
                    
                    List<String> newMessageWordsLower = new ArrayList<String>();
                    for (String msg : newMessageWords) {
                        newMessageWordsLower.add(msg.toLowerCase());
                    }
                    
                    
                    if (3 > oldMessageWords.size() - newMessageWords.size()) {
                        
                        ArrayList<String> duplicates = new ArrayList<String>(oldMessageWordsLower);
                        duplicates.retainAll(newMessageWordsLower);
                        
                        if (duplicates.size() >= newMessageWords.size() - 1) {
                            event.getPlayer().sendMessage("§cYour message has been detected as spam!");
                            event.setCancelled(true);
                            break;
                        }
                    
                    }
                } 
                
            } else if (newMessage.toLowerCase().equals(messages.get(messages.size() - 1).toLowerCase())) {
                event.getPlayer().sendMessage("§cYour message has been detected as spam!");
                event.setCancelled(true);
            }
                
                if (!event.isCancelled()) {
                    if (25 > messages.size()) {
                        messages.add(newMessage);
                    } else {
                        messages.remove(0);
                        messages.add(newMessage);
                    }
                    playerMessages.put(UUID, messages);
                }
            }
        }
}
