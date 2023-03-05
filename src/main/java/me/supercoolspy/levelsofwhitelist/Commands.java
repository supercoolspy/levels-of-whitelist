package me.supercoolspy.levelsofwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private final LevelsOfWhitelist plugin = LevelsOfWhitelist.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            return true;
        }
        if (args.length == 0 || args.length > 2) {
            player.sendMessage(ChatColor.RED + "Please specify a subcommand! Valid subcommands are: on, off, reload, setlevel <level>");
            return true;
        }
        switch (args[0]) {
            case "on" -> {
                player.sendMessage(ChatColor.GREEN + "Turned On Whitelist");
                plugin.getConfig().set("WhitelistEnabled", true);
                plugin.saveConfig();
                return true;
            }
            case "off" -> {
                player.sendMessage(ChatColor.RED + "Turned Off Whitelist");
                plugin.getConfig().set("WhitelistEnabled", false);
                plugin.setWhitelistEnabled(false);
                plugin.saveConfig();
                return true;
            }
            case "reload" -> {
                plugin.reloadConfig();
                player.sendMessage(ChatColor.AQUA + "Reloaded Config");
                return true;
            }
            case "setlevel" -> {
                if (args.length != 2) {
                    player.sendMessage(ChatColor.RED + "Please specify a level!");
                    return true;
                }
                try {
                    final int level = Integer.parseInt(args[1]);
                    plugin.getConfig().set("CurrentLevel", level);
                    plugin.saveConfig();
                    player.sendMessage(ChatColor.AQUA + "Set level to " + level);
                    plugin.setCurrentLevel(level);
                    return true;
                } catch (NumberFormatException error) {
                    player.sendMessage(ChatColor.RED + "Please specify a valid level!");
                    return true;
                }
            }
            default -> {
                player.sendMessage(ChatColor.RED + "Please specify a subcommand! Valid subcommands are: on, off, reload, setlevel <level>");
                return true;
            }
        }
    }
}