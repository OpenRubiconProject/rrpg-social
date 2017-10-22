package com.openrubicon.social.classes;

import com.openrubicon.social.database.models.FriendModel;
import com.openrubicon.social.enums.RelationState;
import com.openrubicon.social.RRPGSocial;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Quinn on 9/24/2017.
 */
public class SocialProfile {

    OfflinePlayer user;
    ArrayList<OfflinePlayer> friends = new ArrayList<OfflinePlayer>();
    OfflinePlayer[] bestFriends = new Player[3];
    ArrayList<OfflinePlayer> requests = new ArrayList<OfflinePlayer>();
    ArrayList<OfflinePlayer> bestRequests = new ArrayList<OfflinePlayer>();

    public SocialProfile(OfflinePlayer p){
        user = p;
    }

    /**
     * Determine if the players are friends.
     * @param p     The player to check if if you are friends with
     * @return      Returns if the two players are friends.
     */
    public boolean isFriends(OfflinePlayer p){
        for(int i=0; i<friends.size(); i++){
            if (friends.get(i).equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the two players are best friends
     * @param p     The player to check if you are best friends
     * @return      Returns if the two players are best friends
     */
    public boolean isBestFriends(OfflinePlayer p){
        for(int i=0; bestFriends[i] != null; i++){
            if (bestFriends[i].equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the player has sent a request to the current profile
     * @param p     The player to check for a request from
     * @return      Returns if the player has sent a request
     */
    public boolean isRequested(OfflinePlayer p){
        for(int i=0; i<requests.size(); i++){
            if (requests.get(i).equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the player has sent a best friend request
     * @param p     The player to check for a request from
     * @return      Returns if the player has sent a best friend request
     */
    public boolean isBestRequested(OfflinePlayer p){
        for(int i=0; i<bestRequests.size(); i++){
            if (bestRequests.get(i).equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * Sends a friend request to the specified player
     * If a request has already been sent, this will fulfill the friend request
     * @param p     The player to send a request to
     */
    public void sendRequest(OfflinePlayer p) {
        if(p != null) {
            if (p.isOnline()) {
                //if p has already sent a request to me,
                if (isRequested(p)) {
                    fulfillRequest(p);  //Add friends to you and your friend's friend list.
                    return;
                } else {
                    //put a request in your friend's account.
                    SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
                    friend.addRequest(user); //Add yourself to friends account
                    getPlayer().sendMessage(p.getName() + " has been sent a friend request.");
                    friend.getPlayer().sendMessage("You have recieved a friend request!");
                }
            } else {
                getPlayer().sendMessage("That player is not online to accept the request.");
                getPlayer().setHealth(0);
            }
        } else {
            getPlayer().sendMessage("That player does not exist");
        }
    }

    /**
     * Sends a best friend request to the specified player.
     * @param p The player to send a best request to
     */
    public void sendBestRequest(OfflinePlayer p) {
        if(p != null) {
            if (p.isOnline()){
                if (!isBestFriends(p)) {
                    //if p has already sent a request to me,
                    if (isBestRequested(p)) {
                        fulfillBestRequest(p);  //Add friends to you and your friend's friend list.
                        return;
                    } else {
                        //put a request in your friend's account.
                        SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
                        friend.addBestRequest(user); //Add yourself to friends account
                        getPlayer().sendMessage(p.getName() + " has been sent a friend request.");
                        friend.getPlayer().sendMessage("You have recieved a Best FriendModel Request.");
                    }
                } else {
                    getPlayer().sendMessage(p.getName() + " is already your best friend.");
                    return;
                }
            } else {
                getPlayer().sendMessage("That player is not online to accept the request.");
                getPlayer().setHealth(0);
            }
        } else {
            getPlayer().sendMessage("That player does not exist");
        }
    }

    /**
     * Removes the specified player from your friends list
     * @param p The player to remove
     */
    public void remove(OfflinePlayer p){
        this.removeBestFriend(p);
        this.removeRequest(p);
        this.removeBestRequest(p);
        this.removeFriend(p);
    }

    /**
     * Shows a list of all pending friend requests.
     */
    public void listRequests(){
        user.getPlayer().sendMessage("Best FriendModel requests:");
        if(bestRequests.size() == 0){
            getPlayer().sendMessage("No friend requests.");
        }
        for(OfflinePlayer r : bestRequests){
            getPlayer().sendMessage("Request from: " + r.getName());
        }
        user.getPlayer().sendMessage("FriendModel requests:");
        if(requests.size() == 0){
            getPlayer().sendMessage("No friend requests.");
        }
        for(OfflinePlayer r : requests){
            getPlayer().sendMessage("Request from: " + r.getName());
        }
    }

    /**
     * Shows a list of all of your friends
     */
    public void listFriends(){
        user.getPlayer().sendMessage("Best FriendModel:");
        for(int i=0; bestFriends[i] != null; i++)
        {
            user.getPlayer().sendMessage(bestFriends[i].getName());
        }

        user.getPlayer().sendMessage("FriendModel:");
        for(int i=0; i<friends.size(); i++){
            user.getPlayer().sendMessage(friends.get(i).getName());
        }
    }

    /**
     * Sends an item to one of your best friends
     * @param p The friend to send an item to.
     */
    public void sendItem(OfflinePlayer p){
        if(isBestFriends(p)){
            ItemStack stack= user.getPlayer().getItemOnCursor().clone();
            if(stack != null) {
                p.getPlayer().getInventory().addItem(stack);
                user.getPlayer().getItemOnCursor().setAmount(0);
            }
        }
    }

    public OfflinePlayer[] getBestFriends(){
        return bestFriends;
    }
    public ArrayList<OfflinePlayer> getFriends(){
        return friends;
    }
    public ArrayList<OfflinePlayer> getRequests(){
        return requests;
    }
    public ArrayList<OfflinePlayer> getBestRequests(){
        return bestRequests;
    }
    public OfflinePlayer getUser(){
        return user;
    }

    /**
     * Adds a friend to your friend list
     * @param p The player to add to your friend list
     */
    public void addFriend(OfflinePlayer p){
        friends.add(p);
    }

    /**
     * Adds a friend to your best friend list.
     * @param p The player to add to your best friend list.
     */
    public void addBestFriend(OfflinePlayer p){
        if(!isBestFriends(p)) {
            boolean added = false;
            for (int i = 0; i < 3; i++) {
                if (bestFriends[i] == null) {
                    bestFriends[i] = p;
                    added = true;
                    break;
                }
            }
            if(added) {
                SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
                friend.addBestFriend(user);
                return;
            } else {
                getPlayer().sendMessage("You have too many best friends!");
                return;
            }
        }
        return;
    }

    /**
     * Adds a friend to your list and updates the database.
     * @param p The friend being added.
     */
    public void addRequest(OfflinePlayer p){
        this.requests.add(p);
        FriendModel friend = new FriendModel(user, p, false, RelationState.PENDING);
        friend.insertInto();
        return;
    }

    /**
     * Adds a friend to your best friend list and updates the database.
     * @param p The friend to add as a best friend.
     */
    public void addBestRequest(OfflinePlayer p){
        this.bestRequests.add(p);
        FriendModel friend = new FriendModel(user, p, true, RelationState.PENDING);
        friend.insertInto();
        return;
    }

    /**
     * Removes a friend request from a player
     * @param p The player to remove the request from.
     */
    private void removeRequest(OfflinePlayer p){
        for(OfflinePlayer request : requests){
            if (request.equals(p)){
                requests.remove(p);
                FriendModel model = this.getRelation(p);
                model.remove();
            }
        }
    }

    /**
     * Removes a best friend request from a player.
     * @param p The player to remove the request from
     */
    private void removeBestRequest(OfflinePlayer p){
        for(OfflinePlayer request : bestRequests){
            if (request.equals(p)){
                bestRequests.remove(p);
                FriendModel model = this.getRelation(p);
                model.remove();
            }
        }
    }

    /**
     * Removes a friend
     * @param p The friend to remove
     */
    private void removeFriend(OfflinePlayer p){
        friends.remove(p);
        SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
        friend.removeFriend(user);
        FriendModel model = this.getRelation(p);
        model.remove();
    }

    /**
     * Removes a best friend
     * @param p The friend to remove
     */
    private void removeBestFriend(OfflinePlayer p){
        if(bestFriends.length == 0){
            getPlayer().sendMessage("You do not have any best friends!");
            return;
        } else {
            for(int i=0; i<3; i++){
                if(bestFriends[i] == p){
                    bestFriends[i] = null;
                    FriendModel model = this.getRelation(p);
                    model.remove();
                    return;
                }
            }
        }

        SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
        if(friend.isBestFriends(user)){
            friend.removeBestFriend(user);
        }
        return;
    }

    /**
     * Completes a friend request if both players have sent a request to each other
     * @param p The opposite friend to fulfill the request with.
     */
    private void fulfillRequest(OfflinePlayer p){
        this.addFriend(p);         //Add friend to your account
        requests.remove(p);     //Remove the friend from your requests list
        SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
        friend.addFriend(user); //Add yourself to friends account

        FriendModel model = this.getRelation(p);
        model.setState(RelationState.CONFIRMED);
        model.updateState();

        getPlayer().sendMessage(p.getName() + " is now your friend!");
        friend.getPlayer().sendMessage(user.getName() + " is now your friend!");

    }

    /**
     * Completes a best friend request if both players have sent a request to each other
     * @param p The friend to fulfill the request with.
     */
    private void fulfillBestRequest(OfflinePlayer p){
        this.addBestFriend(p);         //Add friend to your account
        bestRequests.remove(p);     //Remove the friend from your requests list
        SocialProfile friend = (SocialProfile) RRPGSocial.social.getProfile(p);    //Get friend's account
        friend.addBestFriend(user); //Add yourself to friends account

        FriendModel model = this.getRelation(p);
        model.setState(RelationState.CONFIRMED);
        model.updateState();

        getPlayer().sendMessage(p.getName() + " is now your best friend!");
        friend.getPlayer().sendMessage(user.getName() + " is now your best friend!");

    }
    private Player getPlayer(){
        return user.getPlayer();
    }
    public String getUsername(){
        return user.getName().toString();
    }

    /**
     * Gets the relationship between two players in the database
     * @param p2    The second player in the relationship
     * @return  The database entry defining the relationship between the two players.
     */
    private FriendModel getRelation(OfflinePlayer p2){
        FriendModel model = new FriendModel();
        model.setPlayer2_id(p2.getUniqueId().toString());
        model.setPlayer1_id(user.getUniqueId().toString());
        model = model.selectRelation();
        return model;
    }
}
