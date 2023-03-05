package me.supercoolspy.levelsofwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    private final LevelsOfWhitelist plugin = LevelsOfWhitelist.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args.length > 2) {
            sender.sendMessage(ChatColor.RED + "Please specify a subcommand! Valid subcommands are: on, off, reload, setlevel <level>");
            return true;
        }
        switch (args[0]) {
            case "on" -> {
                sender.sendMessage(ChatColor.GREEN + "Turned On Whitelist");
                plugin.getConfig().set("WhitelistEnabled", true);
                plugin.saveConfig();
                return true;
            }
            case "off" -> {
                sender.sendMessage(ChatColor.RED + "Turned Off Whitelist");
                plugin.getConfig().set("WhitelistEnabled", false);
                plugin.setWhitelistEnabled(false);
                plugin.saveConfig();
                return true;
            }
            case "reload" -> {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.AQUA + "Reloaded Config");
                return true;
            }
            case "setlevel" -> {
                if (args.length != 2) {
                    sender.sendMessage(ChatColor.RED + "Please specify a level!");
                    return true;
                }
                try {
                    final int level = Integer.parseInt(args[1]);
                    plugin.getConfig().set("CurrentLevel", level);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.AQUA + "Set level to " + level);
                    plugin.setCurrentLevel(level);
                    return true;
                } catch (NumberFormatException error) {
                    sender.sendMessage(ChatColor.RED + "Please specify a valid level!");
                    return true;
                }
            }
            default -> {
                sender.sendMessage(ChatColor.RED + "Please specify a subcommand! Valid subcommands are: on, off, reload, setlevel <level>");
                return true;
            }
        }
    }
}