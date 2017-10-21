package com.openrubicon.social.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.social.RRPGSocial;
import com.openrubicon.social.classes.SocialProfile;
import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * Created by Quinn on 9/30/2017.
 */
public class FriendSendItem extends Command {
    @Override
    public String getCommandFormat() {
        return "friend item send $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable interactable, String[] strings) {
        if (((Player)interactable).getPlayer() != null)
            return;

        Player thePlayer = (Player) interactable;


        SocialProfile s= (SocialProfile) RRPGSocial.social.getHashMap().get(thePlayer.getPlayer());
        if(strings.length == 1) {
            if(Bukkit.getPlayer(strings[0]).isOnline()) {
                s.sendItem(Bukkit.getPlayer(strings[0]));
                return;
            } else {
                thePlayer.getPlayer().sendMessage("That player is not online.");
            }
        } else {
            thePlayer.getPlayer().sendMessage("Incorrect usage for /senditem [player]");
        }
        return;
    }
}
