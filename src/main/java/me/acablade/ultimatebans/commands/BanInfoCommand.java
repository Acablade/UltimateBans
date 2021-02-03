package me.acablade.ultimatebans.commands;

import me.acablade.ultimatebans.objects.InfoGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanInfoCommand implements CommandExecutor {

    // baninfo <playerName>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) sender.sendMessage("§cOnly players can use this command");
        Player p = (Player) sender;
        if(p.hasPermission("ultimatebans.baninfo")){
            if(args.length == 1){
                InfoGUI infoGUI = new InfoGUI(args[0]);
                infoGUI.openGUI(p);
            }else{
                p.sendMessage("§cWrong syntax. Syntax: /baninfo <playerName>");
            }
        }
        return false;
    }
}
