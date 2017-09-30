package com.openrubicon.social.database.models;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.DatabaseModel;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;
import com.openrubicon.social.Enums.RelationState;
import com.openrubicon.social.database.migrations.CreateFriends;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Friend extends DatabaseModel<Friend> {

    private int id;
    private String player1_id;
    private String player2_id;
    private boolean bff;
    private String state;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    private String tableName = "rubicon_friends";
    private int version = 1;

    public Friend() {
    }

    public Friend(OfflinePlayer p1, OfflinePlayer p2, boolean bff, RelationState state){
        this.player1_id = p1.getUniqueId().toString();
        this.player2_id = p2.getUniqueId().toString();
        this.bff = bff;
        this.state = state.toString();
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    public Friend(Connection connection) {
        super(connection);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(String player1_id) {
        this.player1_id = player1_id;
    }

    public String getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(String player2_id) {
        this.player2_id = player2_id;
    }

    public boolean isBff() {
        return bff;
    }

    public void setBff(boolean bff) {
        this.bff = bff;
    }

    public RelationState getState() {
        return RelationState.fromString(state);
    }

    public void setState(RelationState state) {
        this.state = state.toString();
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
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

    public Friend selectRelation(){
        Bukkit.broadcastMessage("id: " + id +
                "player1_id:" + player1_id +
                "player2_id: " + player2_id +
                "bff: " + bff +
                "state: " + state);

        return this.select("*").where("((`player1_id` = :player1_id AND `player2_id` = :player2_id) OR (`player1_id` = :player2_id AND `player2_id` = :player1_id))").executeFetch(Friend.class).get(0);
    }

    public boolean insertInto(){

        Bukkit.broadcastMessage("id: " + id +
                "player1_id:" + player1_id +
                "player2_id: " + player2_id +
                "bff: " + bff +
                "state: " + state);

        if (this.count("id").where("((`player1_id` = :player1_id AND `player2_id` = :player2_id) OR (`player1_id` = :player2_id AND `player2_id` = :player1_id))").executeCount() > 0)
            return false;
        else{
            this.insert("player1_id, player2_id, bff, state, created_at, updated_at",":player1_id, :player2_id, :bff, :state, :created_at, :updated_at").executeInsert();
            return true;
        }
    }

    public boolean updateState(){
        this.updated_at = new Date();
        this.update().set("state", ":state").andSet("updated_at", ":updated_at").where("id = :id").executeUpdate();
        return true;
    }

    public boolean remove(){
        this.deleted_at = new Date();
        this.update().set("deleted_at", ":deleted_at").where("id = :id").executeUpdate();
        return true;
    }


//
//    public boolean setBestFriend(Connection connection)
//    {
//        this.update().set("bff", ":bff").where("deleted_at IS NULL AND ((player1_id = :player1_id AND player2_id = :player2_id) OR (player1_id = :player2_id AND player2_id = :player1_id").executeUpdate();
//        return true;
//    }
//
//    public boolean removeBestFriend(Connection connection)
//    {
//        this.update().set("bff", ":bff").where("deleted_at IS NULL AND ((player1_id = :player1_id AND player2_id = :player2_id) OR (player1_id = :player2_id AND player2_id = :player1_id").executeUpdate();
//        return true;
//    }
//
//    public boolean removeFriend(Connection connection)
//    {
//        this.update().set("deleted_at", ":deleted_at").where()
//        connection.get().createQuery("DELETE FROM `rubicon_players` WHERE `deleted_at` IS NULL AND ((`player1_id` = :player1_id AND `player2_id` = :player2_id) OR (`player1_id` = :player2_id AND `player2_id` = :player1_id))").bind(this).executeUpdate();
//        return true;
//    }
//
//    public Friend getFriend(Connection connection)
//    {
//        return connection.get().createQuery("SELECT * FROM `rubicon_friends` WHERE (`player1_id` = :player1_id OR `player2_id` = :player2_id) AND `deleted_at` IS NULL").bind(this).executeAndFetch(Friend.class).get(0);
//    }
//
//    public static List<Friend> all(Connection connection)
//    {
//        return connection.get().createQuery("SELECT * FROM `rubicon_friends` WHERE `deleted_at` IS NULL").executeAndFetch(Friend.class);
//    }
//
//    public boolean insertFriendRequest(Connection connection){
//        String query = "INSERT INTO `rubicon_players` (`player1_id`, `player2_id`, `bff`, `state`) VALUES (:player1_id, :player2_id, :bff, :state)";
//
//        this.id = (Integer)connection.get().createQuery(query, true).bind(this).executeUpdate().getKey();
//        return true;
//    }
//
//    public boolean save(Connection connection)
//    {
//        connection.get().createQuery("UPDATE `rubicon_players` SET `bff`=:bff, `state`=:state, updated_at=:updated_at, deleted_at=:deleted_at WHERE id=:id").bind(this).executeUpdate();
//        return true;
//    }

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
