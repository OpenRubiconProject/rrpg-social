package com.openrubicon.social.Database.Models;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.database.interfaces.DatabaseMigration;
import com.openrubicon.core.database.interfaces.DatabaseModel;
import com.openrubicon.social.Classes.SocialProfile;
import org.bukkit.OfflinePlayer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Quinn on 9/23/2017.
 */
public class Friends implements DatabaseModel {

    private int friend_pk;
    private String uuid1;
    private String uuid2;
    private int bff;
    private int status;
    private Date created_on;
    private Date modified_on;
    private Date deleted_on;

    private String tableName = "rubicon_players";
    private int version = 1;

    public long getFriend_pk() {
        return friend_pk;
    }
    public void setFriend_pk(int friend_pk) {
        this.friend_pk = friend_pk;
    }
    public String getUuid1() {
        return uuid1;
    }
    public void setUuid1(String uuid1) {
        this.uuid1 = uuid1;
    }
    public String getUuid2() {
        return uuid2;
    }
    public void setUuid2(String uuid2) {
        this.uuid2 = uuid2;
    }
    public int getBff() {
        return bff;
    }
    public void setBff(int bff) {
        this.bff = bff;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getCreated_on() {
        return created_on;
    }
    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
    public Date getModified_on() {
        return modified_on;
    }
    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }
    public Date getDeleted_on() {
        return deleted_on;
    }
    public void setDeleted_on(Date deleted_on) {
        this.deleted_on = deleted_on;
    }

    public HashMap<Integer, DatabaseMigration> getMigrations(){return new HashMap<Integer, DatabaseMigration>();}
    public String getTableName(){return tableName;}
    public int getVersion(){return version;}

    //SQL Commands
    public Friends(){
        createTables();
    }

    public boolean updateWhereFriendUUID(SocialProfile userProfile, OfflinePlayer friend){
        String query = "";
        if(userProfile.isBestFriends(friend)){
            query = "UPDATE `economics_friends` SET `bff`='1' WHERE (`uuid1` = :uuid1 OR `uuid2` = :uuid2) AND (`uuid1` = :uuid2 OR `uuid2` = :uuid1);";
        } else if(userProfile.isFriends(friend)) {
            query = "UPDATE `economics_friends` SET `bff`='0' WHERE (`uuid1` = :uuid1 OR `uuid2` = :uuid2) AND (`uuid1` = :uuid2 OR `uuid2` = :uuid1);";
        } else {
            //User is not a friend, should be removed.
        }
        RRPGCore.database.connection().get().createQuery(query).bind(this).executeUpdate();

        return true;
    }
    public boolean removeWhereFriendUUID(SocialProfile userProfile, OfflinePlayer friend){
        String uuid = userProfile.getUser().getUniqueId().toString();
        String frienduuid = friend.getUniqueId().toString();
        String query = "DELETE FROM `economics_friends` WHERE (`uuid1` = :uuid1 OR `uuid2` = :uuid2) AND (`uuid1` = :uuid2 OR `uuid2` = :uuid1);";

        RRPGCore.database.connection().get().createQuery(query).bind(this).executeUpdate();
        return true;
    }
    public boolean createTables(){
        String query = "";

        RRPGCore.database.connection().get().createQuery(query).bind(this).executeUpdate();
        return true;

    }
    /*public Friends selectWhereUuid(SocialProfile playerProfile){
        String query = "SELECT * FROM `economics_friends` WHERE `UUID1` = :uuid1 OR `UUID2` = :uuid1;";

        String uuid = playerProfile.getUser().getUniqueId().toString();
        try {
            PreparedStatement statement = Economics.connection.prepareStatement("SELECT * FROM `economics_friends` WHERE `UUID1` = ? OR `UUID2` = ?;");
            statement.setString(1, uuid);
            statement.setString(2, uuid);
            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet selectAll(){
        try {
            return Economics.connection.createStatement().executeQuery("SELECT * FROM `economics_friends`;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    */
    public boolean insertRequestInto(SocialProfile playerProfile, OfflinePlayer friend){
        String query = "INSERT INTO `economics_friends` (`uuid1`, `uuid2`, `bff`, `status`) VALUES (:uuid1, :uuid2, :bff, :status);";

        RRPGCore.database.connection().get().createQuery(query).bind(this).executeUpdate();
        return true;
    }


}
