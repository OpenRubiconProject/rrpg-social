package com.openrubicon.social.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.social.RRPGSocial;
import com.openrubicon.social.classes.SocialProfile;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendAdd extends Command {
    @Override
    public String getCommandFormat() {
        return "friend add $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable interactable, String[] strings) {
        if(!(interactable instanceof Player))
            return;

        Player thePlayer = (Player) interactable;
        //Check friend list here.
        if(RRPGSocial.social == null){
            Bukkit.broadcastMessage("ITS NULL");
        }


        SocialProfile s= (SocialProfile) RRPGSocial.social.getHashMap().get((OfflinePlayer)thePlayer.getPlayer());
        if(strings.length == 1){
            s.sendRequest(Bukkit.getPlayer(strings[0]));
        } else if (strings.length == 2) {
            if(strings[0].equals("best")) {
                s.sendBestRequest(Bukkit.getPlayer(strings[1]));
            } else {
                thePlayer.getPlayer().sendMessage("Incorrect usage for /friend add");
            }
        }
    }
}
