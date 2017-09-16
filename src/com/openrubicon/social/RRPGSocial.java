package com.openrubicon.social;

import org.bukkit.plugin.java.JavaPlugin;
import com.openrubicon.core.iModule;

public class RRPGSocial extends JavaPlugin implements iModule {

    @Override
    public String getKey() {
        return "rrpg-social";
    }

    @Override
    public String getOverview() {
        return "Socialization of RRPG";
    }

    @Override
    public String getConfiguration() {
        return this.getDataFolder().getAbsolutePath();
    }
}
