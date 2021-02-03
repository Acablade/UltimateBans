package me.acablade.ultimatebans;

import me.acablade.ultimatebans.commands.BanCommand;
import me.acablade.ultimatebans.commands.BanInfoCommand;
import me.acablade.ultimatebans.commands.TempBanCommand;
import me.acablade.ultimatebans.listeners.InventoryListener;
import me.acablade.ultimatebans.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltimateBans extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("tempban").setExecutor(new TempBanCommand());
        getCommand("baninfo").setExecutor(new BanInfoCommand());
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
