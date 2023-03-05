package me.supercoolspy.levelsofwhitelist;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
public class WhitelistLevel implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("LevelsOfWhitelist.join."+Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig().getString("WhitelistLevel")) || player.hasPermission("LevelsOfWhitelist.join.bypass"))
            player.sendMessage(ChatColor.AQUA + Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig().getString("WhitelistedWelcomeMSG"));
        else
            player.kickPlayer(ChatColor.RED + Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig().getString("KickMSG"));
    }
}
