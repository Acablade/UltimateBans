package me.acablade.ultimatebans;

import me.acablade.ultimatebans.commands.*;
import me.acablade.ultimatebans.data.MuteConfiguration;
import me.acablade.ultimatebans.listeners.ChatListener;
import me.acablade.ultimatebans.listeners.InventoryListener;
import me.acablade.ultimatebans.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class UltimateBans extends JavaPlugin {

    private static UltimateBans instance;

    public static UltimateBans getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("tempban").setExecutor(new TempBanCommand());
        getCommand("baninfo").setExecutor(new BanInfoCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unmute").setExecutor(new UnmuteCommand());
        getCommand("tempmute").setExecutor(new TempMuteCommand());
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        instance = this;
        try {
            MuteConfiguration.createCustomConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
