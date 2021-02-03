package me.acablade.ultimatebans.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class InfoGUI {

    Ban ban;

    public InfoGUI(String playerName){
        this.ban = new Ban(playerName);
    }

    public void openGUI(Player p){
        if(ban.isBanned()){
            Inventory inv = Bukkit.createInventory(null,9*3,"UltimateBans Info");

            ItemStack glassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta glassPaneMeta = glassPane.getItemMeta();
            glassPaneMeta.setDisplayName(" ");
            glassPane.setItemMeta(glassPaneMeta);

            ItemStack bannedPlayer = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta bannedPlayerMeta = (SkullMeta) bannedPlayer.getItemMeta();
            bannedPlayerMeta.setOwningPlayer(Bukkit.getOfflinePlayer(ban.playerName));
            bannedPlayerMeta.setDisplayName("§eBanned player:");
            bannedPlayerMeta.setLore(Arrays.asList("§c"+ban.playerName));
            bannedPlayer.setItemMeta(bannedPlayerMeta);

            ItemStack expirationDate = new ItemStack(Material.TNT);
            ItemMeta expirationDateMeta = expirationDate.getItemMeta();
            expirationDateMeta.setDisplayName("§eExpiration date:");
            expirationDateMeta.setLore(Arrays.asList("§c"+ban.getDate().toString()));
            expirationDate.setItemMeta(expirationDateMeta);

            ItemStack reason = new ItemStack(Material.PAPER);
            ItemMeta reasonMeta = reason.getItemMeta();
            reasonMeta.setDisplayName("§eReason:");
            reasonMeta.setLore(Arrays.asList("§c"+ban.getReason()));
            reason.setItemMeta(reasonMeta);

            ItemStack source = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta sourceMeta = (SkullMeta) source.getItemMeta();
            sourceMeta.setOwningPlayer(Bukkit.getOfflinePlayer(ban.getSource()));
            sourceMeta.setDisplayName("§eSource:");
            sourceMeta.setLore(Arrays.asList("§c"+ban.getSource()));
            source.setItemMeta(sourceMeta);

            inv.setItem(4,bannedPlayer);
            inv.setItem(11,expirationDate);
            inv.setItem(13,reason);
            inv.setItem(15,source);

            for(int i = 0; i<27;i++){
                if(inv.getItem(i) == null){
                    inv.setItem(i, glassPane);
                }
            }
            p.openInventory(inv);
        }else{
            p.sendMessage("§cSpecified player is not banned.");
        }

    }

}
