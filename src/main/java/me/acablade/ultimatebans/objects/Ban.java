package me.acablade.ultimatebans.objects;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Date;
import java.util.List;

public class Ban {

    String playerName;
    List<BanOption> options;

    public Ban(String player){
        this.playerName = player;
    }

    public boolean isBanned(){
        return Bukkit.getBanList(BanList.Type.NAME).isBanned(playerName);
    }

    public void ban(String reason, List<BanOption> option, Date expireDate, CommandSender sender){
        this.options = option;
        if(!option.contains(BanOption.SILENT)) {
            Bukkit.broadcastMessage("§c"+playerName+" has been banned for: "+reason+"\nbanned by: "+sender.getName());
        }
        if(Bukkit.getPlayer(playerName) != null){
            Bukkit.getPlayer(playerName).kickPlayer("Ban!1!1 \n§rReason: "+reason+"\n§rExpire Date: "+expireDate);
        }
        Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,reason,expireDate,sender.getName());
        sender.sendMessage(getBanMessage());
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

    public String getBanMessage(){
        StringBuilder options = new StringBuilder();
        this.options.forEach((banOption -> options.append(banOption).append(", ")));
        return "Banned "+playerName+" for reason: "+getReason()+",until: "+getDate() + " with options: "+options.toString();
    }

    public String getSource(){
        return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(playerName).getSource();
    }


}
