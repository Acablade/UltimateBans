package me.acablade.ultimatebans.objects;

import me.acablade.ultimatebans.UltimateBans;
import me.acablade.ultimatebans.data.MuteConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Mute {

    String playerName;
    UUID playerUUID;


    public Mute(OfflinePlayer player){
        this.playerName = player.getName();
        this.playerUUID = player.getUniqueId();
    }

    public boolean isMuted(){
        return MuteConfiguration.getCustomConfig().contains("muted."+playerUUID.toString());
    }

    /**
     * Mutes the specified player
     * @param reason reason of the mute
     * @param muteOptions options of mute
     * @param expirationDate expiration date of mute, when the mute will end
     * @param source executor of the mute, whom muted the player
     */
    public void mute(String reason, List<MuteOption> muteOptions, Date expirationDate, CommandSender source){
        // Set reason
        MuteConfiguration.getCustomConfig().set("muted." + playerUUID + ".reason", reason);

        // Set expiration date
        String expireDateString;
        if(expirationDate == null){
            expireDateString = "forever";
            MuteConfiguration.getCustomConfig().set("muted." + playerUUID + ".date", "0");
        }else{
            expireDateString = expirationDate.toString();
            MuteConfiguration.getCustomConfig().set("muted." + playerUUID + ".date", expirationDate.getTime());
        }


        // Set source
        MuteConfiguration.getCustomConfig().set("muted." + playerUUID + ".source", source.getName());

        MuteConfiguration.getCustomConfig().set("muted." + playerUUID + ".muted", true);
        if(!muteOptions.contains(MuteOption.SILENT)){
            Bukkit.broadcastMessage("§7[§bUB§7] §c"+playerName+" has been muted by "+ source.getName()+" until "+expireDateString+" with reason '"+ reason+"'");
        }else{
            source.sendMessage("§7[§bUB§7] §c"+playerName+" has been muted by "+ source.getName()+" until "+expireDateString+" with reason '"+ reason + "' silently");
        }
        MuteConfiguration.save();
    }

    /**
     * Unmutes the specified player
     */
    public void unmute(){
        if(isMuted()){
            MuteConfiguration.getCustomConfig().set("muted."+playerUUID+".muted", false);
            MuteConfiguration.save();
        }
    }
    public String getReason(){
        return MuteConfiguration.getCustomConfig().getString("muted."+playerUUID+".reason");
    }

    public Date getDate(){
        return MuteConfiguration.getCustomConfig().getLong("muted."+playerUUID+".date") == 0 ?
                null
                :
                new Date(MuteConfiguration.getCustomConfig().getLong("muted."+playerUUID+".date"));
    }

    public String getSource(){
        return MuteConfiguration.getCustomConfig().getString("muted."+playerUUID+".source");
    }



}
