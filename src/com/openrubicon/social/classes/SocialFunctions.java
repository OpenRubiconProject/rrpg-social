package com.openrubicon.social.classes;

import com.openrubicon.social.RRPGSocial;
import org.bukkit.OfflinePlayer;

/**
 * Created by Quinn on 9/24/2017.
 */
public class SocialFunctions {

    public static String getPlayerName(OfflinePlayer p){
        if (RRPGSocial.social.getHashMap().containsKey(p)){
            return RRPGSocial.social.getHashMap().get(p).getUsername();
        } else {
            return "";
        }
    }
}
