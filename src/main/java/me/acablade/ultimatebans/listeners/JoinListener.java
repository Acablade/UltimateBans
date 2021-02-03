package me.acablade.ultimatebans.listeners;

import me.acablade.ultimatebans.objects.Ban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerLoginEvent event){
        if(event.getResult() == PlayerLoginEvent.Result.KICK_BANNED){
            Ban ban = new Ban(event.getPlayer().getDisplayName());
            event.setKickMessage("Ban!1!1 \n Reason: "+ban.getReason()+"\n Expire Date:"+ban.getDate());
        }
    }

}
