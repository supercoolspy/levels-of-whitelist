package me.supercoolspy.levelsofwhitelist;

import lombok.Getter;
import lombok.Setter;

public class WhitelistLevel {
    @Getter private final int level;
    @Getter @Setter private String permIdentifier;
    public WhitelistLevel(int level, String permIdentifier) {
        this.level = level;
        this.permIdentifier = permIdentifier;
    }
}
