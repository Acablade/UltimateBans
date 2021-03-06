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
            Bukkit.broadcastMessage("§7[§bUB§7] §c"+playerName +", "+ sender.getName()+" tarafından "+expireDate+" tarihine kadar '"+ reason+"' sebebiyle uzaklaştırıldı.");
        }else{
            sender.sendMessage("§7[§bUB§7] §c"+playerName +", "+ sender.getName()+" tarafından "+expireDate+" tarihine kadar '"+ reason+"' sebebiyle sessizce uzaklaştırıldı.");
        }
        if(Bukkit.getPlayer(playerName) != null){
            Bukkit.getPlayer(playerName).kickPlayer("Çekiç konuştu! \n§rSebep: "+reason+"\n§rBitiş tarihi: "+expireDate);
        }
        Bukkit.getBanList(BanList.Type.NAME).addBan(playerName,reason,expireDate,sender.getName());
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


    public String getSource(){
        return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(playerName).getSource();
    }


}
