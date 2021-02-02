package me.acablade.ultimatebans.objects;

import org.bukkit.BanList;
import org.bukkit.Bukkit;

import java.util.Date;
import java.util.List;

public class Ban {

    String playerName;


    public Ban(String player){
        this.playerName = player;
    }

    public boolean isBanned(){
        return Bukkit.getBanList(BanList.Type.NAME).isBanned(playerName);
    }

    public void ban(String reason, List<BanOption> option, Date expireDate){
        if(!option.contains(BanOption.SILENT)) {
            Bukkit.broadcastMessage("§c"+playerName+" has been banned for: "+reason);
        }
        String bumper = org.apache.commons.lang.StringUtils.repeat("\n", 35);
        String editedReason = bumper + "Ban !1!1!1!!\n" +"Reason: " + reason;
        if(Bukkit.getPlayer(playerName) != null){
            Bukkit.getPlayer(playerName).kickPlayer(reason);
        }
        Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,reason,expireDate,null);
    }

    public void unban(){
        Bukkit.getBanList(BanList.Type.NAME).pardon(playerName);
    }

    public String getReason(){
        return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(playerName).getReason();
    }
    public Date getDate(){
        return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(playerName).getExpiration();
    }


}
