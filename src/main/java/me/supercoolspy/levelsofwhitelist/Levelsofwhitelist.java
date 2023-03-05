package me.supercoolspy.levelsofwhitelist;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import  org.bukkit.*;

import java.util.Objects;

public final class Levelsofwhitelist extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("LevelsOfWhitelist.join."+Levelsofwhitelist.getPlugin(Levelsofwhitelist.class).getConfig().getString("WhitelistLevel"));
        this.getCommand("LevelsOfWhitelist").setExecutor(new Commands());
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        System.out.println("Enabling Levels of whitelist");
        if(config.getBoolean("WhitelistEnabled")) {
            getServer().getPluginManager().registerEvents(new WhitelistLevel(), this);
        }
        else{

            config.addDefault("WhitelistEnabled", true);
            config.set("WhitelistEnabled", true);
            config.addDefault("WhitelistLevel", "1");
            config.set("WhitelistLevel", "1");
            config.addDefault("KickMSG", "kicked");
            config.set("KickMSG", "kicked");
            config.addDefault("WhitelistedWelcomeMSG", "Welcome to the server!");
            config.set("WhitelistedWelcomeMSG", "Test");
            config.options().copyDefaults(true);
        }
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(ChatColor.RED + "Bye see you soon");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return  false;
    }
}
