package me.acablade.ultimatebans.objects;

import me.acablade.ultimatebans.data.MuteConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Date;
import java.util.List;

public class Mute {

    String playerName;


    public Mute(String playerName){
        this.playerName = playerName;
    }

    public boolean isMuted(){
        return MuteConfiguration.getCustomConfig().contains("muted."+playerName);
    }

    /**
     * Mutes the specified player
     * @param reason reason of the mute
     * @param muteOptions options of mute
     * @param expirationDate expiration date of mute, when the mute will end
     * @param source executor of the mute, whom muted the player
     */
    public void mute(String reason, List<MuteOption> muteOptions, Date expirationDate, CommandSender source){
        if(!isMuted()) {
            // Set reason
            MuteConfiguration.getCustomConfig().set("muted." + playerName + ".reason", reason);

            // Set expiration date
            MuteConfiguration.getCustomConfig().set("muted." + playerName + ".date", expirationDate.getTime());

            // Set source
            MuteConfiguration.getCustomConfig().set("muted." + playerName + ".source", source);

            MuteConfiguration.getCustomConfig().set("muted." + playerName + ".muted", true);
            if(!muteOptions.contains(MuteOption.SILENT)){
                Bukkit.broadcastMessage("§7[§bUB§7] §c"+playerName+" has been muted by "+ source.getName()+" until "+expirationDate.toString()+" with reason '"+ reason+"'");
            }else{
                source.sendMessage("§7[§bUB§7] §c"+playerName+" has been muted by "+ source.getName()+" until "+expirationDate.toString()+" with reason '"+ reason + "' silently");
            }
        }
    }

    /**
     * Unmutes the specified player
     */
    public void unmute(){
        if(isMuted()){
            MuteConfiguration.getCustomConfig().set("muted."+playerName+".muted", false);
        }
    }
    public String getReason(){
        return MuteConfiguration.getCustomConfig().getString("muted."+playerName+".reason");
    }

    public Date getDate(){
        return new Date(MuteConfiguration.getCustomConfig().getLong("muted."+playerName+".date"));
    }

    public String getSource(){
        return MuteConfiguration.getCustomConfig().getString("muted."+playerName+".source");
    }



}
