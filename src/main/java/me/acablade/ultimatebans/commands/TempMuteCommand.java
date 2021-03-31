package me.acablade.ultimatebans.commands;

import me.acablade.ultimatebans.objects.Ban;
import me.acablade.ultimatebans.objects.BanOption;
import me.acablade.ultimatebans.objects.Mute;
import me.acablade.ultimatebans.objects.MuteOption;
import me.acablade.ultimatebans.utils.DateFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TempMuteCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("ultimatebans.tempban")){
            if(args.length >= 3){
                List<MuteOption> options = new ArrayList<>();
                List<String> modifiedArgs = Arrays.asList(args.clone()).subList(2,args.length);
                StringBuilder reasonBuilder = new StringBuilder();
                Arrays.asList(args).forEach((arg) -> {
                    if(arg.startsWith("-")){
                        options.add(MuteOption.getOptionByName(arg.substring(1)));
                    }
                });
                modifiedArgs.forEach((arg) ->{
                    if(!arg.startsWith("-")){
                        if(modifiedArgs.get(modifiedArgs.size()-1).equals(arg)) reasonBuilder.append(arg);
                        else reasonBuilder.append(arg).append(" ");
                    }
                });
                String date = args[1];
                long dateLong = DateFormatter.getExpireLong(date);
                String reason = ChatColor.translateAlternateColorCodes('&',reasonBuilder.toString());
                String playerName = args[0];

                OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);

                Mute mute = new Mute(player);
                mute.mute(reason,options,new Date(dateLong), sender);

            }else{
                sender.sendMessage("§cWrong syntax\nSyntax: /tempban <playerName> <date> <reason> [options]");
            }

        }
        return false;
    }
}
