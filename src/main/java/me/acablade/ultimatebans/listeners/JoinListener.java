package me.acablade.ultimatebans.listeners;

import me.acablade.ultimatebans.objects.Ban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        if(event.getLoginResult() == AsyncPlayerPreLoginEvent.Result.KICK_BANNED){
            Ban ban = new Ban(event.getPlayerProfile().getName());
            if(System.currentTimeMillis() > ban.getDate().getTime()){
                ban.unban();
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
            }
            event.setKickMessage("Ban!1!1 /n Reason: "+ban.getReason()+"/n Expire Date:"+ban.getDate());
        }
    }

}
