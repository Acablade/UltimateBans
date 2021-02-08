package me.acablade.ultimatebans.data;

import me.acablade.ultimatebans.UltimateBans;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MuteConfiguration {

    private static FileConfiguration customConfig;

    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public static void createCustomConfig() throws IOException {
        File customConfigFile = new File(UltimateBans.getInstance().getDataFolder(), "mutes.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            customConfigFile.createNewFile();
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
