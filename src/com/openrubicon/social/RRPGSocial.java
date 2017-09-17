package com.openrubicon.social;

import com.openrubicon.core.RRPGCore;
import org.bukkit.plugin.java.JavaPlugin;
import com.openrubicon.core.interfaces.iModule;

public class RRPGSocial extends JavaPlugin implements iModule {

    @Override
    public void onLoad()
    {
        RRPGCore.modules.addModule(this);
    }

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
