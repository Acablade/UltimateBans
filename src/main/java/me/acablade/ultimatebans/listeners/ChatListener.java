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
                p.sendMessage("§c"+mute.getReason()+" sebebiyle "+date+" süresine kadar "+mute.getSource() +" tarafından susturuldun!");
            }else if(mute.getDate() == null){
                event.setCancelled(true);
                p.sendMessage("§c"+mute.getReason()+" sebebiyle sonsuza süresine kadar "+mute.getSource() +" tarafından susturuldun!");
            }else{
                mute.unmute();
            }

        }

    }

}
