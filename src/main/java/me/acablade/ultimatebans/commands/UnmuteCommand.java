package me.acablade.ultimatebans.commands;

import me.acablade.ultimatebans.objects.Mute;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnmuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("ultimatebans.mute")){
            if(args.length == 1){
                String player = args[0];
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
                Mute mute = new Mute(offlinePlayer);
                mute.unmute();
                sender.sendMessage("§aBaşarıyla "+ args[0] + " adlı oyuncunun susturulması açıldı");
            }else{
                sender.sendMessage("§cWrong syntax\nSyntax: /unmute <playerName>");
            }
        }

        return false;
    }
}
