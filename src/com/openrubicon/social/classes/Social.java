package com.openrubicon.social.classes;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * Created by Quinn on 9/23/2017.
 */
public class Social {

    private HashMap<OfflinePlayer, SocialProfile> socialHash = new HashMap<OfflinePlayer, SocialProfile>();
    private Plugin plugin = null;

    public Social(Plugin plugin){
        this.plugin = plugin;
        new SocialEventListener();
        loadSocialAccounts();
    }

    public class SocialEventListener implements Listener {

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event){
            OfflinePlayer player = event.getPlayer();
            //When a player joins the server, if they don't have an account, make them an account
            player.getPlayer().sendMessage("Player joined the game!");
            if(!hasAccount(player.getPlayer())){
                player.getPlayer().sendMessage("Creating Social Profile");
                createPlayerAccount(player.getPlayer());
            }
        }

        public SocialEventListener(){
            Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        }
    }

    public HashMap<OfflinePlayer, SocialProfile> getHashMap(){
        return socialHash;
    }
    public boolean hasAccount(OfflinePlayer p){
        return socialHash.containsKey(p) ? true : false;
    }
    public void createPlayerAccount(OfflinePlayer p) {
        socialHash.put(p, new SocialProfile(p));
    }
    public void loadSocialAccounts(){
        /*try {
            ResultSet results = Economics.dbManager.useFriends().selectAll();
            if(results != null) {
                while (results.next()) {
                    //Get data:
                    String uuid1 = results.getString("uuid1");
                    String uuid2 = results.getString("uuid2");
                    Integer bff = results.getInt("bff");
                    Integer status = results.getInt("status");


                    //check if user 1 has been loaded.
                    SocialProfile sp1;
                    SocialProfile sp2;
                    if (!socialHash.containsKey(Bukkit.getOfflinePlayer(uuid1))) {
                        //Create a social profile
                        sp1 = new SocialProfile(uuid1);
                        socialHash.put(Bukkit.getOfflinePlayer(results.getString("uuid1")), sp1);
                    } else {
                        sp1 = socialHash.get(Bukkit.getOfflinePlayer(uuid1));
                    }
                    //check if user 2 has been loaded.
                    if (!socialHash.containsKey(Bukkit.getOfflinePlayer(results.getString("uuid2")))) {
                        sp2 = new SocialProfile(uuid2);
                        socialHash.put(Bukkit.getOfflinePlayer(results.getString("uuid2")), sp2);
                    } else {
                        sp2 = socialHash.get(Bukkit.getOfflinePlayer(uuid2));
                    }

                    //Update the social profiles.
                    if (bff == 1) {
                        if (status == 1) {
                            sp1.addBestFriend(Bukkit.getOfflinePlayer(uuid2));
                            sp2.addBestFriend(Bukkit.getOfflinePlayer(uuid1));
                        } else {
                            sp1.addBestRequest(Bukkit.getOfflinePlayer(uuid2));
                            sp2.addBestRequest(Bukkit.getOfflinePlayer(uuid1));
                        }
                    } else {
                        if (status == 1) {
                            sp1.addFriend(Bukkit.getOfflinePlayer(uuid2));
                            sp2.addFriend(Bukkit.getOfflinePlayer(uuid1));
                        } else {
                            sp1.addRequest(Bukkit.getOfflinePlayer(uuid2));
                            sp2.addRequest(Bukkit.getOfflinePlayer(uuid1));
                        }
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }*/
    }


}
