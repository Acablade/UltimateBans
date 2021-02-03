package me.acablade.ultimatebans.commands;

import me.acablade.ultimatebans.objects.Ban;
import me.acablade.ultimatebans.objects.BanOption;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        // ban <playerName> <reason> <options>

        if(sender.hasPermission("ultimatebans.ban")){
            if(args.length >= 2){
                List<BanOption> options = new ArrayList<>();
                List<String> modifiedArgs = Arrays.asList(args.clone()).subList(1,args.length);
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
                String reason = ChatColor.translateAlternateColorCodes('&',reasonBuilder.toString());
                String playerName = args[0];

                Ban ban = new Ban(playerName);
                ban.ban(reason,options,null, sender);

            }else{
                sender.sendMessage("§cWrong syntax\nSyntax: /ban <playerName> <reason> [options]");
            }

        }
        return false;
    }
}
