package com.openrubicon.social.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import com.openrubicon.social.RRPGSocial;
import com.openrubicon.social.classes.SocialProfile;

import java.util.ArrayList;

public class FriendList extends Command {
    @Override
    public String getCommandFormat() {
        return "friend list";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable interactable, ArrayList<DynamicPrimitive> args) {
        if (((Player)interactable).getPlayer() != null)
            return;

        Player thePlayer = (Player) interactable;

        SocialProfile s= (SocialProfile) RRPGSocial.social.getProfile(thePlayer.getPlayer());
        s.listFriends();
    }
}
