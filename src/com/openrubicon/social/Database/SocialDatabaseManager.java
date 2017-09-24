package com.openrubicon.social.Database;

import com.openrubicon.core.interfaces.Module;
import com.openrubicon.social.Database.Models.Friends;

/**
 * Created by Quinn on 9/23/2017.
 */
public class SocialDatabaseManager {

    SocialDatabaseManager(){

    }

    public static Friends useFriends(){
        return new Friends();
    }

}
