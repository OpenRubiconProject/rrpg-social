package com.openrubicon.social;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.database.interfaces.DatabaseModel;
import com.openrubicon.social.Classes.Social;
import com.openrubicon.social.Database.Models.Friends;
import org.bukkit.plugin.java.JavaPlugin;
import com.openrubicon.core.interfaces.Module;

import javax.lang.model.type.ArrayType;
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
        Models.add(new Friends());
        return Models;
    }
}
