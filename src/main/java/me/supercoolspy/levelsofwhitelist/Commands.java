package me.supercoolspy.levelsofwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("on")) {
                    player.sendMessage(ChatColor.GREEN + "Turned On Whitelist");
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig().set("WhitelistEnabled", true);
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).saveConfig();
                return true;
            }
                else if (args[0].equalsIgnoreCase("off")){
                    player.sendMessage(ChatColor.RED + "Turned Off Whitelist");
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig().set("WhitelistEnabled", false);
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).saveConfig();
                    return true;
            }
                else if (args[0].equalsIgnoreCase("reload")){
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).saveDefaultConfig();
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig();
                    Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).reloadConfig();
                    player.sendMessage(ChatColor.AQUA + "Reloaded Config");
                    return true;
                }
        }
        return true;
    }
}