package com.openrubicon.social;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.database.interfaces.DatabaseModel;
import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.social.commands.*;
import org.bukkit.plugin.java.JavaPlugin;
import com.openrubicon.core.interfaces.Module;

import com.openrubicon.social.classes.Social;
import com.openrubicon.social.database.models.FriendModel;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class RRPGSocial extends JavaPlugin implements Module {

    public static Social social;

    @Override
    public void onLoad()
    {
        RRPGCore.modules.addModule(this);
    }

    @Override
    public void onEnable()
    {
        social = new Social(this);
    }

    @Override
    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new FriendAdd());
        commands.add(new FriendList());
        commands.add(new FriendRemove());
        commands.add(new FriendRequests());
        commands.add(new FriendSendItem());
        return commands;
    }

    @Override
    public ArrayList<PostDatabaseLoad> getPostDatabaseLoads() {
        ArrayList<PostDatabaseLoad> loads = new ArrayList<>();
        loads.add(social);
        return loads;
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
        models.add(new FriendModel());
        return models;
    }
}
