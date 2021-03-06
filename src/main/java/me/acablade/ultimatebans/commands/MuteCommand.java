package me.acablade.ultimatebans.commands;

import me.acablade.ultimatebans.objects.Ban;
import me.acablade.ultimatebans.objects.BanOption;
import me.acablade.ultimatebans.objects.Mute;
import me.acablade.ultimatebans.objects.MuteOption;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        //mute <playerName> <reason> <options>
        if(sender.hasPermission("ultimatebans.mute")){
            if(args.length >= 2){
                List<MuteOption> options = new ArrayList<>();
                List<String> modifiedArgs = Arrays.asList(args.clone()).subList(1,args.length);
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
                String reason = ChatColor.translateAlternateColorCodes('&',reasonBuilder.toString());
                String playerName = args[0];

                OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);

                Mute mute = new Mute(player);
                mute.mute(reason,options,null, sender);

            }else{
                sender.sendMessage("§cWrong syntax\nSyntax: /mute <playerName> <reason> [options]");
            }

        }

        return false;
    }
}
