package me.acablade.ultimatebans.commands;

import me.acablade.ultimatebans.objects.Ban;
import me.acablade.ultimatebans.objects.BanOption;
import me.acablade.ultimatebans.utils.DateFormatter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TempBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        // /tempban <playerName> <date> <reason> <options>

        if(sender.hasPermission("ultimatebans.tempban")){
            if(args.length >= 3){
                List<BanOption> options = new ArrayList<>();
                List<String> modifiedArgs = Arrays.asList(args.clone()).subList(2,args.length-1);
                StringBuilder reasonBuilder = new StringBuilder();
                Arrays.asList(args).forEach((arg) -> {
                    if(arg.startsWith("-")){
                        options.add(BanOption.getOptionByName(arg.substring(1)));
                    }
                });
                modifiedArgs.forEach((arg) ->{
                    if(!arg.startsWith("-")){
                        reasonBuilder.append(arg).append(" ");
                    }
                });
                String date = args[1];
                long dateLong = DateFormatter.getExpireLong(date);
                String reason = ChatColor.translateAlternateColorCodes('&',reasonBuilder.toString());
                String playerName = args[0];

                Ban ban = new Ban(playerName);
                ban.ban(reason,options,new Date(dateLong), sender);

            }

        }

        return false;
    }
}
