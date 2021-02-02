package me.acablade.ultimatebans;

import me.acablade.ultimatebans.commands.BanCommand;
import me.acablade.ultimatebans.listeners.JoinListener;
import me.acablade.ultimatebans.objects.Ban;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltimateBans extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("ban").setExecutor(new BanCommand());
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
