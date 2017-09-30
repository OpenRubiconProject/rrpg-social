package com.openrubicon.social.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.social.RRPGSocial;
import com.openrubicon.social.classes.SocialProfile;

import java.util.ArrayList;

/**
 * Created by Quinn on 9/30/2017.
 */
public class FriendRequests extends Command {
    @Override
    public String getCommandFormat() {
        return "friend requests";
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


        if(strings.length == 1) {
            SocialProfile s= (SocialProfile) RRPGSocial.social.getHashMap().get(thePlayer.getPlayer());
            s.listRequests();
        } else {
            thePlayer.getPlayer().sendMessage("Incorrect usage for /friend requests");
        }
    }
}
