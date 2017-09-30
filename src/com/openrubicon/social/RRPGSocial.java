package com.openrubicon.social;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.database.interfaces.DatabaseModel;
import com.openrubicon.social.commands.FriendAdd;
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
    public ArrayList<Command> getCommands() {
        ArrayList<Command> models = new ArrayList<>();
        models.add(new FriendAdd());
        return models;
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

    @Override
    public ArrayList<DatabaseModel> getDatabaseModels() {
        ArrayList<DatabaseModel> models = new ArrayList<>();
        models.add(new Friend());
        return models;
    }
}
