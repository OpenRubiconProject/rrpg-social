package com.openrubicon.social.classes;

import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.social.enums.RelationState;
import com.openrubicon.social.database.models.Friend;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class Social implements PostDatabaseLoad {

    private HashMap<OfflinePlayer, SocialProfile> socialHash = new HashMap<OfflinePlayer, SocialProfile>();
    private Plugin plugin = null;

    public Social(Plugin plugin){
        this.plugin = plugin;
        new SocialEventListener();
    }

    @Override
    public void run() {
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
        Friend data = new Friend();
        data.selectAll();
        Bukkit.broadcastMessage(data.getSql());
        List<Friend> resultSet = data.executeFetch(Friend.class);

        for(Friend f: resultSet){
            //Consider p1's uuid
            OfflinePlayer p1 = Bukkit.getPlayer(f.getPlayer1_id());
            OfflinePlayer p2 = Bukkit.getPlayer(f.getPlayer1_id());

            SocialProfile s = null;

            if(socialHash.get(p1) != null){
                s = socialHash.get(p1);
            } else {
                socialHash.put(p1, new SocialProfile(p1));
                s = socialHash.get(p1);
            }
            loadSocialHelper(s, f, p2);

            if(socialHash.get(p2) != null){
                s = socialHash.get(p2);
            } else {
                socialHash.put(p2, new SocialProfile(p2));
                s = socialHash.get(p2);
            }
            loadSocialHelper(s, f, p1);
        }
    }

    private void loadSocialHelper(SocialProfile s, Friend f, OfflinePlayer p2){
        if(f.getState() == RelationState.CONFIRMED) {
            if (f.isBff()) {
                s.addBestFriend(p2);
            } else {
                s.addFriend(p2);
            }
        } else if(f.getState() == RelationState.PENDING){
            if(f.isBff()){
                s.addBestRequest(p2);
            } else {
                s.addRequest(p2);
            }
        }
    }


}
