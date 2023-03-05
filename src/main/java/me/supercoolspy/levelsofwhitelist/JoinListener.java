package me.supercoolspy.levelsofwhitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.Objects;

public class JoinListener implements Listener {
    private final LevelsOfWhitelist plugin = LevelsOfWhitelist.getInstance();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void join(PlayerJoinEvent event) {
        if (!plugin.isWhitelistEnabled()) return;
        System.out.println("Join event");
        final Player player = event.getPlayer();
        final int[] level = {0};
        for (PermissionAttachmentInfo permission : player.getEffectivePermissions()) {
            if (permission.getPermission().startsWith("levelsofwhitelist.level.")) {
                try {
                    int newLevel = Integer.parseInt(permission.getPermission().split("\\.")[2]);
                    if (newLevel > level[0]) {
                        level[0] = newLevel;
                    }
                    if (level[0] >= plugin.getCurrentLevel()) {
                        break;
                    }
                } catch (NumberFormatException error) {
                    plugin.getLogger().warning("Invalid permission: " + permission.getPermission());
                }
            }
        }
        if (level[0] < plugin.getCurrentLevel() && !player.hasPermission("levelsofwhitelist.bypass")) {
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("KickMSG"))));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("WhitelistedWelcomeMSG"))));
        }
    }
}
