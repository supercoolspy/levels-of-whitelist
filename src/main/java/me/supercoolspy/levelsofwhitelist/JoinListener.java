package me.supercoolspy.levelsofwhitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class JoinListener implements Listener {
    private final LevelsOfWhitelist plugin = LevelsOfWhitelist.getInstance();
    @EventHandler
    public void join(PlayerJoinEvent event) {
        if (!plugin.isWhitelistEnabled()) return;
        final Player player = event.getPlayer();
        final int[] level = {0};
        player.getEffectivePermissions().forEach(permission -> {
            if (permission.getPermission().startsWith("levelsofwhitelist.level.")) {
                try {
                    int newLevel = Integer.parseInt(permission.getPermission().split("\\.")[2]);
                    if (newLevel > level[0]) {
                        level[0] = newLevel;
                    }
                } catch (NumberFormatException error) {
                    plugin.getLogger().warning("Invalid permission: " + permission.getPermission());
                    return;
                }
                if (level[0] > plugin.getCurrentLevel()) {
                    player.kickPlayer(ChatColor.RED + "You are not whitelisted!");
                }
            }
        });

    }
}
