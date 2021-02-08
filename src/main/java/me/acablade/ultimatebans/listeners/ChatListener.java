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

        Mute mute = new Mute(p);

        if(mute.isMuted()){
            if(mute.getDate()!=null && !(System.currentTimeMillis() > mute.getDate().getTime())){
                event.setCancelled(true);
                String date;
                date = mute.getDate().toString();
                p.sendMessage("§cYou have been muted for "+mute.getReason()+" until "+date+" by "+mute.getSource());
            }else if(mute.getDate() == null){
                event.setCancelled(true);
                p.sendMessage("§cYou have been muted for "+mute.getReason()+" until forever by "+mute.getSource());
            }else{
                mute.unmute();
            }

        }

    }

}
