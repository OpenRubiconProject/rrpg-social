package com.openrubicon.social.database.models;

import com.openrubicon.core.database.Connection;
import com.openrubicon.core.database.interfaces.DatabaseMigration;
import com.openrubicon.core.database.interfaces.DatabaseModel;
import com.openrubicon.social.database.migrations.CreateFriends;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Friend implements DatabaseModel {

    private int id;
    private int player1_id;
    private int player2_id;
    private boolean bff;
    private int state;
    private Date created_at;
    private Date modified_at;
    private Date deleted_at;

    private String tableName = "rubicon_players";
    private int version = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(int player1_id) {
        this.player1_id = player1_id;
    }

    public int getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(int player2_id) {
        this.player2_id = player2_id;
    }

    public boolean isBff() {
        return bff;
    }

    public void setBff(boolean bff) {
        this.bff = bff;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Friend() {
    }

    public boolean setBestFriend(Connection connection)
    {
        connection.get().createQuery("UPDATE `rubicon_players` SET `bff`='1' WHERE `deleted_at` IS NULL AND ((`player1_id` = :player1_id AND `player2_id` = :player2_id) OR (`player1_id` = :player2_id AND `player2_id` = :player1_id))").bind(this).executeUpdate();
        return true;
    }

    public boolean removeBestFriend(Connection connection)
    {
        connection.get().createQuery("UPDATE `rubicon_players` SET `bff`='0' WHERE `deleted_at` IS NULL AND ((`player1_id` = :player1_id AND `player2_id` = :player2_id) OR (`player1_id` = :player2_id AND `player2_id` = :player1_id))").bind(this).executeUpdate();
        return true;
    }

    public boolean removeFriend(Connection connection)
    {
        connection.get().createQuery("DELETE FROM `rubicon_players` WHERE `deleted_at` IS NULL AND ((`player1_id` = :player1_id AND `player2_id` = :player2_id) OR (`player1_id` = :player2_id AND `player2_id` = :player1_id))").bind(this).executeUpdate();
        return true;
    }

    public Friend getFriend(Connection connection)
    {
        return connection.get().createQuery("SELECT * FROM `rubicon_friends` WHERE (`player1_id` = :player1_id OR `player2_id` = :player2_id) AND `deleted_at` IS NULL").bind(this).executeAndFetch(Friend.class).get(0);
    }

    public static List<Friend> all(Connection connection)
    {
        return connection.get().createQuery("SELECT * FROM `rubicon_friends` WHERE `deleted_at` IS NULL").executeAndFetch(Friend.class);
    }

    public boolean insertFriendRequest(Connection connection){
        String query = "INSERT INTO `rubicon_players` (`player1_id`, `player2_id`, `bff`, `status`) VALUES (:player1_id, :player2_id, :bff, :status)";

        connection.get().createQuery(query).bind(this).executeUpdate();
        return true;
    }

    public HashMap<Integer, DatabaseMigration> getMigrations()
    {
        HashMap<Integer, DatabaseMigration> migrations = new HashMap<>();
        migrations.put(1, new CreateFriends());
        return migrations;
    }
    public String getTableName()
    {
        return tableName;
    }
    public int getVersion()
    {
        return version;
    }


}
