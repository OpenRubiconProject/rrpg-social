package com.openrubicon.social;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.database.interfaces.DatabaseModel;
import org.bukkit.plugin.java.JavaPlugin;
import com.openrubicon.core.interfaces.Module;

import com.openrubicon.social.classes.Social;
import com.openrubicon.social.database.models.Friend;
import java.util.ArrayList;

public class RRPGSocial extends JavaPlugin implements Module {

    public static Social social;

    @Override
    public void onLoad()
    {
        RRPGCore.modules.addModule(this);
        social = new Social(this);
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

    public ArrayList<DatabaseModel> getDatabaseModels(){
        ArrayList<DatabaseModel> Models = new ArrayList<DatabaseModel>();
        //Add all database Models to send to the core plugin. This will check if tables have been made.
        Models.add(new Friend());
        return Models;
    }
}
