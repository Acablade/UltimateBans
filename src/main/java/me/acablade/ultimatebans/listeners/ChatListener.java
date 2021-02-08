package me.acablade.ultimatebans.listeners;

import me.acablade.ultimatebans.objects.Mute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();

        Mute mute = new Mute(p.getDisplayName());

        if(mute.isMuted()){
            if(!(System.currentTimeMillis() > mute.getDate().getTime())){
                event.setCancelled(true);
                String date;
                if(mute.getDate() != null){
                    date = mute.getDate().toString();
                }else{
                    date = "forever";
                }
                p.sendMessage("§cYou have been muted for "+mute.getReason()+" until "+date+" by "+mute.getSource());
            }else{
                mute.unmute();
            }

        }

    }

}
