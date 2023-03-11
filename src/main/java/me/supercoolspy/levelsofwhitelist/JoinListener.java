package me.supercoolspy.levelsofwhitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class JoinListener implements Listener {
    private final LevelsOfWhitelist plugin = LevelsOfWhitelist.getInstance();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void join(PlayerJoinEvent event) {
        if (!plugin.isWhitelistEnabled()) return;
        System.out.println("Join event");
        final Player player = event.getPlayer();
        final AtomicInteger level = new AtomicInteger();
        for (PermissionAttachmentInfo permission : player.getEffectivePermissions()) {
            if (permission.getPermission().startsWith("levelsofwhitelist.level.")) {
                try {
                    int newLevel = Integer.parseInt(permission.getPermission().split("\\.")[2]);
                    if (newLevel > level.get()) {
                        level.set(newLevel);
                    }
                    if (level.get() >= plugin.getCurrentLevel()) {
                        break;
                    }
                } catch (NumberFormatException error) {
                    try {
                        Optional<WhitelistLevel> wlLevel = LevelsOfWhitelist.getInstance().getLevels().stream().filter
                                (whitelistLevel -> whitelistLevel.getPermIdentifier().equals(permission.getPermission().split("\\.")[2])).findFirst();
                        if (wlLevel.isPresent()) {
                            int newLevel = wlLevel.orElseThrow().getLevel();
                            if (newLevel > level.get()) {
                                level.set(newLevel);
                            }
                            if (level.get() >= plugin.getCurrentLevel()) {
                                break;
                            }
                        }
                    } catch (NullPointerException | NoSuchElementException e) {
                        plugin.getLogger().warning("Invalid permission: " + permission.getPermission());
                    }
                }
            }
        }
        if (level.get() < plugin.getCurrentLevel() && !player.hasPermission("levelsofwhitelist.bypass")) {
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("KickMSG"))));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("WhitelistedWelcomeMSG"))));
        }
    }
}
