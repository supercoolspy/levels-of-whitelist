package me.supercoolspy.levelsofwhitelist;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.ArrayList;

public final class LevelsOfWhitelist extends JavaPlugin {
    @Getter @Setter
    private boolean whitelistEnabled = false;
    @Getter @Setter
    private int currentLevel = 1;
    @Getter private static LevelsOfWhitelist instance;
    @Getter private final ArrayList<WhitelistLevel> levels = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.getCommand("LevelsOfWhitelist").setExecutor(new Commands());
        try {
            getConfig().load("config.yml");
        } catch (IOException | InvalidConfigurationException e) {
            getConfig().options().copyDefaults(true);
        }
        getAndSetWhitelistValues();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    private void getAndSetWhitelistValues() {
        this.whitelistEnabled = getConfig().getBoolean("WhitelistEnabled");
        this.currentLevel = getConfig().getInt("CurrentLevel");
        getConfig().getIntegerList("Levels").forEach(level -> {
            final WhitelistLevel oldWhitelistLevel = this.levels.stream().filter(oldLevel -> oldLevel.getLevel() == level).findFirst().orElse(null);
            if (oldWhitelistLevel != null) {
                oldWhitelistLevel.setPermIdentifier(getConfig().getString("Levels." + level + ".name"));
            } else {
                this.levels.add(new WhitelistLevel(level, getConfig().getString("Levels." + level + ".name")));
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(ChatColor.RED + "Bye see you soon");
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        getAndSetWhitelistValues();
    }
}
